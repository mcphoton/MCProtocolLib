package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataStorage;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.UnorderedMetadataStorage;
import com.github.steveice10.mc.protocol.data.game.entity.type.MobType;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import java.util.UUID;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerSpawnMobPacket implements Packet {
    private int entityId;
    private UUID uuid;
    private MobType type;
    private double x, y, z;
    /** Angles in radians */
    private float pitch, yaw, headYaw;
    /** Velocity in m/s */
    private double motX, motY, motZ;
    private MetadataStorage metadata;

    @SuppressWarnings("unused")
    private ServerSpawnMobPacket() {}

    public ServerSpawnMobPacket(int entityId, UUID uuid, MobType type, double x, double y, double z,
                                float yaw, float pitch, float headYaw, double motX, double motY,
                                double motZ, MetadataStorage metadata) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headYaw = headYaw;
        this.motX = motX;
        this.motY = motY;
        this.motZ = motZ;
        this.metadata = metadata;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public MobType getType() {
        return type;
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

    public float getHeadYaw() {
        return headYaw;
    }

    public double getMotionX() {
        return motX;
    }

    public double getMotionY() {
        return motY;
    }

    public double getMotionZ() {
        return motZ;
    }

    public MetadataStorage getMetadata() {
        return metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        type = MagicValues.key(MobType.class, in.readVarInt());
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        headYaw = in.readByte() * F_2PI / 256f;
        motX = in.readShort() / 400d;
        motY = in.readShort() / 400d;
        motZ = in.readShort() / 400d;
        metadata = new UnorderedMetadataStorage(NetUtil.readEntityMetadata(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeVarInt(MagicValues.value(Integer.class, type));
        out.writeDouble(x);
        out.writeDouble(y);
        out.writeDouble(z);
        out.writeByte((byte)(yaw * 256f / F_2PI));
        out.writeByte((byte)(pitch * 256f / F_2PI));
        out.writeByte((byte)(headYaw * 256f / F_2PI));
        out.writeShort((int)(motX * 400d));
        out.writeShort((int)(motY * 400d));
        out.writeShort((int)(motZ * 400d));
        metadata.write(out);
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