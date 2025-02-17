package pet.cyan.dyelation.rendering;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.Verdant;

public class VerdantRenderer {
    public static void initialize() {
        DyeCommon.doSomethingForAllColors(color -> {
            BlockRenderLayerMap.INSTANCE.putBlock(Verdant.PRIMROSES.get(color), RenderLayer.getCutoutMipped());
            ColorProviderRegistry.BLOCK.register(VerdantRenderer::getGrassColor, Verdant.PRIMROSES.get(color));
        });
    }

    private static int getGrassColor(BlockState state, BlockRenderView view, BlockPos pos, int tintIndex) {
        if (tintIndex == 0) {return -1;}
        if (view.hasBiomes()) {
            Biome biome = view.getBiomeFabric(pos).value();
            return biome.getGrassColorAt(pos.getX(), pos.getZ());
        }
        return GrassColors.getDefaultColor();
        
    }
}
