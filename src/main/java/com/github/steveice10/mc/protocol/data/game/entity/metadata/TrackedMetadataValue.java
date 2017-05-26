package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * A thread-safe MetadataValue with an additional "changed" field that is set to true whenever
 * the value changes.
 *
 * @author TheElectronWill
 */
public final class TrackedMetadataValue implements MetadataValue {
    private final MetadataType type;
    private volatile Object value;
    private volatile boolean changed;

    public TrackedMetadataValue(MetadataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public MetadataType getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (this.value != value) {
            this.value = value;
            this.changed = true;
        }
    }

    public boolean hasChanged() {
        return changed;
    }

    public void resetState() {
        changed = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (!(obj instanceof TrackedMetadataValue)) {return false;}
        TrackedMetadataValue other = (TrackedMetadataValue)obj;
        return type == other.type && value.equals(other.value) && changed == other.changed;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (changed ? 1 : 0);
        return result;
    }
}