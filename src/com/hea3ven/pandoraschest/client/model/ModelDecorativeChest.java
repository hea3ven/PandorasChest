package com.hea3ven.pandoraschest.client.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.hea3ven.colladamodel.client.model.collada.IModelAnimationCustom;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ModelDecorativeChest {
	private IModelCustom modelChest;
	private String file_name;

	public ModelDecorativeChest(String file_name) {
		modelChest = AdvancedModelLoader.loadModel(new ResourceLocation(
				"pandoraschest:models/" + file_name));
		this.file_name = file_name;
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
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
		GL11.glRotatef(chest.getRotation() * -90.0f + 90.f, 0.0f, 1.0f, 0.0f);

		((IModelAnimationCustom) this.modelChest).renderAnimationAll(chest
				.getAnimationFrame());
		// this.modelChest.renderAll();

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
