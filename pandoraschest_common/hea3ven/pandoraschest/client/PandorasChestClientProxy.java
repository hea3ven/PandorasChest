package hea3ven.pandoraschest.client;

import hea3ven.pandoraschest.PandorasChestCommonProxy;
import hea3ven.pandoraschest.client.gui.GuiPandorasChest;
import hea3ven.pandoraschest.client.renderer.tileentity.TileEntityPandorasChestRenderer;
import hea3ven.pandoraschest.inventory.ContainerPandorasChest;
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
		NetworkRegistry.instance().registerGuiHandler(mod, this);
	};
	
	@Override
	public void registerRenderers(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPandorasChest.class, new TileEntityPandorasChestRenderer());
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityPandorasChest){
                return new ContainerPandorasChest(player.inventory, (TileEntityPandorasChest) tileEntity);
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityPandorasChest){
                return new GuiPandorasChest(player.inventory, ((TileEntityPandorasChest) tileEntity));
        }
        return null;
	}

}
