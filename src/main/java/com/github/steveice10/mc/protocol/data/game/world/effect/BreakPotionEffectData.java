package com.github.steveice10.mc.protocol.data.game.world.effect;

public final class BreakPotionEffectData implements WorldEffectData {
    private final int potionId;

    public BreakPotionEffectData(int potionId) {
        this.potionId = potionId;
    }

    /**
     * @return the potion's id
     */
    @Override
    public int getValue() {
        return potionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        return o instanceof BreakPotionEffectData
               && ((BreakPotionEffectData)o).potionId == potionId;
    }

    @Override
    public int hashCode() {
        return potionId;
    }
}