package org.mcphoton.packetlib.event.server;

import org.mcphoton.packetlib.NetworkServer;

/**
 * Called when the server is about to close.
 */
public class ServerClosingEvent extends ServerEvent {

	/**
	 * Creates a new ServerClosingEvent instance.
	 *
	 * @param server Server being closed.
	 */
	public ServerClosingEvent(NetworkServer server) {
		super(server);
	}

	@Override
	public void call(ServerListener listener) {
		listener.serverClosing(this);
	}
}
