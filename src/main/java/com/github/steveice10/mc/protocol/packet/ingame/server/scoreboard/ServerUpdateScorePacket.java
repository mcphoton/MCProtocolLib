package com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard;

import com.github.steveice10.mc.protocol.data.game.scoreboard.ScoreboardAction;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerUpdateScorePacket implements Packet {

    private String entry;
    private ScoreboardAction action;
    private String objective;
    private int value;

    @SuppressWarnings("unused")
    private ServerUpdateScorePacket() {
    }

    public ServerUpdateScorePacket(String entry, String objective) {
        this.entry = entry;
        this.objective = objective;
        action = ScoreboardAction.REMOVE;
    }

    public ServerUpdateScorePacket(String entry, String objective, int value) {
        this.entry = entry;
        this.objective = objective;
        this.value = value;
        action = ScoreboardAction.ADD_OR_UPDATE;
    }

    public String getEntry() {
        return entry;
    }

    public ScoreboardAction getAction() {
        return action;
    }

    public String getObjective() {
        return objective;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entry = in.readString();
        action = MagicValues.key(ScoreboardAction.class, in.readVarInt());
        objective = in.readString();
        if(action == ScoreboardAction.ADD_OR_UPDATE) {
            value = in.readVarInt();
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(entry);
        out.writeVarInt(MagicValues.value(Integer.class, action));
        out.writeString(objective);
        if(action == ScoreboardAction.ADD_OR_UPDATE) {
            out.writeVarInt(value);
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
