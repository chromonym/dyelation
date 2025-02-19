package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.Verdant;

public class VerdantDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, color.dyeItem).input(Verdant.PRIMROSES.get(color))
        .criterion(FabricRecipeProvider.hasItem(Verdant.PRIMROSES.get(color)), FabricRecipeProvider.conditionsFromItem(Verdant.PRIMROSES.get(color)))
        .offerTo(exporter, "verdant/"+color.asString()+"_dye_from_primrose");
    }

    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(Verdant.PRIMROSES.get(color));
    }

    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(Verdant.PRIMROSES.get(color), DyeCommon.getModdedBlockModelID(Verdant.MOD_NAME, color, "primrose")));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(Verdant.MOD_NAME, color, "primrose"), DyeCommon.parentWithTexturesModel(
            "verdant:block/primrose_template",
            "flower", "dyelation:block/verdant/primrose/"+color.asString()));
    }

    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(Verdant.MOD_NAME, color, "primrose"), DyeCommon.parentWithTexturesModel(
            "minecraft:item/generated", 
            "layer0", "dyelation:block/verdant/primrose/"+color.asString()));
    }

    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(Verdant.PRIMROSES.get(color), StringUtil.capitalize(color.asString())+" Primrose");
    }

    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.SMALL_FLOWERS).add(Verdant.PRIMROSES.get(color));
    }

    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(ItemTags.FLOWERS).add(Verdant.PRIMROSES.get(color).asItem());
    }
}
