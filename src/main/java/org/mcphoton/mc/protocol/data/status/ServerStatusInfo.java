package org.mcphoton.mc.protocol.data.status;

import java.awt.image.BufferedImage;

import org.mcphoton.mc.protocol.data.message.Message;

public class ServerStatusInfo {
    private VersionInfo version;
    private PlayerInfo players;
    private Message description;
    private BufferedImage icon;

    public ServerStatusInfo(VersionInfo version, PlayerInfo players, Message description, BufferedImage icon) {
        this.version = version;
        this.players = players;
        this.description = description;
        this.icon = icon;
    }

    public VersionInfo getVersionInfo() {
        return this.version;
    }

    public PlayerInfo getPlayerInfo() {
        return this.players;
    }

    public Message getDescription() {
        return this.description;
    }

    public BufferedImage getIcon() {
        return this.icon;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ServerStatusInfo && this.version.equals(((ServerStatusInfo) o).version) && this.players.equals(((ServerStatusInfo) o).players) && this.description.equals(((ServerStatusInfo) o).description) && (this.icon != null ? this.icon.equals(((ServerStatusInfo) o).icon) : ((ServerStatusInfo) o).icon == null);
    }

    @Override
    public int hashCode() {
        int result = this.version.hashCode();
        result = 31 * result + this.players.hashCode();
        result = 31 * result + this.description.hashCode();
        result = 31 * result + (this.icon != null ? this.icon.hashCode() : 0);
        return result;
    }
}
