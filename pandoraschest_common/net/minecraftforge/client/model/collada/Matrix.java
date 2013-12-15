package net.minecraftforge.client.model.collada;

import java.nio.DoubleBuffer;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

public class Matrix extends Transform {
	private DoubleBuffer matrix;
	public Matrix(String id, DoubleBuffer matrix) {
		this.matrix = matrix;
	}

	public DoubleBuffer getMatrix() {
		return matrix;
	}

	public void setMatrix(DoubleBuffer matrix) {
		this.matrix = matrix;
	}

	@Override
	public void apply() {
//		matrix.rewind();
//		GL11.glLoadMatrix(matrix);
	}

	@Override
	public void applyAnimation(int frame) {
		apply();
	}

	@Override
	public void setAnimation(String paramName, Animation anim) {
		// TODO Auto-generated method stub
		
	}

}
