package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerSpawnExpOrbPacket implements Packet {
    private int entityId;
    private Vector position;
    private int exp;

    @SuppressWarnings("unused")
    private ServerSpawnExpOrbPacket() {}

    public ServerSpawnExpOrbPacket(int entityId, Vector position, int exp) {
        this.entityId = entityId;
        this.position = position.clone();
        this.exp = exp;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Vector getPosition() {
        return position;
    }

    public int getExp() {
        return this.exp;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
        exp = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
        out.writeShort(exp);
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