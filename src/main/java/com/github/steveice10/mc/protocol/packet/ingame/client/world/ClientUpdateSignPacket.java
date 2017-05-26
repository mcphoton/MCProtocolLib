package com.github.steveice10.mc.protocol.packet.ingame.client.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientUpdateSignPacket implements Packet {

    private IntPosition position;
    private String lines[];

    @SuppressWarnings("unused")
    private ClientUpdateSignPacket() {
    }

    public ClientUpdateSignPacket(IntPosition position, String lines[]) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("Lines must contain exactly 4 strings!");
        }

        this.position = position;
        this.lines = lines;
    }

    public IntPosition getPosition() {
        return position;
    }

    public String[] getLines() {
        return lines;
    }

    @Override
    public void read(NetInput in) throws IOException {
        position = NetUtil.readPosition(in);
        lines = new String[4];
        for(int count = 0; count < lines.length; count++) {
            lines[count] = in.readString();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, position);
        for(String line : lines) {
            out.writeString(line);
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
