package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityAttachPacket implements Packet {

    private int entityId;
    private int attachedToId;

    @SuppressWarnings("unused")
    private ServerEntityAttachPacket() {
    }

    public ServerEntityAttachPacket(int entityId, int attachedToId) {
        this.entityId = entityId;
        this.attachedToId = attachedToId;
    }

    public int getEntityId() {
        return entityId;
    }

    public int getAttachedToId() {
        return attachedToId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readInt();
        attachedToId = in.readInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(entityId);
        out.writeInt(attachedToId);
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
