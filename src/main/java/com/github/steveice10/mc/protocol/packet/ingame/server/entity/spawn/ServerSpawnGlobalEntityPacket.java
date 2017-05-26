package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.type.GlobalEntityType;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerSpawnGlobalEntityPacket implements Packet {
    private int entityId;
    private GlobalEntityType type;
    private Vector position;

    @SuppressWarnings("unused")
    private ServerSpawnGlobalEntityPacket() {}

    public ServerSpawnGlobalEntityPacket(int entityId, GlobalEntityType type, Vector position) {
        this.entityId = entityId;
        this.type = type;
        this.position = position.clone();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public GlobalEntityType getType() {
        return this.type;
    }

    public Vector getPosition() {
        return position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        type = MagicValues.key(GlobalEntityType.class, in.readByte());
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeByte(MagicValues.value(Integer.class, this.type));
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
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