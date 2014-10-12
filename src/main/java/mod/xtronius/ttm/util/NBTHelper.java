package mod.xtronius.ttm.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.UUID;

public class NBTHelper {

	public static boolean hasTag(ItemStack itemStack, String keyName) {
		return itemStack != null && itemStack.stackTagCompound != null
				&& itemStack.stackTagCompound.hasKey(keyName);
	}

	public static void removeTag(ItemStack itemStack, String keyName) {
		if (itemStack.stackTagCompound != null) {
			itemStack.stackTagCompound.removeTag(keyName);
		}
	}
	
	public static boolean hasUUID(ItemStack itemStack) {
        return hasTag(itemStack, "UUIDMostSig") && hasTag(itemStack, "UUIDLeastSig");
    }

    public static void setUUID(ItemStack itemStack) {
        initNBTTagCompound(itemStack);

        if (!hasTag(itemStack, "UUIDMostSig") && !hasTag(itemStack, "UUIDLeastSig")) {
            UUID itemUUID = UUID.randomUUID();
            setLong(itemStack, "UUIDMostSig", itemUUID.getMostSignificantBits());
            setLong(itemStack, "UUIDLeastSig", itemUUID.getLeastSignificantBits());
        }
    }

	private static void initNBTTagCompound(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			itemStack.setTagCompound(new NBTTagCompound());
		}
	}

	public static void setLong(ItemStack itemStack, String keyName, long keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setLong(keyName, keyValue);
	}

	public static String getString(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setString(itemStack, keyName, "");
		}

		return itemStack.stackTagCompound.getString(keyName);
	}

	public static void setString(ItemStack itemStack, String keyName, String keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setString(keyName, keyValue);
	}

	public static boolean getBoolean(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setBoolean(itemStack, keyName, false);
		}

		return itemStack.stackTagCompound.getBoolean(keyName);
	}

	public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setBoolean(keyName, keyValue);
	}

	public static byte getByte(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setByte(itemStack, keyName, (byte) 0);
		}

		return itemStack.stackTagCompound.getByte(keyName);
	}

	public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setByte(keyName, keyValue);
	}

	public static short getShort(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setShort(itemStack, keyName, (short) 0);
		}

		return itemStack.stackTagCompound.getShort(keyName);
	}

	public static void setShort(ItemStack itemStack, String keyName, short keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setShort(keyName, keyValue);
	}

	public static int getInt(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setInteger(itemStack, keyName, 0);
		}

		return itemStack.stackTagCompound.getInteger(keyName);
	}

	public static void setInteger(ItemStack itemStack, String keyName,
			int keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setInteger(keyName, keyValue);
	}
	
	public static long getLong(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setLong(itemStack, keyName, 0);
		}

		return itemStack.stackTagCompound.getLong(keyName);
	}

	public static float getFloat(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setFloat(itemStack, keyName, 0);
		}

		return itemStack.stackTagCompound.getFloat(keyName);
	}

	public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setFloat(keyName, keyValue);
	}

	public static double getDouble(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setDouble(itemStack, keyName, 0);
		}

		return itemStack.stackTagCompound.getDouble(keyName);
	}

	public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setDouble(keyName, keyValue);
	}

	public static NBTTagList getTagList(ItemStack itemStack, String keyName, int nbtBaseType) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setTagList(itemStack, keyName, new NBTTagList());
		}

		return itemStack.stackTagCompound.getTagList(keyName, nbtBaseType);
	}

	public static void setTagList(ItemStack itemStack, String keyName,
			NBTTagList nbtTagList) {
		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setTag(keyName, nbtTagList);
	}

	public static NBTTagCompound getTagCompound(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);

		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setTagCompound(itemStack, keyName, new NBTTagCompound());
		}

		return itemStack.stackTagCompound.getCompoundTag(keyName);
	}
	
	public static void setTagCompound(ItemStack itemStack, String keyName, NBTTagCompound nbtTagCompound) {

		initNBTTagCompound(itemStack);

		itemStack.stackTagCompound.setTag(keyName, nbtTagCompound);
	}
}