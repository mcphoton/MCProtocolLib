package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.sound.CustomSound;
import com.github.steveice10.mc.protocol.data.game.world.sound.Sound;
import com.github.steveice10.mc.protocol.data.game.world.sound.SoundCategory;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.game.world.sound.BuiltinSound;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import org.mcphoton.utils.Vector;

public class ServerPlaySoundPacket implements Packet {

    private Sound sound;
    private SoundCategory category;
    private Vector position;
    private float volume;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerPlaySoundPacket() {}

    public ServerPlaySoundPacket(Sound sound, SoundCategory category, Vector position, float
            volume, float
            pitch) {
        this.sound = sound;
        this.category = category;
        this.position = position.clone();
        this.volume = volume;
        this.pitch = pitch;
    }

    public Sound getSound() {
        return sound;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public Vector getPosition() {
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
        String value = in.readString();
        try {
            sound = MagicValues.key(BuiltinSound.class, value);
        } catch(IllegalArgumentException e) {
            sound = new CustomSound(value);
        }

        category = MagicValues.key(SoundCategory.class, in.readVarInt());
        double x = in.readInt() / 8D;
        double y = in.readInt() / 8D;
        double z = in.readInt() / 8D;
        position = new Vector(x,y,z);
        volume = in.readFloat();
        pitch = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        String value = "";
        if(sound instanceof CustomSound) {
            value = ((CustomSound)sound).getName();
        } else if(sound instanceof BuiltinSound) {
            value = MagicValues.value(String.class, sound);
        }

        out.writeString(value);
        out.writeVarInt(MagicValues.value(Integer.class, category));
        out.writeInt((int) (position.getX() * 8));
        out.writeInt((int) (position.getY() * 8));
        out.writeInt((int) (position.getZ() * 8));
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