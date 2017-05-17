package com.github.steveice10.mc.protocol.data.game.world.effect;

public enum ParticleEffect implements WorldEffect {
    SMOKE {
        @Override
        public WorldEffectData readData(int value) {
            return SmokeEffectData.values()[value];
        }
    },
    BREAK_BLOCK {
        @Override
        public WorldEffectData readData(int value) {
            return new BreakBlockEffectData(value);
        }
    },
    BREAK_SPLASH_POTION {
        @Override
        public WorldEffectData readData(int value) {
            return new BreakPotionEffectData(value);
        }
    },
    BREAK_EYE_OF_ENDER,
    MOB_SPAWN,
    BONEMEAL_GROW {
        @Override
        public WorldEffectData readData(int value) {
            return new BonemealGrowEffectData(value);
        }
    },
    ENDERDRAGON_FIREBALL_EXPLODE,
    END_GATEWAY_SPAWN;
}
