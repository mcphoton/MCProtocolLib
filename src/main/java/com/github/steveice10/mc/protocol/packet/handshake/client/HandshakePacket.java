package com.github.steveice10.mc.protocol.packet.handshake.client;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.handshake.HandshakeIntent;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class HandshakePacket implements Packet {
    private int protocolVersion;
    private String hostname;
    private int port;
    private HandshakeIntent intent;

    @SuppressWarnings("unused")
    private HandshakePacket() {}

    public HandshakePacket(int protocolVersion, String hostname, int port, HandshakeIntent intent) {
        this.protocolVersion = protocolVersion;
        this.hostname = hostname;
        this.port = port;
        this.intent = intent;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getHostName() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public HandshakeIntent getIntent() {
        return intent;
    }

    @Override
    public void read(NetInput in) throws IOException {
        protocolVersion = in.readVarInt();
        hostname = in.readString();
        port = in.readUnsignedShort();
        intent = MagicValues.key(HandshakeIntent.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(protocolVersion);
        out.writeString(hostname);
        out.writeShort(port);
        out.writeVarInt(MagicValues.value(Integer.class, intent));
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