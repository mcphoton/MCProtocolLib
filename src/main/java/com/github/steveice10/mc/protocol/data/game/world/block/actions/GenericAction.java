package com.github.steveice10.mc.protocol.data.game.world.block.actions;

public final class GenericAction implements BlockAction {
    private final int type, param;

    public GenericAction(int type, int param) {
        this.type = type;
        this.param = param;
    }

    @Override
    public int getParam() {
        return this.param;
    }

    @Override
    public int getId() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GenericAction && this.param == ((GenericAction) o).param;
    }

    @Override
    public int hashCode() {
        return this.param;
    }
}