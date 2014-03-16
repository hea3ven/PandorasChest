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

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

public class ModelDecorativeChest {
	private ResourceLocation openResourceName;
	
	public ModelDecorativeChest(ResourceLocation resource) {
		openResourceName = resource;
	}

	public void render(TileEntityDecorativeChest chest, double x, double y,
			double z) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
		GL11.glRotatef(-90.0f + chest.getRotation() * -90.0f, 0.0f, 1.0f, 0.0f);

		chest.getAnimationState().render();

		GL11.glPopMatrix();
	}

	public void renderItem() {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		// GL11.glTranslatef(0.0f, -0.5f, 0.0f);
		GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);

		PandorasChestMod.modelManager.getModel(openResourceName).renderAll();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
