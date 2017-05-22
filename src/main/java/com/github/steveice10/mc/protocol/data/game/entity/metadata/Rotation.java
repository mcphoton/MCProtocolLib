package com.github.steveice10.mc.protocol.data.game.entity.metadata;

/**
 * A rotation with 3 components: pitch, yaw, roll.
 */
public final class Rotation {
    private final float pitch, yaw, roll;

    /**
     * Creates a new Rotation(0,0,0).
     */
    public Rotation() {
        this(0, 0, 0);
    }

    public Rotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getRoll() {
        return this.roll;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Rotation)) {return false;}
        Rotation r = (Rotation)o;
        return (pitch == r.pitch) && (yaw == r.yaw) && (roll == r.roll);
    }

    @Override
    public int hashCode() {
        int result = Float.floatToIntBits(this.pitch);
        result = 31 * result + Float.floatToIntBits(this.yaw);
        result = 31 * result + Float.floatToIntBits(this.roll);
        return result;
    }
}