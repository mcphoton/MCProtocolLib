package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * A "metadata value" with a type and a value.
 *
 * @author TheElectronWill
 */
public interface MetadataValue {
    MetadataType getType();

    Object getValue();
}