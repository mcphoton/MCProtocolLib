package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.ProjectileData;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.FallingBlockData;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.HangingDirection;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.MinecartType;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.ObjectData;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.ObjectType;
import com.github.steveice10.mc.protocol.data.game.entity.type.object.SplashPotionData;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerSpawnObjectPacket implements Packet {
    private int entityId;
    private UUID uuid;
    private ObjectType type;
    private Vec3d position;
    /** Angles in radians */
    private float pitch, yaw;
    private ObjectData data;
    /** Velocity in m/s */
    private Vec3d velocity;

    @SuppressWarnings("unused")
    private ServerSpawnObjectPacket() {}

    public ServerSpawnObjectPacket(int entityId, UUID uuid, ObjectType type, ObjectData data,
                                   Vec3d position, float yaw, float pitch, Vec3d velocity) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.type = type;
        this.data = data;
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
        this.velocity = velocity;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUUID() {
        return uuid;
    }

    public ObjectType getType() {
        return type;
    }

    public ObjectData getData() {
        return data;
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

    public Vec3d getVelocity() {
        return velocity;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        type = MagicValues.key(ObjectType.class, in.readByte());
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vec3d(x,y,z);
        pitch = in.readByte() * F_2PI / 256f;
        yaw = in.readByte() * F_2PI / 256f;

        int data = in.readInt();
        if(data > 0) {
            if(type == ObjectType.MINECART) {
                this.data = MagicValues.key(MinecartType.class, data);
            } else if(type == ObjectType.ITEM_FRAME) {
                this.data = MagicValues.key(HangingDirection.class, data);
            } else if(type == ObjectType.FALLING_BLOCK) {
                this.data = new FallingBlockData(data & 65535, data >> 16);
            } else if(type == ObjectType.POTION) {
                this.data = new SplashPotionData(data);
            } else if(type == ObjectType.SPECTRAL_ARROW ||
					  type == ObjectType.TIPPED_ARROW ||
                      type == ObjectType.GHAST_FIREBALL ||
                      type == ObjectType.BLAZE_FIREBALL ||
                      type == ObjectType.DRAGON_FIREBALL ||
                      type == ObjectType.WITHER_HEAD_PROJECTILE ||
                      type == ObjectType.FISH_HOOK) {
                this.data = new ProjectileData(data);
            } else {
                this.data = new ObjectData() {
                };
            }
        }

        double vx = in.readShort() / 400d;
        double vy = in.readShort() / 400d;
        double vz = in.readShort() / 400d;
        velocity = new Vec3d(vx,vy,vz);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeByte(MagicValues.value(Integer.class, type));
        out.writeDouble(position.x());
        out.writeDouble(position.y());
        out.writeDouble(position.z());
        out.writeByte((byte) (pitch * 256f / F_2PI));
        out.writeByte((byte) (yaw * 256f / F_2PI));

        int data = 0;
        if(this.data != null) {
            if(this.data instanceof MinecartType) {
                data = MagicValues.value(Integer.class, this.data);
            } else if(this.data instanceof HangingDirection) {
                data = MagicValues.value(Integer.class, this.data);
            } else if(this.data instanceof FallingBlockData) {
                data = ((FallingBlockData) this.data).getId() | ((FallingBlockData) this.data).getMetadata() << 16;
            } else if(this.data instanceof SplashPotionData) {
                data = ((SplashPotionData) this.data).getPotionData();
            } else if(this.data instanceof ProjectileData) {
                data = ((ProjectileData) this.data).getOwnerId();
            } else {
                data = 1;
            }
        }

        out.writeInt(data);

        out.writeShort((int) (velocity.x() * 400d));
        out.writeShort((int) (velocity.y() * 400d));
        out.writeShort((int) (velocity.z() * 400d));
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