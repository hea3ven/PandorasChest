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
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.inventory.ContainerClayDrawer;
import com.hea3ven.pandoraschest.tileentity.TileEntityBaseChest;

public class GuiClayDrawer extends GuiContainer {

	private static final ResourceLocation field_110421_t = new ResourceLocation(
			"pandoraschest:textures/gui/container/clay_drawer.png");
	private IInventory playerInventory;
	private TileEntityBaseChest chestInventory;
	private TileEntityBaseChest topInventory;
	private TileEntityBaseChest leftInventory;
	private TileEntityBaseChest rightInventory;
	private TileEntityBaseChest bottomInventory;

	public GuiClayDrawer(InventoryPlayer playerInventory,
			TileEntityBaseChest chestInventory,
			TileEntityBaseChest topInventory,
			TileEntityBaseChest leftInventory,
			TileEntityBaseChest rightInventory,
			TileEntityBaseChest bottomInventory) {
		super(new ContainerClayDrawer(playerInventory, chestInventory,
				topInventory, leftInventory, rightInventory, bottomInventory));
		this.playerInventory = playerInventory;
		this.chestInventory = chestInventory;
		this.topInventory = topInventory;
		this.leftInventory = leftInventory;
		this.rightInventory = rightInventory;
		this.bottomInventory = bottomInventory;

		ySize = 3;
		if (topInventory != null)
			ySize += 14 + 3 * 18;
		ySize += 14 + 3 * 18;
		if (bottomInventory != null)
			ySize += 14 + 3 * 18;
		ySize += 14 + 3 * 18 + 4 + 18 + 7;

		xSize = 7;
		if (leftInventory != null)
			xSize += 18 * 9 + 4;
		xSize += 18 * 9;
		if (rightInventory != null)
			xSize += 4 + 18 * 9;
		xSize += 7;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		int yOffset = 3;
		int middleXOffset = (leftInventory != null) ? 7 + 18 * 9 + 4 : 7;
		if (topInventory != null) {
			String name;
			if (topInventory.hasCustomInventoryName())
				name = topInventory.getInventoryName();
			else
				name = I18n.format(topInventory.getInventoryName(),
						new Object[0]);
			fontRendererObj.drawString(name, middleXOffset + 6, yOffset + 3,
					4210752);
			yOffset += 14 + 3 * 18;
		}
		if (leftInventory != null) {
			String name;
			if (leftInventory.hasCustomInventoryName())
				name = leftInventory.getInventoryName();
			else
				name = I18n.format(leftInventory.getInventoryName(),
						new Object[0]);
			this.fontRendererObj.drawString(name, 8, yOffset + 3, 4210752);
		}
		{
			String name;
			if (chestInventory.hasCustomInventoryName())
				name = chestInventory.getInventoryName();
			else
				name = I18n.format(chestInventory.getInventoryName(),
						new Object[0]);
			fontRendererObj.drawString(name, middleXOffset + 6, yOffset + 3,
					4210752);
		}
		if (rightInventory != null) {
			String name;
			if (rightInventory.hasCustomInventoryName())
				name = rightInventory.getInventoryName();
			else
				name = I18n.format(rightInventory.getInventoryName(),
						new Object[0]);
			fontRendererObj.drawString(name,
					middleXOffset + 4 + 18 * 9 + 4 + 8, yOffset + 3, 4210752);
		}
		yOffset += 14 + 3 * 18;
		if (bottomInventory != null) {
			String name;
			if (bottomInventory.hasCustomInventoryName())
				name = bottomInventory.getInventoryName();
			else
				name = I18n.format(bottomInventory.getInventoryName(),
						new Object[0]);
			fontRendererObj.drawString(name, middleXOffset + 6, yOffset + 3,
					4210752);
			yOffset += 14 + 3 * 18;
		}
		{
			String name;
			if (playerInventory.hasCustomInventoryName())
				name = playerInventory.getInventoryName();
			else
				name = I18n.format(playerInventory.getInventoryName(),
						new Object[0]);
			fontRendererObj.drawString(name, middleXOffset + 6, yOffset + 2,
					4210752);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(field_110421_t);
		int xOffset = (this.width - this.xSize) / 2;
		int middleXOffset = xOffset;
		if (leftInventory != null) {
			middleXOffset += 7 + 18 * 9 + 3;
		}
		int yOffset = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(middleXOffset, yOffset, 0, 0,
				7 + 18 * 9 + 7, 4);
		yOffset += 4;
		if (topInventory != null) {
			this.drawTexturedModalRect(middleXOffset, yOffset, 0, 4,
					7 + 18 * 9 + 7, 13 + 3 * 18);
			yOffset += 13 + 3 * 18;
		}

		this.drawTexturedModalRect(middleXOffset, yOffset, 0, 4,
				7 + 18 * 9 + 7, 13 + 3 * 18);
		int middleYOffset = yOffset;
		yOffset += 13 + 3 * 18;
		if (bottomInventory != null) {
			this.drawTexturedModalRect(middleXOffset, yOffset, 0, 4,
					7 + 18 * 9 + 7, 13 + 3 * 18);
			yOffset += 13 + 3 * 18;
		}
		this.drawTexturedModalRect(middleXOffset, yOffset, 0, 78,
				7 + 18 * 9 + 7, 14 + 3 * 18 + 4 + 18 + 7);

		if (leftInventory != null) {
			this.drawTexturedModalRect(xOffset, middleYOffset - 4, 0, 0, 4,
					13 + 3 * 18 + 11);
			this.drawTexturedModalRect(xOffset + 4, middleYOffset - 4, 4, 176,
					3 + 18 * 9 + 7, 13 + 3 * 18 + 12);
			if (topInventory == null)
				this.drawTexturedModalRect(middleXOffset - 4,
						middleYOffset - 4, 8, 0, 8, 3);

		}

		if (rightInventory != null) {
			this.drawTexturedModalRect(middleXOffset + 3 + 18 * 9 + 7,
					middleYOffset - 4, 0, 176, 3 + 18 * 9 + 7, 13 + 3 * 18 + 11);
			this.drawTexturedModalRect(middleXOffset + 3 + 18 * 9 + 7 + 4 + 18
					* 9 + 5, middleYOffset - 4, 171, 0, 4, 13 + 3 * 18 + 11);
			if (topInventory == null)
				this.drawTexturedModalRect(middleXOffset + 3 + 18 * 9 + 4,
						middleYOffset - 4, 8, 0, 8, 3);
		}

	}

}
