package com.hea3ven.pandoraschest.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.model.ModelPandorasChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TileEntityPandorasChestRenderer extends TileEntitySpecialRenderer
		implements ISimpleBlockRenderingHandler {

	public static ModelPandorasChest modelChest = new ModelPandorasChest(
			"chest2_blender.dae");

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float f) {
		TileEntityDecorativeChest tileEntityPandorasChest = (TileEntityDecorativeChest) tileEntity;
		bindTexture(new ResourceLocation("pandoraschest", "models/chest.png"));
		modelChest.render(tileEntityPandorasChest, x, y, z);
	}

	// private void bindTexture(ResourceLocation resourceLocation)
	// {
	// FMLClientHandler.instance().getClient().renderEngine.bindTexture(resourceLocation);
	// }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		bindTexture(new ResourceLocation("pandoraschest", "models/chest.png"));
		modelChest.renderItem();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return PandorasChestMod.pandorasChestRenderId;
	}

}
