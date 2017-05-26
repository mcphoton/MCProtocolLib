package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerEntityVelocityPacket implements Packet {
    private int entityId;
    /** Velocity in m/s */
    private Vector velocity;

    @SuppressWarnings("unused")
    private ServerEntityVelocityPacket() {}

    public ServerEntityVelocityPacket(int entityId, Vector velocity) {
        this.entityId = entityId;
        this.velocity = velocity.clone();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double vx = in.readShort() / 400d;
        double vy = in.readShort() / 400d;
        double vz = in.readShort() / 400d;
        velocity = new Vector(vx, vy, vz);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeShort((int)(velocity.getX() * 400d));
        out.writeShort((int)(velocity.getY() * 400d));
        out.writeShort((int)(velocity.getZ() * 400d));
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