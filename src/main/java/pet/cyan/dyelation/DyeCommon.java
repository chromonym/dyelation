package pet.cyan.dyelation;

import java.util.EnumMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.interop.Desire;

public class DyeCommon {

    public static <B extends net.minecraft.block.Block> B registerBlock(B block, String name, boolean shouldRegisterItem) {
        Identifier id = new Identifier(Dyelation.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem item = new BlockItem(block, new Item.Settings());
            registerItem(item, name);
        }
        return Registry.register(Registries.BLOCK, id, block);
    }

    public static <I extends Item> I registerItem(I item, String name) {
        Identifier id = new Identifier(Dyelation.MOD_ID, name);
        return Registry.register(Registries.ITEM, id, item);
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

    public static Identifier getModdedBlockModelID(String modId, Color color, String itemName, String suffix) {
        return new Identifier(Dyelation.MOD_ID, "block/"+ modId + "/"+ itemName + "/" + color.dye.getName() + "_" + suffix);
    }

    public static Identifier getModdedBlockModelID(String modId, Color color, String itemName) {
        return new Identifier(Dyelation.MOD_ID, "block/"+ modId + "/"+ itemName + "/" + color.dye.getName());
    }

    public static Identifier getModdedItemModelID(String modId, Color color, String itemName) {
        return new Identifier(Dyelation.MOD_ID, "item/"+getModdedItemName(modId, color, itemName));
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

    public static <I extends ItemConvertible, J extends ItemConvertible> void addInbetweenItemGroupDouble(RegistryKey<ItemGroup> itemGroup, EnumMap<Color, I> itemsA, EnumMap<Color, J> itemsB, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        // AND THERE ARE TWO DIFFERENT TYPES OF ITEM (see: farmer's delight canvas signs)
        // note the ItemConvertibles should be the SECOND item type
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addAfter(brown, itemsA.get(Color.MAROON), itemsB.get(Color.MAROON), itemsA.get(Color.ROSE), itemsB.get(Color.ROSE));
            content.addAfter(red, itemsA.get(Color.CORAL), itemsB.get(Color.CORAL), itemsA.get(Color.GINGER), itemsB.get(Color.GINGER));
            content.addAfter(orange, itemsA.get(Color.TAN), itemsB.get(Color.TAN), itemsA.get(Color.BEIGE), itemsB.get(Color.BEIGE));
            content.addAfter(yellow, itemsA.get(Color.AMBER), itemsB.get(Color.AMBER), itemsA.get(Color.OLIVE), itemsB.get(Color.OLIVE));
            content.addAfter(lime, itemsA.get(Color.FOREST), itemsB.get(Color.FOREST));
            content.addAfter(green, itemsA.get(Color.VERDANT), itemsB.get(Color.VERDANT), itemsA.get(Color.TEAL), itemsB.get(Color.TEAL));
            content.addAfter(cyan, itemsA.get(Color.MINT), itemsB.get(Color.MINT), itemsA.get(Color.AQUA), itemsB.get(Color.AQUA));
            content.addAfter(blue, itemsA.get(Color.SLATE), itemsB.get(Color.SLATE), itemsA.get(Color.NAVY), itemsB.get(Color.NAVY), itemsA.get(Color.INDIGO), itemsB.get(Color.INDIGO));
        });
    }

    public static <I extends ItemConvertible, J extends ItemConvertible> void addInbetweenItemGroupDouble(Identifier igName, EnumMap<Color, I> itemsA, EnumMap<Color, J> itemsB, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        // AND THERE ARE TWO DIFFERENT TYPES OF ITEM (see: farmer's delight canvas signs)
        // note the ItemConvertibles should be the SECOND item type
        addInbetweenItemGroupDouble(RegistryKey.of(RegistryKeys.ITEM_GROUP, igName), itemsA, itemsB, brown, red, orange, yellow, lime, green, cyan, blue);
    }

