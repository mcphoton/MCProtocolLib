package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * @author TheElectronWill
 */
public final class TrackedMetadataValue extends MetadataValue {
    private boolean changed;

    public TrackedMetadataValue(MetadataType type, Object value) {
        super(type, value);
    }

    public void setType(MetadataType type) {
        this.type = type;
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
        return super.hashCode() + 31 * (changed ? 1 : 0);
    }
}