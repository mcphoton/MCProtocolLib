package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * Stores entity metadata by index.
 *
 * @author TheElectronWill
 */
public final class MetadataStorage {
    private volatile TrackedMetadataValue[] values;

    public MetadataStorage(TrackedMetadataValue[] values) {
        this.values = values;
    }

    public MetadataStorage(int capacity) {
        this.values = new TrackedMetadataValue[capacity];
    }

    /**
     * Updates an existing value. This method is NOT thread-safe.
     *
     * @param index the index
     * @param value the new value
     */
    public void update(int index, Object value) {
        final TrackedMetadataValue[] array = values;
        TrackedMetadataValue currentValue = array[index];
        currentValue.setValue(value);
        values = array;
    }

    /**
     * Puts a new value in the storage, or replace an existing value. This method is NOT
     * thread-safe.
     *
     * @param index     the index
     * @param value     the value
     * @param valueType the value's type
     */
    public void put(int index, Object value, MetadataType valueType) {
        final TrackedMetadataValue[] array = values;
        TrackedMetadataValue currentValue = array[index];
        if (currentValue == null) {
            array[index] = new TrackedMetadataValue(valueType, value);
        } else {
            currentValue.setType(valueType);
            currentValue.setValue(value);
        }
        values = array;
    }

    /**
     * Gets the metadata at the given index. This method is thread-safe.
     *
     * @param index the index
     * @return the metadata at the given index
     */
    public MetadataValue get(int index) {
        final TrackedMetadataValue[] array = values;
        return array[index];
    }

    /**
     * Gets the metadata value (without the type) at the given index. This method is thread-safe.
     *
     * @param index the index
     * @return the value at the given index
     */
    public Object getValue(int index) {
        return get(index).getValue();
    }
}