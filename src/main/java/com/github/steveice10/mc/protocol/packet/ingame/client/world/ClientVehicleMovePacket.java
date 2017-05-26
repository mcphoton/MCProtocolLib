package com.github.steveice10.mc.protocol.packet.ingame.client.world;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientVehicleMovePacket implements Packet {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ClientVehicleMovePacket() {
    }

    public ClientVehicleMovePacket(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
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

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
        yaw = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeDouble(x);
        out.writeDouble(y);
        out.writeDouble(z);
        out.writeFloat(yaw);
        out.writeFloat(pitch);
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
