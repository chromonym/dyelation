package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import juuxel.adorn.block.AdornBlocks;
import juuxel.adorn.block.CandlelitLanternBlock;
import juuxel.adorn.block.SofaBlock;
import juuxel.adorn.block.TableLampBlock;
import juuxel.adorn.block.property.FrontConnection;
import juuxel.adorn.item.AdornItems;
import juuxel.adorn.lib.AdornTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;

public class AdornDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())))
            .pattern("***").pattern("*|*").pattern("***")
            .input('*', TagKey.of(RegistryKeys.ITEM, Identifier.of("c","iron_nuggets"))).input('|', color.candle).group("adorn:candlelit_lantern")
            .criterion(FabricRecipeProvider.hasItem(color.candle), FabricRecipeProvider.conditionsFromItem(color.candle))
            .offerTo(exporter, "adorn/"+color.asString()+"_candlelit_lantern");
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())), 3)
            .pattern(" W").pattern("WW").pattern("SS")
            .input('S', TagKey.of(RegistryKeys.ITEM, Identifier.of("c","wooden_rods"))).input('W', color.wool).group("adorn:sofa")
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .offerTo(exporter, "adorn/"+color.asString()+"_sofa");
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())))
            .pattern("-").pattern("|")
            .input('-', color.carpet).input('|', AdornItems.INSTANCE.getSTONE_TORCH()).group("adorn:table_lamp")
            .criterion(FabricRecipeProvider.hasItem(color.carpet), FabricRecipeProvider.conditionsFromItem(color.carpet))
            .offerTo(exporter, "adorn/"+color.asString()+"_table_lamp");
    }
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())));
        provider.addDrop(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())));
        provider.addDrop(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())));
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId()))).coordinate(
            BlockStateVariantMap.create(CandlelitLanternBlock.HANGING).register(true, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "candlelit_lantern", "hanging")))
            .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "candlelit_lantern", "standing")))
        ));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "candlelit_lantern", "standing"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/candlelit_lantern_standing", "candle", "dye_depot:block/"+color.asString()+"_candle_lit"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "candlelit_lantern", "hanging"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/candlelit_lantern_hanging", "candle", "dye_depot:block/"+color.asString()+"_candle_lit"));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId()))).coordinate(
            BlockStateVariantMap.create(TableLampBlock.Companion.getFACING())
            .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")))
            .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")).put(VariantSettings.X, VariantSettings.Rotation.R180))
            .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")).put(VariantSettings.X, VariantSettings.Rotation.R90))
            .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
            .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
            .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp")).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        ));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/table_lamp", "wool", "dye_depot:block/"+color.asString()+"_wool"));

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())))
            // north
            .with(When.create().set(SofaBlock.Companion.getFACING(), Direction.NORTH), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "center"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.NORTH).set(SofaBlock.Companion.getCONNECTED_LEFT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.NORTH).set(SofaBlock.Companion.getCONNECTED_RIGHT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.NORTH).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.LEFT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.NORTH).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.RIGHT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                .put(VariantSettings.UVLOCK, true)
            )
            // south
            .with(When.create().set(SofaBlock.Companion.getFACING(), Direction.SOUTH), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "center"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.SOUTH).set(SofaBlock.Companion.getCONNECTED_LEFT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.SOUTH).set(SofaBlock.Companion.getCONNECTED_RIGHT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.SOUTH).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.LEFT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.SOUTH).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.RIGHT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                .put(VariantSettings.UVLOCK, true)
            )
            // west
            .with(When.create().set(SofaBlock.Companion.getFACING(), Direction.WEST), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "center"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.WEST).set(SofaBlock.Companion.getCONNECTED_LEFT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.WEST).set(SofaBlock.Companion.getCONNECTED_RIGHT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.WEST).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.LEFT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_left"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.WEST).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.RIGHT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_right"))
                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                .put(VariantSettings.UVLOCK, true)
            )
            // east
            .with(When.create().set(SofaBlock.Companion.getFACING(), Direction.EAST), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "center"))
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.EAST).set(SofaBlock.Companion.getCONNECTED_LEFT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_left"))
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.EAST).set(SofaBlock.Companion.getCONNECTED_RIGHT(), false).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.NONE), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_right"))
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.EAST).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.LEFT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_left"))
                .put(VariantSettings.UVLOCK, true)
            ).with(When.create().set(SofaBlock.Companion.getFACING(), Direction.EAST).set(SofaBlock.Companion.getFRONT_CONNECTION(), FrontConnection.RIGHT), BlockStateVariant.create()
                .put(VariantSettings.MODEL, DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_right"))
                .put(VariantSettings.UVLOCK, true)
            )
        );
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_left"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/sofa_arm_left", "wool", "dye_depot:block/"+color.asString()+"_wool"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "arm_right"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/sofa_arm_right", "wool", "dye_depot:block/"+color.asString()+"_wool"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "center"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/sofa_center", "wool", "dye_depot:block/"+color.asString()+"_wool"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_left"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/sofa_corner_left", "wool", "dye_depot:block/"+color.asString()+"_wool"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getDefaultBlockModelID("adorn", color, "sofa", "corner_right"), DyeCommon.parentWithTexturesModel(
            "adorn:block/templates/sofa_corner_right", "wool", "dye_depot:block/"+color.asString()+"_wool"));

        blockStateModelGenerator.registerParentedItemModel(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())), DyeCommon.getDefaultBlockModelID("adorn", color, "candlelit_lantern", "standing"));
        blockStateModelGenerator.registerParentedItemModel(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())), DyeCommon.getDefaultBlockModelID("adorn", color, "table_lamp"));
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getDefaultItemModelID("adorn", color, "sofa"), DyeCommon.parentWithTexturesModel(
            "adorn:item/templates/sofa", "wool", "dye_depot:block/"+color.asString()+"_wool"));
    }

    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())), StringUtil.capitalize(color.asString())+" Sofa");
        builder.add(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())), StringUtil.capitalize(color.asString())+" Candlelit Lantern");
        builder.add(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())), StringUtil.capitalize(color.asString())+" Table Lamp");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(AdornTags.INSTANCE.getSOFAS().getBlock()).add(AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())));
        tagProvider.getOrCreateBlockTagBuilder(AdornTags.INSTANCE.getCANDLELIT_LANTERNS().getBlock()).add(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())));
        tagProvider.getOrCreateBlockTagBuilder(AdornTags.INSTANCE.getTABLE_LAMPS().getBlock()).add(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())));
    }
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(AdornTags.INSTANCE.getSOFAS().getItem()).add(AdornBlocks.INSTANCE.getSOFAS().get(DyeColor.byId(color.dye.getId())).asItem());
        tagProvider.getOrCreateItemTagBuilder(AdornTags.INSTANCE.getCANDLELIT_LANTERNS().getItem()).add(AdornBlocks.INSTANCE.getDYED_CANDLELIT_LANTERNS().get(DyeColor.byId(color.dye.getId())).asItem());
        tagProvider.getOrCreateItemTagBuilder(AdornTags.INSTANCE.getTABLE_LAMPS().getItem()).add(AdornBlocks.INSTANCE.getTABLE_LAMPS().get(DyeColor.byId(color.dye.getId())).asItem());
    }
}
