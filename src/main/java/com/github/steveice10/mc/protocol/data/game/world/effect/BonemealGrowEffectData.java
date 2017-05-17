package com.github.steveice10.mc.protocol.data.game.world.effect;

public final class BonemealGrowEffectData implements WorldEffectData {
    private final int particleCount;

    public BonemealGrowEffectData(int particleCount) {
        this.particleCount = particleCount;
    }

    /**
     * @return the particle's count
     */
    @Override
    public int getValue() {
        return particleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        return o instanceof BonemealGrowEffectData
               && ((BonemealGrowEffectData)o).particleCount == particleCount;
    }

    @Override
    public int hashCode() {
        return particleCount;
    }
}