package com.github.steveice10.mc.protocol.packet.ingame.client;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientTabCompletePacket implements Packet {

    private String text;
    private boolean assumeCommand;
    private IntPosition lookingAt;

    @SuppressWarnings("unused")
    private ClientTabCompletePacket() {
    }

    public ClientTabCompletePacket(String text, boolean assumeCommand) {
        this(text, assumeCommand, null);
    }

    public ClientTabCompletePacket(String text, boolean assumeCommand, IntPosition lookingAt) {
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.lookingAt = lookingAt;
    }

    public String getText() {
        return text;
    }

    public boolean getAssumeCommand() {
        return assumeCommand;
    }

    public IntPosition getLookingAt() {
        return lookingAt;
    }

    @Override
    public void read(NetInput in) throws IOException {
        text = in.readString();
        assumeCommand = in.readBoolean();
        lookingAt = in.readBoolean() ? NetUtil.readPosition(in) : null;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(text);
        out.writeBoolean(assumeCommand);
        out.writeBoolean(lookingAt != null);
        if(lookingAt != null) {
            NetUtil.writePosition(out, lookingAt);
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
