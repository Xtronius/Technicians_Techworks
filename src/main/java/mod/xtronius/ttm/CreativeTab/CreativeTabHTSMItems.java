package mod.xtronius.ttm.CreativeTab;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
public class CreativeTabHTSMItems extends CreativeTabs {
 
    public CreativeTabHTSMItems(int id, String unlocalizedName) {
 
        super(id, unlocalizedName);
        this.setBackgroundImageName("item_search.png");
    }
 
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Items.baked_potato;
    }
    
    public boolean hasSearchBar() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List itemList) {
    	super.displayAllReleventItems(itemList);
    }
    
    public void addMetaDataItems(List list, Item item, int range) {
         
        for (int i = 0; i < range; ++i) {
        	list.add(new ItemStack(item, 1, i));
        }
    }
    
}