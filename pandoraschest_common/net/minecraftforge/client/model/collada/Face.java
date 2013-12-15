package net.minecraftforge.client.model.collada;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.obj.Vertex;

public class Face {

	private Vec3[] vertex;
	private Vec3[] vertexNormals;
	private Vec3[] vertexTexCoord;

	public Face() {
		vertex = new Vec3[3];
		vertexNormals = new Vec3[3];
		vertexTexCoord = new Vec3[3];
	}

	public void addVertex(int i, Vec3 position, Vec3 normal, Vec3 texcoord) {
		vertex[i] = position;
		vertexNormals[i] = normal;
		vertexTexCoord[i] = texcoord;

	}

	public void render(Tessellator tessellator) {

		Vec3 faceNormal = calculateFaceNormal();
		tessellator.setNormal((float) -faceNormal.xCoord, (float) -faceNormal.yCoord, (float) -faceNormal.zCoord);

		float averageU = 0F;
		float averageV = 0F;

		for (int i = 0; i < vertexTexCoord.length; ++i) {
			averageU += vertexTexCoord[i].xCoord;
			averageV += vertexTexCoord[i].yCoord;
		}

		averageU = averageU / vertexTexCoord.length;
		averageV = averageV / vertexTexCoord.length;

		float offsetU, offsetV;

		for (int i = 0; i < vertex.length; i++) {
			offsetU = 0.0005F;
			offsetV = 0.0005F;

			if (vertexTexCoord[i].xCoord > averageU) {
				offsetU = -offsetU;
			}
			if (vertexTexCoord[i].yCoord > averageV) {
				offsetV = -offsetV;
			}

			tessellator.addVertexWithUV(vertex[i].xCoord, vertex[i].yCoord, vertex[i].zCoord, vertexTexCoord[i].xCoord
					+ offsetU, 1 - vertexTexCoord[i].yCoord - offsetV);
		}
	}

	private Vec3 calculateFaceNormal() {
		Vec3 v1 = Vec3.createVectorHelper(vertex[1].xCoord - vertex[0].xCoord,
				vertex[1].yCoord - vertex[0].yCoord,
				vertex[1].zCoord - vertex[0].zCoord);
		Vec3 v2 = Vec3.createVectorHelper(vertex[2].xCoord - vertex[0].xCoord,
				vertex[2].yCoord - vertex[0].yCoord,
				vertex[2].zCoord - vertex[0].zCoord);
		Vec3 normalVector = null;

		return v1.crossProduct(v2).normalize();

	}
}
