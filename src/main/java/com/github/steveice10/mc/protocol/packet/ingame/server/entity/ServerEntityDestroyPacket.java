package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityDestroyPacket implements Packet {

    private int entityIds[];

    @SuppressWarnings("unused")
    private ServerEntityDestroyPacket() {
    }

    public ServerEntityDestroyPacket(int... entityIds) {
        this.entityIds = entityIds;
    }

    public int[] getEntityIds() {
        return entityIds;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityIds = new int[in.readVarInt()];
        for(int index = 0; index < entityIds.length; index++) {
            entityIds[index] = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityIds.length);
        for(int entityId : entityIds) {
            out.writeVarInt(entityId);
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
