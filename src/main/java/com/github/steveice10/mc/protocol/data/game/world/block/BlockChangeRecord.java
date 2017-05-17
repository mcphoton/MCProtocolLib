package com.github.steveice10.mc.protocol.data.game.world.block;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.IntPosition;
import java.util.Objects;

public final class BlockChangeRecord {
    private final IntPosition position;
    private final int blockId;

    public BlockChangeRecord(IntPosition position, int blockId) {
        this.position = Objects.requireNonNull(position, "The position must not be null.");
        this.blockId = blockId;
    }

    /**
     * @return the blockId's position
     */
    public IntPosition getPosition() {
        return position;
    }

    /**
     * @return the blockId's "full" id
     */
    public int getBlockId() {
        return blockId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof BlockChangeRecord)) { return false; }
        BlockChangeRecord other = (BlockChangeRecord)o;
        return (other.blockId == blockId) && other.position.equals(position);
    }

    @Override
    public int hashCode() {
        return blockId + 31 * position.hashCode();
    }
}