    public static <I extends ItemConvertible, J extends ItemConvertible, K extends ItemConvertible, L extends ItemConvertible> void addInbetweenItemGroupQuadruple(RegistryKey<ItemGroup> itemGroup, EnumMap<Color, I> itemsA, EnumMap<Color, J> itemsB, EnumMap<Color, K> itemsC, EnumMap<Color, L> itemsD, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        // AND THERE ARE FOUR DIFFERENT TYPES OF ITEM (see: desire's colored stuff)
        // note the ItemConvertibles should be the FOURTH item type
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> {
            content.addAfter(brown, itemsA.get(Color.MAROON), itemsB.get(Color.MAROON), itemsC.get(Color.MAROON), itemsD.get(Color.MAROON),
            itemsA.get(Color.ROSE), itemsB.get(Color.ROSE), itemsC.get(Color.ROSE), itemsD.get(Color.ROSE));
            content.addAfter(red, itemsA.get(Color.CORAL), itemsB.get(Color.CORAL), itemsC.get(Color.CORAL), itemsD.get(Color.CORAL),
            itemsA.get(Color.GINGER), itemsB.get(Color.GINGER), itemsC.get(Color.GINGER), itemsD.get(Color.GINGER));
            content.addAfter(orange, itemsA.get(Color.TAN), itemsB.get(Color.TAN), itemsC.get(Color.TAN), itemsD.get(Color.TAN),
            itemsA.get(Color.BEIGE), itemsB.get(Color.BEIGE), itemsC.get(Color.BEIGE), itemsD.get(Color.BEIGE));
            content.addAfter(yellow, itemsA.get(Color.AMBER), itemsB.get(Color.AMBER), itemsC.get(Color.AMBER), itemsD.get(Color.AMBER),
            itemsA.get(Color.OLIVE), itemsB.get(Color.OLIVE), itemsC.get(Color.OLIVE), itemsD.get(Color.OLIVE));
            content.addAfter(lime, itemsA.get(Color.FOREST), itemsB.get(Color.FOREST), itemsC.get(Color.FOREST), itemsD.get(Color.FOREST));
            content.addAfter(green, itemsA.get(Color.VERDANT), itemsB.get(Color.VERDANT), itemsC.get(Color.VERDANT), itemsD.get(Color.VERDANT),
            itemsA.get(Color.TEAL), itemsB.get(Color.TEAL), itemsC.get(Color.TEAL), itemsD.get(Color.TEAL));
            content.addAfter(cyan, itemsA.get(Color.MINT), itemsB.get(Color.MINT), itemsC.get(Color.MINT), itemsD.get(Color.MINT),
            itemsA.get(Color.AQUA), itemsB.get(Color.AQUA), itemsC.get(Color.AQUA), itemsD.get(Color.AQUA));
            content.addAfter(blue, itemsA.get(Color.SLATE), itemsB.get(Color.SLATE), itemsC.get(Color.SLATE), itemsD.get(Color.SLATE),
            itemsA.get(Color.NAVY), itemsB.get(Color.NAVY), itemsC.get(Color.NAVY), itemsD.get(Color.NAVY),
            itemsA.get(Color.INDIGO), itemsB.get(Color.INDIGO), itemsC.get(Color.INDIGO), itemsD.get(Color.INDIGO));
        });
    }

    public static <I extends ItemConvertible, J extends ItemConvertible, K extends ItemConvertible, L extends ItemConvertible> void addInbetweenItemGroupQuadruple(Identifier igName, EnumMap<Color, I> itemsA, EnumMap<Color, J> itemsB, EnumMap<Color, K> itemsC, EnumMap<Color, L> itemsD, ItemConvertible brown, ItemConvertible red, ItemConvertible orange, ItemConvertible yellow, ItemConvertible lime, ItemConvertible green, ItemConvertible cyan, ItemConvertible blue) {
        // Adds items (in the EnumMap) to an ItemGroup. Use if the vanilla-colour items are in colour order (grayscale, brown, red, orange, yellow, etc)
        // AND THERE ARE FOUR DIFFERENT TYPES OF ITEM (see: desire's colored stuff)
        // note the ItemConvertibles should be the FOURTH item type
        addInbetweenItemGroupQuadruple(RegistryKey.of(RegistryKeys.ITEM_GROUP, igName), itemsA, itemsB, itemsC, itemsD, brown, red, orange, yellow, lime, green, cyan, blue);
    }

    public static Supplier<JsonElement> parentWithTexturesModel(String parent, String... textureNames) {
		JsonObject modelFile = new JsonObject();
		modelFile.addProperty("parent", parent);
		JsonObject textures = new JsonObject();
		for (var i = 0; i<textureNames.length; i+=2) {
			textures.addProperty(textureNames[i], textureNames[i+1]);
		}
		modelFile.add("textures", textures);
		return () -> modelFile;
	}

    public static void createStairsModel(BlockStateModelGenerator blockStateModelGenerator, Color color, EnumMap<Color, StairsBlock> stairs, EnumMap<Color, Block> fullBlocks, String blockName, String modName) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs.get(color),
            getModdedBlockModelID(modName, color, blockName+"_stairs", "inner"),
            getModdedBlockModelID(modName, color, blockName+"_stairs"),
            getModdedBlockModelID(modName, color, blockName+"_stairs", "outer")));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(Desire.MOD_NAME, color, blockName+"_stairs", "inner"), parentWithTexturesModel(
            "minecraft:block/inner_stairs",
            "bottom", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "side", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "top", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(Desire.MOD_NAME, color, blockName+"_stairs"), parentWithTexturesModel(
            "minecraft:block/stairs",
            "bottom", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "side", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "top", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(Desire.MOD_NAME, color, blockName+"_stairs", "outer"), parentWithTexturesModel(
            "minecraft:block/outer_stairs",
            "bottom", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "side", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "top", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
    }

    public static void createSlabModel(BlockStateModelGenerator blockStateModelGenerator, Color color, EnumMap<Color, SlabBlock> slabs, EnumMap<Color, Block> fullBlocks, String blockName, String modName) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slabs.get(color),
            getModdedBlockModelID(modName, color, blockName+"_slab"),
            getModdedBlockModelID(modName, color, blockName+"_slab", "top"),
            ModelIds.getBlockModelId(fullBlocks.get(color))));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(Desire.MOD_NAME, color, blockName+"_slab"), parentWithTexturesModel(
            "minecraft:block/slab",
            "bottom", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "side", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "top", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(Desire.MOD_NAME, color, blockName+"_slab", "top"), parentWithTexturesModel(
            "minecraft:block/slab_top",
            "bottom", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "side", ModelIds.getBlockModelId(fullBlocks.get(color)).toString(),
            "top", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
    }

    public static void createWallModel(BlockStateModelGenerator blockStateModelGenerator, Color color, EnumMap<Color, WallBlock> walls, EnumMap<Color, Block> fullBlocks, String blockName, String modName) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(walls.get(color),
        getModdedBlockModelID(modName, color, blockName+"_wall", "post"),
        getModdedBlockModelID(modName, color, blockName+"_wall", "side"),
        getModdedBlockModelID(modName, color, blockName+"_wall", "side_tall")));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(modName, color, blockName+"_wall", "post"), parentWithTexturesModel(
            "minecraft:block/template_wall_post",
            "wall", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(modName, color, blockName+"_wall", "side"), parentWithTexturesModel(
            "minecraft:block/template_wall_side",
            "wall", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(modName, color, blockName+"_wall", "side_tall"), parentWithTexturesModel(
            "minecraft:block/template_wall_side_tall",
            "wall", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.modelCollector.accept(getModdedBlockModelID(modName, color, blockName+"_wall", "inventory"), parentWithTexturesModel(
            "minecraft:block/wall_inventory",
            "wall", ModelIds.getBlockModelId(fullBlocks.get(color)).toString()));
        blockStateModelGenerator.registerParentedItemModel(walls.get(color), getModdedBlockModelID(modName, color, blockName+"_wall", "inventory"));
    }

}
