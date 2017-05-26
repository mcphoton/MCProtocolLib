package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;
import java.util.Collection;

/**
 * Stores metadata entries.
 *
 * @author TheElectronWill
 */
public interface MetadataStorage {
    /**
     * @param index the entry's index
     * @return the MetadataEntry that has the given index.
     */
    MetadataEntry getEntry(int index);

    /**
     * @return a collection containing all the entries in this storage
     */
    Collection<MetadataEntry> getAllEntries();

    /**
     * Puts a copy of all the storage's entries in the given collection
     *
     * @param destination where to put the entries
     */
    void getAllEntries(Collection<MetadataEntry> destination);

    /**
     * Writes the metadata entries.
     *
     * @param out the output to write to
     * @throws IOException if an I/O error occurs
     */
    void write(NetOutput out) throws IOException;
}