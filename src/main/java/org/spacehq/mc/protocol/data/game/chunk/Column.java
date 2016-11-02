package org.spacehq.mc.protocol.data.game.chunk;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public class Column {
    private int x;
    private int z;
    private Chunk chunks[];
    private byte biomeData[];
    private CompoundTag tileEntities[];

    private boolean skylight;

    public Column(int x, int z, Chunk chunks[], CompoundTag tileEntities[]) {
        this(x, z, chunks, null, tileEntities);
    }

    public Column(int x, int z, Chunk chunks[], byte biomeData[], CompoundTag tileEntities[]) {
        if(chunks.length != 16) {
            throw new IllegalArgumentException("Chunk array length must be 16.");
        }

        if(biomeData != null && biomeData.length != 256) {
            throw new IllegalArgumentException("Biome data array length must be 256.");
        }

        this.skylight = false;
        boolean noSkylight = false;
        for (Chunk chunk : chunks) {
            if (chunk != null) {
                if (chunk.getSkyLight() == null) {
                    noSkylight = true;
                } else {
                    this.skylight = true;
                }
            }
        }

        if(noSkylight && this.skylight) {
            throw new IllegalArgumentException("Either all chunks must have skylight values or none must have them.");
        }

        this.x = x;
        this.z = z;
        this.chunks = chunks;
        this.biomeData = biomeData;
        this.tileEntities = tileEntities;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public Chunk[] getChunks() {
        return this.chunks;
    }

    public boolean hasBiomeData() {
        return this.biomeData != null;
    }

    public byte[] getBiomeData() {
        return this.biomeData;
    }

    public CompoundTag[] getTileEntities() {
        return this.tileEntities;
    }

    public boolean hasSkylight() {
        return this.skylight;
    }
}
