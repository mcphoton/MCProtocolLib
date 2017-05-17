package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockChangeRecord;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerBlockChangePacket implements Packet {
    private BlockChangeRecord record;

    @SuppressWarnings("unused")
    private ServerBlockChangePacket() {}

    public ServerBlockChangePacket(BlockChangeRecord record) {
        this.record = record;
    }

    public BlockChangeRecord getRecord() {
        return record;
    }

    @Override
    public void read(NetInput in) throws IOException {
        IntPosition position = NetUtil.readPosition(in);
        int blockId = in.readVarInt();
        record = new BlockChangeRecord(position, blockId);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, record.getPosition());
        out.writeVarInt(record.getBlockId());
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