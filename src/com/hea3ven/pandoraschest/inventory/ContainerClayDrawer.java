package com.hea3ven.pandoraschest.inventory;

import java.util.LinkedList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ContainerClayDrawer extends Container {

	private LinkedList<TileEntityDecorativeChest> chests;

	public ContainerClayDrawer(InventoryPlayer playerInventory,
			TileEntityDecorativeChest chestInventory,
			TileEntityDecorativeChest topInventory,
			TileEntityDecorativeChest leftInventory,
			TileEntityDecorativeChest rightInventory,
			TileEntityDecorativeChest bottomInventory) {
		chests = new LinkedList<TileEntityDecorativeChest>();
		chests.add(chestInventory);
		if (topInventory != null)
			chests.add(topInventory);
		if (leftInventory != null)
			chests.add(leftInventory);
		if (rightInventory != null)
			chests.add(rightInventory);
		if (bottomInventory != null)
			chests.add(bottomInventory);

		for (TileEntityDecorativeChest chest : chests) {
			chest.openInventory();
		}

		int j;
		int k;
		int middleXOffset = 8;
		if (leftInventory != null)
			middleXOffset += 9 * 18 + 3 + 7;
		int yOffset = 18;

		if (topInventory != null) {
			for (j = 0; j < 3; ++j) {
				for (k = 0; k < 9; ++k) {
					this.addSlotToContainer(new Slot(topInventory, k + j * 9,
							middleXOffset + k * 18, yOffset + j * 18));
				}
			}
			yOffset += 3 * 18 + 13;
		}

		if (leftInventory != null) {
			for (j = 0; j < 3; ++j) {
				for (k = 0; k < 9; ++k) {
					this.addSlotToContainer(new Slot(leftInventory, k + j * 9,
							8 + k * 18, yOffset + j * 18));
				}
			}
		}

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(chestInventory, k + j * 9,
						middleXOffset + k * 18, yOffset + j * 18));
			}
		}

		if (rightInventory != null) {
			for (j = 0; j < 3; ++j) {
				for (k = 0; k < 9; ++k) {
					this.addSlotToContainer(new Slot(rightInventory, k + j * 9,
							middleXOffset + 9 * 18 + 10 + k * 18, yOffset + j
									* 18));
				}
			}
		}

		yOffset += 3 * 18 + 13;

		if (bottomInventory != null) {
			for (j = 0; j < 3; ++j) {
				for (k = 0; k < 9; ++k) {
					this.addSlotToContainer(new Slot(bottomInventory,
							k + j * 9, middleXOffset + k * 18, yOffset + j * 18));
				}
			}
			yOffset += 3 * 18 + 13;
		}

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(playerInventory,
						k + j * 9 + 9, middleXOffset + k * 18, yOffset + j * 18));
			}
		}
		yOffset += 3 * 18 + 4;

		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(playerInventory, j, middleXOffset
					+ j * 18, yOffset));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		for (TileEntityDecorativeChest chest : chests) {
			chest.closeInventory();
		}

	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < 27) {
				if (!this.mergeItemStack(itemstack1, 27,
						this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

}
