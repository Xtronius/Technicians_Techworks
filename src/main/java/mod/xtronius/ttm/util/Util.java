package mod.xtronius.ttm.util;

import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class Util {

	public static String getUnlocalizedNameInefficiently(Item item) {
        String s = item.getUnlocalizedName() + ".name";
        return s == null ? "" : StatCollector.translateToLocal(s);
    }
	
	public static String getUnwrappedUnlocalizedName(Item item) {
        return item.getUnlocalizedName().substring(item.getUnlocalizedName().indexOf(".") + 1);
    }
	
	public static String getUnwrappedUnlocalizedName(String name) {
        return name.substring(name.indexOf(".") + 1);
    }
}
