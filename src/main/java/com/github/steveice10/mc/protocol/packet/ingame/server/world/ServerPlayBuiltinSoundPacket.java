package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.world.sound.SoundCategory;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.sound.BuiltinSound;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerPlayBuiltinSoundPacket implements Packet {

    private BuiltinSound sound;
    private SoundCategory category;
    private Vector position;
    private float volume;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerPlayBuiltinSoundPacket() {}

    public ServerPlayBuiltinSoundPacket(BuiltinSound sound, SoundCategory category, Vector
            position, float
            volume, float pitch) {
        this.sound = sound;
        this.category = category;
        this.position = position.clone();
        this.volume = volume;
        this.pitch = pitch;
    }

    public BuiltinSound getSound() {
        return this.sound;
    }

    public SoundCategory getCategory() {
        return this.category;
    }

    public Vector getPosition() {
        return position;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.sound = MagicValues.key(BuiltinSound.class, in.readVarInt());
        this.category = MagicValues.key(SoundCategory.class, in.readVarInt());
        double x = in.readInt() / 8D;
        double y = in.readInt() / 8D;
        double z = in.readInt() / 8D;
        position = new Vector(x,y,z);
        this.volume = in.readFloat();
        this.pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, this.sound));
        out.writeVarInt(MagicValues.value(Integer.class, this.category));
        out.writeInt((int) (position.getX() * 8));
        out.writeInt((int) (position.getY() * 8));
        out.writeInt((int) (position.getZ() * 8));
        out.writeFloat(this.volume);
        out.writeFloat(this.pitch);
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