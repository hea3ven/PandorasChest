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

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;

public class BlockFluorecentSpot extends Block implements ITileEntityProvider {

	public BlockFluorecentSpot() {
		super(Material.rock);

		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockName("fluorecentSpot");
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGlass);
		this.setLightLevel(1.0F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.fluorecentSpotRenderId;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityFluorecentBlock();
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int metadata) {
		if (side == 0 && world.isSideSolid(x, y + 1, z, DOWN, false)) {
			metadata = 0;
		}

		if (side == 5 && world.isSideSolid(x - 1, y, z, EAST, true)) {
			metadata = 1;
		}

		if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH, true)) {
			metadata = 2;
		}

		if (side == 4 && world.isSideSolid(x + 1, y, z, WEST, true)) {
			metadata = 3;
		}

		if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH, true)) {
			metadata = 4;
		}

		return metadata;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.isSideSolid(x - 1, y, z, EAST, true)
				|| world.isSideSolid(x + 1, y, z, WEST, true)
				|| world.isSideSolid(x, y, z - 1, SOUTH, true)
				|| world.isSideSolid(x, y, z + 1, NORTH, true)
				|| world.isSideSolid(x, y + 1, z, DOWN, true);
	}

	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if(meta == 0)
    		this.setBlockBounds(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
    	if(meta == 1)
    		this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
    	if(meta == 2)
    		this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
    	if(meta == 3)
    		this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
    	if(meta == 4)
    		this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
    }

	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}
}
