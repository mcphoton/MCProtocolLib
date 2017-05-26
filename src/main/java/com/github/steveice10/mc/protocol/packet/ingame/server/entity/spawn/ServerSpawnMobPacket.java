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
import org.mcphoton.utils.Vector;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerSpawnMobPacket implements Packet {
    private int entityId;
    private UUID uuid;
    private MobType type;
    private Vector position;
    /** Angles in radians */
    private float pitch, yaw, headYaw;
    /** Velocity in m/s */
    private Vector velocity;
    private MetadataStorage metadata;

    @SuppressWarnings("unused")
    private ServerSpawnMobPacket() {}

    public ServerSpawnMobPacket(int entityId, UUID uuid, MobType type, Vector position, float yaw,
                                float pitch, float headYaw, Vector velocity,
                                MetadataStorage metadata) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.type = type;
        this.position = position.clone();
        this.yaw = yaw;
        this.pitch = pitch;
        this.headYaw = headYaw;
        this.velocity = velocity.clone();
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

    public Vector getPosition() {
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

    public Vector getVelocity() {
        return velocity;
    }

    public MetadataStorage getMetadata() {
        return metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        type = MagicValues.key(MobType.class, in.readVarInt());
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        headYaw = in.readByte() * F_2PI / 256f;
        double vx = in.readShort() / 400d;
        double vy = in.readShort() / 400d;
        double vz = in.readShort() / 400d;
        velocity = new Vector(vx, vy, vz);
        metadata = new UnorderedMetadataStorage(NetUtil.readEntityMetadata(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeVarInt(MagicValues.value(Integer.class, type));
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
        out.writeByte((byte)(yaw * 256f / F_2PI));
        out.writeByte((byte)(pitch * 256f / F_2PI));
        out.writeByte((byte)(headYaw * 256f / F_2PI));
        out.writeShort((int)(velocity.getX() * 400d));
        out.writeShort((int)(velocity.getY() * 400d));
        out.writeShort((int)(velocity.getZ() * 400d));
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