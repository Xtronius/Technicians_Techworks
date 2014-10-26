package mod.xtronius.ttm.util;

import java.util.Iterator;

import net.minecraft.tileentity.TileEntity;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.McBlockPart;

public class MultiPartHelper {
	
	public static scala.collection.Iterator getParts(TileEntity tileEntity) {
		scala.collection.Iterator result = null;
		
		if(tileEntity != null && tileEntity instanceof TileMultipart)
			result = (scala.collection.Iterator) ((TileMultipart) tileEntity).partList().iterator();
		
		return result;
	}

}
