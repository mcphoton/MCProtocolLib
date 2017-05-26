package com.github.steveice10.mc.protocol.packet.ingame.server.world;

import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.game.world.notify.EnterCreditsValue;
import com.github.steveice10.mc.protocol.data.game.world.notify.RainStrengthValue;
import com.github.steveice10.mc.protocol.util.ReflectionToString;
import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.world.notify.ClientNotification;
import com.github.steveice10.mc.protocol.data.game.world.notify.ClientNotificationValue;
import com.github.steveice10.mc.protocol.data.game.world.notify.DemoMessageValue;
import com.github.steveice10.mc.protocol.data.game.world.notify.ThunderStrengthValue;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerNotifyClientPacket implements Packet {

    private ClientNotification notification;
    private ClientNotificationValue value;

    @SuppressWarnings("unused")
    private ServerNotifyClientPacket() {
    }

    public ServerNotifyClientPacket(ClientNotification notification, ClientNotificationValue value) {
        this.notification = notification;
        this.value = value;
    }

    public ClientNotification getNotification() {
        return notification;
    }

    public ClientNotificationValue getValue() {
        return value;
    }

    @Override
    public void read(NetInput in) throws IOException {
        notification = MagicValues.key(ClientNotification.class, in.readUnsignedByte());
        float value = in.readFloat();
        if(notification == ClientNotification.CHANGE_GAMEMODE) {
            this.value = MagicValues.key(GameMode.class, (int) value);
        } else if(notification == ClientNotification.DEMO_MESSAGE) {
            this.value = MagicValues.key(DemoMessageValue.class, (int) value);
        } else if(notification == ClientNotification.ENTER_CREDITS) {
            this.value = MagicValues.key(EnterCreditsValue.class, (int) value);
        } else if(notification == ClientNotification.RAIN_STRENGTH) {
            this.value = new RainStrengthValue(value);
        } else if(notification == ClientNotification.THUNDER_STRENGTH) {
            this.value = new ThunderStrengthValue(value);
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(MagicValues.value(Integer.class, notification));
        float value = 0;
        if(this.value instanceof Enum<?>) {
            value = MagicValues.value(Integer.class, this.value);
        } else if(this.value instanceof RainStrengthValue) {
            value = ((RainStrengthValue) this.value).getStrength();
        } else if(this.value instanceof ThunderStrengthValue) {
            value = ((ThunderStrengthValue) this.value).getStrength();
        }

        out.writeFloat(value);
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
