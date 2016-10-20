package org.mcphoton.mc.protocol.packet.ingame.client.world;

import java.io.IOException;
import java.util.UUID;

import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

public class ClientSpectatePacket implements Packet {
    private UUID target;

    @SuppressWarnings("unused")
    private ClientSpectatePacket() {
    }

    public ClientSpectatePacket(UUID target) {
        this.target = target;
    }

    public UUID getTarget() {
        return this.target;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.target = in.readUUID();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeUUID(this.target);
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
