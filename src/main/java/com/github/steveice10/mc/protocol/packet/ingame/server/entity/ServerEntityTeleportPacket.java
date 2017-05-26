package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import org.mcphoton.utils.Vector;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerEntityTeleportPacket implements Packet {
    private int entityId;
    private Vector position;
    /** Angles in radians */
    private float yaw, pitch;
    private boolean onGround;

    @SuppressWarnings("unused")
    private ServerEntityTeleportPacket() {}

    public ServerEntityTeleportPacket(int entityId, Vector newPosition, float yaw, float pitch,
                                      boolean onGround) {
        this.entityId = entityId;
        this.position = newPosition.clone();
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Vector getPosition() {
        return position;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
        this.yaw = in.readByte() * F_2PI / 256f;
        this.pitch = in.readByte() * F_2PI / 256f;
        this.onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
        out.writeByte((byte)(this.yaw * 256 / F_2PI));
        out.writeByte((byte)(this.pitch * 256 / F_2PI));
        out.writeBoolean(this.onGround);
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