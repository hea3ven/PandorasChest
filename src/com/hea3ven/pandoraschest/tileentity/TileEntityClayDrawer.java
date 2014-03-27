package com.hea3ven.pandoraschest.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityClayDrawer extends TileEntityBaseChest {

	public TileEntityClayDrawer() {
		super(27, new ResourceLocation(
				"pandoraschest:models/clay_cabinet_open.dae"),
				new ResourceLocation(
						"pandoraschest:models/clay_cabinet_close.dae"));
	}

	@Override
	protected String getOpenSound() {
		return "pandoraschest:clay_drawer_open";
	}

	@Override
	protected String getCloseSound() {
		return "pandoraschest:clay_drawer_close";
	}

	public TileEntityBaseChest getUpperInventory() {
		TileEntity te = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
		if (te != null && te instanceof TileEntityBaseChest)
			return (TileEntityBaseChest) te;
		else
			return null;
	}

	public TileEntityBaseChest getLeftInventory() {
		TileEntity te = null;
		if (getRotation() == 0)
			te = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
		else if (getRotation() == 1)
			te = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
		else if (getRotation() == 2)
			te = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
		else if (getRotation() == 3)
			te = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
		if (te != null && te instanceof TileEntityBaseChest)
			return (TileEntityBaseChest) te;
		else
			return null;
	}

	public TileEntityBaseChest getRightInventory() {
		TileEntity te = null;
		if (getRotation() == 0)
			te = worldObj.getTileEntity(xCoord - 1, yCoord, zCoord);
		else if (getRotation() == 1)
			te = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
		else if (getRotation() == 2)
			te = worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
		else if (getRotation() == 3)
			te = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
		if (te != null && te instanceof TileEntityBaseChest)
			return (TileEntityBaseChest) te;
		else
			return null;
	}

	public TileEntityBaseChest getBottomInventory() {
		TileEntity te = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
		if (te != null && te instanceof TileEntityBaseChest)
			return (TileEntityBaseChest) te;
		else
			return null;
	}
}
