package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.type.GlobalEntityType;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSpawnGlobalEntityPacket implements Packet {
    private int entityId;
    private GlobalEntityType type;
    private Vec3d position;

    @SuppressWarnings("unused")
    private ServerSpawnGlobalEntityPacket() {}

    public ServerSpawnGlobalEntityPacket(int entityId, GlobalEntityType type, Vec3d position) {
        this.entityId = entityId;
        this.type = type;
        this.position = position;
    }

    public int getEntityId() {
        return entityId;
    }

    public GlobalEntityType getType() {
        return type;
    }

    public Vec3d getPosition() {
        return position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        type = MagicValues.key(GlobalEntityType.class, in.readByte());
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vec3d(x, y, z);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeByte(MagicValues.value(Integer.class, type));
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.z());
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