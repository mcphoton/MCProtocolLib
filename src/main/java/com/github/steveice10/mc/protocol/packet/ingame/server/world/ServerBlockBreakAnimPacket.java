package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.entity.player.BlockBreakStage;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerBlockBreakAnimPacket implements Packet {

    private int breakerEntityId;
    private IntPosition position;
    private BlockBreakStage stage;

    @SuppressWarnings("unused")
    private ServerBlockBreakAnimPacket() {
    }

    public ServerBlockBreakAnimPacket(int breakerEntityId, IntPosition position, BlockBreakStage stage) {
        this.breakerEntityId = breakerEntityId;
        this.position = position;
        this.stage = stage;
    }

    public int getBreakerEntityId() {
        return breakerEntityId;
    }

    public IntPosition getPosition() {
        return position;
    }

    public BlockBreakStage getStage() {
        return stage;
    }

    @Override
    public void read(NetInput in) throws IOException {
        breakerEntityId = in.readVarInt();
        position = NetUtil.readPosition(in);
        try {
            stage = MagicValues.key(BlockBreakStage.class, in.readUnsignedByte());
        } catch(IllegalArgumentException e) {
            stage = BlockBreakStage.RESET;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(breakerEntityId);
        NetUtil.writePosition(out, position);
        out.writeByte(MagicValues.value(Integer.class, stage));
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
