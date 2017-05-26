package com.github.steveice10.mc.protocol.packet.ingame.client.window;

import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientConfirmTransactionPacket implements Packet {

    private int windowId;
    private int actionId;
    private boolean accepted;

    @SuppressWarnings("unused")
    private ClientConfirmTransactionPacket() {
    }

    public ClientConfirmTransactionPacket(int windowId, int actionId, boolean accepted) {
        this.windowId = windowId;
        this.actionId = actionId;
        this.accepted = accepted;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getActionId() {
        return actionId;
    }

    public boolean getAccepted() {
        return accepted;
    }

    @Override
    public void read(NetInput in) throws IOException {
        windowId = in.readByte();
        actionId = in.readShort();
        accepted = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(windowId);
        out.writeShort(actionId);
        out.writeBoolean(accepted);
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
