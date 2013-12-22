package hea3ven.pandoraschest.client.renderer.tileentity;

import hea3ven.pandoraschest.PandorasChestMod;
import hea3ven.pandoraschest.client.model.ModelPandorasChest;
import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityPandorasChestRenderer extends TileEntitySpecialRenderer {

	public static ModelPandorasChest modelChest = new ModelPandorasChest("/assets/PandorasChest/models/chest2_blender.dae");
	public static ModelPandorasChest modelChest2 = new ModelPandorasChest("/assets/PandorasChest/models/chest_open_bl.dae");
//	public static ModelPandorasChest modelChest2 = new ModelPandorasChest("/assets/PandorasChest/models/chest_open_3ds_ad.DAE");

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		TileEntityPandorasChest tileEntityPandorasChest = (TileEntityPandorasChest) tileEntity;
		if (tileEntityPandorasChest.asd()) {
			bindTexture(new ResourceLocation("pandoraschest", "models/chest.png"));
			modelChest.render(tileEntityPandorasChest, x, y, z);
		} else {
			bindTexture(new ResourceLocation("pandoraschest", "models/chest_bl.png"));
			modelChest2.render(tileEntityPandorasChest, x, y, z);
		}

	}

}
