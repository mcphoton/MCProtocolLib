package com.github.steveice10.mc.protocol.data.game.world.effect;

public interface WorldEffect {
    default WorldEffectData readData(int value) {
        return null;
    }
}