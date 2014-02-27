/**
 * 
 * Copyright (c) 2014 Hea3veN
 * 
 *  This file is part of Pandora's Chest.
 *
 *  Pandora's Chest is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pandora's Chest is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Pandora's Chest.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.hea3ven.pandoraschest.client.model;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.hea3ven.pandoraschest.tileentity.TileEntityFluorecentBlock;

public class ModelFluorecentSpot extends ReloadableModel {

	private ResourceLocation modelResource = new ResourceLocation(
			"pandoraschest:models/fluorecent_spot.dae");

	public ModelFluorecentSpot() {
		super();
	}

	public void render(TileEntityFluorecentBlock chest, double x, double y,
			double z) {

		int meta = chest.getBlockMetadata();

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
		if (meta != 0) {
			GL11.glTranslatef((float) 0.0f, (float) 0.5f, (float) 0.0f);
			GL11.glRotatef(90.0f * (meta - 1) + 90.0f, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-90.f, 1.0f, 0.0f, 0.0f);
			GL11.glTranslatef((float) 0.0f, (float) -0.5f, (float) 0.0f);
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
