package com.github.steveice10.mc.protocol.data.game.chunk;

/**
 * @author TheElectronWill
 */
public final class BasicChunkSection implements ChunkSectionData {
	private final BlockStorage blocks;
	private final NibbleArray3d blocklight, skylight;

	public BasicChunkSection(BlockStorage blocks, NibbleArray3d blocklight, NibbleArray3d skylight) {
		this.blocks = blocks;
		this.blocklight = blocklight;
		this.skylight = skylight;
	}

	@Override
	public BlockStorage getBlocks() {
		return blocks;
	}

	@Override
	public NibbleArray3d getBlockLight() {
		return blocklight;
	}

	@Override
	public NibbleArray3d getSkyLight() {
		return skylight;
	}
}