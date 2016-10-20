package org.mcphoton.packetlib.tcp;

import java.net.Proxy;

import org.mcphoton.packetlib.Client;
import org.mcphoton.packetlib.ConnectionListener;
import org.mcphoton.packetlib.NetworkServer;
import org.mcphoton.packetlib.Session;
import org.mcphoton.packetlib.SessionFactory;

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
