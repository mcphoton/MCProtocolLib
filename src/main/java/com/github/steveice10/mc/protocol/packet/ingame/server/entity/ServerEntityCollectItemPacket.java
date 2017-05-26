package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityCollectItemPacket implements Packet {

    private int collectedEntityId;
    private int collectorEntityId;
    private int itemCount;

    @SuppressWarnings("unused")
    private ServerEntityCollectItemPacket() {
    }

    public ServerEntityCollectItemPacket(int collectedEntityId, int collectorEntityId, int itemCount) {
        this.collectedEntityId = collectedEntityId;
        this.collectorEntityId = collectorEntityId;
        this.itemCount = itemCount;
    }

    public int getCollectedEntityId() {
        return collectedEntityId;
    }

    public int getCollectorEntityId() {
        return collectorEntityId;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public void read(NetInput in) throws IOException {
        collectedEntityId = in.readVarInt();
        collectorEntityId = in.readVarInt();
        itemCount = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(collectedEntityId);
        out.writeVarInt(collectorEntityId);
        out.writeVarInt(itemCount);
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
