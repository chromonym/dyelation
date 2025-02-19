package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.Desire;

public class DesireDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICKS.get(color), 4).pattern("CC").pattern("CC")
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .input('C', color.concrete).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_STAIRS.get(color), 4).pattern("C  ").pattern("CC ").pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .input('C', Desire.CONCRETE_BRICKS.get(color)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_SLABS.get(color), 6).pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .input('C', Desire.CONCRETE_BRICKS.get(color)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Desire.CONCRETE_BRICK_WALLS.get(color), 6).pattern("CCC").pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .input('C', Desire.CONCRETE_BRICKS.get(color)).offerTo(exporter);
        // concrete stonecutter here

        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.concrete), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICKS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICKS.get(color))+"_from_concrete_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.concrete), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_STAIRS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_STAIRS.get(color))+"_from_concrete_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.concrete), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_SLABS.get(color), 2)
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_SLABS.get(color))+"_from_concrete_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.concrete), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_WALLS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_WALLS.get(color))+"_from_concrete_stonecutting");

        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.CONCRETE_BRICKS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_STAIRS.get(color))
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_STAIRS.get(color))+"_from_brick_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.CONCRETE_BRICKS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_SLABS.get(color), 2)
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_SLABS.get(color))+"_from_brick_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.CONCRETE_BRICKS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.CONCRETE_BRICK_WALLS.get(color))
            .criterion(FabricRecipeProvider.hasItem(Desire.CONCRETE_BRICKS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.CONCRETE_BRICKS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.CONCRETE_BRICK_WALLS.get(color))+"_from_brick_stonecutting");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAICS.get(color), 4).pattern("CC").pattern("CC")
            .criterion(FabricRecipeProvider.hasItem(color.glazed_terracotta), FabricRecipeProvider.conditionsFromItem(color.glazed_terracotta))
            .input('C', color.glazed_terracotta).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_STAIRS.get(color), 4).pattern("C  ").pattern("CC ").pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .input('C', Desire.TERRACOTTA_MOSAICS.get(color)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_SLABS.get(color), 6).pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .input('C', Desire.TERRACOTTA_MOSAICS.get(color)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Desire.TERRACOTTA_MOSAIC_WALLS.get(color), 6).pattern("CCC").pattern("CCC")
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .input('C', Desire.TERRACOTTA_MOSAICS.get(color)).offerTo(exporter);
        
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.glazed_terracotta), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAICS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.glazed_terracotta), FabricRecipeProvider.conditionsFromItem(color.glazed_terracotta))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAICS.get(color))+"_from_glazed_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.glazed_terracotta), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_STAIRS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.glazed_terracotta), FabricRecipeProvider.conditionsFromItem(color.glazed_terracotta))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_STAIRS.get(color))+"_from_glazed_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.glazed_terracotta), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_SLABS.get(color), 2)
            .criterion(FabricRecipeProvider.hasItem(color.glazed_terracotta), FabricRecipeProvider.conditionsFromItem(color.glazed_terracotta))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_SLABS.get(color))+"_from_glazed_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(color.glazed_terracotta), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_WALLS.get(color))
            .criterion(FabricRecipeProvider.hasItem(color.glazed_terracotta), FabricRecipeProvider.conditionsFromItem(color.glazed_terracotta))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_WALLS.get(color))+"_from_glazed_stonecutting");
        
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.TERRACOTTA_MOSAICS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_STAIRS.get(color))
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_STAIRS.get(color))+"_from_mosaic_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.TERRACOTTA_MOSAICS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_SLABS.get(color), 2)
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_SLABS.get(color))+"_from_mosaic_stonecutting");
        SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Desire.TERRACOTTA_MOSAICS.get(color)), RecipeCategory.BUILDING_BLOCKS, Desire.TERRACOTTA_MOSAIC_WALLS.get(color))
            .criterion(FabricRecipeProvider.hasItem(Desire.TERRACOTTA_MOSAICS.get(color)), FabricRecipeProvider.conditionsFromItem(Desire.TERRACOTTA_MOSAICS.get(color)))
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(Desire.TERRACOTTA_MOSAIC_WALLS.get(color))+"_from_mosaic_stonecutting");

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(color.concrete), RecipeCategory.BUILDING_BLOCKS, Registries.ITEM.get(Identifier.of("desire","rough_concrete")), 0.1f, 200)
            .criterion(FabricRecipeProvider.hasItem(color.concrete), FabricRecipeProvider.conditionsFromItem(color.concrete))
            .offerTo(exporter, "desire/"+color.asString()+"_rough_concrete");
    }
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
        DyeCommon.createStairsModel(blockStateModelGenerator, color, Desire.CONCRETE_BRICK_STAIRS, Desire.CONCRETE_BRICKS, "concrete_brick", Desire.MOD_NAME);
        DyeCommon.createSlabModel(blockStateModelGenerator, color, Desire.CONCRETE_BRICK_SLABS, Desire.CONCRETE_BRICKS, "concrete_brick", Desire.MOD_NAME);
        DyeCommon.createWallModel(blockStateModelGenerator, color, Desire.CONCRETE_BRICK_WALLS, Desire.CONCRETE_BRICKS, "concrete_brick", Desire.MOD_NAME);
        blockStateModelGenerator.registerSimpleCubeAll(Desire.TERRACOTTA_MOSAICS.get(color));
        DyeCommon.createStairsModel(blockStateModelGenerator, color, Desire.TERRACOTTA_MOSAIC_STAIRS, Desire.TERRACOTTA_MOSAICS, "terracotta_mosaic", Desire.MOD_NAME);
        DyeCommon.createSlabModel(blockStateModelGenerator, color, Desire.TERRACOTTA_MOSAIC_SLABS, Desire.TERRACOTTA_MOSAICS, "terracotta_mosaic", Desire.MOD_NAME);
        DyeCommon.createWallModel(blockStateModelGenerator, color, Desire.TERRACOTTA_MOSAIC_WALLS, Desire.TERRACOTTA_MOSAICS, "terracotta_mosaic", Desire.MOD_NAME);
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
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.STAIRS).add(Desire.CONCRETE_BRICK_STAIRS.get(color), Desire.TERRACOTTA_MOSAIC_STAIRS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.SLABS).add(Desire.CONCRETE_BRICK_SLABS.get(color), Desire.TERRACOTTA_MOSAIC_SLABS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.WALLS).add(Desire.CONCRETE_BRICK_WALLS.get(color), Desire.TERRACOTTA_MOSAIC_WALLS.get(color));
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.PICKAXE_MINEABLE).add(Desire.CONCRETE_BRICKS.get(color), Desire.TERRACOTTA_MOSAICS.get(color), Desire.CONCRETE_BRICK_STAIRS.get(color), Desire.TERRACOTTA_MOSAIC_STAIRS.get(color), Desire.CONCRETE_BRICK_SLABS.get(color), Desire.TERRACOTTA_MOSAIC_SLABS.get(color), Desire.CONCRETE_BRICK_WALLS.get(color), Desire.TERRACOTTA_MOSAIC_WALLS.get(color));
    }
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {}
}
