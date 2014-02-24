package com.hea3ven.pandoraschest.client.model;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ModelDecorativeChest extends ReloadableModel {
	public ModelDecorativeChest(String fileName) {
		super("pandoraschest:models/" + fileName);
		reloadModel();
	}

	public void render(TileEntityDecorativeChest chest, double x, double y,
			double z) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
		GL11.glRotatef(chest.getRotation() * -90.0f + 90.f, 0.0f, 1.0f, 0.0f);

		this.renderAnimation(chest.getAnimationFrame());

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
