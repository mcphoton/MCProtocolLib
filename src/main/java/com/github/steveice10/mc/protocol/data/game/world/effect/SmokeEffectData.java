package com.github.steveice10.mc.protocol.data.game.world.effect;

public enum SmokeEffectData implements WorldEffectData {
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    EAST,
    UP,
    WEST,
    NORTH_EAST,
    NORTH,
    NORTH_WEST;

    /**
     * @return the smoke's direction
     */
    @Override
    public int getValue() {
        return ordinal();
    }
}