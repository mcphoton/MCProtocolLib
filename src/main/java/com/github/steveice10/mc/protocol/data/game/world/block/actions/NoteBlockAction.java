package com.github.steveice10.mc.protocol.data.game.world.block.actions;

public final class NoteBlockAction implements BlockAction {
    private final NoteBlockInstrument instrument;
    private final int notePitch;

    public NoteBlockAction(NoteBlockInstrument instrument, int notePitch) {
        if (notePitch < 0 || notePitch > 24) {
            throw new IllegalArgumentException("Pitch must be between 0 and 24.");
        }
        this.instrument = instrument;
        this.notePitch = notePitch;
    }

    @Override
    public int getId() {
        return instrument.ordinal();
    }

    @Override
    public int getParam() {
        return notePitch;
    }
}