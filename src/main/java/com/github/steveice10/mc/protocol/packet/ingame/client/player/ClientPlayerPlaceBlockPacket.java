package com.github.steveice10.mc.protocol.packet.ingame.client.player;

import com.github.steveice10.mc.protocol.data.game.world.block.BlockFace;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerPlaceBlockPacket implements Packet {

    private IntPosition position;
    private BlockFace face;
    private Hand hand;
    private float cursorX;
    private float cursorY;
    private float cursorZ;

    @SuppressWarnings("unused")
    private ClientPlayerPlaceBlockPacket() {
    }

    public ClientPlayerPlaceBlockPacket(IntPosition position, BlockFace face, Hand hand, float cursorX, float cursorY, float cursorZ) {
        this.position = position;
        this.face = face;
        this.hand = hand;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.cursorZ = cursorZ;
    }

    public IntPosition getPosition() {
        return position;
    }

    public BlockFace getFace() {
        return face;
    }

    public Hand getHand() {
        return hand;
    }

    public float getCursorX() {
        return cursorX;
    }

    public float getCursorY() {
        return cursorY;
    }

    public float getCursorZ() {
        return cursorZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        position = NetUtil.readPosition(in);
        face = MagicValues.key(BlockFace.class, in.readVarInt());
        hand = MagicValues.key(Hand.class, in.readVarInt());
        cursorX = in.readFloat();
        cursorY = in.readFloat();
        cursorZ = in.readFloat();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, position);
        out.writeVarInt(MagicValues.value(Integer.class, face));
        out.writeVarInt(MagicValues.value(Integer.class, hand));
        out.writeFloat(cursorX);
        out.writeFloat(cursorY);
        out.writeFloat(cursorZ);
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
