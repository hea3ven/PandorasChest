package hea3ven.pandoraschest.client;

import hea3ven.pandoraschest.PandorasChestCommonProxy;
import hea3ven.pandoraschest.client.gui.GuiPandorasChest;
import hea3ven.pandoraschest.client.renderer.tileentity.TileEntityPandorasChestRenderer;
import hea3ven.pandoraschest.inventory.ContainerPandorasChest;
import hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;
import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class PandorasChestClientProxy extends PandorasChestCommonProxy implements IGuiHandler {

	@Override
	public void registerGuiHandlers(Object mod) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, this);
	};
	
	@Override
	public void registerRenderers(){
		TileEntityPandorasChestRenderer renderer = new TileEntityPandorasChestRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPandorasChest.class, renderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecorativeChest.class, renderer);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
//		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		TileEntity tileEntity = world.func_147438_o(x, y, z);
        if(tileEntity instanceof TileEntityDecorativeChest){
                return new ContainerPandorasChest(player.inventory, (TileEntityDecorativeChest) tileEntity);
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
//		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		TileEntity tileEntity = world.func_147438_o(x, y, z);
        if(tileEntity instanceof TileEntityDecorativeChest){
                return new GuiPandorasChest(player.inventory, ((TileEntityDecorativeChest) tileEntity));
        }
        return null;
	}

}
