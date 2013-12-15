package hea3ven.pandoraschest;

import hea3ven.pandoraschest.block.BlockPandorasChest;
import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;

import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.collada.ColladaModelLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "PandorasChest", name = "Pandora's Chest", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class PandorasChestMod {

	@Instance("PandorasChest")
	public static PandorasChestMod instance;

	@SidedProxy(clientSide = "hea3ven.pandoraschest.client.PandorasChestClientProxy", serverSide = "hea3ven.pandoraschest.PandorasChestCommonProxy")
	public static PandorasChestCommonProxy proxy;

	private BlockPandorasChest PandorasChest;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ColladaModelLoader.init();
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();
			
			PandorasChest = new BlockPandorasChest(cfg.getBlock("PandorasChest", 700).getInt(700));
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, "Pandora's Chest configuration failed to load.");
		} finally {
			cfg.save();
		}
	}

	@EventHandler
	public void modInit(FMLInitializationEvent event) {
		GameRegistry.registerBlock(PandorasChest, "Pandora's Chest");
		GameRegistry.registerTileEntity(TileEntityPandorasChest.class, "tileentity.pandoraschest");

//		MinecraftForge.EVENT_BUS.register(new PlayerHandler());
//		MinecraftForge.EVENT_BUS.register(new GuiSpeed(Minecraft.getMinecraft()));

		//proxy.registerRenderers();
		proxy.registerGuiHandlers(this);
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
