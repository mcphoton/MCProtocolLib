package com.github.steveice10.mc.protocol.data.game.chunk;

import com.github.steveice10.mc.protocol.util.NetUtil;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;

/**
 * @author TheElectronWill
 */
public final class BasicChunkData implements ChunkColumnData {
	private final int x, z;
	private final BasicChunkSection[] sections;
	private final byte[] biomeData;
	private final CompoundTag[] tileEntities;
	private final boolean skylight;

	public BasicChunkData(int x, int z, BasicChunkSection[] sections, byte[] biomeData,
						  CompoundTag[] tileEntities, boolean skylight) {
		this.x = x;
		this.z = z;
		this.sections = sections;
		this.biomeData = biomeData;
		this.tileEntities = tileEntities;
		this.skylight = skylight;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public BasicChunkSection[] getSections() {
		return sections;
	}

	@Override
	public byte[] getBiomeData() {
		return biomeData;
	}

	public CompoundTag[] getTileEntities() {
		return tileEntities;
	}

	@Override
	public void writeBlockEntitiesNBT(NetOutput out) throws IOException {
		out.writeVarInt(tileEntities.length);
		for (CompoundTag tileEntity : tileEntities) {
			NetUtil.writeNBT(out, tileEntity);
		}
	}

	@Override
	public boolean hasSkylight() {
		return skylight;
	}
}