package pet.cyan.dyelation.rendering;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.AnotherFurniture;

public class AnotherFurnitureRenderer {
    public static void initialize() {
        DyeCommon.doSomethingForAllColors(color -> {
            BlockRenderLayerMap.INSTANCE.putBlock(AnotherFurniture.CURTAINS.get(color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(AnotherFurniture.SOFAS.get(color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(AnotherFurniture.LAMPS.get(color), RenderLayer.getCutout());
        });
    }
}
