package com.hea3ven.pandoraschest.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.model.ModelFluorecentSpot;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FluorecentSpotRenderer extends TileEntitySpecialRenderer implements
		ISimpleBlockRenderingHandler {
	private ModelFluorecentSpot model = new ModelFluorecentSpot();

	// private void bindTexture(ResourceLocation resourceLocation) {
	// FMLClientHandler.instance().getClient().renderEngine
	// .bindTexture(resourceLocation);
	// }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		bindTexture(new ResourceLocation("pandoraschest", "models/fluorecent_spot.png"));
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
		bindTexture(new ResourceLocation("pandoraschest", "models/fluorecent_spot.png"));
		model.render((TileEntityFluorecentBlock) te, x, y, z);

	}

}
