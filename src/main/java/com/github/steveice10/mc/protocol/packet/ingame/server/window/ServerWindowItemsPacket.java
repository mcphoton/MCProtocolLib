package com.github.steveice10.mc.protocol.packet.ingame.server.window;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerWindowItemsPacket implements Packet {

    private int windowId;
    private ItemStack items[];

    @SuppressWarnings("unused")
    private ServerWindowItemsPacket() {
    }

    public ServerWindowItemsPacket(int windowId, ItemStack items[]) {
        this.windowId = windowId;
        this.items = items;
    }

    public int getWindowId() {
        return windowId;
    }

    public ItemStack[] getItems() {
        return items;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readUnsignedByte();
        items = new ItemStack[in.readShort()];
        for(int index = 0; index < items.length; index++) {
            items[index] = NetUtil.readItem(in);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeShort(items.length);
        for(ItemStack item : items) {
            NetUtil.writeItem(out, item);
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
