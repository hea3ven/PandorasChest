package com.hea3ven.pandoraschest;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.hea3ven.pandoraschest.block.BlockDecorativeChest;
import com.hea3ven.pandoraschest.block.BlockFluorecentSpot;
import com.hea3ven.pandoraschest.block.BlockFluorecentTube;
import com.hea3ven.pandoraschest.block.BlockPandorasChest;
import com.hea3ven.pandoraschest.item.ItemFluorecentTube;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;
import com.hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "pandoraschest", name = "Pandora's Chest", version = "0.0.1")
public class PandorasChestMod {

	@Instance("pandoraschest")
	public static PandorasChestMod instance;

	@SidedProxy(clientSide = "com.hea3ven.pandoraschest.client.PandorasChestClientProxy", serverSide = "com.hea3ven.pandoraschest.PandorasChestCommonProxy")
	public static PandorasChestCommonProxy proxy;

	public static int pandorasChestRenderId;
	public static int decorativeChestRenderId;
	public static int fluorecentSpotRenderId;
	public static int fluorecentTubeRenderId;

	public static BlockDecorativeChest decorativeChest;
	public static BlockPandorasChest PandorasChest;
	public static BlockFluorecentSpot fluorecentSpot;
	public static BlockFluorecentTube fluorecentTube;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		decorativeChest = new BlockDecorativeChest();
		PandorasChest = new BlockPandorasChest();
		fluorecentSpot = new BlockFluorecentSpot();
		fluorecentTube = new BlockFluorecentTube();

		GameRegistry.registerBlock(decorativeChest, "Decorative Chest");
		GameRegistry.registerBlock(PandorasChest, "Pandora's Chest");
		GameRegistry.registerTileEntity(TileEntityDecorativeChest.class,
				"tileentity.decorativechest");
		GameRegistry.registerTileEntity(TileEntityPandorasChest.class,
				"tileentity.pandoraschest");
		GameRegistry.registerBlock(fluorecentSpot, "Fluorecent Spot");
		GameRegistry.registerBlock(fluorecentTube, ItemFluorecentTube.class, "Fluorecent Tube");
		GameRegistry.registerTileEntity(TileEntityFluorecentBlock.class,
				"tileentity.fluorecentspot");
		GameRegistry.registerTileEntity(TileEntityFluorecentTubeBlock.class,
				"tileentity.fluorecenttube");

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

		// MinecraftForge.EVENT_BUS.register(new PlayerHandler());
		// MinecraftForge.EVENT_BUS.register(new
		// GuiSpeed(Minecraft.getMinecraft()));

		// proxy.registerRenderers();
		proxy.registerGuiHandlers(this);
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
