package pet.cyan.dyelation.mixin.verdant;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ordana.verdant.blocks.PrimroseBlock;
import com.ordana.verdant.util.WeatheringHelper;

import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.Verdant;

@Mixin(value = WeatheringHelper.class, remap = false)
public class WeatheringHelperMixin {
    
    /*@Inject(method = "lambda$static$5()Lcom/google/common/collect/BiMap;", at = @At("RETURN"))
    private static void dyeDepotToPrimrose(CallbackInfoReturnable<BiMap<?,?>> cir, @Local ImmutableBiMap.Builder<DyeColor, Block> builder) {
        DyeCommon.doSomethingForAllColors(color -> {
            builder.put(DyeColor.byId(color.dye.getId()), Verdant.PRIMROSES.get(color));
        });
    }*/

    @Inject(method = "getPrimroseColor(Lnet/minecraft/util/DyeColor;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void dyeDepotPrimroseColor(DyeColor color, CallbackInfoReturnable<Optional<PrimroseBlock>> cir) {
        if (color.getId() >= 16 && color.getId() < 32) {
            cir.setReturnValue(Optional.of(Verdant.PRIMROSES.get(Color.valueOf(color.toString().toUpperCase()))));
        }
    }

}
