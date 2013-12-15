package hea3ven.pandoraschest.client.model;

import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.collada.ColladaAsset;
import net.minecraftforge.client.model.collada.Model;

import org.lwjgl.opengl.GL11;

public class ModelPandorasChest {
	private IModelCustom modelChest;
	private int frame;
	private String file_name;

	public ModelPandorasChest(String file_name) {
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_3ds_ad.dae");
		modelChest = AdvancedModelLoader.loadModel(file_name);
		this.file_name = file_name;
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_bl.dae");
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_3ds_oc.DAE");
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest.obj");
		frame = 0;
	}

	public void render() {
		modelChest.renderAll();
	}

	public void reloadModel() {
		modelChest = AdvancedModelLoader.loadModel(file_name);
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void render(TileEntityPandorasChest chest, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the
		// OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		// Scale our object to about half-size in all directions (the OBJ file
		// is a little large)
		// GL11.glScalef(0.5f, 0.5f, 0.5f);

		// Bind the texture, so that OpenGL properly textures our block.
		// FMLClientHandler.instance().getClient().renderEngine.bindTexture(new
		// ResourceLocation("PandorasChest", "/models/chest.png"));

		// Render the object, using modelTutBox.renderAll();
		// ((ColladaAsset) this.modelChest).renderAnimationAll(0);
		((Model) this.modelChest).renderAnimationAll(chest.getAnimationFrame());
		// ((ColladaAsset) this.modelChest).renderAll();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}
