package org.mcphoton.mc.protocol.packet.ingame.client.player;

import java.io.IOException;

import org.mcphoton.mc.protocol.data.MagicValues;
import org.mcphoton.mc.protocol.data.game.entity.metadata.Position;
import org.mcphoton.mc.protocol.data.game.entity.player.Hand;
import org.mcphoton.mc.protocol.data.game.world.block.BlockFace;
import org.mcphoton.mc.protocol.util.NetUtil;
import org.mcphoton.mc.protocol.util.ReflectionToString;
import org.mcphoton.packetlib.io.NetInput;
import org.mcphoton.packetlib.io.NetOutput;
import org.mcphoton.packetlib.packet.Packet;

public class ClientPlayerPlaceBlockPacket implements Packet {

    private Position position;
    private BlockFace face;
    private Hand hand;
    private float cursorX;
    private float cursorY;
    private float cursorZ;

    @SuppressWarnings("unused")
    private ClientPlayerPlaceBlockPacket() {
    }

    public ClientPlayerPlaceBlockPacket(Position position, BlockFace face, Hand hand, float cursorX, float cursorY, float cursorZ) {
        this.position = position;
        this.face = face;
        this.hand = hand;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.cursorZ = cursorZ;
    }

    public Position getPosition() {
        return this.position;
    }

    public BlockFace getFace() {
        return this.face;
    }

    public Hand getHand() {
        return this.hand;
    }

    public float getCursorX() {
        return this.cursorX;
    }

    public float getCursorY() {
        return this.cursorY;
    }

    public float getCursorZ() {
        return this.cursorZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.position = NetUtil.readPosition(in);
        this.face = MagicValues.key(BlockFace.class, in.readVarInt());
        this.hand = MagicValues.key(Hand.class, in.readVarInt());
        this.cursorX = in.readUnsignedByte() / 16f;
        this.cursorY = in.readUnsignedByte() / 16f;
        this.cursorZ = in.readUnsignedByte() / 16f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.position);
        out.writeVarInt(MagicValues.value(Integer.class, this.face));
        out.writeVarInt(MagicValues.value(Integer.class, this.hand));
        out.writeByte((int) (this.cursorX * 16));
        out.writeByte((int) (this.cursorY * 16));
        out.writeByte((int) (this.cursorZ * 16));
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
