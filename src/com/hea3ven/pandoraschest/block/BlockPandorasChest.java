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

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

public class BlockPandorasChest extends BlockBaseChest {

	public BlockPandorasChest() {
		super();
		setBlockName("pandorasChest");
		setBlockTextureName("pandoraschest:pandoras_chest");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityPandorasChest tileEntity = new TileEntityPandorasChest();
		return tileEntity;
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.pandorasChestRenderId;
	}

	@Override
	public int getGuiId() {
		return 1;
	}

	@Override
	public ResourceLocation getTexture(int meta) {
		return new ResourceLocation("pandoraschest", "textures/blocks/pandoras_chest.png");
	}

}
