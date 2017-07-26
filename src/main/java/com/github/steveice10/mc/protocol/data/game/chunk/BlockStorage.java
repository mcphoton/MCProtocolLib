package com.github.steveice10.mc.protocol.data.game.chunk;

import com.electronwill.collections.IntList;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;

public class BlockStorage {
    private static final int AIR = 0;

    private int bitsPerEntry;
    private IntList palette;// Maps indexes to full block ids
    private FlexibleStorage storage;

    public BlockStorage() {
        bitsPerEntry = 4;
        palette = new IntList(64);
        palette.add(AIR);
        storage = new FlexibleStorage(bitsPerEntry, 4096);
    }

    public BlockStorage(NetInput in) throws IOException {
        bitsPerEntry = in.readUnsignedByte();
        int idCount = in.readVarInt();
        int[] blockIds = new int[idCount];
        for (int i = 0; i < idCount; i++) {
            blockIds[i] = in.readVarInt();
        }
        palette = new IntList(blockIds);
        storage = new FlexibleStorage(bitsPerEntry, in.readLongs(in.readVarInt()));
    }

    public void write(NetOutput out) throws IOException {
        out.writeByte(bitsPerEntry);
        out.writeVarInt(palette.size());
        for (int i = 0; i < palette.size(); i++) {
            out.writeVarInt(palette.values()[i]);
        }
        long[] data = storage.getData();
        out.writeVarInt(data.length);
        out.writeLongs(data);
    }

    public int getBitsPerEntry() {
        return bitsPerEntry;
    }

    public IntList getPalette() {
        return palette;
    }

    public FlexibleStorage getStorage() {
        return storage;
    }

    public int get(int x, int y, int z) {
        int id = storage.get(index(x, y, z));
        if (bitsPerEntry <= 8) {
            return ((id >= 0) && (id < palette.size())) ? palette.get(id) : AIR;
        }
        return id;
    }

    public void set(int x, int y, int z, int blockFullId) {
        int id = bitsPerEntry <= 8 ? palette.indexOf(blockFullId) : blockFullId;
        if (id == -1) {// Not in the palette
            palette.add(blockFullId);
            if (palette.size() > (1 << bitsPerEntry)) {// We need more bits per entry
                bitsPerEntry++;

                IntList oldPalette = palette;
                if (bitsPerEntry > 8) {
                    oldPalette = palette.clone();
                    palette.clear();
                    bitsPerEntry = 13;
                }

                FlexibleStorage oldStorage = storage;
                int storageSize = storage.getSize();
                storage = new FlexibleStorage(bitsPerEntry, storageSize);
                for (int i = 0; i < storageSize; i++) {
                    if (bitsPerEntry <= 8) {
                        storage.set(i, oldStorage.get(i));
                    } else {
                        storage.set(i, oldPalette.get(i));
                    }
                }
            }
            id = (bitsPerEntry <= 8) ? palette.indexOf(blockFullId) : blockFullId;
        }
        storage.set(index(x, y, z), id);
    }

    public boolean isEmpty() {
        for (int index = 0; index < storage.getSize(); index++) {
            if (storage.get(index) != 0) {
                return false;
            }
        }
        return true;
    }

    private static int index(int x, int y, int z) {
        return (y << 8) | (z << 4) | x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof BlockStorage)) { return false; }
        BlockStorage other = (BlockStorage)o;
        return other.bitsPerEntry == bitsPerEntry
               && other.palette.equals(palette)
               && other.storage.equals(storage);
    }

    @Override
    public int hashCode() {
        int result = bitsPerEntry;
        result = 31 * result + palette.hashCode();
        result = 31 * result + storage.hashCode();
        return result;
    }
}