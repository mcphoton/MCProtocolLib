package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * An entity metadata entry, with an index, a type and a value.
 *
 * @author TheElectronWill
 */
public final class MetadataEntry implements MetadataValue {
    private final MetadataType type;
    private final Object value;
    private final int index;

    public MetadataEntry(int index, MetadataType type, Object value) {
        this.index = index;
        this.type = type;
        this.value = value;
    }

    public MetadataEntry(int index, MetadataValue metadataValue) {
        this(index, metadataValue.getType(), metadataValue.getValue());
    }

    public int getIndex() {
        return index;
    }

    @Override
    public MetadataType getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (!(obj instanceof MetadataEntry)) {return false;}
        MetadataEntry other = (MetadataEntry)obj;
        return type == other.type && value.equals(other.value) && index == other.index;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + index;
        return result;
    }
}