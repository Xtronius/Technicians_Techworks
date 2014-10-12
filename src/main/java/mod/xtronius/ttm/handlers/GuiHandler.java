package mod.xtronius.ttm.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	
		switch(id) {}
		return null;
	}
	
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	
		switch(id) {}
		return null;
	}
}