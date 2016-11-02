package org.spacehq.packetlib.event.server;

import org.spacehq.packetlib.NetworkServer;

/**
 * Called when the server is bound to its host and port.
 */
public class ServerBoundEvent extends ServerEvent {
	/**
	 * Creates a new ServerBoundEvent instance.
	 *
	 * @param server Server being bound.
	 */
	public ServerBoundEvent(NetworkServer server) {
		super(server);
	}

	@Override
	public void call(ServerListener listener) {
		listener.serverBound(this);
	}
}
