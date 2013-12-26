package net.minecraftforge.client.model;

public interface IAnimatedModelCustom extends IModelCustom {

	// Returns the animation length in seconds
	double getLength();
	// Returns the animation lengths in Minecraft's ticks (1/20 of a second)
	int getLengthTicks();
	
    void renderAnimationAll(double time);
    void renderAnimationOnly(double time, String... groupNames);
    void renderAnimationPart(double time, String partName);
    void renderAnimationAllExcept(double time, String... excludedGroupNames);
}
