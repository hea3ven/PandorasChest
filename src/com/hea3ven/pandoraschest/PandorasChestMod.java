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

package com.hea3ven.pandoraschest;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.hea3ven.pandoraschest.block.BlockClayDrawer;
import com.hea3ven.pandoraschest.block.BlockFluorecentSpot;
import com.hea3ven.pandoraschest.block.BlockFluorecentTube;
import com.hea3ven.pandoraschest.block.BlockPandorasChest;
import com.hea3ven.pandoraschest.block.BlockStainedClayDrawer;
import com.hea3ven.pandoraschest.item.ItemFluorecentTube;
import com.hea3ven.pandoraschest.tileentity.TileEntityClayDrawer;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.Action;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "pandoraschest", version = "1.0a2", dependencies = "required-after:Forge@[10.12.0.1047,);required-after:colladamodel@[1.0a1,)")
public class PandorasChestMod {

	@Instance("pandoraschest")
	public static PandorasChestMod instance;

	@SidedProxy(clientSide = "com.hea3ven.pandoraschest.client.PandorasChestClientProxy", serverSide = "com.hea3ven.pandoraschest.PandorasChestCommonProxy")
	public static PandorasChestCommonProxy proxy;

	public static int pandorasChestRenderId;
	public static int clayDrawerRenderId;
	public static int fluorecentSpotRenderId;
	public static int fluorecentTubeRenderId;

	public static BlockClayDrawer clayDrawer;
	public static BlockStainedClayDrawer stainedClayDrawer;
	public static BlockPandorasChest PandorasChest;
	public static BlockFluorecentSpot fluorecentSpot;
	public static BlockFluorecentTube fluorecentTube;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		clayDrawer = new BlockClayDrawer();
		stainedClayDrawer = new BlockStainedClayDrawer();
		PandorasChest = new BlockPandorasChest();
		fluorecentSpot = new BlockFluorecentSpot();
		fluorecentTube = new BlockFluorecentTube();

		GameRegistry.registerBlock(clayDrawer, "Clay Drawer");
		GameRegistry.registerTileEntityWithAlternatives(
				TileEntityClayDrawer.class, "tileentity.claydrawer",
				"tileentity.decorativechest");
		GameRegistry.addRecipe(new ItemStack(clayDrawer), "xxx", "x x", "xxx",
				'x', new ItemStack(Blocks.hardened_clay));

		GameRegistry.registerBlock(stainedClayDrawer, ItemCloth.class,
				"Stained Clay Drawer");
		for (int i = 0; i < 16; i++) {
			ItemStack stack = new ItemStack(stainedClayDrawer, 1, i);
			GameRegistry.addRecipe(stack, "xxx", "x x", "xxx", 'x',
					new ItemStack(Blocks.stained_hardened_clay, 1, i));
		}

		GameRegistry.registerBlock(PandorasChest, "Pandora's Chest");
		GameRegistry.registerTileEntity(TileEntityPandorasChest.class,
				"tileentity.pandoraschest");
		GameRegistry
				.addRecipe(new ItemStack(PandorasChest), "xyx", "yzy", "yxy",
						'x', new ItemStack(Items.iron_ingot), 'y',
						new ItemStack(Blocks.chest), 'z', new ItemStack(
								Items.redstone));

		GameRegistry.registerBlock(fluorecentSpot, "Fluorecent Spot");
		GameRegistry.registerTileEntity(TileEntityFluorecentBlock.class,
				"tileentity.fluorecentspot");
		GameRegistry.addRecipe(new ItemStack(fluorecentSpot), "x", "y", "z",
				'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(
						Items.glowstone_dust), 'z', new ItemStack(
						Blocks.glass_pane));

		GameRegistry.registerBlock(fluorecentTube, ItemFluorecentTube.class,
				"Fluorecent Tube");
		GameRegistry.registerTileEntity(TileEntityFluorecentTubeBlock.class,
				"tileentity.fluorecenttube");
		GameRegistry.addRecipe(new ItemStack(fluorecentTube), "xxx", "yyy",
				"zzz", 'x', new ItemStack(Items.iron_ingot), 'y',
				new ItemStack(Items.glowstone_dust), 'z', new ItemStack(
						Blocks.glass_pane));

		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			//
			// PandorasChest = new
			// BlockPandorasChest(cfg.getBlock("PandorasChest",
			// 700).getInt(700));
		} catch (Exception e) {
			FMLLog.log(Level.FATAL, e,
					"Pandora's Chest configuration failed to load.");
		} finally {
			cfg.save();
		}
	}

	@EventHandler
	public void modInit(FMLInitializationEvent event) {

		FMLCommonHandler.instance().bus().register(this);

		proxy.registerGuiHandlers(this);
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	@EventHandler
	public void missingMappings(FMLMissingMappingsEvent event) {
		List<MissingMapping> mappings = event.get();
		for (MissingMapping missingMapping : mappings) {
			if (missingMapping.name.equals("pandoraschest:Decorative Chest"))
				missingMapping.setAction(Action.IGNORE);
		}
	}
}
