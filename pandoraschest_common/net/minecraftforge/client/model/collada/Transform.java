package net.minecraftforge.client.model.collada;

import java.util.LinkedList;
import java.util.List;

public abstract class Transform {

	public Transform() {
	}

	public abstract void apply();
	public abstract void applyAnimation(int frame);

	public abstract void addAnimation(String paramName, Animation anim);
}
