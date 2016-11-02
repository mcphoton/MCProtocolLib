package org.spacehq.packetlib.event.session;

import org.spacehq.event.Event;
import org.spacehq.packetlib.Session;

/**
 * An event relating to sessions.
 */
public abstract class SessionEvent implements Event {
	/**
	 * Calls the event.
	 *
	 * @param listener Listener to call the event on.
	 */
	public abstract void call(SessionListener listener);

	protected final Session session;

	public SessionEvent(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}
}
