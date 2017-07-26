package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityVelocityPacket implements Packet {
    private int entityId;
    /** Velocity in m/s */
    private Vec3d velocity;

    @SuppressWarnings("unused")
    private ServerEntityVelocityPacket() {}

    public ServerEntityVelocityPacket(int entityId, Vec3d velocity) {
        this.entityId = entityId;
        this.velocity = velocity;
    }

    public int getEntityId() {
        return entityId;
    }

    public Vec3d getVelocity() {
        return velocity;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double vx = in.readShort() / 400d;
        double vy = in.readShort() / 400d;
        double vz = in.readShort() / 400d;
        velocity = new Vec3d(vx, vy, vz);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeShort((int)(velocity.x() * 400d));
        out.writeShort((int)(velocity.y() * 400d));
        out.writeShort((int)(velocity.z() * 400d));
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