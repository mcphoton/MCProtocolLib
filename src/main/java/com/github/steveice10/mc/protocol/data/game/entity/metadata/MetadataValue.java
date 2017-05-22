package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * @author TheElectronWill
 */
public class MetadataValue {
    protected MetadataType type;
    protected Object value;

    public MetadataValue(MetadataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public MetadataType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + 31 * value.hashCode();
    }
}