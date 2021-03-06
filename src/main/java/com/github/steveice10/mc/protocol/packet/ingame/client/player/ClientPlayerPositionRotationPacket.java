package com.github.steveice10.mc.protocol.packet.ingame.client.player;

public class ClientPlayerPositionRotationPacket extends ClientPlayerMovementPacket {

    protected ClientPlayerPositionRotationPacket() {
        pos = true;
        rot = true;
    }

    public ClientPlayerPositionRotationPacket(boolean onGround, double x, double y, double z, float yaw, float pitch) {
        super(onGround);
        pos = true;
        rot = true;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

}
