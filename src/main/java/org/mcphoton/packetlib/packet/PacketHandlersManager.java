package org.mcphoton.packetlib.packet;

import java.util.HashMap;
import java.util.Map;

import org.mcphoton.packetlib.Session;

import com.electronwill.utils.Bag;
import com.electronwill.utils.SimpleBag;

public final class PacketHandlersManager {

	private final Map<Class<? extends Packet>, Bag<PacketHandler>> handlersMap = new HashMap<>();

	public <P extends Packet> void addHandler(Class<P> packetClass, PacketHandler<? super P> handler) {
		synchronized (handlersMap) {
			Bag<PacketHandler> handlersBag = handlersMap.get(packetClass);
			if (handlersBag == null) {
				handlersBag = new SimpleBag<>();
				handlersMap.put(packetClass, handlersBag);
			}
			handlersBag.add(handler);
		}
	}

	public <P extends Packet> void removeHandler(Class<P> packetClass, PacketHandler<? super P> handler) {
		synchronized (handlersMap) {
			Bag<PacketHandler> handlersBag = handlersMap.get(packetClass);
			if (handlersBag != null) {
				handlersBag.remove(handler);
			}
		}
	}

	public void callHandlers(Session session, Packet packet) {
		synchronized (handlersMap) {
			Class<? extends Packet> packetClass = packet.getClass();
			Bag<PacketHandler> handlersBag = handlersMap.get(packetClass);
			if (handlersBag != null) {
				for (PacketHandler<Packet> handler : handlersBag) {
					handler.onPacketReceived(session, packet);
				}
			}
		}
	}

}
