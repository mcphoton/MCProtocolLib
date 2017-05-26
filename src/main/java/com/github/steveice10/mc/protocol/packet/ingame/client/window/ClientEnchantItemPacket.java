package com.github.steveice10.mc.protocol.packet.ingame.client.window;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientEnchantItemPacket implements Packet {

    private int windowId;
    private int enchantment;

    @SuppressWarnings("unused")
    private ClientEnchantItemPacket() {
    }

    public ClientEnchantItemPacket(int windowId, int enchantment) {
        this.windowId = windowId;
        this.enchantment = enchantment;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getEnchantment() {
        return enchantment;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readByte();
        enchantment = in.readByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeByte(enchantment);
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
