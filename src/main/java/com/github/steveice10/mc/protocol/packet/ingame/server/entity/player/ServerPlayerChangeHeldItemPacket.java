package com.github.steveice10.mc.protocol.packet.ingame.server.entity.player;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerChangeHeldItemPacket implements Packet {

    private int slot;

    @SuppressWarnings("unused")
    private ServerPlayerChangeHeldItemPacket() {
    }

    public ServerPlayerChangeHeldItemPacket(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public void read(NetInput in) throws IOException {
        slot = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(slot);
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
