package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSpawnPositionPacket implements Packet {

    private IntPosition position;

    @SuppressWarnings("unused")
    private ServerSpawnPositionPacket() {
    }

    public ServerSpawnPositionPacket(IntPosition position) {
        this.position = position;
    }

    public IntPosition getPosition() {
        return position;
    }

    @Override
    public void read(NetInput in) throws IOException {
        position = NetUtil.readPosition(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, position);
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
