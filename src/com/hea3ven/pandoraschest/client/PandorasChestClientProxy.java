package com.hea3ven.pandoraschest.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestCommonProxy;
import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.gui.GuiPandorasChest;
import com.hea3ven.pandoraschest.client.renderer.DecorativeChestRenderer;
import com.hea3ven.pandoraschest.client.renderer.FluorecentSpotRenderer;
import com.hea3ven.pandoraschest.client.renderer.FluorecentTubeRenderer;
import com.hea3ven.pandoraschest.inventory.ContainerPandorasChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class PandorasChestClientProxy extends PandorasChestCommonProxy
		implements IGuiHandler {

	@Override
	public void registerGuiHandlers(Object mod) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, this);
	};

	@Override
	public void registerRenderers() {
		DecorativeChestRenderer decorativeChestRenderer = new DecorativeChestRenderer(
				"pandoraschest:models/decorative_chest_open.dae",
				"pandoraschest:models/decorative_chest_close.dae",
				"pandoraschest:models/decorative_chest.png");
		DecorativeChestRenderer pandorasChestRenderer = new DecorativeChestRenderer(
				"pandoraschest:models/pandoras_chest_open.dae",
				"pandoraschest:models/pandoras_chest_close.dae",
				"pandoraschest:models/pandoras_chest.png");
		FluorecentSpotRenderer fluorecentRenderer = new FluorecentSpotRenderer();
		FluorecentTubeRenderer fluorecentTubeRenderer = new FluorecentTubeRenderer();

		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityPandorasChest.class, pandorasChestRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityDecorativeChest.class, decorativeChestRenderer);
		PandorasChestMod.pandorasChestRenderId = RenderingRegistry
				.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(
				PandorasChestMod.pandorasChestRenderId, pandorasChestRenderer);
		PandorasChestMod.decorativeChestRenderId = RenderingRegistry
				.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(
				PandorasChestMod.decorativeChestRenderId,
				decorativeChestRenderer);

		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityFluorecentBlock.class, fluorecentRenderer);
		PandorasChestMod.fluorecentSpotRenderId = RenderingRegistry
				.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(
				PandorasChestMod.fluorecentSpotRenderId, fluorecentRenderer);

		ClientRegistry.bindTileEntitySpecialRenderer(
				TileEntityFluorecentTubeBlock.class, fluorecentTubeRenderer);
		PandorasChestMod.fluorecentTubeRenderId = RenderingRegistry
				.getNextAvailableRenderId();
		RenderingRegistry
				.registerBlockHandler(PandorasChestMod.fluorecentTubeRenderId,
						fluorecentTubeRenderer);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityDecorativeChest) {
			return new ContainerPandorasChest(player.inventory,
					(TileEntityDecorativeChest) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityDecorativeChest) {
			return new GuiPandorasChest(player.inventory,
					((TileEntityDecorativeChest) tileEntity));
		}
		return null;
	}

}
