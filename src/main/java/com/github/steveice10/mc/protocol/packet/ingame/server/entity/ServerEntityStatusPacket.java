package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.EntityStatus;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityStatusPacket implements Packet {

    protected int entityId;
    protected EntityStatus status;

    @SuppressWarnings("unused")
    private ServerEntityStatusPacket() {
    }

    public ServerEntityStatusPacket(int entityId, EntityStatus status) {
        this.entityId = entityId;
        this.status = status;
    }

    public int getEntityId() {
        return entityId;
    }

    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readInt();
        status = MagicValues.key(EntityStatus.class, in.readByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(entityId);
        out.writeByte(MagicValues.value(Integer.class, status));
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
