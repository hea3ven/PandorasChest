package net.minecraftforge.client.model.collada;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;

public class SceneNode {

	private List<Transform> transforms;
	private Geometry geometry;

	public SceneNode() {
		this.transforms = new LinkedList<>();
		this.geometry = null;
	}

	public void addTransform(Transform transform) {
		this.transforms.add(transform);
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Geometry getGeometry() {
		return this.geometry;
	}

	public void render(Tessellator tessellator) {
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		GL11.glPushMatrix();
		
		for (Transform trans : transforms) {
			trans.apply();
		}
		
		geometry.render(tessellator);
		
		tessellator.draw();
		GL11.glPopMatrix();
	}
	
	public void renderAnimation(Tessellator tessellator, int frame) {
		tessellator.startDrawing(GL11.GL_TRIANGLES);
		GL11.glPushMatrix();
		
		for (Transform trans : transforms) {
			trans.applyAnimation(frame);
		}
		
		geometry.render(tessellator);
		
		tessellator.draw();
		GL11.glPopMatrix();
	}

}
