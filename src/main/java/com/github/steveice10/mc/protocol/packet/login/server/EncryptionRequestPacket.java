package com.github.steveice10.mc.protocol.packet.login.server;

import com.github.steveice10.mc.protocol.util.CryptUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.security.PublicKey;

public class EncryptionRequestPacket implements Packet {

    private String serverId;
    private PublicKey publicKey;
    private byte verifyToken[];

    @SuppressWarnings("unused")
    private EncryptionRequestPacket() {
    }

    public EncryptionRequestPacket(String serverId, PublicKey publicKey, byte verifyToken[]) {
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public String getServerId() {
        return serverId;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void read(NetInput in) throws IOException {
        serverId = in.readString();
        publicKey = CryptUtil.decodePublicKey(in.readBytes(in.readVarInt()));
        verifyToken = in.readBytes(in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(serverId);
        byte encoded[] = publicKey.getEncoded();
        out.writeVarInt(encoded.length);
        out.writeBytes(encoded);
        out.writeVarInt(verifyToken.length);
        out.writeBytes(verifyToken);
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
