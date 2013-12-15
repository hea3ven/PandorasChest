package net.minecraftforge.client.model.collada;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.Tessellator;

public class Scene {

	private String id;
	private List<SceneNode> nodes;

	public Scene() {
		nodes = new LinkedList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addNode(SceneNode node) {
		nodes.add(node);
	}

	public void render(Tessellator tessellator) {
		for (SceneNode node : nodes) {
			node.render(tessellator);
		}
	}
	
	public void renderAnimation(Tessellator tessellator, int frame) {
		for (SceneNode node : nodes) {
			node.renderAnimation(tessellator, frame);
		}
	}

}
