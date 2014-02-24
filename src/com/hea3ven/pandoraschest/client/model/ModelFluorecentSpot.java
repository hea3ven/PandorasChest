package com.hea3ven.pandoraschest.client.model;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;

public class ModelFluorecentSpot extends ReloadableModel {

	public ModelFluorecentSpot() {
		super("pandoraschest:models/fluorecent_spot.dae");
	}

	public void render(TileEntityFluorecentBlock chest, double x, double y,
			double z) {

		int meta = chest.getBlockMetadata();

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x+0.5f, (float) y, (float) z+0.5f);
		if (meta != 0) {
			GL11.glTranslatef((float) 0.0f, (float) 0.5f, (float) 0.0f);
			GL11.glRotatef(90.0f * (meta-1) + 90.0f, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-90.f, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef((float) 0.0f, (float) -0.5f, (float) 0.0f);
		}
		//GL11.glTranslated((float) 2.0f*x, (float) 2.0f*y, (float) 2.0f*z);


		//GL11.glTranslatef((float) 0.5f, (float) 0.0f, (float)0.5f);

		// this.renderAnimation(chest.getAnimationFrame());
		this.model.renderAll();

		GL11.glPopMatrix();
	}

	public void renderItem() {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef(0.0f, -0.5f, 0.0f);
		this.render();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
