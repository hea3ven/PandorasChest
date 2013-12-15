package hea3ven.pandoraschest.client.gui;

import hea3ven.pandoraschest.inventory.ContainerPandorasChest;
import hea3ven.pandoraschest.tileentity.TileEntityPandorasChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiPandorasChest extends GuiContainer {

	private static final ResourceLocation field_110421_t = new ResourceLocation(
			"textures/gui/container/generic_54.png");
	private IInventory playerInventory;
	private TileEntityPandorasChest chestInventory;

	public GuiPandorasChest(IInventory playerInventory,
			TileEntityPandorasChest chestInventory) {
		super(new ContainerPandorasChest(playerInventory, chestInventory));
		this.playerInventory = playerInventory;
		this.chestInventory = chestInventory;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(
				this.chestInventory.isInvNameLocalized() ? this.chestInventory
						.getInvName() : I18n.getString(this.chestInventory
						.getInvName()), 8, 6, 4210752);
		this.fontRenderer.drawString(
				((Integer)this.chestInventory.selectedChest).toString(), 140, 6, 4210752);
		this.fontRenderer
				.drawString(
						this.playerInventory.isInvNameLocalized() ? this.playerInventory
								.getInvName() : I18n
								.getString(this.playerInventory.getInvName()),
						8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(field_110421_t);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
		this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
	}

}
