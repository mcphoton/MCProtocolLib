package com.github.steveice10.mc.protocol.packet.ingame.client.player;

public class ClientPlayerRotationPacket extends ClientPlayerMovementPacket {

    protected ClientPlayerRotationPacket() {
        rot = true;
    }

    public ClientPlayerRotationPacket(boolean onGround, float yaw, float pitch) {
        super(onGround);
        rot = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
