package com.github.steveice10.mc.protocol.data.game.entity.metadata;

public final class IntPosition {
    private final int x, y, z;

    public IntPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof IntPosition)) { return false; }
        IntPosition other = (IntPosition)o;
        return other.x == x && other.y == y && other.z == z;
    }

    @Override
    public int hashCode() {
        return z + 31 * (y + 31 * x);
    }

    @Override
    public String toString() {
        return "IntPosition(" + x + "," + y + "," + z + ")";
    }
}