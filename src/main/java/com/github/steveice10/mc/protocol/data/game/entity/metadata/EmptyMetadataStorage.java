package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author TheElectronWill
 */
public final class EmptyMetadataStorage implements MetadataStorage {
	public static final EmptyMetadataStorage INSTANCE = new EmptyMetadataStorage();

	private EmptyMetadataStorage() {}

	@Override
	public MetadataEntry getEntry(int index) {
		return null;
	}

	@Override
	public Collection<MetadataEntry> getAllEntries() {
		return Collections.emptyList();
	}

	@Override
	public void getAllEntries(Collection<MetadataEntry> destination) {}

	@Override
	public void write(NetOutput out) throws IOException {}
}