package com.github.steveice10.mc.protocol.packet.ingame.server;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.TitleAction;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerTitlePacket implements Packet {
    private TitleAction action;

    private Message title;

    private Message subtitle;

    private Message actionBar;

    private int fadeIn;
    private int stay;
    private int fadeOut;

    @SuppressWarnings("unused")
    private ServerTitlePacket() {
    }

    public ServerTitlePacket(String title, boolean sub) {
        this(Message.fromString(title), sub);
    }

    public ServerTitlePacket(Message title, boolean sub) {
        this(sub ? TitleAction.SUBTITLE : TitleAction.TITLE, title);
    }

    public ServerTitlePacket(TitleAction action, String title) {
        this(action, Message.fromString(title));
    }

    public ServerTitlePacket(TitleAction action, Message title) {
        this.action = action;

        switch (action) {
            case TITLE:
                this.title = title;
                break;
            case SUBTITLE:
                subtitle = title;
                break;
            case ACTION_BAR:
                actionBar = title;
                break;
            default:
                throw new IllegalArgumentException("action must be one of TITLE, SUBTITLE, ACTION_BAR");
        }
    }

    public ServerTitlePacket(int fadeIn, int stay, int fadeOut) {
        action = TitleAction.TIMES;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public ServerTitlePacket(boolean clear) {
        if(clear) {
            action = TitleAction.CLEAR;
        } else {
            action = TitleAction.RESET;
        }
    }

    public TitleAction getAction() {
        return action;
    }

    public Message getTitle() {
        return title;
    }

    public Message getSubtitle() {
        return subtitle;
    }

    public Message getActionBar() {
        return actionBar;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    @Override
    public void read(NetInput in) throws IOException {
        action = MagicValues.key(TitleAction.class, in.readVarInt());
        switch(action) {
            case TITLE:
                title = Message.fromString(in.readString());
                break;
            case SUBTITLE:
                subtitle = Message.fromString(in.readString());
                break;
            case ACTION_BAR:
                actionBar = Message.fromString(in.readString());
                break;
            case TIMES:
                fadeIn = in.readInt();
                stay = in.readInt();
                fadeOut = in.readInt();
                break;
            case CLEAR:
                break;
            case RESET:
                break;
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, action));
        switch(action) {
            case TITLE:
                out.writeString(title.toJsonString());
                break;
            case SUBTITLE:
                out.writeString(subtitle.toJsonString());
                break;
            case ACTION_BAR:
                out.writeString(actionBar.toJsonString());
                break;
            case TIMES:
                out.writeInt(fadeIn);
                out.writeInt(stay);
                out.writeInt(fadeOut);
                break;
            case CLEAR:
                break;
            case RESET:
                break;
        }
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
