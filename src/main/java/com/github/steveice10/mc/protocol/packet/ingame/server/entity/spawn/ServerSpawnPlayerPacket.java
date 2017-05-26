package com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataStorage;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.UnorderedMetadataStorage;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import java.util.UUID;
import org.mcphoton.utils.Vector;

import static com.github.steveice10.mc.protocol.util.NetUtil.F_2PI;

public class ServerSpawnPlayerPacket implements Packet {
    private int entityId;
    private UUID uuid;
    private Vector position;
    /** Angles in radians */
    private float yaw, pitch;
    private MetadataStorage metadata;

    @SuppressWarnings("unused")
    private ServerSpawnPlayerPacket() {}

    public ServerSpawnPlayerPacket(int entityId, UUID uuid, Vector position, float yaw, float pitch,
                                   MetadataStorage metadata) {
        this.entityId = entityId;
        this.uuid = uuid;
        this.position = position.clone();
        this.yaw = yaw;
        this.pitch = pitch;
        this.metadata = metadata;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getUUID() {
        return uuid;
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

    public MetadataStorage getMetadata() {
        return metadata;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        uuid = in.readUUID();
        double x = in.readDouble();
        double y = in.readDouble();
        double z = in.readDouble();
        position = new Vector(x, y, z);
        yaw = in.readByte() * F_2PI / 256f;
        pitch = in.readByte() * F_2PI / 256f;
        metadata = new UnorderedMetadataStorage(NetUtil.readEntityMetadata(in));
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeUUID(uuid);
        out.writeDouble(position.getX());
        out.writeDouble(position.getY());
        out.writeDouble(position.getZ());
        out.writeByte((byte)(yaw * 256 / F_2PI));
        out.writeByte((byte)(pitch * 256 / F_2PI));
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