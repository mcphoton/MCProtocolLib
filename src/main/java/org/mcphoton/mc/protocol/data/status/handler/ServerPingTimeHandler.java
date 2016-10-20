package org.mcphoton.mc.protocol.data.status.handler;

import org.mcphoton.packetlib.Session;

public interface ServerPingTimeHandler {
    public void handle(Session session, long pingTime);
}
