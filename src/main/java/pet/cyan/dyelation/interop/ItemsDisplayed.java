package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import net.freedinner.items_displayed.item.ModItems;
import net.freedinner.items_displayed.item.custom.JewelryPillowItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class ItemsDisplayed {

    public static final String MOD_NAME = "items_displayed";

    public static EnumMap<Color, JewelryPillowItem> JEWELRY_PILLOWS = new EnumMap<>(Color.class);

    public static void initialize() {
        DyeCommon.addInbetweenItemGroup(Identifier.of(MOD_NAME, "item_group"), JEWELRY_PILLOWS, ModItems.BROWN_JEWELRY_PILLOW, ModItems.RED_JEWELRY_PILLOW, ModItems.ORANGE_JEWELRY_PILLOW, ModItems.YELLOW_JEWELRY_PILLOW, ModItems.LIME_JEWELRY_PILLOW, ModItems.GREEN_JEWELRY_PILLOW, ModItems.CYAN_JEWELRY_PILLOW, ModItems.BLUE_JEWELRY_PILLOW);
    }

    static {
        DyeCommon.doSomethingForAllColors(color -> {
            JewelryPillowItem pillow = DyeCommon.registerItem(new JewelryPillowItem(new Item.Settings(), DyeColor.byId(color.dye.getId())), DyeCommon.getModdedItemName(MOD_NAME, color, "jewelry_pillow"));
            JEWELRY_PILLOWS.put(color, pillow);
        });
    }

}
