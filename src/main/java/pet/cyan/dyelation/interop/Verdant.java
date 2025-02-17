package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import com.ordana.verdant.blocks.PrimroseBlock;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class Verdant {

    public static final String MOD_NAME = "verdant";

    public static EnumMap<Color, PrimroseBlock> PRIMROSES = new EnumMap<>(Color.class);
    
    public static void initialize() {}

    static {
        DyeCommon.doSomethingForAllColors(color -> {
            DyeColor dye = DyeColor.byId(color.dye.getId());
            PrimroseBlock primrose = DyeCommon.registerBlock(new PrimroseBlock(dye, Settings.create().mapColor(dye).noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)), DyeCommon.getModdedItemName(MOD_NAME, color, "primrose"), true);
            PRIMROSES.put(color, primrose);
            RegHelper.registerCompostable(primrose.asItem(), 0.2f);
        });
    }

}
