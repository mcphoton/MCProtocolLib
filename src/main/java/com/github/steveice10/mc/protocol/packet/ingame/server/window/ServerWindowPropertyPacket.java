package com.github.steveice10.mc.protocol.packet.ingame.server.window;

import com.github.steveice10.mc.protocol.data.game.window.property.WindowProperty;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerWindowPropertyPacket implements Packet {

    private int windowId;
    private int property;
    private int value;

    @SuppressWarnings("unused")
    private ServerWindowPropertyPacket() {
    }

    public ServerWindowPropertyPacket(int windowId, int property, int value) {
        this.windowId = windowId;
        this.property = property;
        this.value = value;
    }

    public <T extends Enum<T> & WindowProperty> ServerWindowPropertyPacket(int windowId, T property, int value) {
        this.windowId = windowId;
        this.property = MagicValues.value(Integer.class, property);
        this.value = value;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getRawProperty() {
        return property;
    }

    public <T extends Enum<T> & WindowProperty> T getProperty(Class<T> type) {
        return MagicValues.key(type, value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readUnsignedByte();
        property = in.readShort();
        value = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeShort(property);
        out.writeShort(value);
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
