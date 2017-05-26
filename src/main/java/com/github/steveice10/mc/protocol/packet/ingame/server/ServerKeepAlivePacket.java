package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerKeepAlivePacket implements Packet {

    private int id;

    @SuppressWarnings("unused")
    private ServerKeepAlivePacket() {
    }

    public ServerKeepAlivePacket(int id) {
        this.id = id;
    }

    public int getPingId() {
        return id;
    }

    @Override
    public void read(NetInput in) throws IOException {
        id = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(id);
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
