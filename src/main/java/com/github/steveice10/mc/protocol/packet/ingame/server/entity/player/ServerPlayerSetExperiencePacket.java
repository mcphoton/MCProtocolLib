package com.github.steveice10.mc.protocol.packet.ingame.server.entity.player;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerSetExperiencePacket implements Packet {

    private float experience;
    private int level;
    private int totalExperience;

    @SuppressWarnings("unused")
    private ServerPlayerSetExperiencePacket() {
    }

    public ServerPlayerSetExperiencePacket(float experience, int level, int totalExperience) {
        this.experience = experience;
        this.level = level;
        this.totalExperience = totalExperience;
    }

    public float getSlot() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    @Override
    public void read(NetInput in) throws IOException {
        experience = in.readFloat();
        level = in.readVarInt();
        totalExperience = in.readVarInt();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeFloat(experience);
        out.writeVarInt(level);
        out.writeVarInt(totalExperience);
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
