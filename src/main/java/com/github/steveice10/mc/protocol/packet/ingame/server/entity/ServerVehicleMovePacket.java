package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import com.electronwill.utils.Vec3d;

public class ServerVehicleMovePacket implements Packet {
    private Vec3d position;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerVehicleMovePacket() {}

    public ServerVehicleMovePacket(Vec3d newPosition, float newYaw, float newPitch) {
        position = newPosition;
        yaw = newYaw;
        pitch = newPitch;
    }

    public Vec3d getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vec3d(x, y, z);
        yaw = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.z());
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