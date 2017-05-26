package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.entity.player.PlayerAction;
import com.github.steveice10.mc.protocol.data.game.world.block.BlockFace;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerActionPacket implements Packet {

    private PlayerAction action;
    private IntPosition position;
    private BlockFace face;

    @SuppressWarnings("unused")
    private ClientPlayerActionPacket() {
    }

    public ClientPlayerActionPacket(PlayerAction action, IntPosition position, BlockFace face) {
        this.action = action;
        this.position = position;
        this.face = face;
    }

    public PlayerAction getAction() {
        return action;
    }

    public IntPosition getPosition() {
        return position;
    }

    public BlockFace getFace() {
        return face;
    }

    @Override
    public void read(NetInput in) throws IOException {
        action = MagicValues.key(PlayerAction.class, in.readVarInt());
        position = NetUtil.readPosition(in);
        face = MagicValues.key(BlockFace.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, action));
        NetUtil.writePosition(out, position);
        out.writeByte(MagicValues.value(Integer.class, face));
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
