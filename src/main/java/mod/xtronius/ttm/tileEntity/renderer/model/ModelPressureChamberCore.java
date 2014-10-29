package mod.xtronius.ttm.tileEntity.renderer.model;

import mod.xtronius.ttm.util.ResHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPressureChamberCore {
    private IModelCustom modelPressureChamberCore;
    
    public static final String MODEL_LOCATION = "models/";

    public ModelPressureChamberCore() {
    	modelPressureChamberCore = AdvancedModelLoader.loadModel(ResHelper.getResourceLocation(MODEL_LOCATION + "ModelPressureChamberCore_2.obj"));
    }

    public void render() {
//    	modelPressureChamberCore.renderAll();
    }
}
