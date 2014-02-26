package com.hea3ven.pandoraschest.client.model;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.hea3ven.colladamodel.client.model.collada.IModelAnimationCustom;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ModelDecorativeChest extends ReloadableModel {
	private ResourceLocation openAnimationFileName;
	private ResourceLocation closeAnimationFileName;

	public ModelDecorativeChest(String openAnimationFileName,
			String closeAnimationFileName) {
		super();
		this.openAnimationFileName = new ResourceLocation(openAnimationFileName);
		this.closeAnimationFileName = new ResourceLocation(
				closeAnimationFileName);
	}

	public void render(TileEntityDecorativeChest chest, double x, double y,
			double z) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
		GL11.glRotatef(-90.0f + chest.getRotation() * -90.0f, 0.0f, 1.0f, 0.0f);

		double frame = chest.getOpenAnimationFrame();
		IModelAnimationCustom model = null;
		if (frame == -1.0d) {
			frame = chest.getCloseAnimationFrame();
			model = this.getModel(this.closeAnimationFileName);
			if (frame > model.getAnimationLength()) {
				chest.setOpenAnimationFrame(0.0d);
				chest.setCloseAnimationFrame(-1.0d);
				model = this.getModel(this.openAnimationFileName);
				frame = 0.0d;
			}
		} else {
			model = this.getModel(this.openAnimationFileName);
			if (frame > model.getAnimationLength()) {
				chest.setOpenAnimationFrame(-1.0d);
				chest.setCloseAnimationFrame(0.0d);
				model = this.getModel(this.closeAnimationFileName);
				frame = 0.0d;
			}
		}
		model.renderAnimationAll(frame);

		GL11.glPopMatrix();
	}

	public void renderItem() {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		// GL11.glTranslatef(0.0f, -0.5f, 0.0f);
		GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);

		this.getModel(this.openAnimationFileName).renderAll();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
