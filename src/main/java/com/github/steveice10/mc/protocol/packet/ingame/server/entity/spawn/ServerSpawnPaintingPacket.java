package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.entity.type.PaintingType;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.HangingDirection;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

public class ServerSpawnPaintingPacket implements Packet {
    private int entityId;
    private UUID uuid;
    private PaintingType paintingType;
    private IntPosition position;
    private HangingDirection direction;

    @SuppressWarnings("unused")
    private ServerSpawnPaintingPacket() {}

    public ServerSpawnPaintingPacket(int entityId, UUID uuid, PaintingType paintingType, IntPosition position, HangingDirection direction) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.paintingType = paintingType;
        this.position = position;
        this.direction = direction;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public PaintingType getPaintingType() {
        return paintingType;
    }

    public IntPosition getPosition() {
        return position;
    }

    public HangingDirection getDirection() {
        return direction;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        paintingType = MagicValues.key(PaintingType.class, in.readString());
        position = NetUtil.readPosition(in);
        direction = MagicValues.key(HangingDirection.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeString(MagicValues.value(String.class, paintingType));
        NetUtil.writePosition(out, position);
        out.writeByte(MagicValues.value(Integer.class, direction));
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