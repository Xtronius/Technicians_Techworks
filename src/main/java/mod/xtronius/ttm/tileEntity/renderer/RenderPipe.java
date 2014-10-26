package mod.xtronius.ttm.tileEntity.renderer;

import mod.xtronius.ttm.lib.Reference;
import mod.xtronius.ttm.proxy.ClientProxy;
import mod.xtronius.ttm.tileEntity.TileEntityPipe;
import mod.xtronius.ttm.tileEntity.psi.IPipe;
import mod.xtronius.ttm.util.DirHelper;
import mod.xtronius.ttm.util.ResHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderPipe extends TileEntitySpecialRenderer {
	
	public static RenderPipe instance;
	
	public RenderPipe() {
		instance = this;
	}
	
	float pixel = 1F/16F;
	float texel = 1F/32F;
	
	private ResourceLocation texture = ResHelper.getResourceLocation("textures/blocks/BlockPipe.png");
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void renderTileEntityAt (TileEntity tileEntity, double x, double y, double z, float partialTicks) {
		if(tileEntity instanceof IPipe)
		renderTileEntityAt((IPipe)tileEntity, x, y, z, partialTicks);
	}
	
	public void renderTileEntityAt(IPipe pipe, double x, double y, double z, float partialTicks) {
		GL11.glTranslated(x, y, z);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		this.bindTexture(texture);
	
		if(DirHelper.doDirsOnlyPertainToOneAxis(pipe.getConnections())) {
			for(int i = 0; i < pipe.getConnections().length; i++) 
				if(pipe.getConnections()[i] != null) { drawStraight(pipe.getConnections()[i], x, y, z); break; }
		} else {
			this.drawCore(x, y, z);
			for(int i = 0; i < pipe.getConnections().length; i++) 
				if(pipe.getConnections()[i] != null) drawConnection(pipe.getConnections()[i], true, x, y, z);
		}
			
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-x, -y, -z);
	}
	
	public void drawConnection(ForgeDirection dir, boolean renderAlpha, double x, double y, double z) {	
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		{
			GL11.glTranslated(0.5, 0.5, 0.5);
			if(dir.equals(ForgeDirection.DOWN)) GL11.glRotatef(180, 1, 0, 0);
			else if(dir.equals(ForgeDirection.NORTH)) GL11.glRotatef(270, 1, 0, 0);
			else if(dir.equals(ForgeDirection.SOUTH)) GL11.glRotatef(90, 1, 0, 0);
			else if(dir.equals(ForgeDirection.EAST)) GL11.glRotatef(270, 0, 0, 1);
			else if(dir.equals(ForgeDirection.WEST)) GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslated(-0.5, -0.5, -0.5);
			
			float dist = (pixel*10);
			if(this.mc.isFancyGraphicsEnabled()) {
				tes.addVertexWithUV(1-dist, dist, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, dist, 14*texel, 1*texel);
				tes.addVertexWithUV(dist, dist, dist, 14*texel, 1*texel);
				
				tes.addVertexWithUV(dist, dist, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, 1-dist, 14*texel, 1*texel);
				tes.addVertexWithUV(1-dist, dist, 1-dist, 14*texel, 1*texel);
				
				tes.addVertexWithUV(dist, dist, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, 1-dist, 14*texel, 1*texel);
				tes.addVertexWithUV(dist, dist, 1-dist, 14*texel, 1*texel);
				
				tes.addVertexWithUV(1-dist, dist, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, dist, 14*texel, 1*texel);
				tes.addVertexWithUV(1-dist, dist, dist, 14*texel, 1*texel);
			}
			
			tes.addVertexWithUV(dist, dist, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, dist, 14*texel, 1*texel);
			tes.addVertexWithUV(1-dist, dist, dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(1-dist, dist, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, 1-dist, 14*texel, 1*texel);
			tes.addVertexWithUV(dist, dist, 1-dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(dist, dist, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, dist, 14*texel, 1*texel);
			tes.addVertexWithUV(dist, dist, dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(1-dist, dist, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, 1-dist, 14*texel, 1*texel);
			tes.addVertexWithUV(1-dist, dist, 1-dist, 14*texel, 1*texel);	

			tes.draw();
		
			GL11.glTranslated(0.5, 0.5, 0.5);
			if(dir.equals(ForgeDirection.DOWN)) GL11.glRotatef(-180, 1, 0, 0);
			else if(dir.equals(ForgeDirection.NORTH)) GL11.glRotatef(-270, 1, 0, 0);
			else if(dir.equals(ForgeDirection.SOUTH)) GL11.glRotatef(-90, 1, 0, 0);
			else if(dir.equals(ForgeDirection.EAST)) GL11.glRotatef(-270, 0, 0, 1);
			else if(dir.equals(ForgeDirection.WEST)) GL11.glRotatef(-90, 0, 0, 1);
			GL11.glTranslated(-0.5, -0.5, -0.5);
		}
	}
	
	public void drawCore(double x, double y, double z) {
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		{
			
			float dist = (pixel*10.5F);
			
			float texBRU = 12*texel;
			float texBRV = 12 *texel;
			float texTRU = 12*texel;
			float tetTRV = 0;
			float texTLU = 0;
			float texTLV = 0;
			float texBLU = 0;
			float texBLV = 12*texel;
			
			if(mc.isFancyGraphicsEnabled()) {	
				tes.addVertexWithUV(dist, dist, dist, texBLU, texBLV);
				tes.addVertexWithUV(dist, 1-dist, dist, texTLU, texTLV);
				tes.addVertexWithUV(1-dist, 1-dist, dist, texTRU, tetTRV);
				tes.addVertexWithUV(1-dist, dist, dist, texBRU, texBRV);
	
				tes.addVertexWithUV(dist, dist, 1-dist, texBLU, texBLV);
				tes.addVertexWithUV(dist, 1-dist, 1-dist, texTLU, texTLV);
				tes.addVertexWithUV(dist, 1-dist, dist, texTRU, tetTRV);
				tes.addVertexWithUV(dist, dist, dist, texBRU, texBRV);
				
				tes.addVertexWithUV(1-dist, dist, 1-dist, texBLU, texBLV);
				tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTLU, texTLV);
				tes.addVertexWithUV(dist, 1-dist, 1-dist, texTRU, tetTRV);
				tes.addVertexWithUV(dist, dist, 1-dist, texBRU, texBRV);
	
				tes.addVertexWithUV(1-dist, dist, dist, texBLU, texBLV);
				tes.addVertexWithUV(1-dist, 1-dist, dist, texTLU, texTLV);
				tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTRU, tetTRV);
				tes.addVertexWithUV(1-dist, dist, 1-dist, texBRU, texBRV);
				
				tes.addVertexWithUV(1-dist, dist, dist, texBLU, texBLV);
				tes.addVertexWithUV(1-dist, dist, 1-dist, texTLU, texTLV);
				tes.addVertexWithUV(dist, dist, 1-dist, texTRU, tetTRV);
				tes.addVertexWithUV(dist, dist, dist, texBRU, texBRV);
				
				tes.addVertexWithUV(dist, 1-dist, dist, texBRU, texBRV);
				tes.addVertexWithUV(dist, 1-dist, 1-dist, texTRU, tetTRV);
				tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTLU, texTLV);
				tes.addVertexWithUV(1-dist, 1-dist, dist, texBLU, texBLV);
			}
			
			tes.addVertexWithUV(1-dist, dist, dist, texBRU, texBRV);
			tes.addVertexWithUV(1-dist, 1-dist, dist, texTRU, tetTRV);
			tes.addVertexWithUV(dist, 1-dist, dist, texTLU, texTLV);
			tes.addVertexWithUV(dist, dist, dist, texBLU, texBLV);
			
			tes.addVertexWithUV(dist, dist, dist, texBRU, texBRV);
			tes.addVertexWithUV(dist, 1-dist, dist, texTRU, tetTRV);
			tes.addVertexWithUV(dist, 1-dist, 1-dist, texTLU, texTLV);
			tes.addVertexWithUV(dist, dist, 1-dist, texBLU, texBLV);
			
			tes.addVertexWithUV(dist, dist, 1-dist, texBRU, texBRV);
			tes.addVertexWithUV(dist, 1-dist, 1-dist, texTRU, tetTRV);
			tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTLU, texTLV);
			tes.addVertexWithUV(1-dist, dist, 1-dist, texBLU, texBLV);
			
			tes.addVertexWithUV(1-dist, dist, 1-dist, texBRU, texBRV);
			tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTRU, tetTRV);
			tes.addVertexWithUV(1-dist, 1-dist, dist, texTLU, texTLV);
			tes.addVertexWithUV(1-dist, dist, dist, texBLU, texBLV);
			
			tes.addVertexWithUV(dist, dist, dist, texBRU, texBRV);
			tes.addVertexWithUV(dist, dist, 1-dist, texTRU, tetTRV);
			tes.addVertexWithUV(1-dist, dist, 1-dist, texTLU, texTLV);
			tes.addVertexWithUV(1-dist, dist, dist, texBLU, texBLV);
			
			tes.addVertexWithUV(1-dist, 1-dist, dist, texBLU, texBLV);
			tes.addVertexWithUV(1-dist, 1-dist, 1-dist, texTLU, texTLV);
			tes.addVertexWithUV(dist, 1-dist, 1-dist, texTRU, tetTRV);
			tes.addVertexWithUV(dist, 1-dist, dist, texBRU, texBRV);
		}
		
		tes.draw();
	}
	
	public void drawStraight(ForgeDirection dir, double x, double y, double z) {

		float dist = (pixel*10);
		
		Tessellator tes = Tessellator.instance;
		tes.startDrawingQuads();
		{
			GL11.glTranslated(0.5, 0.5, 0.5);
			if(dir.equals(ForgeDirection.DOWN)) GL11.glRotatef(180, 1, 0, 0);
			else if(dir.equals(ForgeDirection.NORTH)) GL11.glRotatef(270, 1, 0, 0);
			else if(dir.equals(ForgeDirection.SOUTH)) GL11.glRotatef(90, 1, 0, 0);
			else if(dir.equals(ForgeDirection.EAST)) GL11.glRotatef(270, 0, 0, 1);
			else if(dir.equals(ForgeDirection.WEST)) GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslated(-0.5, -0.5, -0.5);
			
			if(this.mc.isFancyGraphicsEnabled()) {
				tes.addVertexWithUV(1-dist, 0, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, dist, 28*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, dist, 28*texel, 1*texel);
				tes.addVertexWithUV(dist, 0, dist, 14*texel, 1*texel);//
				
				tes.addVertexWithUV(dist, 0, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, 1-dist, 28*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, 1-dist, 28*texel, 1*texel);
				tes.addVertexWithUV(1-dist, 0, 1-dist, 14*texel, 1*texel);//
				
				tes.addVertexWithUV(dist, 0, dist, 14*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, dist, 28*texel, 11*texel);
				tes.addVertexWithUV(dist, 1, 1-dist, 28*texel, 1*texel);
				tes.addVertexWithUV(dist, 0, 1-dist, 14*texel, 1*texel);//
				
				tes.addVertexWithUV(1-dist, 0, 1-dist, 14*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, 1-dist, 28*texel, 11*texel);
				tes.addVertexWithUV(1-dist, 1, dist, 28*texel, 1*texel);
				tes.addVertexWithUV(1-dist, 0, dist, 14*texel, 1*texel);//
			}	
			
			tes.addVertexWithUV(dist, 0, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, dist, 28*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, dist, 28*texel, 1*texel);
			tes.addVertexWithUV(1-dist, 0, dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(1-dist, 0, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, 1-dist, 28*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, 1-dist, 28*texel, 1*texel);
			tes.addVertexWithUV(dist, 0, 1-dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(dist, 0, 1-dist, 14*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, 1-dist, 28*texel, 11*texel);
			tes.addVertexWithUV(dist, 1, dist, 28*texel, 1*texel);
			tes.addVertexWithUV(dist, 0, dist, 14*texel, 1*texel);
			
			tes.addVertexWithUV(1-dist, 0, dist, 14*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, dist, 28*texel, 11*texel);
			tes.addVertexWithUV(1-dist, 1, 1-dist, 28*texel, 1*texel);
			tes.addVertexWithUV(1-dist, 0, 1-dist, 14*texel, 1*texel);
		}
		tes.draw();
		
		GL11.glTranslated(0.5, 0.5, 0.5);
		if(dir.equals(ForgeDirection.DOWN)) GL11.glRotatef(-180, 1, 0, 0);
		else if(dir.equals(ForgeDirection.NORTH)) GL11.glRotatef(-270, 1, 0, 0);
		else if(dir.equals(ForgeDirection.SOUTH)) GL11.glRotatef(-90, 1, 0, 0);
		else if(dir.equals(ForgeDirection.EAST)) GL11.glRotatef(-270, 0, 0, 1);
		else if(dir.equals(ForgeDirection.WEST)) GL11.glRotatef(-90, 0, 0, 1);
		GL11.glTranslated(-0.5, -0.5, -0.5);
	}
}
