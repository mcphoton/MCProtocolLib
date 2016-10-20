package org.mcphoton.mc.protocol.packet.ingame.client.world;

import java.io.IOException;

import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

public class ClientSteerBoatPacket implements Packet {

    private boolean unk1;
    private boolean unk2;

    @SuppressWarnings("unused")
    private ClientSteerBoatPacket() {
    }

    public ClientSteerBoatPacket(boolean unk1, boolean unk2) {
        this.unk1 = unk1;
        this.unk2 = unk2;
    }

    public boolean getUnknown1() {
        return this.unk1;
    }

    public boolean getUnknown2() {
        return this.unk2;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.unk1 = in.readBoolean();
        this.unk2 = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeBoolean(this.unk1);
        out.writeBoolean(this.unk2);
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
