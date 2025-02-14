package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

public class FarmersDelightDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, FarmersDelight.CANVAS_SIGN_ITEMS.get(color)).input(ModTags.CANVAS_SIGNS).input(color.dyeItem)
        .criterion(FabricRecipeProvider.hasItem(ModItems.CANVAS_SIGN.get()), FabricRecipeProvider.conditionsFromItem(ModItems.CANVAS_SIGN.get()))
        .group("fd_canvas_sign").offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, FarmersDelight.HANGING_CANVAS_SIGN_ITEMS.get(color)).input(ModTags.HANGING_CANVAS_SIGNS).input(color.dyeItem)
        .criterion(FabricRecipeProvider.hasItem(ModItems.HANGING_CANVAS_SIGN.get()), FabricRecipeProvider.conditionsFromItem(ModItems.HANGING_CANVAS_SIGN.get()))
        .group("fd_hanging_canvas_sign").offerTo(exporter);
    }
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(FarmersDelight.CANVAS_SIGNS.get(color), FarmersDelight.CANVAS_SIGN_ITEMS.get(color));
        provider.addDrop(FarmersDelight.WALL_CANVAS_SIGNS.get(color), FarmersDelight.CANVAS_SIGN_ITEMS.get(color));
        provider.addDrop(FarmersDelight.HANGING_CANVAS_SIGNS.get(color), FarmersDelight.HANGING_CANVAS_SIGN_ITEMS.get(color));
        provider.addDrop(FarmersDelight.WALL_HANGING_CANVAS_SIGNS.get(color), FarmersDelight.HANGING_CANVAS_SIGN_ITEMS.get(color));
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(FarmersDelight.CANVAS_SIGNS.get(color), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(FarmersDelight.MOD_NAME, "block/canvas_sign"))));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(FarmersDelight.WALL_CANVAS_SIGNS.get(color), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(FarmersDelight.MOD_NAME, "block/canvas_sign"))));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(FarmersDelight.HANGING_CANVAS_SIGNS.get(color), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(FarmersDelight.MOD_NAME, "block/canvas_sign"))));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(FarmersDelight.WALL_HANGING_CANVAS_SIGNS.get(color), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(FarmersDelight.MOD_NAME, "block/canvas_sign"))));
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(FarmersDelight.MOD_NAME, color, "canvas_sign"), DyeCommon.parentWithTexturesModel(
            "minecraft:item/generated",
            "layer0", "dyelation:item/farmersdelight/canvas_sign/"+color.asString()));
            itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(FarmersDelight.MOD_NAME, color, "hanging_canvas_sign"), DyeCommon.parentWithTexturesModel(
                "minecraft:item/generated",
                "layer0", "dyelation:item/farmersdelight/hanging_canvas_sign/"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(FarmersDelight.CANVAS_SIGNS.get(color), StringUtil.capitalize(color.asString())+" Canvas Sign");
        builder.add(FarmersDelight.HANGING_CANVAS_SIGNS.get(color), StringUtil.capitalize(color.asString())+" Hanging Canvas Sign");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.STANDING_SIGNS).add(FarmersDelight.CANVAS_SIGNS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(FarmersDelight.HANGING_CANVAS_SIGNS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.WALL_SIGNS).add(FarmersDelight.WALL_CANVAS_SIGNS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(FarmersDelight.WALL_HANGING_CANVAS_SIGNS.get(color));
    } // don't think i need this one
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(ModTags.HANGING_CANVAS_SIGNS).add(FarmersDelight.HANGING_CANVAS_SIGN_ITEMS.get(color));
        tagProvider.getOrCreateItemTagBuilder(ModTags.CANVAS_SIGNS).add(FarmersDelight.CANVAS_SIGN_ITEMS.get(color));
    }
}
