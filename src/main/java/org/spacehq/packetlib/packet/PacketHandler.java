package org.spacehq.packetlib.packet;

import org.spacehq.packetlib.Session;

/**
 * 
 * @author TheElectronWill
 *
 * @param <P>
 */
public interface PacketHandler<P extends Packet> {

	/**
	 * Handles a received packet.
	 * 
	 * @param session the Session that has received the packet.
	 * @param packet the packet that has been received.
	 */
	void onPacketReceived(Session session, P packet);

}
