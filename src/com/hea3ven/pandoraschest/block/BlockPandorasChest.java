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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

public class BlockPandorasChest extends BlockDecorativeChest {

	public BlockPandorasChest() {
		super();
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockName("pandorasChest");
		setBlockTextureName("pandoraschest:pandoras_chest");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityPandorasChest tileEntity = new TileEntityPandorasChest();
		return tileEntity;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}

			player.openGui(PandorasChestMod.instance, 1, world, x, y, z);
			return true;
		}
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.pandorasChestRenderId;
	}

}
