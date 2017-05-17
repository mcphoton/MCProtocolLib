package com.github.steveice10.mc.protocol.data.game.world.effect;

public final class BreakBlockEffectData implements WorldEffectData {
    private final int blockId;

    public BreakBlockEffectData(int blockId) {
        this.blockId = blockId;
    }

    /**
     * @return the block's id
     */
    @Override
    public int getValue() {
        return blockId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        return o instanceof BreakBlockEffectData && ((BreakBlockEffectData)o).blockId == blockId;
    }

    @Override
    public int hashCode() {
        return blockId;
    }
}