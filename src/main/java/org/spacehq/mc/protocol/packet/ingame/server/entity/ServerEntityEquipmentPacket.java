package org.spacehq.mc.protocol.packet.ingame.server.entity;

import java.io.IOException;

import org.spacehq.mc.protocol.data.MagicValues;
import org.spacehq.mc.protocol.data.game.entity.EquipmentSlot;
import org.spacehq.mc.protocol.data.game.entity.metadata.ItemStack;
import org.spacehq.mc.protocol.util.NetUtil;
import org.spacehq.mc.protocol.util.ReflectionToString;
import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

public class ServerEntityEquipmentPacket implements Packet {

    private int entityId;
    private EquipmentSlot slot;
    private ItemStack item;

    @SuppressWarnings("unused")
    private ServerEntityEquipmentPacket() {
    }

    public ServerEntityEquipmentPacket(int entityId, EquipmentSlot slot, ItemStack item) {
        this.entityId = entityId;
        this.slot = slot;
        this.item = item;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public EquipmentSlot getSlot() {
        return this.slot;
    }

    public ItemStack getItem() {
        return this.item;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readVarInt();
        this.slot = MagicValues.key(EquipmentSlot.class, in.readVarInt());
        this.item = NetUtil.readItem(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(this.entityId);
        out.writeVarInt(MagicValues.value(Integer.class, this.slot));
        NetUtil.writeItem(out, this.item);
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
