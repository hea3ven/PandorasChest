package net.minecraftforge.client.model.collada;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.IModelCustom;

public class Model implements IModelCustom{
	private Scene defaultScene;
	
	public Model()
	{
		defaultScene = null;
	}

	@Override
	public String getType() {
		return "dae";
	}

	public void addScene(Scene scene) {
	}

	public void setDefaultScene(Scene scene) {
		defaultScene = scene;
	}

	@Override
	public void renderAll() {
		Tessellator tessellator = Tessellator.instance;
		defaultScene.render(tessellator);
	}

	@Override
	public void renderOnly(String... geometriesNames) {
		Tessellator tessellator = Tessellator.instance;
//		for (String geometryName : geometriesNames) {
//			geometries.get(geometryName).render(tessellator);
//		}
	}

	@Override
	public void renderPart(String partName) {
		Tessellator tessellator = Tessellator.instance;
//		geometries.get(partName).render(tessellator);
	}

	@Override
	public void renderAllExcept(String... excludedGroupNames) {
		Set<String> excludedSet = new HashSet<String>(Arrays.asList(excludedGroupNames));
		Tessellator tessellator = Tessellator.instance;
//		for (Geometry geometry : geometries.values()) {
//			if (!excludedSet.contains(geometry.getId()))
//				geometry.render(tessellator);
//		}

	}

	public void renderAnimationAll(int frame) {
		Tessellator tessellator = Tessellator.instance;
		defaultScene.renderAnimation(tessellator, frame);
		// scenes.get("Scene").render(tessellator);
	}

}
