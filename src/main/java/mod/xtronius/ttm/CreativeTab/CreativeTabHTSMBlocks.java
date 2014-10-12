package mod.xtronius.ttm.CreativeTab;

import java.util.List;

import mod.xtronius.ttm.core.TTM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
 
public class CreativeTabHTSMBlocks extends CreativeTabs {
 
    public CreativeTabHTSMBlocks(int id, String unlocalizedName) {
        super(id, unlocalizedName);
        this.setBackgroundImageName("item_search.png");
    }
 
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Items.arrow;
    }
    
    public boolean hasSearchBar() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List blockList) {
    	super.displayAllReleventItems(blockList);
    }
}