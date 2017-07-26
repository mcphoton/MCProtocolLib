package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.electronwill.utils.Vec3d;
import com.github.steveice10.mc.protocol.data.game.world.sound.SoundCategory;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.sound.BuiltinSound;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayBuiltinSoundPacket implements Packet {

    private BuiltinSound sound;
    private SoundCategory category;
    private Vec3d position;
    private float volume;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerPlayBuiltinSoundPacket() {}

    public ServerPlayBuiltinSoundPacket(BuiltinSound sound, SoundCategory category, Vec3d
            position, float
            volume, float pitch) {
        this.sound = sound;
        this.category = category;
        this.position = position;
        this.volume = volume;
        this.pitch = pitch;
    }

    public BuiltinSound getSound() {
        return sound;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public Vec3d getPosition() {
        return position;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        sound = MagicValues.key(BuiltinSound.class, in.readVarInt());
        category = MagicValues.key(SoundCategory.class, in.readVarInt());
        double x = in.readInt() / 8D;
        double y = in.readInt() / 8D;
        double z = in.readInt() / 8D;
        position = new Vec3d(x,y,z);
        volume = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, sound));
        out.writeVarInt(MagicValues.value(Integer.class, category));
        out.writeInt((int) (position.x() * 8));
        out.writeInt((int) (position.y() * 8));
        out.writeInt((int) (position.z() * 8));
        out.writeFloat(volume);
        out.writeFloat(pitch);
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