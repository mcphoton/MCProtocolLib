package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerEntityPositionPacket implements Packet {
    private int entityId;
    private Vec3d relativeMove;
    private boolean onGround;

    private ServerEntityPositionPacket() {}

    public ServerEntityPositionPacket(int entityId, Vec3d relativeMove, boolean onGround) {
        this.entityId = entityId;
        this.relativeMove = relativeMove;
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double moveX = in.readShort() / 4096.0;
        double moveY = in.readShort() / 4096.0;
        double moveZ = in.readShort() / 4096.0;
        relativeMove = new Vec3d(moveX, moveY, moveZ);
        onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeShort((int)(relativeMove.x() * 4096.0));
        out.writeShort((int)(relativeMove.y() * 4096.0));
        out.writeShort((int)(relativeMove.z() * 4096.0));
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