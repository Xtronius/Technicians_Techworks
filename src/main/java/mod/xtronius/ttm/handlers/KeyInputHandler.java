package mod.xtronius.ttm.handlers;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
	
	Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
//        if(KeyBindings.keyBindingList.get(0).isPressed()) {
//        	EntityPlayer player = mc.thePlayer;
//        }
    }
}