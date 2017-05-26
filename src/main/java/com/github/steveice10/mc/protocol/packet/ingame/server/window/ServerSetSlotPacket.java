package com.github.steveice10.mc.protocol.packet.ingame.server.window;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSetSlotPacket implements Packet {

    private int windowId;
    private int slot;
    private ItemStack item;

    @SuppressWarnings("unused")
    private ServerSetSlotPacket() {
    }

    public ServerSetSlotPacket(int windowId, int slot, ItemStack item) {
        this.windowId = windowId;
        this.slot = slot;
        this.item = item;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readUnsignedByte();
        slot = in.readShort();
        item = NetUtil.readItem(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeShort(slot);
        NetUtil.writeItem(out, item);
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
