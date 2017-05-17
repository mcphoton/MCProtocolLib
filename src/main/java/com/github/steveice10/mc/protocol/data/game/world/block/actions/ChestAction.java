package com.github.steveice10.mc.protocol.data.game.world.block.actions;

public final class ChestAction implements BlockAction {
    private final int viewers;

    public ChestAction(int viewers) {
        this.viewers = viewers;
    }

    /**
     * @return the numbers of player that are viewing the chest's content
     */
    @Override
    public int getParam() {
        return viewers;
    }

    @Override
    public int getId() {
        return 1;// There is only one id for ChestAction
    }
}