package mod.xtronius.ttm.tileEntity.renderer;

import mod.xtronius.ttm.util.ResHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import mod.xtronius.ttm.tileEntity.psi.TileEntityPSIGuage;

public class RenderPSIGuage extends TileEntitySpecialRenderer {
	
	float texel = 1F/16F;
	
	private ResourceLocation block_side = ResHelper.getResourceLocation("textures/blocks/BlockPSIGuage_Side.png");
	private ResourceLocation block_front = ResHelper.getResourceLocation("textures/blocks/BlockPSIGuage_Front.png");
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void renderTileEntityAt (TileEntity tileEntity, double x, double y, double z, float partialTicks) {
		renderTileEntityAt( (TileEntityPSIGuage)tileEntity, x, y, z, partialTicks);
	}
	
	public void renderTileEntityAt(TileEntityPSIGuage tileEntity, double x, double y, double z, float partialTicks) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int meta = tileEntity.getBlockMetadata();
		int axis = 0;
		float rot = 0.0f;
		
//		if(meta < 2) axis = 1;
		
		if(axis == 0) {
	        if (meta == 3) 
	        	rot = 180.0f;
	        if (meta == 4) 
	        	rot = -90.0f;
	        if (meta == 5) 
	        	rot = 90.0f;
		} else {
			if(meta == 0) rot = -90f;
			if(meta == 1) rot = 90f;
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		String text = Math.round(tileEntity.getPSI()) + " PSI";
		GL11.glTranslated(0.5, 0, 0.5);
		if(axis == 0) {
			GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
			GL11.glTranslated(-0.5, 0, -0.5);
			GL11.glTranslated((1.0f/32.0f)*5, 0.55, 0.126);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
		} else {
			GL11.glRotatef(rot, 1.0F, 0.0F, 0.0F);
			GL11.glTranslated(-0.5, 0, -0.5);
			GL11.glTranslated((1.0f/32.0f)*5, 0.55, 0.126);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(180, 0, 0, 1);
		}
        
		FontRenderer fontrenderer = this.func_147498_b();
		float scale = (float) (1/(128 * Math.floor(fontrenderer.getStringWidth(text))/90));
		GL11.glScalef(scale, scale, scale);
		GL11.glDepthMask(false);
		
		fontrenderer.drawString(text, 0, 0, 8355711);
		
		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if(axis == 0) {
			GL11.glRotatef(-180, 0, 1, 0);
			GL11.glRotatef(-180, 0, 0, 1);
		} else {
			
		}
		GL11.glTranslated(0.5, 0, 0.5);
		if(axis == 0) {
			GL11.glRotatef(-rot, 0.0F, 1.0F, 0.0F);
			GL11.glTranslated(-0.5, 0, -0.5);
		    GL11.glTranslated(-0.0625, -0.55, -0.126);
		}
		else { 
			GL11.glRotatef(-rot, 1.0F, 0.0F, 0.0F);
			GL11.glTranslated(-0.5, 0, -0.5);
		    GL11.glTranslated(-0.0625, -0.55, -0.126);
		}
	    
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
