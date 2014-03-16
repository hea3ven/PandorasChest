/**
 * 
 * Copyright (c) 2014 Hea3veN
 * 
 *  This file is part of Pandora's Chest.
 *
 *  Pandora's Chest is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pandora's Chest is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Pandora's Chest.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.hea3ven.pandoraschest.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;

public class BlockFluorecentTube extends BlockFluorecentSpot {

	public BlockFluorecentTube() {
		super();
		this.setBlockName("fluorecentTube");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityFluorecentTubeBlock();
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.fluorecentTubeRenderId;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y,
			int z) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEntityFluorecentTubeBlock te = (TileEntityFluorecentTubeBlock) world
				.getTileEntity(x, y, z);
		float minX = 0.0f;
		float minY = 0.0f;
		float minZ = 0.0f;
		float maxX = 1.0f;
		float maxY = 1.0f;
		float maxZ = 1.0f;
		if (meta == 0) {
			minY = 0.75f;
			minX = minZ = 0.25f;
			maxX = maxZ = 0.75f;
		} else {
			minY = 0.25f;
			maxY = 0.75f;
			if (meta == 1) {
				maxX = 0.25f;
				minZ = 0.25f;
				maxZ = 0.75f;
			}
			if (meta == 2) {
				minX = 0.25f;
				maxX = 0.75f;
				minZ = 0.75f;
			}
			if (meta == 3) {
				minX = 0.75f;
				minZ = 0.25f;
				maxZ = 0.75f;
			}
			if (meta == 4) {
				minX = 0.25f;
				maxX = 0.75f;
				maxZ = 0.25f;
			}
		}
		if (te.getXOffset() < 0)
			minX = 0.0f;
		if (te.getXOffset() > 0)
			maxX = 1.0f;
		if (te.getYOffset() < 0)
			minY = 0.0f;
		if (te.getYOffset() > 0)
			maxY = 1.0f;
		if (te.getZOffset() < 0)
			minZ = 0.0f;
		if (te.getZOffset() > 0)
			maxZ = 1.0f;

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int metadata) {
		return metadata;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block,
			int p_149749_6_) {
		TileEntityFluorecentTubeBlock te = (TileEntityFluorecentTubeBlock) world
				.getTileEntity(x, y, z);
		world.setBlockToAir(x + te.getXOffset(), y + te.getYOffset(),
				z + te.getZOffset());
		super.breakBlock(world, x, y, z, block, p_149749_6_);

	}

	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}
}
