package pet.cyan.dyelation.interop;

import java.util.EnumMap;

import com.starfish_studios.another_furniture.block.CurtainBlock;
import com.starfish_studios.another_furniture.block.LampBlock;
import com.starfish_studios.another_furniture.block.LampConnectorBlock;
import com.starfish_studios.another_furniture.block.SofaBlock;
import com.starfish_studios.another_furniture.block.StoolBlock;
import com.starfish_studios.another_furniture.block.TallStoolBlock;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFRegistry;

import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class AnotherFurniture {

    public static final String MOD_NAME = "another_furniture";

    public static EnumMap<Color, StoolBlock> STOOLS = new EnumMap<>(Color.class);
    public static EnumMap<Color, CurtainBlock> CURTAINS = new EnumMap<>(Color.class);
    public static EnumMap<Color, LampBlock> LAMPS = new EnumMap<>(Color.class);
    public static EnumMap<Color, LampConnectorBlock> LAMP_CONNECTORS = new EnumMap<>(Color.class);
    public static EnumMap<Color, SofaBlock> SOFAS = new EnumMap<>(Color.class);
    public static EnumMap<Color, TallStoolBlock> TALL_STOOLS = new EnumMap<>(Color.class);

    public static void initialize() {
        // Stools
        DyeCommon.doSomethingForAllColors((color) -> {
            StoolBlock stool = DyeCommon.registerBlock(new StoolBlock(com.starfish_studios.another_furniture.registry.AFBlocks.Properties.weak_wood), DyeCommon.getModdedItemName(MOD_NAME, color, "stool"), true);
            AFRegistry.setFlammable(() -> stool, 5, 20);
            STOOLS.put(color, stool);
        });
        DyeCommon.addAfterInItemGroup(new Identifier("another_furniture", "tab"), STOOLS, AFBlocks.BLACK_STOOL.get());

        // Curtains
        DyeCommon.doSomethingForAllColors((color) -> {
            CurtainBlock curtain = DyeCommon.registerBlock(new CurtainBlock(com.starfish_studios.another_furniture.registry.AFBlocks.Properties.curtain), DyeCommon.getModdedItemName(MOD_NAME, color, "curtain"), true);
            AFRegistry.setFlammable(() -> curtain, 5, 20);
            CURTAINS.put(color, curtain);
        });
        DyeCommon.addAfterInItemGroup(new Identifier("another_furniture", "tab"), CURTAINS, AFBlocks.BLACK_CURTAIN.get());

        // Lamps
        DyeCommon.doSomethingForAllColors((color) -> {
            LampBlock lamp = DyeCommon.registerBlock(new LampBlock(DyeColor.byId(color.dye.getId()), com.starfish_studios.another_furniture.registry.AFBlocks.Properties.lamp), DyeCommon.getModdedItemName(MOD_NAME, color, "lamp"), true);
            AFRegistry.setFlammable(() -> lamp, 5, 20);
            LAMPS.put(color, lamp);
        });
        DyeCommon.addAfterInItemGroup(new Identifier("another_furniture", "tab"), LAMPS, AFBlocks.BLACK_LAMP.get());

        // Lamp Connectors
        DyeCommon.doSomethingForAllColors((color) -> {
            LampConnectorBlock lampConn = DyeCommon.registerBlock(new LampConnectorBlock(DyeColor.byId(color.dye.getId()), com.starfish_studios.another_furniture.registry.AFBlocks.Properties.lamp), DyeCommon.getModdedItemName(MOD_NAME, color, "lamp_connector"), false);
            AFRegistry.setFlammable(() -> lampConn, 5, 20);
            LAMP_CONNECTORS.put(color, lampConn);
        });

        // Sofas
        DyeCommon.doSomethingForAllColors((color) -> {
            SofaBlock sofa = DyeCommon.registerBlock(new SofaBlock(com.starfish_studios.another_furniture.registry.AFBlocks.Properties.weak_wood), DyeCommon.getModdedItemName(MOD_NAME, color, "sofa"), true);
            AFRegistry.setFlammable(() -> sofa, 5, 20);
            SOFAS.put(color, sofa);
        });
        DyeCommon.addAfterInItemGroup(new Identifier("another_furniture", "tab"), SOFAS, AFBlocks.BLACK_SOFA.get());

        // Tall Stools
        DyeCommon.doSomethingForAllColors((color) -> {
            TallStoolBlock tallStool = DyeCommon.registerBlock(new TallStoolBlock(com.starfish_studios.another_furniture.registry.AFBlocks.Properties.weak_wood), DyeCommon.getModdedItemName(MOD_NAME, color, "tall_stool"), true);
            AFRegistry.setFlammable(() -> tallStool, 5, 20);
            TALL_STOOLS.put(color, tallStool);
        });
        DyeCommon.addAfterInItemGroup(new Identifier("another_furniture", "tab"), TALL_STOOLS, AFBlocks.BLACK_TALL_STOOL.get());
    }
}
