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

package com.hea3ven.pandoraschest.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestCommonProxy;
import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.gui.GuiClayDrawer;
import com.hea3ven.pandoraschest.client.gui.GuiPandorasChest;
import com.hea3ven.pandoraschest.client.model.ModelManager;
import com.hea3ven.pandoraschest.client.renderer.DecorativeChestRenderer;
import com.hea3ven.pandoraschest.client.renderer.FluorecentSpotRenderer;
import com.hea3ven.pandoraschest.client.renderer.FluorecentTubeRenderer;
import com.hea3ven.pandoraschest.inventory.ContainerClayDrawer;
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
		PandorasChestMod.modelManager = new ModelManager();

		((IReloadableResourceManager) Minecraft.getMinecraft()
				.getResourceManager())
				.registerReloadListener(PandorasChestMod.modelManager);

		DecorativeChestRenderer decorativeChestRenderer = new DecorativeChestRenderer(
				"pandoraschest:models/clay_cabinet_open.dae",
				"pandoraschest:models/clay_cabinet_close.dae",
				"pandoraschest:textures/blocks/clay_cabinet.png");
		DecorativeChestRenderer pandorasChestRenderer = new DecorativeChestRenderer(
				"pandoraschest:models/pandoras_chest_open.dae",
				"pandoraschest:models/pandoras_chest_close.dae",
				"pandoraschest:textures/blocks/pandoras_chest.png");
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
			if (ID == 0) {
				TileEntityDecorativeChest te = (TileEntityDecorativeChest) tileEntity;
				return new ContainerClayDrawer(player.inventory, te,
						te.getUpperInventory(), te.getLeftInventory(),
						te.getRightInventory(), te.getBottomInventory());
			} else if (ID == 1) {
				return new ContainerPandorasChest(player.inventory,
						(TileEntityDecorativeChest) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityDecorativeChest) {
			if (ID == 0) {
				TileEntityDecorativeChest te = (TileEntityDecorativeChest) tileEntity;
				return new GuiClayDrawer(player.inventory, te,
						te.getUpperInventory(), te.getLeftInventory(),
						te.getRightInventory(), te.getBottomInventory());
			} else if (ID == 1) {
				return new GuiPandorasChest(player.inventory,
						((TileEntityDecorativeChest) tileEntity));
			}
		}
		return null;
	}

}
