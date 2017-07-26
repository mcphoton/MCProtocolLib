package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataStorage;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.UnorderedMetadataStorage;
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
    private int typeId;
    private Vec3d position;
    /** Angles in radians */
    private float pitch, yaw, headYaw;
    /** Velocity in m/s */
    private Vec3d velocity;
    private MetadataStorage metadata;

    @SuppressWarnings("unused")
    private ServerSpawnMobPacket() {}

    public ServerSpawnMobPacket(int entityId, UUID uuid, int typeId, Vec3d position, float yaw,
                                float pitch, float headYaw, Vec3d velocity,
                                MetadataStorage metadata) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.typeId = typeId;
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headYaw = headYaw;
        this.velocity = velocity;
        this.metadata = metadata;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getTypeId() {
        return typeId;
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

    public float getHeadYaw() {
        return headYaw;
    }

    public Vec3d getVelocity() {
        return velocity;
    }

    public MetadataStorage getMetadata() {
        return metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        typeId = in.readVarInt();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vec3d(x, y, z);
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        headYaw = in.readByte() * F_2PI / 256f;
        double vx = in.readShort() / 400d;
        double vy = in.readShort() / 400d;
        double vz = in.readShort() / 400d;
        velocity = new Vec3d(vx, vy, vz);
        metadata = new UnorderedMetadataStorage(NetUtil.readEntityMetadata(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeVarInt(typeId);
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.z());
        out.writeByte((byte)(yaw * 256f / F_2PI));
        out.writeByte((byte)(pitch * 256f / F_2PI));
        out.writeByte((byte)(headYaw * 256f / F_2PI));
        out.writeShort((int)(velocity.x() * 400d));
        out.writeShort((int)(velocity.y() * 400d));
        out.writeShort((int)(velocity.z() * 400d));
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