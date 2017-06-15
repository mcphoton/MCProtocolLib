package com.github.steveice10.mc.protocol.data.game.chunk;

/**
 * Interface for accessing the raw data of a chunk section (16x16x16 blocks).
 */
public interface ChunkSectionData {
	/**
	 * @return the BlockStorage containing the block's IDs in the protocol format
	 */
	BlockStorage getBlocks();

	/**
	 * @return the blocklight data, 1 byte per block, 4096 in total
	 */
	NibbleArray3d getBlockLight();

	/**
	 * @return the skylight data, 1 byte per block, 4096 in total. May be null if the chunk
	 * column has no skylight data (for example in the nether).
	 */
	NibbleArray3d getSkyLight();
}