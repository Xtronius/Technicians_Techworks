//package mod.xtronius.ttm.handlers;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import mod.xtronius.ttm.tileEntity.TileEntityPipe;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.EntityClientPlayerMP;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//import cpw.mods.fml.common.eventhandler.SubscribeEvent;
//
//public class AlphaRenderingHandler {
//
//	private final List<TileEntityPipe> alphas = new ArrayList<TileEntityPipe>();
//
//
//	@SubscribeEvent
//	public void onRenderLastEvent (RenderWorldLastEvent event) {
//		
//		alphas.clear();
//
//		for ( Object o : event.context.tileEntities ) {
//			if ( o instanceof TileEntityPipe) {
//				alphas.add( (TileEntityPipe)o );
//			}
//		}
//
//		if ( alphas.isEmpty() ) return;
//
//		final EntityClientPlayerMP thePlayer = Minecraft.getMinecraft().thePlayer;
//		final double partialTicks = (double)event.partialTicks;
//		final double playerX = thePlayer.prevPosX + (thePlayer.posX - thePlayer.prevPosX) * partialTicks;
//		final double playerY = thePlayer.prevPosY + (thePlayer.posY - thePlayer.prevPosY) * partialTicks;
//		final double playerZ = thePlayer.prevPosZ + (thePlayer.posZ - thePlayer.prevPosZ) * partialTicks;
//
//		for ( TileEntityPipe alpha : alphas )
//			alpha.setDistanceToPlayer( alpha.getDistanceFrom( playerX, playerY, playerZ ) );
//
//		Collections.sort( alphas );
//
//		for ( TileEntityPipe alpha : alphas ) {
//
//			// Translate off the player
//			final double x = (double)alpha.xCoord - playerX;
//			final double y = (double)alpha.yCoord - playerY;
//			final double z = (double)alpha.zCoord - playerZ;
//
//			// Old school render
//			AlphaRenderer.renderTileEntityAt( alpha, x, y, z, event.partialTicks );
//		}
//
//
//	}
//
//
//}
