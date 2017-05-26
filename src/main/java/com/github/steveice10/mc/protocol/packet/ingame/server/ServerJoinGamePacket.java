package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.game.setting.Difficulty;
import com.github.steveice10.mc.protocol.data.game.world.WorldType;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerJoinGamePacket implements Packet {

    private int entityId;
    private boolean hardcore;
    private GameMode gamemode;
    private int dimension;
    private Difficulty difficulty;
    private int maxPlayers;
    private WorldType worldType;
    private boolean reducedDebugInfo;

    @SuppressWarnings("unused")
    private ServerJoinGamePacket() {
    }

    public ServerJoinGamePacket(int entityId, boolean hardcore, GameMode gamemode, int dimension, Difficulty difficulty, int maxPlayers, WorldType worldType, boolean reducedDebugInfo) {
        this.entityId = entityId;
        this.hardcore = hardcore;
        this.gamemode = gamemode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.worldType = worldType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    public int getEntityId() {
        return entityId;
    }

    public boolean getHardcore() {
        return hardcore;
    }

    public GameMode getGameMode() {
        return gamemode;
    }

    public int getDimension() {
        return dimension;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public WorldType getWorldType() {
        return worldType;
    }

    public boolean getReducedDebugInfo() {
        return reducedDebugInfo;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readInt();
        int gamemode = in.readUnsignedByte();
        hardcore = (gamemode & 8) == 8;
        gamemode &= -9;
        this.gamemode = MagicValues.key(GameMode.class, gamemode);
        dimension = in.readInt();
        difficulty = MagicValues.key(Difficulty.class, in.readUnsignedByte());
        maxPlayers = in.readUnsignedByte();
        worldType = MagicValues.key(WorldType.class, in.readString().toLowerCase());
        reducedDebugInfo = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(entityId);
        int gamemode = MagicValues.value(Integer.class, this.gamemode);
        if(hardcore) {
            gamemode |= 8;
        }

        out.writeByte(gamemode);
        out.writeInt(dimension);
        out.writeByte(MagicValues.value(Integer.class, difficulty));
        out.writeByte(maxPlayers);
        out.writeString(MagicValues.value(String.class, worldType));
        out.writeBoolean(reducedDebugInfo);
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
