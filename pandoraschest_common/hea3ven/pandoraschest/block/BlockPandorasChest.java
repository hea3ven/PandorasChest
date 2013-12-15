package hea3ven.pandoraschest.block;

import java.util.Random;

import hea3ven.pandoraschest.PandorasChestMod;
import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;
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
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class BlockPandorasChest extends BlockContainer {

	private final Random random = new Random();

	public BlockPandorasChest(int blockID) {
		super(blockID, Material.rock);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setUnlocalizedName("PandorasChest");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		TileEntityPandorasChest tileEntity = new TileEntityPandorasChest();
		return tileEntity;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		if (par6ItemStack.hasDisplayName()) {
			((TileEntityPandorasChest) par1World.getBlockTileEntity(par2, par3,
					par4)).setChestGuiName(par6ItemStack.getDisplayName());
		}
	}

	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		TileEntityPandorasChest tileentitychest = (TileEntityPandorasChest) par1World
				.getBlockTileEntity(par2, par3, par4);

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
										itemstack.itemID, k1,
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

			par1World.func_96440_m(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}

		player.openGui(PandorasChestMod.instance, 0, world, x, y, z);
		return true;
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		return (IInventory) par1World.getBlockTileEntity(par2, par3, par4);
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
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
