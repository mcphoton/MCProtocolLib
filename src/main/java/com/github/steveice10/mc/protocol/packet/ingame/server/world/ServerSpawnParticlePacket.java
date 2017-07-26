package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.Particle;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerSpawnParticlePacket implements Packet {
    private Particle particle;
    private boolean longDistance;
    private Vec3d position, offset;
    private float velocityOffset;
    private int amount;
    private int data[];

    @SuppressWarnings("unused")
    private ServerSpawnParticlePacket() {}

    public ServerSpawnParticlePacket(Particle particle, boolean longDistance, Vec3d position,
                                     Vec3d offset, float velocityOffset, int amount, int... data) {
        this.particle = particle;
        this.longDistance = longDistance;
        this.position = position;
        this.offset = offset;
        this.velocityOffset = velocityOffset;
        this.amount = amount;
        this.data = data;
        if (this.data.length != particle.getDataLength()) {
            throw new IllegalArgumentException(
                    "Data array length must be equal to particle's data length.");
        }
    }

    public Particle getParticle() {
        return particle;
    }

    public boolean isLongDistance() {
        return longDistance;
    }

    public Vec3d getPosition() {
        return position;
    }

    public Vec3d getOffset() {
        return offset;
    }

    public float getVelocityOffset() {
        return velocityOffset;
    }

    public int getAmount() {
        return amount;
    }

    public int[] getData() {
        return data;
    }

    @Override
    public void read(NetInput in) throws IOException {
        particle = MagicValues.key(Particle.class, in.readInt());
        longDistance = in.readBoolean();
        double x = in.readFloat();
        double y = in.readFloat();
        double z = in.readFloat();
        position = new Vec3d(x, y, z);
        double offsetX = in.readFloat();
        double offsetY = in.readFloat();
        double offsetZ = in.readFloat();
        offset = new Vec3d(offsetX, offsetY, offsetZ);
        velocityOffset = in.readFloat();
        amount = in.readInt();
        data = new int[particle.getDataLength()];
        for (int index = 0; index < data.length; index++) {
            data[index] = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(MagicValues.value(Integer.class, particle));
        out.writeBoolean(longDistance);
        out.writeFloat((float)position.x());
        out.writeFloat((float)position.y());
        out.writeFloat((float)position.z());
        out.writeFloat((float)offset.x());
        out.writeFloat((float)offset.y());
        out.writeFloat((float)offset.z());
        out.writeFloat(velocityOffset);
        out.writeInt(amount);
        for (int index = 0; index < particle.getDataLength(); index++) {
            out.writeVarInt(data[index]);
        }
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