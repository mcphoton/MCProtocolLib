package org.mcphoton.mc.protocol.data.status.handler;

import org.mcphoton.mc.protocol.data.status.ServerStatusInfo;
import org.mcphoton.packetlib.Session;


public interface ServerInfoHandler {
    public void handle(Session session, ServerStatusInfo info);
}
