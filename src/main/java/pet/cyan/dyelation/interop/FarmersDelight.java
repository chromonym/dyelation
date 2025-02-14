package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.SignItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.configs.FarmersDelightConfig;
import vectorwing.farmersdelight.common.block.CeilingHangingCanvasSignBlock;
import vectorwing.farmersdelight.common.block.StandingCanvasSignBlock;
import vectorwing.farmersdelight.common.block.WallCanvasSignBlock;
import vectorwing.farmersdelight.common.block.WallHangingCanvasSignBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FarmersDelight {

    public static String MOD_NAME = "farmersdelight";

    public static FarmersDelightConfig config = ConfigApiJava.registerAndLoadConfig(FarmersDelightConfig::new, RegisterType.SERVER);

    public static EnumMap<Color, StandingCanvasSignBlock> CANVAS_SIGNS = new EnumMap<>(Color.class);
    public static EnumMap<Color, WallCanvasSignBlock> WALL_CANVAS_SIGNS = new EnumMap<>(Color.class);
    public static EnumMap<Color, SignItem> CANVAS_SIGN_ITEMS = new EnumMap<>(Color.class);
    public static EnumMap<Color, CeilingHangingCanvasSignBlock> HANGING_CANVAS_SIGNS = new EnumMap<>(Color.class);
    public static EnumMap<Color, WallHangingCanvasSignBlock> WALL_HANGING_CANVAS_SIGNS = new EnumMap<>(Color.class);
    public static EnumMap<Color, HangingSignItem> HANGING_CANVAS_SIGN_ITEMS = new EnumMap<>(Color.class);

    public static void initialize() {
        DyeCommon.addInbetweenItemGroupDouble(Identifier.of(MOD_NAME, MOD_NAME), CANVAS_SIGN_ITEMS, HANGING_CANVAS_SIGN_ITEMS, ModItems.BROWN_HANGING_CANVAS_SIGN.get(), ModItems.RED_HANGING_CANVAS_SIGN.get(), ModItems.ORANGE_HANGING_CANVAS_SIGN.get(), ModItems.YELLOW_HANGING_CANVAS_SIGN.get(), ModItems.LIME_HANGING_CANVAS_SIGN.get(), ModItems.GREEN_HANGING_CANVAS_SIGN.get(), ModItems.CYAN_HANGING_CANVAS_SIGN.get(), ModItems.BLUE_HANGING_CANVAS_SIGN.get());
    }

    static {
        DyeCommon.doSomethingForAllColors(color -> {
            // regular canvas signs
            StandingCanvasSignBlock signBlock = DyeCommon.registerBlock(new StandingCanvasSignBlock(DyeColor.byId(color.dye.getId())), DyeCommon.getModdedItemName(MOD_NAME, color, "canvas_sign"), false);
            CANVAS_SIGNS.put(color, signBlock);
            WallCanvasSignBlock wallBlock = DyeCommon.registerBlock(new WallCanvasSignBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_SIGN).dropsLike(signBlock), DyeColor.byId(color.dye.getId())), DyeCommon.getModdedItemName(MOD_NAME, color, "canvas_wall_sign"), false);
            WALL_CANVAS_SIGNS.put(color, wallBlock);
            SignItem signItem = DyeCommon.registerItem(new SignItem(new FabricItemSettings(), signBlock, wallBlock), DyeCommon.getModdedItemName(MOD_NAME, color, "canvas_sign"));
            CANVAS_SIGN_ITEMS.put(color, signItem);
            // hanging canvas signs
            CeilingHangingCanvasSignBlock ceilingHangingBlock = DyeCommon.registerBlock(new CeilingHangingCanvasSignBlock(DyeColor.byId(color.dye.getId())), DyeCommon.getModdedItemName(MOD_NAME, color, "hanging_canvas_sign"), false);
            HANGING_CANVAS_SIGNS.put(color, ceilingHangingBlock);
            WallHangingCanvasSignBlock wallHangingBlock = DyeCommon.registerBlock(new WallHangingCanvasSignBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_SIGN).dropsLike(ceilingHangingBlock), DyeColor.byId(color.dye.getId())), DyeCommon.getModdedItemName(MOD_NAME, color, "wall_hanging_canvas_sign"), false);
            WALL_HANGING_CANVAS_SIGNS.put(color, wallHangingBlock);
            HangingSignItem hangingSignItem = DyeCommon.registerItem(new HangingSignItem(ceilingHangingBlock, wallHangingBlock, new FabricItemSettings()), DyeCommon.getModdedItemName(MOD_NAME, color, "hanging_canvas_sign"));
            HANGING_CANVAS_SIGN_ITEMS.put(color, hangingSignItem);
        });
    }
}
