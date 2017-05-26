package com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard;

import com.github.steveice10.mc.protocol.data.game.scoreboard.ScoreType;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.scoreboard.ObjectiveAction;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerScoreboardObjectivePacket implements Packet {

    private String name;
    private ObjectiveAction action;
    private String displayName;
    private ScoreType type;

    @SuppressWarnings("unused")
    private ServerScoreboardObjectivePacket() {
    }

    public ServerScoreboardObjectivePacket(String name) {
        this.name = name;
        action = ObjectiveAction.REMOVE;
    }

    public ServerScoreboardObjectivePacket(String name, ObjectiveAction action, String displayName, ScoreType type) {
        if(action != ObjectiveAction.ADD && action != ObjectiveAction.UPDATE) {
            throw new IllegalArgumentException("(name, action, displayName) constructor only valid for adding and updating objectives.");
        }

        this.name = name;
        this.action = action;
        this.displayName = displayName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ObjectiveAction getAction() {
        return action;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ScoreType getType() {
        return type;
    }

    @Override
    public void read(NetInput in) throws IOException {
        name = in.readString();
        action = MagicValues.key(ObjectiveAction.class, in.readByte());
        if(action == ObjectiveAction.ADD || action == ObjectiveAction.UPDATE) {
            displayName = in.readString();
            type = MagicValues.key(ScoreType.class, in.readString());
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(name);
        out.writeByte(MagicValues.value(Integer.class, action));
        if(action == ObjectiveAction.ADD || action == ObjectiveAction.UPDATE) {
            out.writeString(displayName);
            out.writeString(MagicValues.value(String.class, type));
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
