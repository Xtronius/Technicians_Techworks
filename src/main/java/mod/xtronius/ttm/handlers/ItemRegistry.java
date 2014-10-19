package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.core.TTM;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {
	
	public ItemRegistry() {
		for(String name : TTM.Items.itemNames) { 
			Item item = TTM.Items.getItemByName(name);
			regItemAuto(item, name);
		}
		
		regItemManual();
	}

	private void regItemAuto(Item item, String name) {	
		GameRegistry.registerItem(item, name);
	}
	
	private void regItemManual() {}
}
