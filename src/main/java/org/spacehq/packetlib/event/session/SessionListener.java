package org.spacehq.packetlib.event.session;

/**
 * A listener for listening to session events.
 */
public interface SessionListener {
    /**
     * Called when a session receives a packet.
     *
     * @param event Data relating to the event.
     */
    default void packetReceived(PacketReceivedEvent event){}

    /**
     * Called when a session sends a packet.
     *
     * @param event Data relating to the event.
     */
    default void packetSent(PacketSentEvent event){}

    /**
     * Called when a session connects.
     *
     * @param event Data relating to the event.
     */
    default void connected(ConnectedEvent event){}

    /**
     * Called when a session is about to disconnect.
     *
     * @param event Data relating to the event.
     */
    default void disconnecting(DisconnectingEvent event){}

    /**
     * Called when a session is disconnected.
     *
     * @param event Data relating to the event.
     */
    default void disconnected(DisconnectedEvent event){}
}
