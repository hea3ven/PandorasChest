package hea3ven.pandoraschest.tileentity;

import cpw.mods.fml.common.FMLLog;
import hea3ven.pandoraschest.client.renderer.tileentity.TileEntityPandorasChestRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

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
				this.onInventoryChanged();
				return itemStack;
			} else {
				ItemStack returnItemStack = itemStack.splitStack(amount);

				if (itemStack.stackSize == 0)
					setStackInSlot(slot, null);

				this.onInventoryChanged();
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

		this.onInventoryChanged();
	}

	@Override
	// public String getInvName() {
	public String func_145825_b() {
		return this.func_145818_k_() ? this.customName
				: "container.pandoraschest";
	}

	@Override
	// public boolean isInvNameLocalized() {
	public boolean func_145818_k_() {
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
		// return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
		// this.zCoord) != this ? false : entityplayer.getDistanceSq(
		// (double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
		// (double) this.zCoord + 0.5D) <= 64.0D;
		return this.field_145850_b.func_147438_o(this.field_145851_c,
				this.field_145848_d, this.field_145849_e) != this ? false
				: entityplayer.getDistanceSq(
						(double) this.field_145851_c + 0.5D,
						(double) this.field_145848_d + 0.5D,
						(double) this.field_145849_e + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		// if (!this.worldObj.isRemote) {
		if (!this.field_145850_b.isRemote) {
			if (this.numUsingPlayers < 0)
				this.numUsingPlayers = 0;

			TileEntityPandorasChestRenderer.modelChest.reloadModel();

			++this.numUsingPlayers;
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord,
			// this.getBlockType().blockID, 1, this.numUsingPlayers);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 1, this.numUsingPlayers);
			// this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
			// this.yCoord, this.zCoord, this.getBlockType().blockID);
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q());
			// this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
			// this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d - 1, this.field_145849_e,
					this.func_145838_q());
		}
	}

	@Override
	public void closeChest() {
		// if (!this.worldObj.isRemote) {
		if (!this.field_145850_b.isRemote) {
			--this.numUsingPlayers;

			// this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
			// this.yCoord, this.zCoord, this.getBlockType().blockID);
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q());
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord, this.getBlockType().blockID, 1,
			// this.numUsingPlayers);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 1, this.numUsingPlayers);
			// this.worldObj.notifyBlocksOfNeighborChange(this.xCoord,
			// this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
			this.field_145850_b.func_147459_d(this.field_145851_c,
					this.field_145848_d - 1, this.field_145849_e,
					this.func_145838_q());
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	// public boolean receiveClientEvent(int par1, int par2) {
	public boolean func_145842_c(int par1, int par2) {
		if (par1 == 1) {
			this.numUsingPlayers = par2;
			return true;
		} else {
			// return super.receiveClientEvent(par1, par2);
			return super.func_145842_c(par1, par2);
		}
	}

	@Override
	// public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
	public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
		// super.readFromNBT(par1NBTTagCompound);
		super.func_145839_a(par1NBTTagCompound);
		// NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);

		if (par1NBTTagCompound.hasKey("CustomName")) {
			this.customName = par1NBTTagCompound.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			// NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
			// .tagAt(i);
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.func_150305_b(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	// public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
	public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
		// super.writeToNBT(par1NBTTagCompound);
		super.func_145841_b(par1NBTTagCompound);
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

		// if (this.isInvNameLocalized()) {
		if (this.func_145818_k_()) {
			par1NBTTagCompound.setString("CustomName", this.customName);
		}
	}

	@Override
	// public void updateEntity() {
	public void func_145845_h() {
		animationFrame++;
		animationFrame++;
		if (animationFrame > 40)
			animationFrame = 0;
	}

	public int getAnimationFrame() {
		return animationFrame;
	}

}
