package org.mcphoton.packetlib.event.server;

import org.mcphoton.packetlib.NetworkServer;

/**
 * Called when the server is closed.
 */
public class ServerClosedEvent extends ServerEvent {

	/**
	 * Creates a new ServerClosedEvent instance.
	 *
	 * @param server Server being closed.
	 */
	public ServerClosedEvent(NetworkServer server) {
		super(server);
	}

	@Override
	public void call(ServerListener listener) {
		listener.serverClosed(this);
	}
}
