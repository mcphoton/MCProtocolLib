package org.mcphoton.mc.protocol.packet.ingame.server.window;

import java.io.IOException;

import org.mcphoton.mc.protocol.data.MagicValues;
import org.mcphoton.mc.protocol.data.game.window.WindowType;
import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

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
        return this.windowId;
    }

    public WindowType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getSlots() {
        return this.slots;
    }

    public int getOwnerEntityId() {
        return this.ownerEntityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.windowId = in.readUnsignedByte();
        this.type = MagicValues.key(WindowType.class, in.readString());
        this.name = in.readString();
        this.slots = in.readUnsignedByte();
        if(this.type == WindowType.HORSE) {
            this.ownerEntityId = in.readInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.windowId);
        out.writeString(MagicValues.value(String.class, this.type));
        out.writeString(this.name);
        out.writeByte(this.slots);
        if(this.type == WindowType.HORSE) {
            out.writeInt(this.ownerEntityId);
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
