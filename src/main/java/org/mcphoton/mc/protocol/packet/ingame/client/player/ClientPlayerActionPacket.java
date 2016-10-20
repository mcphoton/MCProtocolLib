package org.mcphoton.mc.protocol.packet.ingame.client.player;

import java.io.IOException;

import org.mcphoton.mc.protocol.data.MagicValues;
import org.mcphoton.mc.protocol.data.game.entity.metadata.Position;
import org.mcphoton.mc.protocol.data.game.entity.player.PlayerAction;
import org.mcphoton.mc.protocol.data.game.world.block.BlockFace;
import org.mcphoton.mc.protocol.util.NetUtil;
import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

public class ClientPlayerActionPacket implements Packet {

    private PlayerAction action;
    private Position position;
    private BlockFace face;

    @SuppressWarnings("unused")
    private ClientPlayerActionPacket() {
    }

    public ClientPlayerActionPacket(PlayerAction action, Position position, BlockFace face) {
        this.action = action;
        this.position = position;
        this.face = face;
    }

    public PlayerAction getAction() {
        return this.action;
    }

    public Position getPosition() {
        return this.position;
    }

    public BlockFace getFace() {
        return this.face;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = MagicValues.key(PlayerAction.class, in.readVarInt());
        this.position = NetUtil.readPosition(in);
        this.face = MagicValues.key(BlockFace.class, in.readUnsignedByte());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, this.action));
        NetUtil.writePosition(out, this.position);
        out.writeByte(MagicValues.value(Integer.class, this.face));
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
