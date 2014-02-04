package com.hea3ven.pandoraschest.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import com.hea3ven.pandoraschest.PandorasChestMod;
import com.hea3ven.pandoraschest.client.model.ModelPandorasChest;
import com.hea3ven.pandoraschest.tileentity.TileEntityDecorativeChest;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class TileEntityPandorasChestRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    public static ModelPandorasChest modelChest = new ModelPandorasChest("chest2_blender.dae");

    // public static ModelPandorasChest modelChest2 = new
    // ModelPandorasChest("/assets/PandorasChest/models/chest_open_3ds_ad.DAE");

    @Override
    // public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
    // double z, float f) {
    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f)
    {
        TileEntityDecorativeChest tileEntityPandorasChest = (TileEntityDecorativeChest) tileEntity;
        bindTexture(new ResourceLocation("pandoraschest", "models/chest.png"));
        // func_147499_a(new ResourceLocation("pandoraschest",
        // "models/chest.png"));
        modelChest.render(tileEntityPandorasChest, x, y, z);
    }

    private void bindTexture(ResourceLocation resourceLocation)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(resourceLocation);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        bindTexture(new ResourceLocation("pandoraschest", "models/chest.png"));
        modelChest.renderItem();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        // TileEntity tileEntityPandorasChest = world.func_147438_o(x, y, z);
        // func_147500_a(tileEntityPandorasChest, x, y, z, 0);
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        // TODO Auto-generated method stub
        return PandorasChestMod.pandorasChestRenderId;
    }

}
