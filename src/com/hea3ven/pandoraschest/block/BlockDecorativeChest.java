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

package com.hea3ven.pandoraschest.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class BlockDecorativeChest extends BlockContainer {

	private final Random random = new Random();

	public BlockDecorativeChest() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockName("decorativeChest");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityDecorativeChest tileEntity = new TileEntityDecorativeChest();
		return tileEntity;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase player, ItemStack par6ItemStack) {
		TileEntityDecorativeChest te = (TileEntityDecorativeChest) par1World
				.getTileEntity(par2, par3, par4);

		int l = MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		te.setRotation(l);

		if (par6ItemStack.hasDisplayName()) {
			(te).setChestGuiName(par6ItemStack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			Block par5, int par6) {
		TileEntityDecorativeChest tileentitychest = (TileEntityDecorativeChest) par1World
				.getTileEntity(par2, par3, par4);

		if (tileentitychest != null) {
			for (int j1 = 0; j1 < tileentitychest.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentitychest.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World
							.spawnEntityInWorld(entityitem)) {
						int k1 = this.random.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						entityitem = new EntityItem(par1World,
								(double) ((float) par2 + f),
								(double) ((float) par3 + f1),
								(double) ((float) par4 + f2), new ItemStack(
										itemstack.getItem(), k1,
										itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random
								.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random
								.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random
								.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound(
									(NBTTagCompound) itemstack.getTagCompound()
											.copy());
						}
					}
				}
			}
			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}

			player.openGui(PandorasChestMod.instance, 0, world, x, y, z);
			return true;
		}
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		return (IInventory) par1World.getTileEntity(par2, par3, par4);
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	/**
	 * If hasComparatorInputOverride returns true, the return value from this is
	 * used instead of the redstone signal strength when this block inputs to a
	 * comparator.
	 */
	public int getComparatorInputOverride(World par1World, int par2, int par3,
			int par4, int par5) {
		return Container.calcRedstoneFromInventory(this.getInventory(par1World,
				par2, par3, par4));
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.decorativeChestRenderId;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}
}
