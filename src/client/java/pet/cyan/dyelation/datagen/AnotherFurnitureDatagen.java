package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import com.starfish_studios.another_furniture.block.CurtainBlock;
import com.starfish_studios.another_furniture.block.LampBlock;
import com.starfish_studios.another_furniture.block.LampConnectorBlock;
import com.starfish_studios.another_furniture.block.SofaBlock;
import com.starfish_studios.another_furniture.block.StoolBlock;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.block.properties.SofaType;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFItemTags;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.loader.impl.util.StringUtil;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.AnotherFurniture;

public class AnotherFurnitureDatagen {

    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        // stools
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.STOOLS.get(color), 3).pattern("pwp").pattern("s s")
            .input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .group("stools").offerTo(exporter);
        // stools (dyeing)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.STOOLS.get(color)).input(color.dyeItem).input(AFItemTags.STOOLS)
            .criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_STOOL.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_STOOL.get())).group("stools")
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.STOOLS.get(color))+"_dyeing");
        // curtains
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.CURTAINS.get(color), 3).pattern("ss").pattern("ww").pattern("ww")
            .input('w', color.wool).input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .group("curtains").offerTo(exporter);
        // curtains (dyeing)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.CURTAINS.get(color)).input(color.dyeItem).input(AFItemTags.CURTAINS)
            .criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_CURTAIN.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_CURTAIN.get())).group("curtains")
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.CURTAINS.get(color))+"_dyeing");
        // lamps
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.LAMPS.get(color), 3).pattern(" w ").pattern("wtw").pattern(" s ")
            .input('w', color.wool).input('s', Items.STICK).input('t', Items.TORCH)
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .group("lamps").offerTo(exporter);
        // lamps (dyeing)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.LAMPS.get(color)).input(color.dyeItem).input(AFItemTags.LAMPS)
            .criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_LAMP.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_LAMP.get())).group("lamps")
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.LAMPS.get(color))+"_dyeing");
        // sofas
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.SOFAS.get(color), 3).pattern("pw ").pattern("pww").pattern("s s")
            .input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .group("sofas").offerTo(exporter);
        // sofas (dyeing)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.SOFAS.get(color)).input(color.dyeItem).input(AFItemTags.SOFAS)
            .criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_SOFA.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_SOFA.get())).group("sofas")
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.SOFAS.get(color))+"_dyeing");
        // tall stools
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.TALL_STOOLS.get(color), 3).pattern("pwp").pattern("sss").pattern("s s")
            .input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
            .group("tall_stools").offerTo(exporter);
        // tall stools (dyeing)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.TALL_STOOLS.get(color)).input(color.dyeItem).input(AFItemTags.TALL_STOOLS)
            .criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_TALL_STOOL.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_TALL_STOOL.get())).group("tall_stools")
            .offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.TALL_STOOLS.get(color))+"_dyeing");
    }

    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(AnotherFurniture.STOOLS.get(color));
		provider.addDrop(AnotherFurniture.CURTAINS.get(color), provider.dropsWithProperty(AnotherFurniture.CURTAINS.get(color), CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP));
		provider.addDrop(AnotherFurniture.LAMPS.get(color));
		provider.addDrop(AnotherFurniture.SOFAS.get(color));
		provider.addDrop(AnotherFurniture.TALL_STOOLS.get(color));
		provider.addDrop(AnotherFurniture.LAMP_CONNECTORS.get(color), AnotherFurniture.LAMPS.get(color));
    }

    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        // stools
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.STOOLS.get(color))
            .coordinate(BlockStateVariantMap.create(StoolBlock.LOW)
            .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool")))
            .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool", "low")))));
            //
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/stool",
            "bottom", "another_furniture:block/stool/bottom",
            "legs", "another_furniture:block/stool/legs",
            "side", "dyelation:block/another_furniture/stool/"+color.asString()+"_side",
            "top", "dyelation:block/another_furniture/stool/"+color.asString()+"_top"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool", "low"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/stool_low",
            "bottom", "another_furniture:block/stool/bottom",
            "legs", "another_furniture:block/stool/legs",
            "side", "dyelation:block/another_furniture/stool/"+color.asString()+"_side",
            "top", "dyelation:block/another_furniture/stool/"+color.asString()+"_top"));
        blockStateModelGenerator.registerParentedItemModel(AnotherFurniture.STOOLS.get(color), DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool"));

        // curtains
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.CURTAINS.get(color))
            .coordinate(BlockStateVariantMap.create(CurtainBlock.FACING, CurtainBlock.VERTICAL_CONNECTION_TYPE, CurtainBlock.HORIZONTAL_CONNECTION_TYPE, CurtainBlock.OPEN)
            .register((facing, vert, horiz, open) -> {
                String suffix = "";
                if (open) {
                    if (horiz == HorizontalConnectionType.MIDDLE || horiz == HorizontalConnectionType.SINGLE) {
                        if (vert == Direction.UP) {
                            suffix = "middle";
                        } else {
                            return BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("minecraft","block/air"));
                        }
                    } else {
                        if (horiz == HorizontalConnectionType.LEFT) {
                            suffix += "left_";
                        } else {
                            suffix += "right_";
                        }
                        if (vert == Direction.UP) {
                            suffix += "top";
                        } else {
                            suffix += "bottom";
                        }
                    }
                } else {
                    if (vert == Direction.UP) {
                        suffix = "closed_top";
                    } else {
                        suffix = "closed_bottom";
                    }
                }
                BlockStateVariant bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", suffix));
                switch (facing) {
                    case NORTH:
                        return bsv;
                    case EAST:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                    case SOUTH:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                    case WEST:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                    default:
                        return bsv;
                }
            }))
        );

        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_closed_bottom",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_closed_bottom"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top"), DyeCommon.parentWithTexturesModel(
                "another_furniture:block/template/curtain_closed_top",
                "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_closed_top"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_left_bottom",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_open_bottom"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_left_top",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_open_top"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_middle",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_middle"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_right_bottom",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_open_bottom"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/curtain_right_top",
            "curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_open_top"));
        
        // lamps
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.LAMPS.get(color))
            .coordinate(BlockStateVariantMap.create(LampBlock.FACING, LampBlock.BASE, LampBlock.LIT)
            .register((f, b, l) -> {
                BlockStateVariant wallBlock;
                if (l) {
                    wallBlock = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "wall"));
                } else {
                    wallBlock = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "wall_off"));
                }
                switch (f) {
                    case UP:
                        if (b) {
                            if (l) {
                                return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp"));
                            } else {
                                return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "off"));
                            }
                        } else {
                            if (l) {
                                return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "tall_top"));
                            } else {
                                return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "tall_top_off"));
                            }
                        }
                    case NORTH:
                        return wallBlock;
                    case EAST:
                        return wallBlock.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                    case SOUTH:
                        return wallBlock.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                    case WEST:
                        return wallBlock.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                    default:
                        return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp"));
                }
            }))
        );
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "off"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp_off",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "tall_top"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp_tall_top",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "tall_top_off"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp_tall_top_off",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "wall"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp_wall",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp", "wall_off"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/lamp_wall_off",
            "top", "dyelation:block/another_furniture/lamp/"+color.asString()+"_top",
            "side", "dyelation:block/another_furniture/lamp/"+color.asString()+"_side"));
        blockStateModelGenerator.registerParentedItemModel(AnotherFurniture.LAMPS.get(color), DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "lamp"));

        // lamp connectors
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.LAMP_CONNECTORS.get(color))
            .coordinate(BlockStateVariantMap.create(LampConnectorBlock.BASE)
            .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("another_furniture","block/template/lamp_tall_base")))
            .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("another_furniture","block/template/lamp_tall_middle"))))
        );

        // sofas
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.SOFAS.get(color))
            .coordinate(BlockStateVariantMap.create(SofaBlock.TYPE, SofaBlock.FACING)
            .register((type, facing) -> {
                BlockStateVariant bsv;
                if (type == SofaType.SINGLE) {
                    bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
                } else {
                    switch (type) {
                        case LEFT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "left"));
                            break;
                        case RIGHT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "right"));
                            break;
                        case MIDDLE:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "middle"));
                            break;
                        case INNER_LEFT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "inner"));
                            break;
                        case OUTER_LEFT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "outer"));
                            break;
                        case INNER_RIGHT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "inner"));
                            switch (facing) {
                                case NORTH:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                case EAST:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                                case SOUTH:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                                case WEST:
                                    return bsv;
                                default:
                                    return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
                            }
                        case OUTER_RIGHT:
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "outer"));
                            switch (facing) {
                                case NORTH:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                                case EAST:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                                case SOUTH:
                                    return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                                case WEST:
                                    return bsv;
                                default:
                                    return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
                            }
                        default: // and SINGLE
                            bsv = BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
                            break;
                    }
                }
                switch (facing) {
                    case NORTH:
                        return bsv;
                    case EAST:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R90);
                    case SOUTH:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R180);
                    case WEST:
                        return bsv.put(VariantSettings.Y, VariantSettings.Rotation.R270);
                    default:
                        return BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
                }
            }))
        );
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_single").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_back_1", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_1").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "inner"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa_inner",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_inner").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_back_2", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_2").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "left"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa_left",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_edge").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_side", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_side").toString(),
            "seat_back_1", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_1").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "middle"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa_middle",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_middle").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_side", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_side").toString(),
            "seat_back_1", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_1").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "outer"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa_outer",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_corner").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_side", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_side").toString(),
            "seat_back_1", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_1").toString(),
            "seat_back_2", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_2").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa", "right"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/sofa_right",
            "seat_top", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_edge").toString(),
            "seat_front", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_front").toString(),
            "seat_side", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_side").toString(),
            "seat_back_1", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "seat_back_1").toString(),
            "back", DyeCommon.getModdedBlockModelID("another_furniture", color, "sofa", "back").toString()));
        blockStateModelGenerator.registerParentedItemModel(AnotherFurniture.SOFAS.get(color), DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "sofa"));
        
        // tall stools
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(AnotherFurniture.TALL_STOOLS.get(color), BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "tall_stool"))));
        blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "tall_stool"), DyeCommon.parentWithTexturesModel(
            "another_furniture:block/template/tall_stool",
            "top", DyeCommon.getModdedBlockModelID("another_furniture", color, "tall_stool", "top").toString()));
        blockStateModelGenerator.registerParentedItemModel(AnotherFurniture.TALL_STOOLS.get(color), DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "tall_stool"));
    }

    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(AnotherFurniture.MOD_NAME, color, "curtain"), DyeCommon.parentWithTexturesModel(
			"another_furniture:item/template/curtain",
			"all", "dyelation:block/another_furniture/curtain/"+color.asString()));
    }

    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(AnotherFurniture.STOOLS.get(color), StringUtil.capitalize(color.asString())+" Stool");
        builder.add(AnotherFurniture.CURTAINS.get(color), StringUtil.capitalize(color.asString())+" Curtain");
        builder.add(AnotherFurniture.LAMPS.get(color), StringUtil.capitalize(color.asString())+" Lamp");
        builder.add(AnotherFurniture.LAMP_CONNECTORS.get(color), StringUtil.capitalize(color.asString())+" Lamp");
        builder.add(AnotherFurniture.SOFAS.get(color), StringUtil.capitalize(color.asString())+" Sofa");
        builder.add(AnotherFurniture.TALL_STOOLS.get(color), StringUtil.capitalize(color.asString())+" Tall Stool");
        // StringUtil.capitalize(color.asString())
    }

    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(AFBlockTags.STOOLS).add(AnotherFurniture.STOOLS.get(color));
		tagProvider.getOrCreateBlockTagBuilder(AFBlockTags.CURTAINS).add(AnotherFurniture.CURTAINS.get(color));
		tagProvider.getOrCreateBlockTagBuilder(AFBlockTags.LAMPS).add(AnotherFurniture.LAMPS.get(color)).add(AnotherFurniture.LAMP_CONNECTORS.get(color));
		tagProvider.getOrCreateBlockTagBuilder(AFBlockTags.SOFAS).add(AnotherFurniture.SOFAS.get(color));
		tagProvider.getOrCreateBlockTagBuilder(AFBlockTags.TALL_STOOLS).add(AnotherFurniture.TALL_STOOLS.get(color));
    }

    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(AFItemTags.STOOLS).add(AnotherFurniture.STOOLS.get(color).asItem());
		tagProvider.getOrCreateItemTagBuilder(AFItemTags.CURTAINS).add(AnotherFurniture.CURTAINS.get(color).asItem());
		tagProvider.getOrCreateItemTagBuilder(AFItemTags.LAMPS).add(AnotherFurniture.LAMPS.get(color).asItem());
		tagProvider.getOrCreateItemTagBuilder(AFItemTags.SOFAS).add(AnotherFurniture.SOFAS.get(color).asItem());
		tagProvider.getOrCreateItemTagBuilder(AFItemTags.TALL_STOOLS).add(AnotherFurniture.TALL_STOOLS.get(color).asItem());
    }

}
