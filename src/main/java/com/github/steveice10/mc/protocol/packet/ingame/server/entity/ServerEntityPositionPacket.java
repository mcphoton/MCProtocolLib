package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerEntityPositionPacket implements Packet {
    private int entityId;
    private Vector relativeMove;
    private boolean onGround;

    private ServerEntityPositionPacket() {}

    public ServerEntityPositionPacket(int entityId, Vector relativeMove, boolean onGround) {
        this.entityId = entityId;
        this.relativeMove = relativeMove.clone();
        this.onGround = onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double moveX = in.readShort() / 4096.0;
        double moveY = in.readShort() / 4096.0;
        double moveZ = in.readShort() / 4096.0;
        relativeMove = new Vector(moveX, moveY, moveZ);
        onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeShort((int)(relativeMove.getX() * 4096.0));
        out.writeShort((int)(relativeMove.getY() * 4096.0));
        out.writeShort((int)(relativeMove.getZ() * 4096.0));
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