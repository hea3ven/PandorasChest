package com.hea3ven.pandoraschest.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityClayDrawer;

public class BlockClayDrawer extends BlockBaseChest {

	public BlockClayDrawer() {
		super();
		setBlockName("clayDrawer");
		setBlockTextureName("pandoraschest:clay_drawer");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityClayDrawer tileEntity = new TileEntityClayDrawer();
		return tileEntity;
	}

	@Override
	public int getRenderType() {
		return PandorasChestMod.clayDrawerRenderId;
	}

	@Override
	public int getGuiId() {
		return 0;
	}

	@Override
	public ResourceLocation getTexture(int meta) {
		return new ResourceLocation("pandoraschest", "textures/blocks/clay_drawer.png");
	}

}
