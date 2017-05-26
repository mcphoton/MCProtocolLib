package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.Effect;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEntityEffectPacket implements Packet {

    private int entityId;
    private Effect effect;
    private int amplifier;
    private int duration;
    private boolean ambient;
    private boolean showParticles;

    @SuppressWarnings("unused")
    private ServerEntityEffectPacket() {
    }

    public ServerEntityEffectPacket(int entityId, Effect effect, int amplifier, int duration, boolean ambient, boolean showParticles) {
        this.entityId = entityId;
        this.effect = effect;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    public int getEntityId() {
        return entityId;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean getShowParticles() {
        return showParticles;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        effect = MagicValues.key(Effect.class, in.readByte());
        amplifier = in.readByte();
        duration = in.readVarInt();

        int flags = in.readByte();
        ambient = (flags & 0x1) == 0x1;
        showParticles = (flags & 0x2) == 0x2;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeByte(MagicValues.value(Integer.class, effect));
        out.writeByte(amplifier);
        out.writeVarInt(duration);

        int flags = 0;
        if(ambient) {
            flags |= 0x1;
        }

        if(showParticles) {
            flags |= 0x2;
        }

        out.writeByte(flags);
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
