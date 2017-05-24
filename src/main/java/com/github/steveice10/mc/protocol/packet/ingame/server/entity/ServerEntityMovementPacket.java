package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

/**
 * This packet corresponds to 0x28 "Entity" and indicates that the entity hasn't moved.
 * The subpackets correspond to 0x25,0x26,0x27 and indicate that the entity has moved, rotated,
 * or both.
 */
public class ServerEntityMovementPacket implements Packet {
    protected int entityId;
    /** Delta position */
    protected double moveX, moveY, moveZ;
    /** New angle, in radians */
    protected float yaw, pitch;
    private boolean onGround;

    protected boolean pos = false;
    protected boolean rot = false;

    protected ServerEntityMovementPacket() {}

    public ServerEntityMovementPacket(int entityId, boolean onGround) {
        this.entityId = entityId;
        this.onGround = onGround;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public double getMovementX() {
        return this.moveX;
    }

    public double getMovementY() {
        return this.moveY;
    }

    public double getMovementZ() {
        return this.moveZ;
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
        if (this.pos) {
            this.moveX = in.readShort() / 4096D;
            this.moveY = in.readShort() / 4096D;
            this.moveZ = in.readShort() / 4096D;
        }

        if (this.rot) {
            this.yaw = in.readByte() * F_2PI / 256f;
            this.pitch = in.readByte() * F_2PI / 256f;
        }

        if (this.pos || this.rot) {
            this.onGround = in.readBoolean();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        if (this.pos) {
            out.writeShort((int)(this.moveX * 4096));
            out.writeShort((int)(this.moveY * 4096));
            out.writeShort((int)(this.moveZ * 4096));
        }

        if (this.rot) {
            out.writeByte((byte)(this.yaw * 256 / F_2PI));
            out.writeByte((byte)(this.pitch * 256 / F_2PI));
        }

        if (this.pos || this.rot) {
            out.writeBoolean(this.onGround);
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