package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockChangeRecord;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public final class ServerMultiBlockChangePacket implements Packet {
    private BlockChangeRecord records[];

    @SuppressWarnings("unused")
    private ServerMultiBlockChangePacket() {}

    public ServerMultiBlockChangePacket(BlockChangeRecord... records) {
        if (records == null || records.length == 0) {
            throw new IllegalArgumentException("Records must contain at least 1 value.");
        }
        this.records = records;
    }

    public BlockChangeRecord[] getRecords() {
        return records;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int chunkX = in.readInt();
        int chunkZ = in.readInt();
        records = new BlockChangeRecord[in.readVarInt()];
        for (int index = 0; index < records.length; index++) {
            short pos = in.readShort();
            int blockId = in.readVarInt();
            int x = (chunkX << 4) + (pos >> 12 & 15);
            int y = pos & 255;
            int z = (chunkZ << 4) + (pos >> 8 & 15);
            records[index] = new BlockChangeRecord(new IntPosition(x, y, z), blockId);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        int chunkX = records[0].getPosition().getX() >> 4;
        int chunkZ = records[0].getPosition().getZ() >> 4;
        out.writeInt(chunkX);
        out.writeInt(chunkZ);
        out.writeVarInt(records.length);
        for (BlockChangeRecord record : records) {
            out.writeShort((record.getPosition().getX() - (chunkX << 4)) << 12
                           | (record.getPosition().getZ() - (chunkZ << 4)) << 8
                           | record.getPosition().getY());
            out.writeVarInt(record.getBlockId());
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