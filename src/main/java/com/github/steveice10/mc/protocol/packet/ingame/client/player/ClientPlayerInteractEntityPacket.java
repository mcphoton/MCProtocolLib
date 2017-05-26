package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.player.InteractAction;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerInteractEntityPacket implements Packet {

    private int entityId;
    private InteractAction action;

    private float targetX;
    private float targetY;
    private float targetZ;
    private Hand hand;

    @SuppressWarnings("unused")
    private ClientPlayerInteractEntityPacket() {
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action) {
        this(entityId, action, Hand.MAIN_HAND);
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, Hand hand) {
        this(entityId, action, 0, 0, 0, hand);
    }

    public ClientPlayerInteractEntityPacket(int entityId, InteractAction action, float targetX, float targetY, float targetZ, Hand hand) {
        this.entityId = entityId;
        this.action = action;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.hand = hand;
    }

    public int getEntityId() {
        return entityId;
    }

    public InteractAction getAction() {
        return action;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public float getTargetZ() {
        return targetZ;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        action = MagicValues.key(InteractAction.class, in.readVarInt());
        if(action == InteractAction.INTERACT_AT) {
            targetX = in.readFloat();
            targetY = in.readFloat();
            targetZ = in.readFloat();
        }

        if(action == InteractAction.INTERACT || action == InteractAction.INTERACT_AT) {
            hand = MagicValues.key(Hand.class, in.readVarInt());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeVarInt(MagicValues.value(Integer.class, action));
        if(action == InteractAction.INTERACT_AT) {
            out.writeFloat(targetX);
            out.writeFloat(targetY);
            out.writeFloat(targetZ);
        }

        if(action == InteractAction.INTERACT || action == InteractAction.INTERACT_AT) {
            out.writeVarInt(MagicValues.value(Integer.class, hand));
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
