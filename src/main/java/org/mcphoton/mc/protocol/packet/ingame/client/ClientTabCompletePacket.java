package org.mcphoton.mc.protocol.packet.ingame.client;

import java.io.IOException;

import org.mcphoton.mc.protocol.data.game.entity.metadata.Position;
import org.mcphoton.mc.protocol.util.NetUtil;
import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

public class ClientTabCompletePacket implements Packet {

    private String text;
    private boolean assumeCommand;
    private Position lookingAt;

    @SuppressWarnings("unused")
    private ClientTabCompletePacket() {
    }

    public ClientTabCompletePacket(String text, boolean assumeCommand) {
        this(text, assumeCommand, null);
    }

    public ClientTabCompletePacket(String text, boolean assumeCommand, Position lookingAt) {
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.lookingAt = lookingAt;
    }

    public String getText() {
        return this.text;
    }

    public boolean getAssumeCommand() {
        return this.assumeCommand;
    }

    public Position getLookingAt() {
        return this.lookingAt;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.text = in.readString();
        this.assumeCommand = in.readBoolean();
        this.lookingAt = in.readBoolean() ? NetUtil.readPosition(in) : null;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(this.text);
        out.writeBoolean(this.assumeCommand);
        out.writeBoolean(this.lookingAt != null);
        if(this.lookingAt != null) {
            NetUtil.writePosition(out, this.lookingAt);
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
