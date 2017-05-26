package com.github.steveice10.mc.protocol.packet.ingame.server.window;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.window.WindowType;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerOpenWindowPacket implements Packet {

    private int windowId;
    private WindowType type;
    private String name;
    private int slots;
    private int ownerEntityId;

    @SuppressWarnings("unused")
    private ServerOpenWindowPacket() {
    }

    public ServerOpenWindowPacket(int windowId, WindowType type, String name, int slots) {
        this(windowId, type, name, slots, 0);
    }

    public ServerOpenWindowPacket(int windowId, WindowType type, String name, int slots, int ownerEntityId) {
        this.windowId = windowId;
        this.type = type;
        this.name = name;
        this.slots = slots;
        this.ownerEntityId = ownerEntityId;
    }

    public int getWindowId() {
        return windowId;
    }

    public WindowType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }

    public int getOwnerEntityId() {
        return ownerEntityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readUnsignedByte();
        type = MagicValues.key(WindowType.class, in.readString());
        name = in.readString();
        slots = in.readUnsignedByte();
        if(type == WindowType.HORSE) {
            ownerEntityId = in.readInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeString(MagicValues.value(String.class, type));
        out.writeString(name);
        out.writeByte(slots);
        if(type == WindowType.HORSE) {
            out.writeInt(ownerEntityId);
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
