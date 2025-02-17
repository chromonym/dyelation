package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.Desire;

public class DesireDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {}
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(Desire.CONCRETE_BRICKS.get(color));
        provider.addDrop(Desire.CONCRETE_BRICK_STAIRS.get(color));
        provider.addDrop(Desire.CONCRETE_BRICK_SLABS.get(color));
        provider.addDrop(Desire.CONCRETE_BRICK_WALLS.get(color));
        provider.addDrop(Desire.TERRACOTTA_MOSAICS.get(color));
        provider.addDrop(Desire.TERRACOTTA_MOSAIC_STAIRS.get(color));
        provider.addDrop(Desire.TERRACOTTA_MOSAIC_SLABS.get(color));
        provider.addDrop(Desire.TERRACOTTA_MOSAIC_WALLS.get(color));
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.registerSimpleCubeAll(Desire.CONCRETE_BRICKS.get(color));
        // MORE DATAGEN HERE
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(Desire.CONCRETE_BRICKS.get(color), StringUtil.capitalize(color.asString())+" Concrete Bricks");
        builder.add(Desire.CONCRETE_BRICK_STAIRS.get(color), StringUtil.capitalize(color.asString())+" Concrete Brick Stairs");
        builder.add(Desire.CONCRETE_BRICK_SLABS.get(color), StringUtil.capitalize(color.asString())+" Concrete Brick Slab");
        builder.add(Desire.CONCRETE_BRICK_WALLS.get(color), StringUtil.capitalize(color.asString())+" Concrete Brick Wall");

        builder.add(Desire.TERRACOTTA_MOSAICS.get(color), StringUtil.capitalize(color.asString())+" Terracotta Mosaic");
        builder.add(Desire.TERRACOTTA_MOSAIC_STAIRS.get(color), StringUtil.capitalize(color.asString())+" Terracotta Mosaic Stairs");
        builder.add(Desire.TERRACOTTA_MOSAIC_SLABS.get(color), StringUtil.capitalize(color.asString())+" Terracotta Mosaic Slab");
        builder.add(Desire.TERRACOTTA_MOSAIC_WALLS.get(color), StringUtil.capitalize(color.asString())+" Terracotta Mosaic Wall");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {}
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {}
}
