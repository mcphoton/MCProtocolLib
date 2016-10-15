package org.spacehq.packetlib.event.server;

import org.spacehq.packetlib.Server;

/**
 * Called when the server is closed.
 */
public class ServerClosedEvent extends ServerEvent {

	/**
	 * Creates a new ServerClosedEvent instance.
	 *
	 * @param server Server being closed.
	 */
	public ServerClosedEvent(Server server) {
		super(server);
	}

	@Override
	public void call(ServerListener listener) {
		listener.serverClosed(this);
	}
}
