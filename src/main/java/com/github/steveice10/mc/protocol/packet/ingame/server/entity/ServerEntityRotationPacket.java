package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerEntityRotationPacket implements Packet {
    private int entityId;
    /** New angle, in radians */
    private float yaw, pitch;
    private boolean onGround;

    private ServerEntityRotationPacket() {}

    public ServerEntityRotationPacket(int entityId, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeByte((byte)(yaw * 256 / F_2PI));
        out.writeByte((byte)(pitch * 256 / F_2PI));
        out.writeBoolean(onGround);
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