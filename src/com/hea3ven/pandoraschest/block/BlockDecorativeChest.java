package com.hea3ven.pandoraschest.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class BlockDecorativeChest extends BlockContainer {

	private final Random random = new Random();

	public BlockDecorativeChest() {
		super(Material.field_151576_e);
		this.func_149647_a(CreativeTabs.tabDecorations);
		this.func_149663_c("block.decorativeChest"); // Unlocalized Name
		// this.func_149658_d("examplemod:testBlock"); // Texture Name
	}

	@Override
	public TileEntity func_149915_a(World world, int metadata) {
		TileEntityDecorativeChest tileEntity = new TileEntityDecorativeChest();
		return tileEntity;
	}

	@Override
	// public void onBlockPlacedBy(World par1World, int par2, int par3, int
	// par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
	public void func_149689_a(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		if (par6ItemStack.hasDisplayName()) {
			// ((TileEntityPandorasChest) par1World.getBlockTileEntity(par2,
			// par3, par4)).setChestGuiName(par6ItemStack.getDisplayName());
			((TileEntityDecorativeChest) par1World.func_147438_o(par2, par3,
					par4)).setChestGuiName(par6ItemStack.getDisplayName());
		}
	}

	@Override
	// public void breakBlock(World par1World, int par2, int par3, int par4,
	// int par5, int par6) {
	public void func_149749_a(World par1World, int par2, int par3, int par4,
			Block par5, int par6) {
		// TileEntityPandorasChest tileentitychest = (TileEntityPandorasChest)
		// par1World.getBlockTileEntity(par2, par3, par4);
		TileEntityDecorativeChest tileentitychest = (TileEntityDecorativeChest) par1World
				.func_147438_o(par2, par3, par4);

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

			// par1World.func_96440_m(par2, par3, par4, par5);
			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.func_149749_a(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	// public boolean onBlockActivated(World world, int x, int y, int z,
	// EntityPlayer player, int par6, float par7, float par8, float par9) {
	public boolean func_149727_a(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.isRemote) {
			return true;
		} else {
			// TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			TileEntity tileEntity = world.func_147438_o(x, y, z);
			if (tileEntity == null || player.isSneaking()) {
				return false;
			}

			player.openGui(PandorasChestMod.instance, 0, world, x, y, z);
			return true;
		}
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		// return (IInventory) par1World.getBlockTileEntity(par2, par3, par4);
		return (IInventory) par1World.func_147438_o(par2, par3, par4);
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
	public boolean func_149686_d() {
		return false;
	}

	@Override
	public int func_149645_b() {
		return -1;
	}

	@Override
	public boolean func_149662_c() {
		return false;
	}
}
