package org.spacehq.packetlib.event.server;

import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;

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
	public SessionAddedEvent(Server server, Session session) {
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
