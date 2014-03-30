package com.hea3ven.pandoraschest.block;

import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStainedClayDrawer extends BlockClayDrawer {

	public BlockStainedClayDrawer() {
		super();
		setBlockName("clayDrawerStained");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 16; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	@Override
    public int damageDropped(int damage)
    {
        return damage;
    }
	
	public ResourceLocation getTexture(int meta) {
		return new ResourceLocation("pandoraschest", "textures/blocks/clay_drawer_" + ItemDye.field_150921_b[BlockColored.func_150032_b(meta)] + ".png");
	}
}
