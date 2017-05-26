package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerMovementPacket implements Packet {

    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;

    protected boolean pos = false;
    protected boolean rot = false;

    protected ClientPlayerMovementPacket() {
    }

    public ClientPlayerMovementPacket(boolean onGround) {
        this.onGround = onGround;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void read(NetInput in) throws IOException {
        if(pos) {
            x = in.readDouble();
            y = in.readDouble();
            z = in.readDouble();
        }

        if(rot) {
            yaw = in.readFloat();
            pitch = in.readFloat();
        }

        onGround = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        if(pos) {
            out.writeDouble(x);
            out.writeDouble(y);
            out.writeDouble(z);
        }

        if(rot) {
            out.writeFloat(yaw);
            out.writeFloat(pitch);
        }

        out.writeBoolean(onGround);
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
