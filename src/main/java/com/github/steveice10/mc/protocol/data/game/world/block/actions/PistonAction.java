package com.github.steveice10.mc.protocol.data.game.world.block.actions;

public final class PistonAction implements BlockAction {
    private final Type actionType;
    private final Direction direction;

    public PistonAction(Type actionType, Direction direction) {
        this.actionType = actionType;
        this.direction = direction;
    }

    @Override
    public int getId() {
        return actionType.ordinal();
    }

    @Override
    public int getParam() {
        return direction.ordinal();
    }

    public enum Type {
        PUSHING, PULLING
    }

    public enum Direction {
        DOWN, UP, SOUTH, WEST, NORTH, EAST
    }
}