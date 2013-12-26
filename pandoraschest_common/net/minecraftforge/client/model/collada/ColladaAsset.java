package net.minecraftforge.client.model.collada;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Tuple;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.ModelFormatException;

import org.lwjgl.opengl.GL11;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import scala.reflect.internal.Mirrors.Roots;

public class ColladaAsset {

	private Map<String, Geometry> geometries;
	private Map<String, Model> models;
	private Map<String, Transform> transforms;
	private List<Tuple> animations;
	private String rootScene;
	public String xAxis;
	public String zAxis;
	public String yAxis;

	public ColladaAsset() {
		geometries = new HashMap<>();
		models = new HashMap<>();
		transforms = new HashMap<>();
		animations = new LinkedList<>();
		rootScene = null;
	}

	public void SetUpAxis(String upAxis) {
		switch (upAxis) {
		case "X_UP":
			xAxis = "Z";
			yAxis = "X";
			zAxis = "Y";
			break;
		case "Y_UP":
		default:
			xAxis = "X";
			yAxis = "Y";
			zAxis = "Z";
			break;
		case "Z_UP":
			xAxis = "Y";
			yAxis = "Z";
			zAxis = "X";
			break;
		}
	}

	public void addGeometry(String id, Geometry geom) {
		geometries.put(id, geom);
	}

	public Geometry getGeometry(String url) {
		return geometries.get(url);
	}

	public void addScene(String id, Model scene) {
		models.put(id, scene);
	}

	private int GetAxisIndex(String axis) {
		switch (axis) {
		case "X":
		default:
			return 0;
		case "Y":
			return 1;
		case "Z":
			return 2;
		}
	}

	public int GetXAxisIndex() {
		return GetAxisIndex(xAxis);
	}

	public int GetYAxisIndex() {
		return GetAxisIndex(yAxis);
	}

	public int GetZAxisIndex() {
		return GetAxisIndex(zAxis);
	}

	public void addTransform(String url, Transform trans) {
		transforms.put(url, trans);
	}

	public Model GetModel(String sceneId) {
		Model scene = models.get(sceneId);

		return scene;
	}

	public void assignAnimations(String sceneId) {
		for (Tuple t : animations) {
			String target = (String) t.getFirst();
			Animation anim = (Animation) t.getSecond();

			String geomName = target.substring(0, (target.contains(".")) ? target.indexOf('.') : (target.length() - 1));
			String paramName = (target.contains(".")) ? target.substring(target.indexOf('.') + 1) : null;

			if (paramName.equals(xAxis))
				paramName = "X";
			else if (paramName.equals(yAxis))
				paramName = "Y";
			else if (paramName.equals(zAxis))
				paramName = "Z";
			transforms.get(sceneId + "/" + geomName).addAnimation(paramName, anim);
		}
	}

	public void addAnimation(String target, Animation animation) {
		animations.add(new Tuple(target, animation));
	}

	public Vec3 toMinecraftCoords(double x, double y, double z) {
		return toMinecraftCoords(Vec3.createVectorHelper(x, y, z));
	}

	public Vec3 toMinecraftCoords(Vec3 vec) {
		if (yAxis.equals("X"))
			return Vec3.createVectorHelper(vec.zCoord, vec.yCoord, vec.zCoord); // TODO: fix
		else if (yAxis.equals("Y"))
			return Vec3.createVectorHelper(vec.xCoord, vec.zCoord, vec.yCoord); // TODO: fix
		else if (yAxis.equals("Z"))
			return Vec3.createVectorHelper(vec.yCoord, vec.zCoord, vec.xCoord);
		else
			return null;
	}
}
