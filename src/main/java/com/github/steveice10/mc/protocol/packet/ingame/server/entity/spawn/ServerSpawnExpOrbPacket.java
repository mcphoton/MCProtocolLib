package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerSpawnExpOrbPacket implements Packet {
    private int entityId;
    private Vec3d position;
    private int exp;

    @SuppressWarnings("unused")
    private ServerSpawnExpOrbPacket() {}

    public ServerSpawnExpOrbPacket(int entityId, Vec3d position, int exp) {
        this.entityId = entityId;
        this.position = position;
        this.exp = exp;
    }

    public int getEntityId() {
        return entityId;
    }

    public Vec3d getPosition() {
        return position;
    }

    public int getExp() {
        return exp;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vec3d(x, y, z);
        exp = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.z());
        out.writeShort(exp);
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