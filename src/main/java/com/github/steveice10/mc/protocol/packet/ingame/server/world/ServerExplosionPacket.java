package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.world.block.ExplodedBlockRecord;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mcphoton.utils.Vector;

public class ServerExplosionPacket implements Packet {
    private Vector position;
    private float radius;
    private List<ExplodedBlockRecord> exploded;
    private Vector pushVelocity;

    @SuppressWarnings("unused")
    private ServerExplosionPacket() {}

    public ServerExplosionPacket(Vector position, float radius, List<ExplodedBlockRecord> exploded,
                                 Vector pushVelocity) {
        this.position = position.clone();
        this.radius = radius;
        this.exploded = exploded;
        this.pushVelocity = pushVelocity.clone();
    }

    public Vector getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    public List<ExplodedBlockRecord> getExploded() {
        return exploded;
    }

    public Vector getPushVelocity() {
        return pushVelocity;
    }

    @Override
    public void read(NetInput in) throws IOException {
        float x = in.readFloat();
        float y = in.readFloat();
        float z = in.readFloat();
        position = new Vector(x, y, z);
        radius = in.readFloat();
        exploded = new ArrayList<ExplodedBlockRecord>();
        int length = in.readInt();
        for (int count = 0; count < length; count++) {
            exploded.add(new ExplodedBlockRecord(in.readByte(), in.readByte(), in.readByte()));
        }

        float pushX = in.readFloat();
        float pushY = in.readFloat();
        float pushZ = in.readFloat();
        pushVelocity = new Vector(pushX, pushY, pushZ);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat((float)position.getX());
        out.writeFloat((float)position.getY());
        out.writeFloat((float)position.getZ());
        out.writeFloat(radius);
        out.writeInt(exploded.size());
        for (ExplodedBlockRecord record : exploded) {
            out.writeByte(record.getX());
            out.writeByte(record.getY());
            out.writeByte(record.getZ());
        }

        out.writeFloat((float)pushVelocity.getX());
        out.writeFloat((float)pushVelocity.getY());
        out.writeFloat((float)pushVelocity.getZ());
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