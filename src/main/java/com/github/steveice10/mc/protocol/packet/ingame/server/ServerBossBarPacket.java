package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.BossBarAction;
import com.github.steveice10.mc.protocol.data.game.BossBarColor;
import com.github.steveice10.mc.protocol.data.game.BossBarDivision;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.util.UUID;

public class ServerBossBarPacket implements Packet {
    private UUID uuid;
    private BossBarAction action;

    private Message title;
    private float health;
    private BossBarColor color;
    private BossBarDivision division;
    private boolean darkenSky;
    private boolean dragonBar;

    @SuppressWarnings("unused")
    private ServerBossBarPacket() {}

    public ServerBossBarPacket(UUID uuid, BossBarAction action, Message title, float health, BossBarColor color, BossBarDivision division, boolean darkenSky, boolean dragonBar) {
        this.uuid = uuid;
        this.action = BossBarAction.ADD;

        this.title = title;
        this.health = health;
        this.color = color;
        this.division = division;
        this.darkenSky = darkenSky;
        this.dragonBar = dragonBar;
    }

    public ServerBossBarPacket(UUID uuid) {
        this.uuid = uuid;
        this.action = BossBarAction.REMOVE;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, float health) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_HEALTH;

        this.health = health;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, Message title) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_TITLE;

        this.title = title;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, BossBarColor color, BossBarDivision division) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_STYLE;

        this.color = color;
        this.division = division;
    }

    public ServerBossBarPacket(UUID uuid, BossBarAction action, boolean darkenSky, boolean dragonBar) {
        this.uuid = uuid;
        this.action = BossBarAction.UPDATE_FLAGS;

        this.darkenSky = darkenSky;
        this.dragonBar = dragonBar;
    }

    public UUID getUUID() {
        return uuid;
    }

    public BossBarAction getAction() {
        return action;
    }

    public Message getTitle() {
        return title;
    }

    public float getHealth() {
        return health;
    }

    public BossBarColor getColor() {
        return color;
    }

    public BossBarDivision getDivision() {
        return division;
    }

    public boolean getDarkenSky() {
        return darkenSky;
    }

    public boolean isDragonBar() {
        return dragonBar;
    }

    @Override
    public void read(NetInput in) throws IOException {
        uuid = in.readUUID();
        action = MagicValues.key(BossBarAction.class, in.readVarInt());

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_TITLE) {
            title = Message.fromString(in.readString());
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_HEALTH) {
            health = in.readFloat();
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_STYLE) {
            color = MagicValues.key(BossBarColor.class, in.readVarInt());
            division = MagicValues.key(BossBarDivision.class, in.readVarInt());
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_FLAGS) {
            int flags = in.readUnsignedByte();
            darkenSky = (flags & 0x1) == 0x1;
            dragonBar = (flags & 0x2) == 0x2;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeUUID(uuid);
        out.writeVarInt(MagicValues.value(Integer.class, action));

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_TITLE) {
            out.writeString(title.toJsonString());
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_HEALTH) {
            out.writeFloat(health);
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_STYLE) {
            out.writeVarInt(MagicValues.value(Integer.class, color));
            out.writeVarInt(MagicValues.value(Integer.class, division));
        }

        if(action == BossBarAction.ADD || action == BossBarAction.UPDATE_FLAGS) {
            int flags = 0;
            if(darkenSky) {
                flags |= 0x1;
            }

            if(dragonBar) {
                flags |= 0x2;
            }

            out.writeByte(flags);
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
