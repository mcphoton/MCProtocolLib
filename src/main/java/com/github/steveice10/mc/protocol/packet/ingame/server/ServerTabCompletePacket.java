package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerTabCompletePacket implements Packet {

    private String matches[];

    @SuppressWarnings("unused")
    private ServerTabCompletePacket() {
    }

    public ServerTabCompletePacket(String matches[]) {
        this.matches = matches;
    }

    public String[] getMatches() {
        return matches;
    }

    @Override
    public void read(NetInput in) throws IOException {
        matches = new String[in.readVarInt()];
        for(int index = 0; index < matches.length; index++) {
            matches[index] = in.readString();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(matches.length);
        for(String match : matches) {
            out.writeString(match);
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
