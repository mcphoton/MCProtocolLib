package org.spacehq.packetlib.event.session;

import org.spacehq.packetlib.Session;

/**
 * Called when the session connects.
 */
public class ConnectedEvent extends SessionEvent {

	/**
	 * Creates a new ConnectedEvent instance.
	 *
	 * @param session Session being connected.
	 */
	public ConnectedEvent(Session session) {
		super(session);
	}

	@Override
	public void call(SessionListener listener) {
		listener.connected(this);
	}
}
