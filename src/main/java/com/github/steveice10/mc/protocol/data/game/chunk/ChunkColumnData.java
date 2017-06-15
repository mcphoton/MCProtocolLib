package com.github.steveice10.mc.protocol.data.game.chunk;

import com.github.steveice10.opennbt.tag.builtin.CompoundTag;

/**
 * Interface for accessing the raw data of a chunk column (16 vertically aligned sections,
 * 16x256x16 blocks in total).
 */
public interface ChunkColumnData {
	/**
	 * @return the chunk's X coordinate
	 */
	int getX();

	/**
	 * @return the chunk's Z coordinate
	 */
	int getZ();

	/**
	 * @return the array containing the 16 chunk sections
	 */
	<C extends ChunkSectionData> C[] getSections();

	/**
	 * @return the biomes data, one byte per biome, 256 in total
	 */
	byte[] getBiomeData();

	/**
	 * @return the tile entities' data
	 */
	CompoundTag[] getTileEntities();

	/**
	 * @return {@code true} if this ChunkColumn stores some information about the skylight
	 */
	boolean hasSkylight();
}