package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BundleItem;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class BundleBackportish {

    public static final String MOD_NAME = "bundle-backportish";

    public static EnumMap<Color, BundleItem> BUNDLES = new EnumMap<>(Color.class);

    public static void initialize() {
        DyeCommon.doSomethingForAllColors((color) -> {
            BundleItem bi = DyeCommon.registerItem(new BundleItem(new FabricItemSettings().maxCount(1)), DyeCommon.getModdedItemName(MOD_NAME, color, "bundle"));
            BUNDLES.put(color, bi);
            // itemGroups done in a mixin
        });
    }
}
