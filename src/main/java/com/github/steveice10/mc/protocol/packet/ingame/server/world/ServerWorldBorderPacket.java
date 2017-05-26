package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.WorldBorderAction;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerWorldBorderPacket implements Packet {

    private WorldBorderAction action;

    private double radius;

    private double oldRadius;
    private double newRadius;
    private long speed;

    private double centerX;
    private double centerY;

    private int portalTeleportBoundary;

    private int warningTime;

    private int warningBlocks;

    @SuppressWarnings("unused")
    private ServerWorldBorderPacket() {
    }

    public ServerWorldBorderPacket(double radius) {
        action = WorldBorderAction.SET_SIZE;
        this.radius = radius;
    }

    public ServerWorldBorderPacket(double oldRadius, double newRadius, long speed) {
        action = WorldBorderAction.LERP_SIZE;
        this.oldRadius = oldRadius;
        this.newRadius = newRadius;
        this.speed = speed;
    }

    public ServerWorldBorderPacket(double centerX, double centerY) {
        action = WorldBorderAction.SET_CENTER;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public ServerWorldBorderPacket(double centerX, double centerY, double oldRadius, double newRadius, long speed, int portalTeleportBoundary, int warningTime, int warningBlocks) {
        action = WorldBorderAction.INITIALIZE;
        this.centerX = centerX;
        this.centerY = centerY;
        this.oldRadius = oldRadius;
        this.newRadius = newRadius;
        this.speed = speed;
        this.portalTeleportBoundary = portalTeleportBoundary;
        this.warningTime = warningTime;
        this.warningBlocks = warningBlocks;
    }

    public ServerWorldBorderPacket(int warning, boolean time) {
        if(time) {
            action = WorldBorderAction.SET_WARNING_TIME;
            warningTime = warning;
        } else {
            action = WorldBorderAction.SET_WARNING_BLOCKS;
            warningBlocks = warning;
        }
    }

    public WorldBorderAction getAction() {
        return action;
    }

    public double getRadius() {
        return radius;
    }

    public double getOldRadius() {
        return oldRadius;
    }

    public double getNewRadius() {
        return newRadius;
    }

    public long getSpeed() {
        return speed;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public int getPortalTeleportBoundary() {
        return portalTeleportBoundary;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public int getWarningBlocks() {
        return warningBlocks;
    }

    @Override
    public void read(NetInput in) throws IOException {
        action = MagicValues.key(WorldBorderAction.class, in.readVarInt());
        if(action == WorldBorderAction.SET_SIZE) {
            radius = in.readDouble();
        } else if(action == WorldBorderAction.LERP_SIZE) {
            oldRadius = in.readDouble();
            newRadius = in.readDouble();
            speed = in.readVarLong();
        } else if(action == WorldBorderAction.SET_CENTER) {
            centerX = in.readDouble();
            centerY = in.readDouble();
        } else if(action == WorldBorderAction.INITIALIZE) {
            centerX = in.readDouble();
            centerY = in.readDouble();
            oldRadius = in.readDouble();
            newRadius = in.readDouble();
            speed = in.readVarLong();
            portalTeleportBoundary = in.readVarInt();
            warningTime = in.readVarInt();
            warningBlocks = in.readVarInt();
        } else if(action == WorldBorderAction.SET_WARNING_TIME) {
            warningTime = in.readVarInt();
        } else if(action == WorldBorderAction.SET_WARNING_BLOCKS) {
            warningBlocks = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, action));
        if(action == WorldBorderAction.SET_SIZE) {
            out.writeDouble(radius);
        } else if(action == WorldBorderAction.LERP_SIZE) {
            out.writeDouble(oldRadius);
            out.writeDouble(newRadius);
            out.writeVarLong(speed);
        } else if(action == WorldBorderAction.SET_CENTER) {
            out.writeDouble(centerX);
            out.writeDouble(centerY);
        } else if(action == WorldBorderAction.INITIALIZE) {
            out.writeDouble(centerX);
            out.writeDouble(centerY);
            out.writeDouble(oldRadius);
            out.writeDouble(newRadius);
            out.writeVarLong(speed);
            out.writeVarInt(portalTeleportBoundary);
            out.writeVarInt(warningTime);
            out.writeVarInt(warningBlocks);
        } else if(action == WorldBorderAction.SET_WARNING_TIME) {
            out.writeVarInt(warningTime);
        } else if(action == WorldBorderAction.SET_WARNING_BLOCKS) {
            out.writeVarInt(warningBlocks);
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
