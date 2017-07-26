package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.data.game.world.block.ExplodedBlockRecord;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerExplosionPacket implements Packet {
    private Vec3d position;
    private float radius;
    private List<ExplodedBlockRecord> exploded;
    private Vec3d pushVelocity;

    @SuppressWarnings("unused")
    private ServerExplosionPacket() {}

    public ServerExplosionPacket(Vec3d position, float radius, List<ExplodedBlockRecord> exploded,
                                 Vec3d pushVelocity) {
        this.position = position;
        this.radius = radius;
        this.exploded = exploded;
        this.pushVelocity = pushVelocity;
    }

    public Vec3d getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    public List<ExplodedBlockRecord> getExploded() {
        return exploded;
    }

    public Vec3d getPushVelocity() {
        return pushVelocity;
    }

    @Override
    public void read(NetInput in) throws IOException {
        float x = in.readFloat();
        float y = in.readFloat();
        float z = in.readFloat();
        position = new Vec3d(x, y, z);
        radius = in.readFloat();
        exploded = new ArrayList<ExplodedBlockRecord>();
        int length = in.readInt();
        for (int count = 0; count < length; count++) {
            exploded.add(new ExplodedBlockRecord(in.readByte(), in.readByte(), in.readByte()));
        }

        float pushX = in.readFloat();
        float pushY = in.readFloat();
        float pushZ = in.readFloat();
        pushVelocity = new Vec3d(pushX, pushY, pushZ);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat((float)position.x());
        out.writeFloat((float)position.y());
        out.writeFloat((float)position.z());
        out.writeFloat(radius);
        out.writeInt(exploded.size());
        for (ExplodedBlockRecord record : exploded) {
            out.writeByte(record.getX());
            out.writeByte(record.getY());
            out.writeByte(record.getZ());
        }

        out.writeFloat((float)pushVelocity.x());
        out.writeFloat((float)pushVelocity.y());
        out.writeFloat((float)pushVelocity.z());
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