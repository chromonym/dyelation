package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import com.ordana.spelunkery.blocks.GlowstickBlock;
import com.ordana.spelunkery.items.GlowstickItem;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class Spelunkery {

    public static final String MOD_NAME = "spelunkery";

    public static EnumMap<Color, GlowstickBlock> GLOWSTICK_BLOCKS = new EnumMap<>(Color.class);
    public static EnumMap<Color, GlowstickItem> GLOWSTICK_ITEMS = new EnumMap<>(Color.class);

    public static void initialize() {
        //
    }

    static {
        DyeCommon.doSomethingForAllColors(color -> {
            GlowstickBlock block = DyeCommon.registerBlock(new GlowstickBlock(FabricBlockSettings.copy(Blocks.END_ROD).breakInstantly().noCollision().nonOpaque().emissiveLighting((s,v,p) -> true).luminance((blockStatex) -> {return 14;}).sounds(BlockSoundGroup.CANDLE)), DyeCommon.getModdedItemName(MOD_NAME, color, "glowstick"), false);
            GLOWSTICK_BLOCKS.put(color, block);
            GlowstickItem item = DyeCommon.registerItem(new GlowstickItem(DyeColor.byId(color.dye.getId()), (Block)block, new Item.Settings()), DyeCommon.getModdedItemName(MOD_NAME, color, "glowstick"));
            GLOWSTICK_ITEMS.put(color, item);
        });
    }

}
