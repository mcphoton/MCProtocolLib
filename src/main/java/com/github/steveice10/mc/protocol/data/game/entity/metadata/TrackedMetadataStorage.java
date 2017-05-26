package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Stores entity metadata by index and tracks their changes. Access by index is O(1).
 *
 * @author TheElectronWill
 */
public final class TrackedMetadataStorage implements MetadataStorage {
    private final TrackedMetadataValue[] values;
    /*
    The same TrackedMetadataValue are kept in the array. The only thing that can change is their
    value and state ("changed" field).
     */

    public TrackedMetadataStorage(TrackedMetadataValue[] values) {
        this.values = values;
    }

    public TrackedMetadataStorage(List<TrackedMetadataValue> values) {
    	TrackedMetadataValue[] array = new TrackedMetadataValue[values.size()];
    	this.values = values.toArray(array);
	}

    /**
     * Sets an existing value. This is thread-safe.
     *
     * @param index the index
     * @param value the new value
     */
    public void setValue(int index, Object value) {
        TrackedMetadataValue currentValue = values[index];
        currentValue.setValue(value);
    }

    /**
     * Gets the metadata value (without the type) at the given index. This method is thread-safe.
     *
     * @param index the index
     * @return the value at the given index
     */
    public Object getValue(int index) {
        return values[index].getValue();
    }

    /**
     * Gets the metadata type (without the value) at the given index. This method is thread-safe.
     *
     * @param index the index
     * @return the type at the given index
     */
    public MetadataType getType(int index) {
        return values[index].getType();
    }

    /**
     * Gets the metadata at the given index. This method is thread-safe.
     *
     * @param index the index
     * @return the metadata at the given index
     */
    public MetadataValue getMetadata(int index) {
        return values[index];
    }

    @Override
    public MetadataEntry getEntry(int index) {
        MetadataValue metadata = values[index];
        return new MetadataEntry(index, metadata.getType(), metadata.getValue());
    }

    @Override
    public Collection<MetadataEntry> getAllEntries() {
        List<MetadataEntry> entryList = new ArrayList<>(values.length);
        getAllEntries(entryList);
        return entryList;
    }

    @Override
    public void getAllEntries(Collection<MetadataEntry> destination) {
        for (int i = 0; i < values.length; i++) {
            MetadataEntry entry = getEntry(i);
            destination.add(entry);
        }
    }

    public void writeChanged(NetOutput out, boolean resetState) throws IOException {
        for (int i = 0; i < values.length; i++) {
            TrackedMetadataValue value = values[i];
            if (value.hasChanged()) {
                MetadataType type = value.getType();
                out.writeVarInt(type.getId());
                type.write(value.getValue(), out);
                if (resetState) {
                    value.resetState();
                }
            }
        }
        out.writeByte(255);//end of data
    }

    @Override
    public void write(NetOutput out) throws IOException {
        for (int i = 0; i < values.length; i++) {
            TrackedMetadataValue value = values[i];
            NetUtil.writeEntityMetadataEntry(out, i, value);
        }
        out.writeByte(255);//end of data
    }

    public boolean hasChanged(int index) {
        return values[index].hasChanged();
    }

    public void resetState() {
        for (TrackedMetadataValue value : values) {
            value.resetState();
        }
    }

    /**
     * Returns a MetadataStorage that reports only the changed values of this
     * TrackedMetadataStorage.
     *
     * @param resetStateAfterWrite true to reset the state after writing a value to a NetOutput
     * @return a MetadaStorage that reports only the changed values of this storage.
     */
    public MetadataStorage changedView(boolean resetStateAfterWrite) {
        return new MetadataStorage() {
            @Override
            public MetadataEntry getEntry(int index) {
                TrackedMetadataValue metadata = values[index];
                if (metadata.hasChanged()) {
                    return new MetadataEntry(index, metadata);
                }
                return null;
            }

            @Override
            public Collection<MetadataEntry> getAllEntries() {
                List<MetadataEntry> entryList = new ArrayList<>();
                getAllEntries(entryList);
                return entryList;
            }

            @Override
            public void getAllEntries(Collection<MetadataEntry> destination) {
                for (int i = 0; i < values.length; i++) {
                    TrackedMetadataValue metadata = values[i];
                    if (metadata.hasChanged()) {
                        destination.add(new MetadataEntry(i, metadata));
                    }
                }
            }

            @Override
            public void write(NetOutput out) throws IOException {
                TrackedMetadataStorage.this.writeChanged(out, resetStateAfterWrite);
            }
        };
    }
}