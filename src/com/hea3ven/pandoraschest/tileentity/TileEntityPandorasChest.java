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

package com.hea3ven.pandoraschest.tileentity;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.FMLLog;

public class TileEntityPandorasChest extends TileEntityDecorativeChest {

	private int nextSelectedChest;
	public int selectedChest;
	private Random rand = new Random();

	public TileEntityPandorasChest() {
		super(27 * 5);

		openAnimation = new ResourceLocation("pandoraschest",
				"models/pandoras_chest_open.dae");
		closeAnimation = new ResourceLocation("pandoraschest",
				"models/pandoras_chest_close.dae");

		this.nextSelectedChest = 0;
		this.selectedChest = -1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.chestContents[getInventoryOffset() + slot];
	}

	@Override
	protected void setStackInSlot(int slot, ItemStack stack) {
		this.chestContents[getInventoryOffset() + slot] = stack;
	}

	private int getInventoryOffset() {
		return (this.selectedChest != -1) ? this.selectedChest * 27 : this.rand
				.nextInt(5) * 27;
	}

	@Override
	public void openInventory() {
		if (!this.worldObj.isRemote) {
			if (this.numUsingPlayers < 0)
				this.numUsingPlayers = 0;

			if (this.numUsingPlayers == 0) {
				this.selectedChest = this.nextSelectedChest;
				this.nextSelectedChest = this.rand.nextInt(5);
			}

			++this.numUsingPlayers;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 1, this.numUsingPlayers);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 2, this.selectedChest);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 3, this.nextSelectedChest);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType());
		} else {
			if (this.selectedChest == -1) {
				this.selectedChest = this.nextSelectedChest;
			}
		}
	}

	@Override
	public void closeInventory() {
		if (!this.worldObj.isRemote) {
			--this.numUsingPlayers;
			if (this.numUsingPlayers <= 0)
				this.selectedChest = -1;

			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 1, this.numUsingPlayers);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 2, this.selectedChest);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType());
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 2) {
			if (this.selectedChest != par2)
				FMLLog.warning(
						"SelectedChest received (%s) different from ours (%s).",
						par2, this.selectedChest);
			this.selectedChest = par2;
			return true;
		} else if (par1 == 3) {
			this.nextSelectedChest = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
	}

}
