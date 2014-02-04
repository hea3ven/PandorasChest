package com.hea3ven.pandoraschest.client.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.hea3ven.colladamodel.client.model.collada.IModelAnimationCustom;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ModelPandorasChest {
	private IModelCustom modelChest;
	private String file_name;

	public ModelPandorasChest(String file_name) {
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_3ds_ad.dae");
		modelChest = AdvancedModelLoader.loadModel(new ResourceLocation(
				"pandoraschest:models/" + file_name));
		this.file_name = file_name;
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_bl.dae");
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest_open_3ds_oc.DAE");
		// modelChest =
		// AdvancedModelLoader.loadModel("/assets/PandorasChest/models/chest.obj");
	}

	public void render() {
		modelChest.renderAll();
	}

	public void reloadModel() {
		modelChest = AdvancedModelLoader.loadModel(new ResourceLocation(
				"pandoraschest:models/" + file_name));
	}

	public void render(TileEntityDecorativeChest chest, double x, double y,
			double z) {
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
		((IModelAnimationCustom) this.modelChest).renderAnimationAll(chest
				.getAnimationFrame());
		// ((ColladaAsset) this.modelChest).renderAll();
		// this.modelChest.renderAll();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}

	public void renderItem() {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef(0.0f, -0.5f, 0.0f);
		this.modelChest.renderAll();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
