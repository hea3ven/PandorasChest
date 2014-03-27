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

package com.hea3ven.pandoraschest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.inventory.ContainerPandorasChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityBaseChest;

public class GuiPandorasChest extends GuiContainer {

	private static final ResourceLocation field_110421_t = new ResourceLocation(
			"textures/gui/container/generic_54.png");
	private IInventory playerInventory;
	private TileEntityBaseChest chestInventory;

	public GuiPandorasChest(IInventory playerInventory,
			TileEntityBaseChest chestInventory) {
		super(new ContainerPandorasChest(playerInventory, chestInventory));
		this.playerInventory = playerInventory;
		this.chestInventory = chestInventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRendererObj
				.drawString(
						this.chestInventory.hasCustomInventoryName() ? this.chestInventory
								.getInventoryName() : I18n.format(
								this.chestInventory.getInventoryName(),
								new Object[0]), 8, 6, 4210752);
		// this.fontRenderer.drawString(((Integer)this.chestInventory.selectedChest).toString(),
		// 140, 6, 4210752);
		this.fontRendererObj
				.drawString(
						this.playerInventory.hasCustomInventoryName() ? this.playerInventory
								.getInventoryName() : I18n.format(
								this.playerInventory.getInventoryName(),
								new Object[0]), 8, this.ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(field_110421_t);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
		this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
	}

}
