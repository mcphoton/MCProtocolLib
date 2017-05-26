package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntitySetPassengersPacket implements Packet {

    private int entityId;
    private int passengerIds[];

    @SuppressWarnings("unused")
    private ServerEntitySetPassengersPacket() {
    }

    public ServerEntitySetPassengersPacket(int entityId, int... passengerIds) {
        this.entityId = entityId;
        this.passengerIds = passengerIds;
    }

    public int getEntityId() {
        return entityId;
    }

    public int[] getPassengerIds() {
        return passengerIds;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        passengerIds = new int[in.readVarInt()];
        for(int index = 0; index < passengerIds.length; index++) {
            passengerIds[index] = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeVarInt(passengerIds.length);
        for(int entityId : passengerIds) {
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
