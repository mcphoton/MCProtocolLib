package org.spacehq.mc.protocol.packet.login.client;

import java.io.IOException;

import org.spacehq.mc.protocol.util.ReflectionToString;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

public class LoginStartPacket implements Packet {

    private String username;

    @SuppressWarnings("unused")
    private LoginStartPacket() {
    }

    public LoginStartPacket(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.username = in.readString();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.username);
    }

    @Override
    public boolean isPriority() {
        return true;
    }

    @Override
    public String toString() {
        return ReflectionToString.toString(this);
    }
}
