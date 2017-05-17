package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.BeaconAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.BlockAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.ChestAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.EndGatewayAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.GenericAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.MobSpawnerAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.NoteBlockAction;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.NoteBlockInstrument;
import com.github.steveice10.mc.protocol.data.game.world.block.actions.PistonAction;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

/**
 * @see <a href="http://wiki.vg/Block_Actions">Protocol: Block actions</a>
 */
public class ServerBlockActionPacket implements Packet {
    private static final int NOTE_BLOCK = 25;
    private static final int PISTON = 33, STICKY_PISTON = 29;
    private static final int CHEST = 54, TRAPPED_CHEST = 146, ENDER_CHEST = 130;
    private static final int BEACON = 138;
    private static final int MOB_SPAWNER = 52;
    private static final int END_GATEWAY = 209;
    private static final int SHULKER_BOX_MIN = 219, SHULKER_BOX_MAX = 234;

    private IntPosition position;
    private BlockAction action;
    private int blockLegacyId;

    @SuppressWarnings("unused")
    private ServerBlockActionPacket() {}

    public ServerBlockActionPacket(IntPosition position, BlockAction action, int blockLegacyId) {
        this.position = position;
        this.action = action;
        this.blockLegacyId = blockLegacyId;
    }

    public IntPosition getPosition() {
        return position;
    }

    public BlockAction getAction() {
        return action;
    }

    /**
     * @return the block id, without the metadata value.
     */
    public int getBlockLegacyId() {
        return blockLegacyId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        position = NetUtil.readPosition(in);
        int type = in.readUnsignedByte();
        int value = in.readUnsignedByte();
        blockLegacyId = in.readVarInt() & 0xFFF;

        if (blockLegacyId == NOTE_BLOCK) {
            action = new NoteBlockAction(NoteBlockInstrument.values()[type], value);
        } else if (blockLegacyId == STICKY_PISTON || blockLegacyId == PISTON) {
            PistonAction.Type pType = PistonAction.Type.values()[type];
            PistonAction.Direction pDir = PistonAction.Direction.values()[value];
            action = new PistonAction(pType, pDir);
        } else if (blockLegacyId == MOB_SPAWNER) {
            action = MobSpawnerAction.RESET_DELAY;
        } else if (blockLegacyId == CHEST
                   || blockLegacyId == ENDER_CHEST
                   || blockLegacyId == TRAPPED_CHEST
                   || (blockLegacyId >= SHULKER_BOX_MIN && blockLegacyId <= SHULKER_BOX_MAX)) {
            action = new ChestAction(value);
        } else if (blockLegacyId == BEACON) {
            action = BeaconAction.RECALCULATE_BEAM;
        } else if (blockLegacyId == END_GATEWAY) {
            action = EndGatewayAction.TRIGGER_BEAM;
        } else {
            action = new GenericAction(type, value);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writePosition(out, position);
        out.writeByte(action.getId());
        out.writeByte(action.getParam());
        out.writeVarInt(blockLegacyId);
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