package com.hea3ven.pandoraschest.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.hea3ven.pandoraschest.client.model.ModelPandorasChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class TileEntityPandorasChestRenderer extends TileEntitySpecialRenderer {

	public static ModelPandorasChest modelChest = new ModelPandorasChest(
			"chest2_blender.dae");

	// public static ModelPandorasChest modelChest2 = new
	// ModelPandorasChest("/assets/PandorasChest/models/chest_open_3ds_ad.DAE");

	@Override
	// public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
	// double z, float f) {
	public void func_147500_a(TileEntity tileEntity, double x, double y,
			double z, float f) {
		TileEntityDecorativeChest tileEntityPandorasChest = (TileEntityDecorativeChest) tileEntity;
		// bindTexture(new ResourceLocation("pandoraschest",
		// "models/chest.png"));
		func_147499_a(new ResourceLocation("pandoraschest", "models/chest.png"));
		modelChest.render(tileEntityPandorasChest, x, y, z);
	}

}
