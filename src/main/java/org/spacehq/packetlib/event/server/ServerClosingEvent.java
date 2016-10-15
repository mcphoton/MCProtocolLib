package org.spacehq.packetlib.event.server;

import org.spacehq.packetlib.Server;

/**
 * Called when the server is about to close.
 */
public class ServerClosingEvent extends ServerEvent {

	/**
	 * Creates a new ServerClosingEvent instance.
	 *
	 * @param server Server being closed.
	 */
	public ServerClosingEvent(Server server) {
		super(server);
	}

	@Override
	public void call(ServerListener listener) {
		listener.serverClosing(this);
	}
}
