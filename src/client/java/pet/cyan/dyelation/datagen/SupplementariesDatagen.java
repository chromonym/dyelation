package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.loader.impl.util.StringUtil;
import net.mehvahdjukaar.supplementaries.common.block.blocks.AwningBlock;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;

public class SupplementariesDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {}
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(ModRegistry.AWNINGS.get(DyeColor.byId(color.dye.getId())).get());
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModRegistry.AWNINGS.get(DyeColor.byId(color.dye.getId())).get()).coordinate(
            BlockStateVariantMap.create(AwningBlock.SLANTED, AwningBlock.FACING, AwningBlock.BOTTOM).register((slant, face, bot) -> {
                BlockStateVariant var = BlockStateVariant.create();
                String modelName = "block/awnings/"+color.asString()+"_";
                if (bot) {
                    modelName += "bottom";
                } else {
                    modelName += "top";
                }
                if (slant) {
                    modelName += "_slanted";
                }
                var.put(VariantSettings.MODEL, Identifier.of("supplementaries", modelName));
                switch (face) {
                    case EAST:
                        var.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                        break;
                    case SOUTH:
                        var.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                        break;
                    case WEST:
                        var.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                        break;
                    default: // north (and up and down, which shouldn't be normally possible but)
                        break;
                }
                return var;
            })
        ));
        blockStateModelGenerator.modelCollector.accept(Identifier.of("supplementaries", "block/awnings/"+color.asString()+"_bottom_slanted"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/awnings/bottom_slanted",
            "1", "supplementaries:block/awnings/awning_"+color.asString(),
            "up", "supplementaries:block/awnings/awning_"+color.asString()+"_side",
            "particle", "supplementaries:block/awnings/awning_"+color.asString()));
        blockStateModelGenerator.modelCollector.accept(Identifier.of("supplementaries", "block/awnings/"+color.asString()+"_bottom"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/awnings/bottom",
            "1", "supplementaries:block/awnings/awning_"+color.asString(),
            "up", "supplementaries:block/awnings/awning_"+color.asString()+"_side",
            "particle", "supplementaries:block/awnings/awning_"+color.asString()));
        blockStateModelGenerator.modelCollector.accept(Identifier.of("supplementaries", "block/awnings/"+color.asString()+"_top_slanted"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/awnings/top_slanted",
            "1", "supplementaries:block/awnings/awning_"+color.asString(),
            "up", "supplementaries:block/awnings/awning_"+color.asString()+"_side",
            "particle", "supplementaries:block/awnings/awning_"+color.asString()));
        blockStateModelGenerator.modelCollector.accept(Identifier.of("supplementaries", "block/awnings/"+color.asString()+"_top"), DyeCommon.parentWithTexturesModel(
            "supplementaries:block/awnings/top",
            "1", "supplementaries:block/awnings/awning_"+color.asString(),
            "up", "supplementaries:block/awnings/awning_"+color.asString()+"_side",
            "particle", "supplementaries:block/awnings/awning_"+color.asString()));
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(Identifier.of("supplementaries", "awning_"+color.asString()), DyeCommon.parentWithTexturesModel(
            "supplementaries:item/awning",
            "1", "supplementaries:block/awnings/awning_"+color.asString(),
            "up", "supplementaries:block/awnings/awning_"+color.asString()+"_side",
            "particle", "supplementaries:block/awnings/awning_"+color.asString()));
        itemModelGenerator.writer.accept(Identifier.of("supplementaries", "bunting_"+color.asString()), DyeCommon.parentWithTexturesModel(
            "item/generated", "layer0", "supplementaries:item/buntings/bunting_"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(Identifier.of("item.supplementaries","bunting_"+color.asString()), StringUtil.capitalize(color.asString())+" Bunting");
        builder.add(ModRegistry.AWNINGS.get(DyeColor.byId(color.dye.getId())).get(), StringUtil.capitalize(color.asString())+" Awning");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(FabricMineableTags.SHEARS_MINEABLE).add(ModRegistry.AWNINGS.get(DyeColor.byId(color.dye.getId())).get());
        
    }
}
