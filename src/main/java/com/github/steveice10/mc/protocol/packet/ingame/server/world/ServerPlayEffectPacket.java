package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.world.effect.SmokeEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.SoundEffect;
import com.github.steveice10.mc.protocol.data.game.world.effect.WorldEffectData;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockState;
import com.github.steveice10.mc.protocol.data.game.world.effect.BonemealGrowEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.BreakBlockEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.BreakPotionEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.ParticleEffect;
import com.github.steveice10.mc.protocol.data.game.world.effect.RecordEffectData;
import com.github.steveice10.mc.protocol.data.game.world.effect.WorldEffect;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public final class ServerPlayEffectPacket implements Packet {
    private WorldEffect effect;
    private IntPosition position;
    private WorldEffectData data;
    private boolean broadcast;

    @SuppressWarnings("unused")
    private ServerPlayEffectPacket() {
    }

    public ServerPlayEffectPacket(WorldEffect effect, IntPosition position, WorldEffectData data) {
        this(effect, position, data, false);
    }

    public ServerPlayEffectPacket(WorldEffect effect, IntPosition position, WorldEffectData data,
                                  boolean broadcast) {
        this.effect = effect;
        this.position = position;
        this.data = data;
        this.broadcast = broadcast;
    }

    public WorldEffect getEffect() {
        return effect;
    }

    public IntPosition getPosition() {
        return position;
    }

    public WorldEffectData getData() {
        return data;
    }

    public boolean getBroadcast() {
        return broadcast;
    }

    @Override
    public void read(NetInput in) throws IOException {
        effect = MagicValues.key(WorldEffect.class, in.readInt());
        position = NetUtil.readPosition(in);
        int value = in.readInt();
        if (effect != null) {
            data = effect.readData(value);
        }
        broadcast = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(MagicValues.value(Integer.class, effect));
        NetUtil.writePosition(out, position);
        int value = (data == null) ? 0 : data.getValue();
        out.writeInt(value);
        out.writeBoolean(broadcast);
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