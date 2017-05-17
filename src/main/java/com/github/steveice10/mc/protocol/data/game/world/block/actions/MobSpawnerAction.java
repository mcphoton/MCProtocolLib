package com.github.steveice10.mc.protocol.data.game.world.block.actions;

public enum MobSpawnerAction implements BlockAction {
    RESET_DELAY {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public int getParam() {
            return 0;
        }
    };
}