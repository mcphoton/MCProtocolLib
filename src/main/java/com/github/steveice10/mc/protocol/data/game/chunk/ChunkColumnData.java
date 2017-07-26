package com.github.steveice10.mc.protocol.data.game.chunk;

import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;

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
	ChunkSectionData[] getSections();

	/**
	 * @return the biomes data, one byte per biome, 256 in total
	 */
	byte[] getBiomeData();

	/**
	 * Writes the NBT data of the block entities (= tile entities).
	 * <p>
	 * This method must write the number of block entities, as a varint, and then the NBT tag
	 * of each entity.
	 */
	void writeBlockEntitiesNBT(NetOutput out) throws IOException;

	/**
	 * @return {@code true} if this ChunkColumn stores some information about the skylight
	 */
	boolean hasSkylight();
}