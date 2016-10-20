package org.mcphoton.mc.protocol;

import org.mcphoton.packetlib.Session;

public interface ServerLoginHandler {
    public void loggedIn(Session session);
}
