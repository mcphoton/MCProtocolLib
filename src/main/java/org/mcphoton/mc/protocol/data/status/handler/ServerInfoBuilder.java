package org.mcphoton.mc.protocol.data.status.handler;

import org.mcphoton.mc.protocol.data.status.ServerStatusInfo;
import org.mcphoton.packetlib.Session;

public interface ServerInfoBuilder {
    public ServerStatusInfo buildInfo(Session session);
}
