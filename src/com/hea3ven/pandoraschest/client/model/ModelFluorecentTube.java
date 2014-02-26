package com.hea3ven.pandoraschest.client.model;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentTubeBlock;

public class ModelFluorecentTube extends ReloadableModel {

	private ResourceLocation modelResource = new ResourceLocation(
			"pandoraschest:models/fluorecent_tube.dae");

	public ModelFluorecentTube() {
		super();
	}

	public void render(TileEntityFluorecentTubeBlock chest, double x, double y,
			double z) {

		int meta = chest.getBlockMetadata();

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
		if (meta != 0) {
			GL11.glTranslatef((float) 0.0f + (chest.getXOffset() / 2.0f),
					(float) 0.5f + (chest.getYOffset() / 2.0f), (float) 0.0f
							+ (chest.getZOffset() / 2.0f));
			GL11.glRotatef(90.0f * (meta - 1) + 90.0f
					+ ((chest.getYOffset() == 0) ? 90.0f : 0.0f), 0.0f, 1.0f,
					0.0f);
			if (chest.getYOffset() != 0)
				GL11.glRotatef(-90.f, 1.0f, 0.0f, 0.0f);
			else
				GL11.glRotatef(-90.f, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef((float) 0.0f, (float) -0.5f, (float) 0.0f);
		} else {
			GL11.glTranslatef(chest.getXOffset() / 2.0f,
					chest.getYOffset() / 2.0f, chest.getZOffset() / 2.0f);
			if (chest.getXOffset() != 0)
				GL11.glRotatef(90.f, 0.0f, 1.0f, 0.0f);
		}
		// GL11.glTranslated((float) 2.0f*x, (float) 2.0f*y, (float) 2.0f*z);

		// GL11.glTranslatef((float) 0.5f, (float) 0.0f, (float)0.5f);

		// this.renderAnimation(chest.getAnimationFrame());
		this.getModel(modelResource).renderAll();

		GL11.glPopMatrix();
	}

	public void renderItem() {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef(0.0f, -0.5f, 0.0f);

		this.getModel(modelResource).renderAll();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
