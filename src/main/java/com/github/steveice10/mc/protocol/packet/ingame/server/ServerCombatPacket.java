package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.player.CombatState;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerCombatPacket implements Packet {

    private CombatState state;
    private int entityId;
    private int duration;
    private int playerId;
    private Message message;

    public ServerCombatPacket() {
        state = CombatState.ENTER_COMBAT;
    }

    public ServerCombatPacket(int entityId, int duration) {
        this.state = CombatState.END_COMBAT;
        this.entityId = entityId;
        this.duration = duration;
    }

    public ServerCombatPacket(int entityId, int playerId, Message message) {
        this.state = CombatState.ENTITY_DEAD;
        this.entityId = entityId;
        this.playerId = playerId;
        this.message = message;
    }

    public CombatState getCombatState() {
        return state;
    }

    public int getEntityId() {
        return entityId;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public void read(NetInput in) throws IOException {
        state = MagicValues.key(CombatState.class, in.readVarInt());
        if(state == CombatState.END_COMBAT) {
            duration = in.readVarInt();
            entityId = in.readInt();
        } else if(state == CombatState.ENTITY_DEAD) {
            playerId = in.readVarInt();
            entityId = in.readInt();
            message = Message.fromString(in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, state));
        if(state == CombatState.END_COMBAT) {
            out.writeVarInt(duration);
            out.writeInt(entityId);
        } else if(state == CombatState.ENTITY_DEAD) {
            out.writeVarInt(playerId);
            out.writeInt(entityId);
            out.writeString(message.toJsonString());
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
