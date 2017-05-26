package com.github.steveice10.mc.protocol.packet.ingame.client.window;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.ItemStack;
import com.github.steveice10.mc.protocol.data.game.window.ClickItemParam;
import com.github.steveice10.mc.protocol.data.game.window.CreativeGrabParam;
import com.github.steveice10.mc.protocol.data.game.window.SpreadItemParam;
import com.github.steveice10.mc.protocol.data.game.window.WindowAction;
import com.github.steveice10.mc.protocol.data.game.window.WindowActionParam;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.window.DropItemParam;
import com.github.steveice10.mc.protocol.data.game.window.FillStackParam;
import com.github.steveice10.mc.protocol.data.game.window.MoveToHotbarParam;
import com.github.steveice10.mc.protocol.data.game.window.ShiftClickItemParam;
import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientWindowActionPacket implements Packet {

    private int windowId;
    private int slot;
    private WindowActionParam param;
    private int actionId;
    private WindowAction action;
    private ItemStack clicked;

    @SuppressWarnings("unused")
    private ClientWindowActionPacket() {
    }

    public ClientWindowActionPacket(int windowId, int actionId, int slot, ItemStack clicked, WindowAction action, WindowActionParam param) {
        this.windowId = windowId;
        this.actionId = actionId;
        this.slot = slot;
        this.clicked = clicked;
        this.action = action;
        this.param = param;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getActionId() {
        return actionId;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getClickedItem() {
        return clicked;
    }

    public WindowAction getAction() {
        return action;
    }

    public WindowActionParam getParam() {
        return param;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readByte();
        slot = in.readShort();
        byte param = in.readByte();
        actionId = in.readShort();
        action = MagicValues.key(WindowAction.class, in.readByte());
        clicked = NetUtil.readItem(in);
        if(action == WindowAction.CLICK_ITEM) {
            this.param = MagicValues.key(ClickItemParam.class, param);
        } else if(action == WindowAction.SHIFT_CLICK_ITEM) {
            this.param = MagicValues.key(ShiftClickItemParam.class, param);
        } else if(action == WindowAction.MOVE_TO_HOTBAR_SLOT) {
            this.param = MagicValues.key(MoveToHotbarParam.class, param);
        } else if(action == WindowAction.CREATIVE_GRAB_MAX_STACK) {
            this.param = MagicValues.key(CreativeGrabParam.class, param);
        } else if(action == WindowAction.DROP_ITEM) {
            this.param = MagicValues.key(DropItemParam.class, param + (slot != -999 ? 2 : 0));
        } else if(action == WindowAction.SPREAD_ITEM) {
            this.param = MagicValues.key(SpreadItemParam.class, param);
        } else if(action == WindowAction.FILL_STACK) {
            this.param = MagicValues.key(FillStackParam.class, param);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeShort(slot);
        int param = 0;
        if(action == WindowAction.CLICK_ITEM) {
            param = MagicValues.value(Integer.class, this.param);
        } else if(action == WindowAction.SHIFT_CLICK_ITEM) {
            param = MagicValues.value(Integer.class, this.param);
        } else if(action == WindowAction.MOVE_TO_HOTBAR_SLOT) {
            param = MagicValues.value(Integer.class, this.param);
        } else if(action == WindowAction.CREATIVE_GRAB_MAX_STACK) {
            param = MagicValues.value(Integer.class, this.param);
        } else if(action == WindowAction.DROP_ITEM) {
            param = MagicValues.value(Integer.class, this.param) + (slot != -999 ? 2 : 0);
        } else if(action == WindowAction.SPREAD_ITEM) {
            param = MagicValues.value(Integer.class, this.param);
        } else if(action == WindowAction.FILL_STACK) {
            param = MagicValues.value(Integer.class, this.param);
        }

        out.writeByte(param);
        out.writeShort(actionId);
        out.writeByte(MagicValues.value(Integer.class, action));
        NetUtil.writeItem(out, clicked);
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
