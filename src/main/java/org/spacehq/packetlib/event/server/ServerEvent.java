package org.spacehq.packetlib.event.server;

import org.spacehq.event.Event;
import org.spacehq.packetlib.NetworkServer;

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

	protected final NetworkServer server;

	public ServerEvent(NetworkServer server) {
		this.server = server;
	}

	public NetworkServer getServer() {
		return server;
	}
}
