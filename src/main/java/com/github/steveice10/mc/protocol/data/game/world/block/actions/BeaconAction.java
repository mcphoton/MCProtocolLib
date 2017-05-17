package com.github.steveice10.mc.protocol.data.game.world.block.actions;

/**
 * @author TheElectronWill
 */
public enum BeaconAction implements BlockAction {
    RECALCULATE_BEAM {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public int getParam() {
            return 0;
        }
    }
}