package pet.cyan.dyelation.mixin.another_furniture;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.starfish_studios.another_furniture.block.LampBlock;

import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.AnotherFurniture;

@Mixin(LampBlock.class)
public class LampBlockMixin {
    @Inject(method = "getLampConnectorByColor", at = @At("HEAD"), cancellable = true)
    private static void fixLampConnectorColor(DyeColor color, CallbackInfoReturnable<Block> cir) {
        if (color.getId() > 16 && color.getId() < 32) {
            cir.setReturnValue(AnotherFurniture.LAMP_CONNECTORS.get(Color.valueOf(color.getName().toUpperCase())));
        }
    }
}
