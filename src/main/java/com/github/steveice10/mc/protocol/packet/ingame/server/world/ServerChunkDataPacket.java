package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.chunk.Column;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.io.stream.StreamNetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServerChunkDataPacket implements Packet {
    private Column column;

    @SuppressWarnings("unused")
    private ServerChunkDataPacket() {
    }

    public ServerChunkDataPacket(Column column) {
        this.column = column;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int x = in.readInt();
        int z = in.readInt();
        boolean fullChunk = in.readBoolean();
        int chunkMask = in.readVarInt();
        byte data[] = in.readBytes(in.readVarInt());
        CompoundTag[] tileEntities = new CompoundTag[in.readVarInt()];
        for(int i = 0; i < tileEntities.length; i++) {
            tileEntities[i] = NetUtil.readNBT(in);
        }

        column = NetUtil.readColumn(data, x, z, fullChunk, false, chunkMask, tileEntities);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        NetOutput netOut = new StreamNetOutput(byteOut);
        int mask = NetUtil.writeColumn(netOut, column, column.hasBiomeData(), column.hasSkylight());

        out.writeInt(column.getX());
        out.writeInt(column.getZ());
        out.writeBoolean(column.hasBiomeData());
        out.writeVarInt(mask);
        out.writeVarInt(byteOut.size());
        out.writeBytes(byteOut.toByteArray(), byteOut.size());
        out.writeVarInt(column.getTileEntities().length);
        for(CompoundTag tag : column.getTileEntities()) {
            NetUtil.writeNBT(out, tag);
        }
    }

    @Override
    public boolean isPriority() {
        return false;
    }

    @Override
    public String toString() {
        return ReflectionToString.toString(this);
    }
}
