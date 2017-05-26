package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerUnloadChunkPacket implements Packet {
    private int x;
    private int z;

    @SuppressWarnings("unused")
    private ServerUnloadChunkPacket() {
    }

    public ServerUnloadChunkPacket(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public void read(NetInput in) throws IOException {
        x = in.readInt();
        z = in.readInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(z);
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
