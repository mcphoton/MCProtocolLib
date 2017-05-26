package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Stores entity metadata unordered. Access by index is O(n).
 *
 * @author TheElectronWill
 */
public final class UnorderedMetadataStorage implements MetadataStorage {
    private final List<MetadataEntry> entryList;

    public UnorderedMetadataStorage(List<MetadataEntry> entryList) {
        this.entryList = entryList;
    }

    public UnorderedMetadataStorage() {
        this.entryList = new ArrayList<>();
    }

    @Override
    public MetadataEntry getEntry(int index) {
        for (MetadataEntry entry : entryList) {
            if (entry.getIndex() == index) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Collection<MetadataEntry> getAllEntries() {
        return new ArrayList<>(entryList);
    }

    @Override
    public void getAllEntries(Collection<MetadataEntry> destination) {
        destination.addAll(entryList);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        NetUtil.writeEntityMetadata(out, entryList);
    }
}