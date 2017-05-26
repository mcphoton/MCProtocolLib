package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerVehicleMovePacket implements Packet {
    private Vector position;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerVehicleMovePacket() {}

    public ServerVehicleMovePacket(Vector newPosition, float newYaw, float newPitch) {
        this.position = newPosition.clone();
        this.yaw = newYaw;
        this.pitch = newPitch;
    }

    public Vector getPosition() {
        return position;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
        yaw = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
        out.writeFloat(this.yaw);
        out.writeFloat(this.pitch);
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