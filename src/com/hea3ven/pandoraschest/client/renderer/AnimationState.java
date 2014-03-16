package com.hea3ven.pandoraschest.client.renderer;

import com.hea3ven.pandoraschest.PandorasChestMod;

import net.minecraft.util.ResourceLocation;

public class AnimationState {
	private double frame;
	private boolean lock;
	private boolean repeat;
	private ResourceLocation resource;

	public AnimationState() {
		frame = 0.0d;
	}

	public void setAnimation(ResourceLocation resource) {
		this.setResource(resource);
		frame = 0.0d;
	}
	
	public boolean addFrame() {
		if(!lock)
		{
			frame += 0.05d;
			if(PandorasChestMod.modelManager.getModel(getResource()).getAnimationLength() <= frame)
			{
				setLockFrame(lock);
				return false;
			}
		}
		return true;
	}

	public boolean isLockFrame() {
		return lock;
	}

	public void setLockFrame(boolean lock) {
		this.lock = lock;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public ResourceLocation getResource() {
		return resource;
	}

	public void setResource(ResourceLocation resource) {
		this.resource = resource;
	}

	public void render() {
		PandorasChestMod.modelManager.getModel(getResource()).renderAnimationAll(frame);
	}
}
