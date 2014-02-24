package com.hea3ven.pandoraschest.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.block.BlockFluorecentTube;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;

public class ItemFluorecentTube extends ItemBlock {

	public ItemFluorecentTube(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		// this.setUnlocalizedName("item.fluorecentTube");
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float par8, float par9,
			float par10) {
		if (world.isRemote) {
			return true;
		} else if (side == 1) {
			return false;
		} else {
			BlockFluorecentTube blockbed = PandorasChestMod.fluorecentTube;
			int meta = 0;
			byte xOffset = 0;
			byte yOffset = 0;
			byte zOffset = 0;
			if (side == 0) {
				--y;
				meta = 0;
				int i1 = MathHelper
						.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
				if (i1 == 0)
					zOffset = 1;
				if (i1 == 1)
					xOffset = -1;
				if (i1 == 2)
					zOffset = -1;
				if (i1 == 3)
					xOffset = 1;
			} else {
				int i1 = MathHelper
						.floor_double((double) (player.rotationYaw * 4.0F / 360.0F)) & 3;
				int i2 = MathHelper
						.floor_double((double) ((player.rotationPitch + 90.0f) * 3.0f / 180)) & 3;
				if (side == 2) {
					--z;
					meta = 2;
					if (i2 == 0)
						yOffset = 1;
					else if (i2 == 2)
						yOffset = -1;
					else {
						if (i1 == 0 || i1 == 1)
							xOffset = -1;
						if (i1 == 2 || i1 == 3)
							xOffset = 1;
					}
				} else if (side == 3) {
					++z;
					meta = 4;
					if (i2 == 0)
						yOffset = 1;
					else if (i2 == 2)
						yOffset = -1;
					else {
						if (i1 == 0 || i1 == 1)
							xOffset = -1;
						if (i1 == 2 || i1 == 3)
							xOffset = 1;
					}
				} else if (side == 4) {
					--x;
					meta = 3;
					if (i2 == 0)
						yOffset = 1;
					else if (i2 == 2)
						yOffset = -1;
					else {
						if (i1 == 1 || i1 == 2)
							zOffset = -1;
						if (i1 == 3 || i1 == 0)
							zOffset = 1;
					}
				} else if (side == 5) {
					++x;
					meta = 1;
					if (i2 == 0)
						yOffset = 1;
					else if (i2 == 2)
						yOffset = -1;
					else {
						if (i1 == 1 || i1 == 2)
							zOffset = -1;
						if (i1 == 3 || i1 == 0)
							zOffset = 1;
					}
				}
			}

			if (player.canPlayerEdit(x, y, z, side, itemStack)
					&& player.canPlayerEdit(x + xOffset, y + yOffset, z
							+ zOffset, side, itemStack)) {
				ForgeDirection dir = ForgeDirection.getOrientation(side);
				if (world.isAirBlock(x, y, z)
						&& world.isAirBlock(x + xOffset, y + yOffset, z
								+ zOffset)
						&& world.isSideSolid(x - dir.offsetX, y - dir.offsetY,
								z - dir.offsetZ, dir)
						&& world.isSideSolid(x - dir.offsetX + xOffset, y
								- dir.offsetY + yOffset, z - dir.offsetZ
								+ zOffset, dir)) {
					world.setBlock(x, y, z, blockbed, meta, 3);

					if (world.getBlock(x, y, z) == blockbed) {
						world.setBlock(x + xOffset, y + yOffset, z + zOffset,
								blockbed, meta, 3);
						TileEntityFluorecentTubeBlock te = (TileEntityFluorecentTubeBlock) world
								.getTileEntity(x, y, z);
						te.setMaster(true);
						te.setOffsets(xOffset, yOffset, zOffset);
						TileEntityFluorecentTubeBlock te2 = (TileEntityFluorecentTubeBlock) world
								.getTileEntity(x + xOffset, y + yOffset, z
										+ zOffset);
						te2.setMaster(false);
						te2.setOffsets((byte) -xOffset, (byte) -yOffset,
								(byte) -zOffset);
					}

					--itemStack.stackSize;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
