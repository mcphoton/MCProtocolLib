package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.player.PlayerState;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerStatePacket implements Packet {

    private int entityId;
    private PlayerState state;
    private int jumpBoost;

    @SuppressWarnings("unused")
    private ClientPlayerStatePacket() {
    }

    public ClientPlayerStatePacket(int entityId, PlayerState state) {
        this(entityId, state, 0);
    }

    public ClientPlayerStatePacket(int entityId, PlayerState state, int jumpBoost) {
        this.entityId = entityId;
        this.state = state;
        this.jumpBoost = jumpBoost;
    }

    public int getEntityId() {
        return entityId;
    }

    public PlayerState getState() {
        return state;
    }

    public int getJumpBoost() {
        return jumpBoost;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        state = MagicValues.key(PlayerState.class, in.readVarInt());
        jumpBoost = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeVarInt(MagicValues.value(Integer.class, state));
        out.writeVarInt(jumpBoost);
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
