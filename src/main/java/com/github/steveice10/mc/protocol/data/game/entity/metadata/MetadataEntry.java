package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * @author TheElectronWill
 */
public final class MetadataEntry extends MetadataValue {
    private final int id;

    public MetadataEntry(int id, MetadataType type, Object value) {
        super(type, value);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if (!(obj instanceof MetadataEntry)) {return false;}
        MetadataEntry other = (MetadataEntry)obj;
        return type == other.type && value.equals(other.value) && id == other.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * id;
    }
}