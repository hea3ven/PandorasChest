package com.hea3ven.pandoraschest.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFluorecentTubeBlock extends TileEntity {

	private boolean master;
	private byte xOffset;
	private byte yOffset;
	private byte zOffset;

	public TileEntityFluorecentTubeBlock() {
		this.master = false;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public boolean getMaster() {
		return this.master;
	}

	public void setOffsets(byte xOffset, byte yOffset, byte zOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;

	}

	public byte getXOffset() {
		return xOffset;
	}

	public byte getYOffset() {
		return yOffset;
	}

	public byte getZOffset() {
		return zOffset;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Master")) {
			this.master = par1NBTTagCompound.getBoolean("Master");
		}

		if (par1NBTTagCompound.hasKey("xOffset")) {
			this.xOffset = par1NBTTagCompound.getByte("xOffset");
		}

		if (par1NBTTagCompound.hasKey("yOffset")) {
			this.yOffset = par1NBTTagCompound.getByte("yOffset");
		}

		if (par1NBTTagCompound.hasKey("zOffset")) {
			this.zOffset = par1NBTTagCompound.getByte("zOffset");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setBoolean("Master", this.master);
		par1NBTTagCompound.setByte("xOffset", this.xOffset);
		par1NBTTagCompound.setByte("yOffset", this.yOffset);
		par1NBTTagCompound.setByte("zOffset", this.zOffset);
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
