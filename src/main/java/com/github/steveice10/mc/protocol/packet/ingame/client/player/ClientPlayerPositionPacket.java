package com.github.steveice10.mc.protocol.packet.ingame.client.player;

public class ClientPlayerPositionPacket extends ClientPlayerMovementPacket {

    protected ClientPlayerPositionPacket() {
        pos = true;
    }

    public ClientPlayerPositionPacket(boolean onGround, double x, double y, double z) {
        super(onGround);
        pos = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
