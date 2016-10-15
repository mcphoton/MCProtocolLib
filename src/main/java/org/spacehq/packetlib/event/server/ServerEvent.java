package org.spacehq.packetlib.event.server;

import org.mcphoton.event.Event;
import org.spacehq.packetlib.Server;

/**
 * An event relating to servers.
 */
public abstract class ServerEvent implements Event {
	/**
	 * Calls the event.
	 *
	 * @param listener Listener to call the event on.
	 */
	public abstract void call(ServerListener listener);

	protected final Server server;

	public ServerEvent(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}
}
