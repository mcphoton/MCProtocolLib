package com.github.steveice10.mc.protocol.packet.ingame.server.scoreboard;

import com.github.steveice10.mc.protocol.data.game.scoreboard.CollisionRule;
import com.github.steveice10.mc.protocol.data.game.scoreboard.NameTagVisibility;
import com.github.steveice10.mc.protocol.data.game.scoreboard.TeamAction;
import com.github.steveice10.mc.protocol.data.game.scoreboard.TeamColor;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerTeamPacket implements Packet {

    private String name;
    private TeamAction action;
    private String displayName;
    private String prefix;
    private String suffix;
    private boolean friendlyFire;
    private boolean seeFriendlyInvisibles;
    private NameTagVisibility nameTagVisibility;
    private CollisionRule collisionRule;
    private TeamColor color;
    private String players[];

    @SuppressWarnings("unused")
    private ServerTeamPacket() {
    }

    public ServerTeamPacket(String name) {
        this.name = name;
        action = TeamAction.REMOVE;
    }

    public ServerTeamPacket(String name, TeamAction action, String players[]) {
        if(action != TeamAction.ADD_PLAYER && action != TeamAction.REMOVE_PLAYER) {
            throw new IllegalArgumentException("(name, action, players) constructor only valid for adding and removing players.");
        }

        this.name = name;
        this.action = action;
        this.players = players;
    }

    public ServerTeamPacket(String name, String displayName, String prefix, String suffix, boolean friendlyFire, boolean seeFriendlyInvisibles, NameTagVisibility nameTagVisibility, CollisionRule collisionRule, TeamColor color) {
        this.name = name;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.seeFriendlyInvisibles = seeFriendlyInvisibles;
        this.nameTagVisibility = nameTagVisibility;
        this.collisionRule = collisionRule;
        this.color = color;
        action = TeamAction.UPDATE;
    }

    public ServerTeamPacket(String name, String displayName, String prefix, String suffix, boolean friendlyFire, boolean seeFriendlyInvisibles, NameTagVisibility nameTagVisibility, CollisionRule collisionRule, TeamColor color, String players[]) {
        this.name = name;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.seeFriendlyInvisibles = seeFriendlyInvisibles;
        this.nameTagVisibility = nameTagVisibility;
        this.collisionRule = collisionRule;
        this.color = color;
        this.players = players;
        action = TeamAction.CREATE;
    }

    public String getTeamName() {
        return name;
    }

    public TeamAction getAction() {
        return action;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public boolean getFriendlyFire() {
        return friendlyFire;
    }

    public boolean getSeeFriendlyInvisibles() {
        return seeFriendlyInvisibles;
    }

    public NameTagVisibility getNameTagVisibility() {
        return nameTagVisibility;
    }

    public CollisionRule getCollisionRule() {
        return collisionRule;
    }

    public TeamColor getColor() {
        return color;
    }

    public String[] getPlayers() {
        return players;
    }

    @Override
    public void read(NetInput in) throws IOException {
        name = in.readString();
        action = MagicValues.key(TeamAction.class, in.readByte());
        if(action == TeamAction.CREATE || action == TeamAction.UPDATE) {
            displayName = in.readString();
            prefix = in.readString();
            suffix = in.readString();
            byte flags = in.readByte();
            friendlyFire = (flags & 0x1) != 0;
            seeFriendlyInvisibles = (flags & 0x2) != 0;
            nameTagVisibility = MagicValues.key(NameTagVisibility.class, in.readString());
            collisionRule = MagicValues.key(CollisionRule.class, in.readString());
            color = MagicValues.key(TeamColor.class, in.readByte());
        }

        if(action == TeamAction.CREATE || action == TeamAction.ADD_PLAYER || action
                                                                             == TeamAction.REMOVE_PLAYER) {
            players = new String[in.readVarInt()];
            for(int index = 0; index < players.length; index++) {
                players[index] = in.readString();
            }
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(name);
        out.writeByte(MagicValues.value(Integer.class, action));
        if(action == TeamAction.CREATE || action == TeamAction.UPDATE) {
            out.writeString(displayName);
            out.writeString(prefix);
            out.writeString(suffix);
            out.writeByte((friendlyFire ? 0x1 : 0x0) | (seeFriendlyInvisibles ? 0x2 : 0x0));
            out.writeString(MagicValues.value(String.class, nameTagVisibility));
            out.writeString(MagicValues.value(String.class, collisionRule));
            out.writeByte(MagicValues.value(Integer.class, color));
        }

        if(action == TeamAction.CREATE || action == TeamAction.ADD_PLAYER || action
                                                                             == TeamAction.REMOVE_PLAYER) {
            out.writeVarInt(players.length);
            for(String player : players) {
                if(player != null) {
                    out.writeString(player);
                }
            }
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
