package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerEntityPacket implements Packet {
    private int entityId;

    private ServerEntityPacket() {}

    public ServerEntityPacket(int entityId) {
        this.entityId = entityId;
    }

    public int getEntityId() {
        return this.entityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
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