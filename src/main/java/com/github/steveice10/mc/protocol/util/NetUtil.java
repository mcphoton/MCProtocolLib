package com.github.steveice10.mc.protocol.util;

import com.github.steveice10.mc.protocol.data.game.chunk.BasicChunkData;
import com.github.steveice10.mc.protocol.data.game.chunk.BasicChunkSection;
import com.github.steveice10.mc.protocol.data.game.chunk.BlockStorage;
import com.github.steveice10.mc.protocol.data.game.chunk.ChunkSectionData;
import com.github.steveice10.mc.protocol.data.game.chunk.ChunkColumnData;
import com.github.steveice10.mc.protocol.data.game.chunk.NibbleArray3d;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataEntry;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataType;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataValue;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.Rotation;
import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.io.stream.StreamNetInput;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class NetUtil {
    public static final float F_2PI = (float)(2.0 * Math.PI);
    private static final int POSITION_X_SIZE = 38;
    private static final int POSITION_Y_SIZE = 26;
    private static final int POSITION_Z_SIZE = 38;

    private static final int POSITION_Y_SHIFT = 0xFFF;
    private static final int POSITION_WRITE_SHIFT = 0x3FFFFFF;

    public static CompoundTag readNBT(NetInput in) throws IOException {
        byte b = in.readByte();
        if (b == 0) {
            return null;
        } else {
            return (CompoundTag)NBTIO.readTag(new NetInputStream(in, b));
        }
    }

    public static void writeNBT(NetOutput out, CompoundTag tag) throws IOException {
        if (tag == null) {
            out.writeByte(0);
        } else {
            NBTIO.writeTag(new NetOutputStream(out), tag);
        }
    }

    public static ItemStack readItem(NetInput in) throws IOException {
        short item = in.readShort();
        if (item < 0) {
            return null;
        } else {
            return new ItemStack(item, in.readByte(), in.readShort(), readNBT(in));
        }
    }

    public static void writeItem(NetOutput out, ItemStack item) throws IOException {
        if (item == null) {
            out.writeShort(-1);
        } else {
            out.writeShort(item.getId());
            out.writeByte(item.getAmount());
            out.writeShort(item.getData());
            writeNBT(out, item.getNBT());
        }
    }

    public static IntPosition readPosition(NetInput in) throws IOException {
        long val = in.readLong();

        int x = (int)(val >> POSITION_X_SIZE);
        int y = (int)((val >> POSITION_Y_SIZE) & POSITION_Y_SHIFT);
        int z = (int)((val << POSITION_Z_SIZE) >> POSITION_Z_SIZE);

        return new IntPosition(x, y, z);
    }

    public static void writePosition(NetOutput out, IntPosition pos) throws IOException {
        long x = pos.getX() & POSITION_WRITE_SHIFT;
        long y = pos.getY() & POSITION_Y_SHIFT;
        long z = pos.getZ() & POSITION_WRITE_SHIFT;

        out.writeLong(x << POSITION_X_SIZE | y << POSITION_Y_SIZE | z);
    }

    public static Rotation readRotation(NetInput in) throws IOException {
        return new Rotation(in.readFloat(), in.readFloat(), in.readFloat());
    }

    public static void writeRotation(NetOutput out, Rotation rot) throws IOException {
        out.writeFloat(rot.getPitch());
        out.writeFloat(rot.getYaw());
        out.writeFloat(rot.getRoll());
    }

    public static MetadataEntry readEntityMetadataEntry(NetInput in) throws IOException {
        int index = in.readUnsignedByte();
        int typeId = in.readVarInt();
        MetadataType type = MetadataType.getType(typeId);
        Object value = type.read(in);
        return new MetadataEntry(index, type, value);
    }

    public static List<MetadataEntry> readEntityMetadata(NetInput in) throws IOException {
        List<MetadataEntry> entries = new ArrayList<>();
        int index;
        while ((index = in.readUnsignedByte()) != 255) {
            int typeId = in.readVarInt();
            MetadataType type = MetadataType.getType(typeId);
            Object value = type.read(in);
            MetadataEntry entry = new MetadataEntry(index, type, value);
            entries.add(entry);
        }
        return entries;
    }

    public static void writeEntityMetadataEntry(NetOutput out, MetadataEntry entry)
            throws IOException {
        writeEntityMetadataEntry(out, entry.getIndex(), entry);
    }

    public static void writeEntityMetadataEntry(NetOutput out, int index, MetadataValue value)
            throws IOException {
        out.writeByte(index);
        out.writeVarInt(value.getType().getId());
        value.getType().write(value.getValue(), out);
    }

    public static void writeEntityMetadata(NetOutput out, List<MetadataEntry> metadataEntries)
            throws IOException {
        for (MetadataEntry entry : metadataEntries) {
            writeEntityMetadataEntry(out, entry);
        }
        out.writeByte(255);
    }

    public static ChunkColumnData readColumn(byte data[], int x, int z, boolean fullChunk,
											 boolean hasSkylight, int mask, CompoundTag[] tileEntities)
            throws IOException {
        NetInput in = new StreamNetInput(new ByteArrayInputStream(data));
        Exception ex = null;
        ChunkColumnData column = null;
        try {
            BasicChunkSection[] chunks = new BasicChunkSection[16];
            for (int index = 0; index < chunks.length; index++) {
                if ((mask & (1 << index)) != 0) {
                    BlockStorage blocks = new BlockStorage(in);
                    NibbleArray3d blocklight = new NibbleArray3d(in, 4096);
                    NibbleArray3d skylight = hasSkylight ? new NibbleArray3d(in, 4096) : null;
                    chunks[index] = new BasicChunkSection(blocks, blocklight, skylight);
                }
            }

            byte biomeData[] = null;
            if (fullChunk) {
                biomeData = in.readBytes(256);
            }

            column = new BasicChunkData(x, z, chunks, biomeData, tileEntities, hasSkylight);
        } catch (Exception e) {
            ex = e;
        }

        // Unfortunately, this is needed to detect whether the chunks contain skylight or not.
        if ((in.available() > 0 || ex != null) && !hasSkylight) {
            return readColumn(data, x, z, fullChunk, true, mask, tileEntities);
        } else if (ex != null) {
            throw new IOException("Failed to read chunk data.", ex);
        }

        return column;
    }

    public static int writeColumn(NetOutput out, ChunkColumnData column, boolean fullChunk,
								  boolean hasSkylight) throws IOException {
        int mask = 0;
        ChunkSectionData chunks[] = column.getSections();
        for (int index = 0; index < chunks.length; index++) {
            ChunkSectionData chunk = chunks[index];
            if (chunk != null) {
                mask |= (1 << index);
                chunk.getBlocks().write(out);
                chunk.getBlockLight().write(out);
                if (hasSkylight) {
                    chunk.getSkyLight().write(out);
                }
            }
        }

        if (fullChunk) {
            out.writeBytes(column.getBiomeData());
        }

        return mask;
    }

    private static class NetInputStream extends InputStream {
        private NetInput in;
        private boolean readFirst;
        private byte firstByte;

        public NetInputStream(NetInput in, byte firstByte) {
            this.in = in;
            this.firstByte = firstByte;
        }

        @Override
        public int read() throws IOException {
            if (!readFirst) {
                readFirst = true;
                return firstByte;
            } else {
                return in.readUnsignedByte();
            }
        }
    }

    private static class NetOutputStream extends OutputStream {
        private NetOutput out;

        public NetOutputStream(NetOutput out) {
            this.out = out;
        }

        @Override
        public void write(int b) throws IOException {
            out.writeByte(b);
        }
    }
}