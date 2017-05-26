package com.github.steveice10.mc.protocol.packet.login.client;

import javax.crypto.SecretKey;

import com.github.steveice10.mc.protocol.util.CryptUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptionResponsePacket implements Packet {

    private byte sharedKey[];
    private byte verifyToken[];

    @SuppressWarnings("unused")
    private EncryptionResponsePacket() {
    }

    public EncryptionResponsePacket(SecretKey secretKey, PublicKey publicKey, byte verifyToken[]) {
        sharedKey = CryptUtil.encryptData(publicKey, secretKey.getEncoded());
        this.verifyToken = CryptUtil.encryptData(publicKey, verifyToken);
    }

    public SecretKey getSecretKey(PrivateKey privateKey) {
        return CryptUtil.decryptSharedKey(privateKey, sharedKey);
    }

    public byte[] getVerifyToken(PrivateKey privateKey) {
        return CryptUtil.decryptData(privateKey, verifyToken);
    }

    @Override
    public void read(NetInput in) throws IOException {
        sharedKey = in.readBytes(in.readVarInt());
        verifyToken = in.readBytes(in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(sharedKey.length);
        out.writeBytes(sharedKey);
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
