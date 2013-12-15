package hea3ven.pandoraschest.tileentity;

import hea3ven.pandoraschest.client.renderer.tileentity.TileEntityPandorasChestRenderer;

import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPandorasChest extends TileEntity implements IInventory {

	private ItemStack[] chestContents = new ItemStack[27 * 5];

	private String customName;

	public int numUsingPlayers;
	private int nextSelectedChest;
	public int selectedChest;
	private Random rand = new Random();
	static int dsa = 0;
	
	private int animationFrame;

	private int asd;

	public TileEntityPandorasChest() {
		this.nextSelectedChest = 0;
		this.selectedChest = -1;
		this.numUsingPlayers = 0;
		animationFrame = 0;
		asd = dsa++;
	}

	@Override
	public int getSizeInventory() {
		// 5 * 3 * 9 = 135
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.chestContents[getInventoryOffset() + slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		int invOffset = getInventoryOffset();
		if (this.chestContents[invOffset + slot] != null) {
			ItemStack itemstack;

			if (this.chestContents[invOffset + slot].stackSize <= amount) {
				itemstack = this.chestContents[invOffset + slot];
				this.chestContents[invOffset + slot] = null;
				this.onInventoryChanged();
				return itemstack;
			} else {
				itemstack = this.chestContents[invOffset + slot]
						.splitStack(amount);

				if (this.chestContents[invOffset + slot].stackSize == 0) {
					this.chestContents[invOffset + slot] = null;
				}

				this.onInventoryChanged();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	private int getInventoryOffset() {
		return (this.selectedChest != -1) ? this.selectedChest * 27 : this.rand
				.nextInt(5) * 27;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		int invOffset = getInventoryOffset();
		if (this.chestContents[invOffset + slot] != null) {
			ItemStack itemstack = this.chestContents[invOffset + slot];
			this.chestContents[invOffset + slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		int invOffset = getInventoryOffset();
		this.chestContents[invOffset + slot] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return this.isInvNameLocalized() ? this.customName
				: "container.pandoraschest";
	}

	@Override
	public boolean isInvNameLocalized() {
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
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		if (!this.worldObj.isRemote) {
			if (this.numUsingPlayers < 0)
				this.numUsingPlayers = 0;

			if (this.numUsingPlayers == 0)
			{
				this.selectedChest = this.nextSelectedChest; 
				this.nextSelectedChest = this.rand.nextInt(5);
			}
			
			TileEntityPandorasChestRenderer.modelChest.reloadModel();
			TileEntityPandorasChestRenderer.modelChest2.reloadModel();

			++this.numUsingPlayers;
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType().blockID, 1, this.numUsingPlayers);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType().blockID, 2, this.selectedChest);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType().blockID, 3, this.nextSelectedChest);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType().blockID);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
		}
		else
		{
			if(this.selectedChest == -1)
			{
				this.selectedChest = this.nextSelectedChest;
			}
		}
	}

	@Override
	public void closeChest() {
		if (!this.worldObj.isRemote) {
			--this.numUsingPlayers;
			if (this.numUsingPlayers <= 0)
				this.selectedChest = -1;

			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord, this.zCoord, this.getBlockType().blockID);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType().blockID, 1, this.numUsingPlayers);
			this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
					this.getBlockType().blockID, 2, this.selectedChest);
			this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
					this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
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
		} else if (par1 == 2) {
			if(this.selectedChest != par2)
				FMLLog.log(Level.WARNING, "SelectedChest received (" + par2 + " different from ours (" + this.selectedChest + ") .");
			this.selectedChest = par2;
			return true;
		} else if (par1 == 3) {
			this.nextSelectedChest = par2;
			return true;
		} else {

			return super.receiveClientEvent(par1, par2);
		}
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		// this.chestContents = new ItemStack[this.getSizeInventory()];

		if (par1NBTTagCompound.hasKey("CustomName")) {
			this.customName = par1NBTTagCompound.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

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

		if (this.isInvNameLocalized()) {
			par1NBTTagCompound.setString("CustomName", this.customName);
		}
	}

	@Override
	public void updateEntity() {
		animationFrame++;
		animationFrame++;
		if(animationFrame > 40)
			animationFrame = 0;
	}

	public int getAnimationFrame() {
		return animationFrame;
	}

	public boolean asd() {
		return (asd % 2) == 0;
	}
}
