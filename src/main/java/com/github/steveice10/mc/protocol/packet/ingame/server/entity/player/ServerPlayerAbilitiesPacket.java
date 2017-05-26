package com.github.steveice10.mc.protocol.packet.ingame.server.entity.player;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerPlayerAbilitiesPacket implements Packet {

    private boolean invincible;
    private boolean canFly;
    private boolean flying;
    private boolean creative;
    private float flySpeed;
    private float walkSpeed;

    @SuppressWarnings("unused")
    private ServerPlayerAbilitiesPacket() {
    }

    public ServerPlayerAbilitiesPacket(boolean invincible, boolean canFly, boolean flying, boolean creative, float flySpeed, float walkSpeed) {
        this.invincible = invincible;
        this.canFly = canFly;
        this.flying = flying;
        this.creative = creative;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
    }

    public boolean getInvincible() {
        return invincible;
    }

    public boolean getCanFly() {
        return canFly;
    }

    public boolean getFlying() {
        return flying;
    }

    public boolean getCreative() {
        return creative;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    @Override
    public void read(NetInput in) throws IOException {
        byte flags = in.readByte();
        invincible = (flags & 1) > 0;
        canFly = (flags & 2) > 0;
        flying = (flags & 4) > 0;
        creative = (flags & 8) > 0;
        flySpeed = in.readFloat();
        walkSpeed = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        byte flags = 0;
        if(invincible) {
            flags = (byte) (flags | 1);
        }

        if(canFly) {
            flags = (byte) (flags | 2);
        }

        if(flying) {
            flags = (byte) (flags | 4);
        }

        if(creative) {
            flags = (byte) (flags | 8);
        }

        out.writeByte(flags);
        out.writeFloat(flySpeed);
        out.writeFloat(walkSpeed);
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
