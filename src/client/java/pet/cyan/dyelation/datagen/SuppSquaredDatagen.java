package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.mehvahdjukaar.supplementaries.common.block.blocks.CandleHolderBlock;
import net.mehvahdjukaar.suppsquared.SuppSquared;
import net.mehvahdjukaar.suppsquared.common.ColoredSackBlock;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;

public class SuppSquaredDatagen {
    
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get())
        .criterion(FabricRecipeProvider.hasItem(color.candle), FabricRecipeProvider.conditionsFromItem(color.candle))
        .pattern("1").pattern("2").input('1', color.candle).input('2', Items.GOLD_INGOT).offerTo(exporter, "suppsquared/candle_holders/gold_candle_holder_"+color.asString());
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(SuppSquared.SACKS.get(DyeColor.byId(color.dye.getId())).get())
        .coordinate(BlockStateVariantMap.create(ColoredSackBlock.OPEN)
        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("suppsquared", "block/sacks/"+color.asString()+"_closed")))
        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("suppsquared", "block/sacks/"+color.asString()+"_open")))));
        // model here
        blockStateModelGenerator.modelCollector.accept(Identifier.of("suppsquared", "block/sacks/"+color.asString()+"_closed"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/sack_closed",
            "1", "suppsquared:block/sack_"+color.asString()+"_front",
            "2", "suppsquared:block/sack_"+color.asString()+"_top",
            "3", "suppsquared:block/sack_"+color.asString()+"_bottom",
            "4", "suppsquared:block/sack_"+color.asString()+"_closed",
            "particle", "suppsquared:block/sack_"+color.asString()+"_front"));
        blockStateModelGenerator.modelCollector.accept(Identifier.of("suppsquared", "block/sacks/"+color.asString()+"_open"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/sack_open",
            "1", "suppsquared:block/sack_"+color.asString()+"_front",
            "2", "suppsquared:block/sack_"+color.asString()+"_top",
            "3", "suppsquared:block/sack_"+color.asString()+"_bottom",
            "4", "suppsquared:block/sack_"+color.asString()+"_open",
            "particle", "suppsquared:block/sack_"+color.asString()+"_front"));
            
        blockStateModelGenerator.registerParentedItemModel(SuppSquared.SACKS.get(DyeColor.byId(color.dye.getId())).get(), Identifier.of("suppsquared", "block/sacks/"+color.asString()+"_closed"));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get())
        .coordinate(BlockStateVariantMap.create(CandleHolderBlock.LIT, CandleHolderBlock.FACING, CandleHolderBlock.FACE, CandleHolderBlock.CANDLES).register((lit, facing, face, candles) -> {
            BlockStateVariant var = BlockStateVariant.create();
            String modelName = "block/candle_holders/"+color.asString();
            switch (face) {
                case CEILING:
                    modelName += "_ceiling_";
                    break;
                case FLOOR:
                    modelName += "_floor_";
                    break;
                case WALL:
                    modelName += "_wall_";
                    break;
                default:
                    modelName += "_floor_";
                    break;
            }
            modelName += Integer.toString(candles);
            if ((facing == Direction.EAST || facing == Direction.WEST) && face == WallMountLocation.CEILING && candles < 4) {
                modelName += "f";
            }
            if (lit) {
                modelName += "_lit";
            }
            var.put(VariantSettings.MODEL, Identifier.of("suppsquared", modelName));
            switch (facing) {
                case EAST:
                    var.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                    break;
                case NORTH:
                    break;
                case SOUTH:
                    var.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                    break;
                case WEST:
                    var.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                    break;
                default:
                    break;
            }
            return var;
        })));
        for (int i = 0; i < 2; i++) {
            boolean lit = i == 1;
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "1", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "2", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "3", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "4", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "1f", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "2f", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "ceiling", "3f", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "floor", "1", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "floor", "2", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "floor", "3", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "floor", "4", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "wall", "1", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "wall", "2", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "wall", "3", lit);
            addCandleHolderVariant(blockStateModelGenerator, color, "wall", "4", lit);
        }
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(Identifier.of("suppsquared", "item/gold_candle_holder_"+color.asString()), DyeCommon.parentWithTexturesModel(
            "item/generated",
            "layer0", "suppsquared:item/candle_holders/"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(SuppSquared.SACKS.get(DyeColor.byId(color.dye.getId())).get(), StringUtil.capitalize(color.asString())+" Sack");
        builder.add(SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get(), "Gold "+StringUtil.capitalize(color.asString())+" Candle Holder");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.GUARDED_BY_PIGLINS).add(SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get());
        tagProvider.getOrCreateBlockTagBuilder(TagKey.of(RegistryKeys.BLOCK, Identifier.of("supplementaries","candle_holders"))).add(SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get());
        tagProvider.getOrCreateBlockTagBuilder(TagKey.of(RegistryKeys.BLOCK, Identifier.of("supplementaries","sacks"))).add(SuppSquared.SACKS.get(DyeColor.byId(color.dye.getId())).get());
    }
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("supplementaries","candle_holders"))).add(SuppSquared.GOLDEN_CANDLE_HOLDERS.get(DyeColor.byId(color.dye.getId())).get().asItem());
        tagProvider.getOrCreateItemTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("supplementaries","sacks"))).add(SuppSquared.SACKS.get(DyeColor.byId(color.dye.getId())).get().asItem());
    }

    private static void addCandleHolderVariant(BlockStateModelGenerator blockStateModelGenerator, Color color, String wfc, String num, boolean lit) {
        blockStateModelGenerator.modelCollector.accept(Identifier.of("suppsquared", "block/candle_holders/"+color.asString()+"_"+wfc+"_"+num+(lit ? "_lit" : "")), DyeCommon.parentWithTexturesModel(
            "suppsquared:block/candle_holders/"+wfc+"_"+num,
            "1", "suppsquared:block/candle_holder",
            "2", "suppsquared:block/candle_holder",
            "3", "suppsquared:block/candle_holder_ceiling",
            "4", "suppsquared:block/candle_holder_ceiling",
            "chain", "block/chain",
            "particle", "suppsquared:block/candle_holder",
            "all", "dye_depot:block/"+color.asString()+"_candle"+(lit ? "_lit" : "")));
    }

}
