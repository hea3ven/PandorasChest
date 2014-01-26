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

public class TileEntityPandorasChest extends TileEntityDecorativeChest {

	private int nextSelectedChest;
	public int selectedChest;
	private Random rand = new Random();

	public TileEntityPandorasChest() {
		super(27 * 5);
		
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
	public void openChest() {
		// if (!this.worldObj.isRemote) {
		if (!this.field_145850_b.isRemote) {
			if (this.numUsingPlayers < 0)
				this.numUsingPlayers = 0;

			if (this.numUsingPlayers == 0) {
				this.selectedChest = this.nextSelectedChest;
				this.nextSelectedChest = this.rand.nextInt(5);
			}

			TileEntityPandorasChestRenderer.modelChest.reloadModel();

			++this.numUsingPlayers;
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord,
			// this.getBlockType().blockID, 1, this.numUsingPlayers);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 1, this.numUsingPlayers);
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord,
			// this.getBlockType().blockID, 2, this.selectedChest);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 2, this.selectedChest);
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord,
			// this.getBlockType().blockID, 3, this.nextSelectedChest);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 3, this.nextSelectedChest);
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
		} else {
			if (this.selectedChest == -1) {
				this.selectedChest = this.nextSelectedChest;
			}
		}
	}

	@Override
	public void closeChest() {
		// if (!this.worldObj.isRemote) {
		if (!this.field_145850_b.isRemote) {
			--this.numUsingPlayers;
			if (this.numUsingPlayers <= 0)
				this.selectedChest = -1;

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
			// this.worldObj.addBlockEvent(this.xCoord, this.yCoord,
			// this.zCoord,
			// this.getBlockType().blockID, 2, this.selectedChest);
			this.field_145850_b.func_147452_c(this.field_145851_c,
					this.field_145848_d, this.field_145849_e,
					this.func_145838_q(), 2, this.selectedChest);
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
		if (par1 == 2) {
			if (this.selectedChest != par2)
				FMLLog.warning("SelectedChest received (%s) different from ours (%s).", par2, this.selectedChest);
			this.selectedChest = par2;
			return true;
		} else if (par1 == 3) {
			this.nextSelectedChest = par2;
			return true;
		} else {
			// return super.receiveClientEvent(par1, par2);
			return super.func_145842_c(par1, par2);
		}
	}


}
