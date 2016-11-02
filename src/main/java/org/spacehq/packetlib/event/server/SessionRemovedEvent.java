package org.spacehq.packetlib.event.server;

import org.spacehq.packetlib.NetworkServer;
import org.spacehq.packetlib.Session;

/**
 * Called when a session is removed and disconnected from the server.
 */
public class SessionRemovedEvent extends ServerEvent {
	private Session session;

	/**
	 * Creates a new SessionRemovedEvent instance.
	 *
	 * @param server Server the session is being removed from.
	 * @param session Session being removed.
	 */
	public SessionRemovedEvent(NetworkServer server, Session session) {
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
		listener.sessionRemoved(this);
	}
}
