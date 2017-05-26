package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import org.mcphoton.utils.Vector;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerEntityPositionRotationPacket implements Packet {
    private int entityId;
    private Vector relativeMove;
    /** New angle, in radians */
    private float yaw, pitch;
    private boolean onGround;

    private ServerEntityPositionRotationPacket() {}

    public ServerEntityPositionRotationPacket(int entityId, Vector relativeMove, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.relativeMove = relativeMove.clone();
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double moveX = in.readShort() / 4096.0;
        double moveY = in.readShort() / 4096.0;
        double moveZ = in.readShort() / 4096.0;
        relativeMove = new Vector(moveX, moveY, moveZ);
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeShort((int)(relativeMove.getX() * 4096.0));
        out.writeShort((int)(relativeMove.getY() * 4096.0));
        out.writeShort((int)(relativeMove.getZ() * 4096.0));
        out.writeByte((byte)(this.yaw * 256 / F_2PI));
        out.writeByte((byte)(this.pitch * 256 / F_2PI));
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