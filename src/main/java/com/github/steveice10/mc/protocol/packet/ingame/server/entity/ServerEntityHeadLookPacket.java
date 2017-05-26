package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerEntityHeadLookPacket implements Packet {
    private int entityId;
    /** Angle in radians */
    private float headYaw;

    @SuppressWarnings("unused")
    private ServerEntityHeadLookPacket() {}

    public ServerEntityHeadLookPacket(int entityId, float headYaw) {
        this.entityId = entityId;
        this.headYaw = headYaw;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public float getHeadYaw() {
        return this.headYaw;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.headYaw = in.readByte() * F_2PI / 256f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeByte((byte)(this.headYaw * 256f / F_2PI));
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