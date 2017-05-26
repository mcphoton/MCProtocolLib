package com.github.steveice10.mc.protocol.packet.ingame.client;

import com.github.steveice10.mc.protocol.data.game.entity.player.Hand;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientSettingsPacket implements Packet {

    private String locale;
    private int renderDistance;
    private ChatVisibility chatVisibility;
    private boolean chatColors;
    private List<SkinPart> visibleParts;
    private Hand mainHand;

    @SuppressWarnings("unused")
    private ClientSettingsPacket() {
    }

    public ClientSettingsPacket(String locale, int renderDistance, ChatVisibility chatVisibility, boolean chatColors, SkinPart[] visibleParts, Hand mainHand) {
        this.locale = locale;
        this.renderDistance = renderDistance;
        this.chatVisibility = chatVisibility;
        this.chatColors = chatColors;
        this.visibleParts = Arrays.asList(visibleParts);
        this.mainHand = mainHand;
    }

    public String getLocale() {
        return locale;
    }

    public int getRenderDistance() {
        return renderDistance;
    }

    public ChatVisibility getChatVisibility() {
        return chatVisibility;
    }

    public boolean getUseChatColors() {
        return chatColors;
    }

    public List<SkinPart> getVisibleParts() {
        return visibleParts;
    }

    public Hand getMainHand() {
        return mainHand;
    }

    @Override
    public void read(NetInput in) throws IOException {
        locale = in.readString();
        renderDistance = in.readByte();
        chatVisibility = MagicValues.key(ChatVisibility.class, in.readVarInt());
        chatColors = in.readBoolean();
        visibleParts = new ArrayList<SkinPart>();

        int flags = in.readUnsignedByte();
        for(SkinPart part : SkinPart.values()) {
            int bit = 1 << part.ordinal();
            if((flags & bit) == bit) {
                visibleParts.add(part);
            }
        }

        mainHand = MagicValues.key(Hand.class, in.readVarInt());
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeString(locale);
        out.writeByte(renderDistance);
        out.writeVarInt(MagicValues.value(Integer.class, chatVisibility));
        out.writeBoolean(chatColors);

        int flags = 0;
        for(SkinPart part : visibleParts) {
            flags |= 1 << part.ordinal();
        }

        out.writeByte(flags);

        out.writeVarInt(MagicValues.value(Integer.class, mainHand));
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
