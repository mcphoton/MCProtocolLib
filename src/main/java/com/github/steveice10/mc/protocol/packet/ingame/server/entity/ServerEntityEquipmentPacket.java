package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.data.game.entity.EquipmentSlot;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

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
        return entityId;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public void read(NetInput in) throws IOException {
        entityId = in.readVarInt();
        slot = MagicValues.key(EquipmentSlot.class, in.readVarInt());
        item = NetUtil.readItem(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(entityId);
        out.writeVarInt(MagicValues.value(Integer.class, slot));
        NetUtil.writeItem(out, item);
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
