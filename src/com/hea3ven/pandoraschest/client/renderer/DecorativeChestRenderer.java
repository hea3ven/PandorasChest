package com.hea3ven.pandoraschest.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.model.ModelDecorativeChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class DecorativeChestRenderer extends TileEntitySpecialRenderer
		implements ISimpleBlockRenderingHandler {

	private ModelDecorativeChest model;
	private ResourceLocation textureResource;

	public DecorativeChestRenderer(String openAnimationResourceName,
			String closeAnimationResourceName, String textureResourceName) {
		model = new ModelDecorativeChest(openAnimationResourceName,
				closeAnimationResourceName);
		textureResource = new ResourceLocation(textureResourceName);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		bindTexture(textureResource);
		model.renderItem();
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

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float f) {
		TileEntityDecorativeChest te = (TileEntityDecorativeChest) tileEntity;
		bindTexture(textureResource);

		model.render(te, x, y, z);
	}

}
