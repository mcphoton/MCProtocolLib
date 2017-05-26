package com.github.steveice10.mc.protocol.packet.ingame.client.world;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientSteerVehiclePacket implements Packet {

    private float sideways;
    private float forward;
    private boolean jump;
    private boolean dismount;

    @SuppressWarnings("unused")
    private ClientSteerVehiclePacket() {
    }

    public ClientSteerVehiclePacket(float sideways, float forward, boolean jump, boolean dismount) {
        this.sideways = sideways;
        this.forward = forward;
        this.jump = jump;
        this.dismount = dismount;
    }

    public float getSideways() {
        return sideways;
    }

    public float getForward() {
        return forward;
    }

    public boolean getJumping() {
        return jump;
    }

    public boolean getDismounting() {
        return dismount;
    }

    @Override
    public void read(NetInput in) throws IOException {
        sideways = in.readFloat();
        forward = in.readFloat();
        int flags = in.readUnsignedByte();
        jump = (flags & 1) > 0;
        dismount = (flags & 2) > 0;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(sideways);
        out.writeFloat(forward);
        byte flags = 0;
        if(jump) {
            flags = (byte) (flags | 1);
        }

        if(dismount) {
            flags = (byte) (flags | 2);
        }

        out.writeByte(flags);
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
