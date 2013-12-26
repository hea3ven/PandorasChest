package net.minecraftforge.client.model.collada;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.soap.Node;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Geometry {

	private String name;
	private List<Transform> transforms;
	private List<Face> faces;

	public Geometry() {
		this.name = null;
		this.transforms = new LinkedList<>();
		this.faces = new LinkedList<>();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTransform(Transform transform) {
		this.transforms.add(transform);
	}

	public void addFace(Face face) {
		faces.add(face);
	}

	public void render(Tessellator tessellator) {
		GL11.glPushMatrix();
		
		for (Transform trans : transforms) {
			trans.apply();
		}
		
		for (Face face : faces) {
			face.render(tessellator);
		}
		
		GL11.glPopMatrix();
	}

	public void renderAnimation(Tessellator tessellator, int frame) {
		GL11.glPushMatrix();
		
		for (Transform trans : transforms) {
			trans.applyAnimation(frame);
		}
		
		for (Face face : faces) {
			face.render(tessellator);
		}
		
		GL11.glPopMatrix();
	}

}
