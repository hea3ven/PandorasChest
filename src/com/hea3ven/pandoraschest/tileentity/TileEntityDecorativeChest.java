package com.hea3ven.pandoraschest.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.hea3ven.pandoraschest.client.renderer.tileentity.TileEntityPandorasChestRenderer;

public class TileEntityDecorativeChest extends TileEntity implements IInventory {
	protected ItemStack[] chestContents;

	private String customName;

	public int numUsingPlayers;

	private int animationFrame;

	public TileEntityDecorativeChest() {
		this(27);
	}

	public TileEntityDecorativeChest(int slotsNum) {
		chestContents = new ItemStack[slotsNum];
		numUsingPlayers = 0;
		animationFrame = 0;
		customName = null;
	}

	@Override
	public int getSizeInventory() {
		// 5 * 3 * 9 = 135
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.chestContents[slot];
	}

	protected void setStackInSlot(int slot, ItemStack stack) {
		this.chestContents[slot] = stack;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {

		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {

			if (itemStack.stackSize <= amount) {
				setStackInSlot(slot, null);
				this.markDirty();
				return itemStack;
			} else {
				ItemStack returnItemStack = itemStack.splitStack(amount);

				if (itemStack.stackSize == 0)
					setStackInSlot(slot, null);

				this.markDirty();
				return returnItemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			setStackInSlot(slot, null);
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		setStackInSlot(slot, itemStack);

		if (itemStack != null
				&& itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	// public String getInvName() {
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName
				: "container.pandoraschest";
	}

	@Override
	// public boolean isInvNameLocalized() {
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setChestGuiName(String par1Str) {
		this.customName = par1Str;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		if (!this.worldObj.isRemote) {
			if (this.numUsingPlayers < 0)
				this.numUsingPlayers = 0;

			TileEntityPandorasChestRenderer.modelChest.reloadModel();

			++this.numUsingPlayers;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 1, this.numUsingPlayers);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType());
		}
	}

	@Override
	public void closeInventory() {
		if (!this.worldObj.isRemote) {
			--this.numUsingPlayers;

			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType());
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType(), 1, this.numUsingPlayers);
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
		if (par1 == 1) {
			this.numUsingPlayers = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);

		if (par1NBTTagCompound.hasKey("CustomName")) {
			this.customName = par1NBTTagCompound.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.chestContents.length; ++i) {
			if (this.chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName()) {
			par1NBTTagCompound.setString("CustomName", this.customName);
		}
	}

	@Override
	public void updateEntity() {
		animationFrame++;
		animationFrame++;
		if (animationFrame > 40)
			animationFrame = 0;
	}

	public int getAnimationFrame() {
		return animationFrame;
	}

}
