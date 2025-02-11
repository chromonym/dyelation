package pet.cyan.dyelation;

import java.util.EnumMap;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DyeCommon {

    public static <B extends net.minecraft.block.Block> B registerBlock(B block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Dyelation.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem item = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, item);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void doSomethingForAllColors(Consumer<Color> colorConsumer) {
        colorConsumer.accept(Color.MAROON);
        colorConsumer.accept(Color.ROSE);
        colorConsumer.accept(Color.CORAL);
        colorConsumer.accept(Color.INDIGO);
        colorConsumer.accept(Color.NAVY);
        colorConsumer.accept(Color.SLATE);
        colorConsumer.accept(Color.OLIVE);
        colorConsumer.accept(Color.AMBER);
        colorConsumer.accept(Color.BEIGE);
        colorConsumer.accept(Color.TEAL);
        colorConsumer.accept(Color.MINT);
        colorConsumer.accept(Color.AQUA);
        colorConsumer.accept(Color.VERDANT);
        colorConsumer.accept(Color.FOREST);
        colorConsumer.accept(Color.GINGER);
        colorConsumer.accept(Color.TAN);
    }

    public static String getModdedItemName(String modId, Color color, String itemName) {
        return modId + "/" + color.dye.getName() + "_" + itemName;
    }

    public static <I extends ItemConvertible> void addInbetweenItemGroup(RegistryKey<ItemGroup> itemGroup, EnumMap<Color, I> items, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addAfter(brown, items.get(Color.MAROON), items.get(Color.ROSE));
            content.addAfter(red, items.get(Color.CORAL), items.get(Color.GINGER));
            content.addAfter(orange, items.get(Color.TAN), items.get(Color.BEIGE));
            content.addAfter(yellow, items.get(Color.AMBER), items.get(Color.OLIVE));
            content.addAfter(lime, items.get(Color.FOREST));
            content.addAfter(green, items.get(Color.VERDANT), items.get(Color.TEAL));
            content.addAfter(cyan, items.get(Color.MINT), items.get(Color.AQUA));
            content.addAfter(blue, items.get(Color.SLATE), items.get(Color.NAVY), items.get(Color.INDIGO));
        });
    }

    public static <I extends ItemConvertible> void addInbetweenItemGroup(Identifier igName, EnumMap<Color, I> items, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        addInbetweenItemGroup(RegistryKey.of(RegistryKeys.ITEM_GROUP, igName), items, brown, red, orange, yellow, lime, green, cyan, blue);
    }

    public static <I extends ItemConvertible> void addAfterInItemGroup(RegistryKey<ItemGroup> itemGroup, EnumMap<Color, I> items, ItemConvertible blackItem) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in internal ID order (white, orange, magenta, light blue, etc)
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addAfter(blackItem, items.get(Color.MAROON), items.get(Color.ROSE), items.get(Color.CORAL), items.get(Color.INDIGO),
            items.get(Color.NAVY), items.get(Color.SLATE), items.get(Color.OLIVE), items.get(Color.AMBER),
            items.get(Color.BEIGE), items.get(Color.TEAL), items.get(Color.MINT), items.get(Color.AQUA),
            items.get(Color.VERDANT), items.get(Color.FOREST), items.get(Color.GINGER), items.get(Color.TAN));
        });
    }

    public static <I extends ItemConvertible> void addAfterInItemGroup(Identifier igName, EnumMap<Color, I> items, ItemConvertible blackItem) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in internal ID order (white, orange, magenta, light blue, etc)
        addAfterInItemGroup(RegistryKey.of(RegistryKeys.ITEM_GROUP, igName), items, blackItem);
    }

}
