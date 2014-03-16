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

package com.hea3ven.pandoraschest.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.model.ModelFluorecentTube;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FluorecentTubeRenderer extends TileEntitySpecialRenderer implements
		ISimpleBlockRenderingHandler {
	private ModelFluorecentTube model = new ModelFluorecentTube();

	// private void bindTexture(ResourceLocation resourceLocation) {
	// FMLClientHandler.instance().getClient().renderEngine
	// .bindTexture(resourceLocation);
	// }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		bindTexture(new ResourceLocation("pandoraschest",
				"textures/blocks/fluorecent_tube.png"));
		model.renderItem();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return PandorasChestMod.fluorecentSpotRenderId;
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float var8) {
		TileEntityFluorecentTubeBlock tubeTE = (TileEntityFluorecentTubeBlock) te;
		if (tubeTE.isMaster()) {
			bindTexture(new ResourceLocation("pandoraschest",
					"textures/blocks/fluorecent_tube.png"));
			model.render(tubeTE, x, y, z);
		}

	}

}
