package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Settings;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class Desire {

    public static final String MOD_NAME = "desire";

    public static EnumMap<Color, Block> CONCRETE_BRICKS = new EnumMap<>(Color.class);
    public static EnumMap<Color, StairsBlock> CONCRETE_BRICK_STAIRS = new EnumMap<>(Color.class);
    public static EnumMap<Color, SlabBlock> CONCRETE_BRICK_SLABS = new EnumMap<>(Color.class);
    public static EnumMap<Color, WallBlock> CONCRETE_BRICK_WALLS = new EnumMap<>(Color.class);

    public static EnumMap<Color, Block> TERRACOTTA_MOSAICS = new EnumMap<>(Color.class);
    public static EnumMap<Color, StairsBlock> TERRACOTTA_MOSAIC_STAIRS = new EnumMap<>(Color.class);
    public static EnumMap<Color, SlabBlock> TERRACOTTA_MOSAIC_SLABS = new EnumMap<>(Color.class);
    public static EnumMap<Color, WallBlock> TERRACOTTA_MOSAIC_WALLS = new EnumMap<>(Color.class);

    public static void initialize() {
        DyeCommon.addInbetweenItemGroupQuadruple(Identifier.of("desire", "desire"), CONCRETE_BRICKS, CONCRETE_BRICK_STAIRS, CONCRETE_BRICK_SLABS, CONCRETE_BRICK_WALLS,
        Registries.BLOCK.get(Identifier.of("desire", "brown_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "red_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "orange_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "yellow_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "lime_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "green_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "cyan_concrete_brick_wall")), Registries.BLOCK.get(Identifier.of("desire", "blue_concrete_brick_wall")));

        DyeCommon.addInbetweenItemGroupQuadruple(Identifier.of("desire", "desire"), TERRACOTTA_MOSAICS, TERRACOTTA_MOSAIC_STAIRS, TERRACOTTA_MOSAIC_SLABS, TERRACOTTA_MOSAIC_WALLS,
        Registries.BLOCK.get(Identifier.of("desire", "brown_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "red_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "orange_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "yellow_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "lime_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "green_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "cyan_terracotta_mosaic_wall")), Registries.BLOCK.get(Identifier.of("desire", "blue_terracotta_mosaic_wall")));
    }

    static {
        DyeCommon.doSomethingForAllColors(color -> {
            Settings concSettings = Settings.copy((AbstractBlock)Registries.BLOCK.get(Identifier.of("dye_depot", color.asString() + "_concrete")));
            Block concrete = DyeCommon.registerBlock(new Block(concSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "concrete_bricks"), true);
            CONCRETE_BRICKS.put(color, concrete);
            StairsBlock concreteStairs = DyeCommon.registerBlock(new StairsBlock(concrete.getDefaultState(), concSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "concrete_brick_stairs"), true);
            CONCRETE_BRICK_STAIRS.put(color, concreteStairs);
            SlabBlock concreteSlab = DyeCommon.registerBlock(new SlabBlock(concSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "concrete_brick_slab"), true);
            CONCRETE_BRICK_SLABS.put(color, concreteSlab);
            WallBlock concreteWall = DyeCommon.registerBlock(new WallBlock(concSettings.solid()), DyeCommon.getModdedItemName(MOD_NAME, color, "concrete_brick_wall"), true);
            CONCRETE_BRICK_WALLS.put(color, concreteWall);

            Settings tcSettings = Settings.copy((AbstractBlock)Registries.BLOCK.get(Identifier.of("dye_depot", color.asString() + "_glazed_terracotta")));
            Block terracotta = DyeCommon.registerBlock(new Block(tcSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "terracotta_mosaic"), true);
            TERRACOTTA_MOSAICS.put(color, terracotta);
            StairsBlock terracottaStairs = DyeCommon.registerBlock(new StairsBlock(terracotta.getDefaultState(), tcSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "terracotta_mosaic_stairs"), true);
            TERRACOTTA_MOSAIC_STAIRS.put(color, terracottaStairs);
            SlabBlock terracottaSlab = DyeCommon.registerBlock(new SlabBlock(tcSettings), DyeCommon.getModdedItemName(MOD_NAME, color, "terracotta_mosaic_slab"), true);
            TERRACOTTA_MOSAIC_SLABS.put(color, terracottaSlab);
            WallBlock terracottaWall = DyeCommon.registerBlock(new WallBlock(tcSettings.solid()), DyeCommon.getModdedItemName(MOD_NAME, color, "terracotta_mosaic_wall"), true);
            TERRACOTTA_MOSAIC_WALLS.put(color, terracottaWall);
        });
    }
    
}
