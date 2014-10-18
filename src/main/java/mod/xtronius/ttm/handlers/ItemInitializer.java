package mod.xtronius.ttm.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.item.ItemIDs;
import mod.xtronius.ttm.item.*;
import mod.xtronius.ttm.lib.Reference;
import net.minecraft.item.Item;


public class ItemInitializer {

	public static ArrayList<String> itemNames = new ArrayList<String>();
	public static HashMap<String, Item> items = new HashMap<String, Item>();
	
	public static ItemInitializer INSTANCE;

	public ItemInitializer() {
		INSTANCE = this;
		init();
	}
	
	private void init() {
		addItem(new ItemPSIGuage(), "ItemPSIGuage", true);
	}
	
	private void addItem(Item item, String name, boolean addItemToCreativeTab) {
		item.setUnlocalizedName(name);
		item.setTextureName(Reference.MOD_ASSET + ':' + name);
		if(addItemToCreativeTab) item.setCreativeTab(TTM.tabItems);
		ItemIDs.genNewItemIDObj(name); 
		itemNames.add(name); 
		items.put(name, item); 
	}
	
//	public static void addToItemReg(String name) { Item.itemRegistry.addObject(ItemIDs.getItemID(name), name, items.get(name)); }
	public static Item getItemByName(String name) { return items.get(name); }
}