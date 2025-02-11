package pet.cyan.dyelation.mixin.another_furniture;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.starfish_studios.another_furniture.block.LampConnectorBlock;

import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.AnotherFurniture;

@Mixin(LampConnectorBlock.class)
public class LampConnectorBlockMixin {
    @Inject(method = "getLampByColor", at = @At("HEAD"), cancellable = true)
    private static void fixLampColor(DyeColor color, CallbackInfoReturnable<Block> cir) {
        if (color.getId() > 16 && color.getId() < 32) {
            cir.setReturnValue(AnotherFurniture.LAMPS.get(Color.valueOf(color.getName().toUpperCase())));
        }
    }
}
