package org.spacehq.mc.protocol.packet.ingame.server.entity;

import java.io.IOException;

import org.spacehq.mc.protocol.util.ReflectionToString;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

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
        return this.collectedEntityId;
    }

    public int getCollectorEntityId() {
        return this.collectorEntityId;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.collectedEntityId = in.readVarInt();
        this.collectorEntityId = in.readVarInt();
        this.itemCount = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.collectedEntityId);
        out.writeVarInt(this.collectorEntityId);
        out.writeVarInt(this.itemCount);
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
