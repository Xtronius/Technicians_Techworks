package mod.xtronius.ttm.tileEntity.renderer;


import mod.xtronius.ttm.lib.Reference;
import mod.xtronius.ttm.tileEntity.renderer.model.ModelPressureChamberCore;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPressureChamberCore extends TileEntitySpecialRenderer {
    private final ModelPressureChamberCore modelPressureChamberCore;
    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ASSET, "textures/modelTextureMaps/ModelPressureChamberCore_TextureMap.png");

    public RenderPressureChamberCore() {
    	modelPressureChamberCore = new ModelPressureChamberCore();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glScalef(1.015625F, 1.015625F, 1.015625F);
        this.bindTexture(texture);
        modelPressureChamberCore.render();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
}
