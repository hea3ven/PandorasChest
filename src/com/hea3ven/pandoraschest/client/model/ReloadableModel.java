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

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;

import com.hea3ven.colladamodel.client.model.collada.IModelAnimationCustom;

public class ReloadableModel implements IResourceManagerReloadListener {
	private static Map<ResourceLocation, IModelAnimationCustom> models = new HashMap<ResourceLocation, IModelAnimationCustom>();

	public ReloadableModel() {
		// TODO: Fix this hack by implementing a global Model Resource Manager
		((IReloadableResourceManager) Minecraft.getMinecraft()
				.getResourceManager()).registerReloadListener(this);
	}

	protected IModelAnimationCustom getModel(ResourceLocation resource) {
		if (!models.containsKey(resource))
			models.put(resource, (IModelAnimationCustom) AdvancedModelLoader
					.loadModel(resource));
		return models.get(resource);
	}

	@Override
	public void onResourceManagerReload(IResourceManager var1) {
		for (ResourceLocation resource : models.keySet()) {
			models.put(resource, (IModelAnimationCustom) AdvancedModelLoader
					.loadModel(resource));
		}
	}

}