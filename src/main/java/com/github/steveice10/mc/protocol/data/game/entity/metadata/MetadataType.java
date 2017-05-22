package com.github.steveice10.mc.protocol.data.game.entity.metadata;

public enum MetadataType {
    BYTE,
    VARINT,
    FLOAT,
    STRING,
    CHAT,
    ITEM_STACK,
    BOOLEAN,
    ROTATION,
    POSITION,
    OPTIONAL_POSITION,
    BLOCK_FACE,
    OPTIONAL_UUID,
    OPTIONAL_BLOCK_ID;

    public int getId() {
        return ordinal();
    }
}