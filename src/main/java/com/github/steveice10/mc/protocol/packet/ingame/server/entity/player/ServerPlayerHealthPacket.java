package com.github.steveice10.mc.protocol.packet.ingame.server.entity.player;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerHealthPacket implements Packet {

    private float health;
    private int food;
    private float saturation;

    @SuppressWarnings("unused")
    private ServerPlayerHealthPacket() {
    }

    public ServerPlayerHealthPacket(float health, int food, float saturation) {
        this.health = health;
        this.food = food;
        this.saturation = saturation;
    }

    public float getHealth() {
        return health;
    }

    public int getFood() {
        return food;
    }

    public float getSaturation() {
        return saturation;
    }

    @Override
    public void read(NetInput in) throws IOException {
        health = in.readFloat();
        food = in.readVarInt();
        saturation = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(health);
        out.writeVarInt(food);
        out.writeFloat(saturation);
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
