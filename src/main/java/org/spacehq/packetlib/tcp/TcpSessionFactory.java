package org.spacehq.packetlib.tcp;

import java.net.Proxy;

import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.ConnectionListener;
import org.spacehq.packetlib.NetworkServer;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.SessionFactory;

/**
 * A session factory used to create TCP sessions.
 */
public class TcpSessionFactory implements SessionFactory {
    private Proxy clientProxy;

    public TcpSessionFactory() {
    }

    public TcpSessionFactory(Proxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    @Override
    public Session createClientSession(final Client client) {
        return new TcpClientSession(client.getHost(), client.getPort(), client.getPacketProtocol(), client, this.clientProxy);
    }

    @Override
    public ConnectionListener createServerListener(final NetworkServer server) {
        return new TcpConnectionListener(server.getHost(), server.getPort(), server);
    }
}
