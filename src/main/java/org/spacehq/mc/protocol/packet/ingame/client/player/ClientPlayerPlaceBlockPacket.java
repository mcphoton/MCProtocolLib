package org.spacehq.mc.protocol.packet.ingame.client.player;

import java.io.IOException;

import org.spacehq.mc.protocol.data.MagicValues;
import org.spacehq.mc.protocol.data.game.entity.metadata.Position;
import org.spacehq.mc.protocol.data.game.entity.player.Hand;
import org.spacehq.mc.protocol.data.game.world.block.BlockFace;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.mc.protocol.util.ReflectionToString;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

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
        this.cursorX = in.readFloat();
        this.cursorY = in.readFloat();
        this.cursorZ = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, this.position);
        out.writeVarInt(MagicValues.value(Integer.class, this.face));
        out.writeVarInt(MagicValues.value(Integer.class, this.hand));
        out.writeFloat(this.cursorX);
        out.writeFloat(this.cursorY);
        out.writeFloat(this.cursorZ);
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
