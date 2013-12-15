package net.minecraftforge.client.model.collada;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.soap.Node;

import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.ModelFormatException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ColladaMesh {

	private List<Face> faces;

	public ColladaMesh() {
		faces = new LinkedList<>();
	}

	public void addFace(Face face) {
		faces.add(face);
	}

	public void render(Tessellator tessellator) {
		for (Face face : faces) {
			face.render(tessellator);
		}
	}
}
