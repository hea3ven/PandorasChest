package net.minecraftforge.client.model.collada;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.Node;

import net.minecraft.client.renderer.Tessellator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Geometry {

	private String id;
	private ColladaMesh mesh;

	public Geometry() {
		this.id = null;
		this.mesh = null;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMesh(ColladaMesh mesh) {
		this.mesh = mesh;
	}

	public void render(Tessellator tessellator) {
		this.mesh.render(tessellator);
	}

}
