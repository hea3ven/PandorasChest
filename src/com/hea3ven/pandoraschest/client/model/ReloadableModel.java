package com.hea3ven.pandoraschest.client.model;

import com.hea3ven.colladamodel.client.model.collada.IModelAnimationCustom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ReloadableModel implements IResourceManagerReloadListener {

	protected IModelCustom model;
	protected ResourceLocation resource;

	public ReloadableModel(String resourceName) {
		resource = new ResourceLocation(resourceName);
		reloadModel();
		// TODO: Fix this hack by implementing a global Model Resource Manager
		((IReloadableResourceManager) Minecraft.getMinecraft()
				.getResourceManager()).registerReloadListener(this);
	}

	protected void render() {
		model.renderAll();
	}

	protected void renderAnimation(double time) {
		((IModelAnimationCustom) model).renderAnimationAll(time);
	}

	public void reloadModel() {
		model = AdvancedModelLoader.loadModel(this.resource);
	}

	@Override
	public void onResourceManagerReload(IResourceManager var1) {
		reloadModel();
	}

}