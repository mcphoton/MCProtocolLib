package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.world.block.UpdatedTileType;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerUpdateTileEntityPacket implements Packet {

    private IntPosition position;
    private UpdatedTileType type;
    private CompoundTag nbt;

    @SuppressWarnings("unused")
    private ServerUpdateTileEntityPacket() {
    }

    public ServerUpdateTileEntityPacket(IntPosition position, UpdatedTileType type, CompoundTag nbt) {
        this.position = position;
        this.type = type;
        this.nbt = nbt;
    }

    public IntPosition getPosition() {
        return position;
    }

    public UpdatedTileType getType() {
        return type;
    }

    public CompoundTag getNBT() {
        return nbt;
    }

    @Override
    public void read(NetInput in) throws IOException {
        position = NetUtil.readPosition(in);
        type = MagicValues.key(UpdatedTileType.class, in.readUnsignedByte());
        nbt = NetUtil.readNBT(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, position);
        out.writeByte(MagicValues.value(Integer.class, type));
        NetUtil.writeNBT(out, nbt);
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
