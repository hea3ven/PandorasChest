package com.hea3ven.pandoraschest.block;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

public class BlockPandorasChest extends BlockDecorativeChest {

	private final Random random = new Random();

	public BlockPandorasChest() {
		super();
		this.func_149647_a(CreativeTabs.tabDecorations);
		this.func_149663_c("block.pandorasChest"); // Unlocalized Name
		// this.func_149658_d("examplemod:testBlock"); // Texture Name
	}

	@Override
	public TileEntity func_149915_a(World world, int metadata) {
		TileEntityPandorasChest tileEntity = new TileEntityPandorasChest();
		return tileEntity;
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

			player.openGui(PandorasChestMod.instance, 1, world, x, y, z);
			return true;
		}
	}

}
