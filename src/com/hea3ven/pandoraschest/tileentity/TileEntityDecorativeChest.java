package com.hea3ven.pandoraschest.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDecorativeChest extends TileEntity implements IInventory {
	protected ItemStack[] chestContents;

	private String customName;

	public int numUsingPlayers;

	private double openAnimationFrame;
	private double closeAnimationFrame;

	private int rotation;

	public TileEntityDecorativeChest() {
		this(27);
	}

	public TileEntityDecorativeChest(int slotsNum) {
		chestContents = new ItemStack[slotsNum];
		numUsingPlayers = 0;
		openAnimationFrame = 0.0d;
		closeAnimationFrame = -1.0d;
		customName = null;
		rotation = 2;
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
			if (this.numUsingPlayers == 0 && par2 > 0) {
				this.openAnimationFrame = 0.05d;
				this.closeAnimationFrame = -1.0d;
			}
			if (this.numUsingPlayers > 0 && par2 <= 0) {
				this.openAnimationFrame = -1.0d;
				this.closeAnimationFrame = 0.05d;
			}
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

		this.rotation = par1NBTTagCompound.getInteger("Rotation");

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

		par1NBTTagCompound.setInteger("Rotation", this.rotation);
	}

	@Override
	public void updateEntity() {
		if (this.worldObj.isRemote) {
			if (openAnimationFrame > 0) {
				openAnimationFrame += 0.05d;
			} else {
				if (closeAnimationFrame > 0)
					closeAnimationFrame += 0.05d;
			}
		}
	}

	public double getOpenAnimationFrame() {
		return openAnimationFrame;
	}

	public void setOpenAnimationFrame(double frame) {
		openAnimationFrame = frame;
	}

	public double getCloseAnimationFrame() {
		return closeAnimationFrame;
	}

	public void setCloseAnimationFrame(double frame) {
		closeAnimationFrame = frame;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getRotation() {
		return rotation;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord,
				this.zCoord, 3, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
		super.onDataPacket(net, pkt);
	}

}
