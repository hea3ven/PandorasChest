package com.hea3ven.pandoraschest.block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

public class BlockPandorasChest extends BlockDecorativeChest {

	public BlockPandorasChest() {
		super();
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockName("block.pandorasChest");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityPandorasChest tileEntity = new TileEntityPandorasChest();
		return tileEntity;
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

			player.openGui(PandorasChestMod.instance, 1, world, x, y, z);
			return true;
		}
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.pandorasChestRenderId;
	}

}
