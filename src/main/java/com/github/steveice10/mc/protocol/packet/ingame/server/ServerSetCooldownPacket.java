package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSetCooldownPacket implements Packet {

    private int itemId;
    private int cooldownTicks;

    @SuppressWarnings("unused")
    private ServerSetCooldownPacket() {
    }

    public ServerSetCooldownPacket(int itemId, int cooldownTicks) {
        this.itemId = itemId;
        this.cooldownTicks = cooldownTicks;
    }

    public int getItemId() {
        return itemId;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    @Override
    public void read(NetInput in) throws IOException {
        itemId = in.readVarInt();
        cooldownTicks = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(itemId);
        out.writeVarInt(cooldownTicks);
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
