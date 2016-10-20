package org.mcphoton.packetlib.event.server;

import org.mcphoton.packetlib.NetworkServer;
import org.mcphoton.packetlib.Session;

/**
 * Called when a session is added to the server.
 */
public class SessionAddedEvent extends ServerEvent {
	private final Session session;

	/**
	 * Creates a new SessionAddedEvent instance.
	 *
	 * @param server Server the session is being added to.
	 * @param session Session being added.
	 */
	public SessionAddedEvent(NetworkServer server, Session session) {
		super(server);
		this.session = session;
	}

	/**
	 * Gets the session involved in this event.
	 *
	 * @return The event's session.
	 */
	public Session getSession() {
		return this.session;
	}

	@Override
	public void call(ServerListener listener) {
		listener.sessionAdded(this);
	}
}
