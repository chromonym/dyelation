package pet.cyan.dyelation.mixin.farmersdelight;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.interop.FarmersDelight;
import vectorwing.farmersdelight.common.block.state.CanvasSign;

@Mixin(value = CanvasSign.class)
public interface DarkBackgroundSignsMixin {
    
    @Inject(method = "isDarkBackground()Z", at = @At("HEAD"), cancellable = true, remap = false)
    default void addDyelationBackgrounds(CallbackInfoReturnable<Boolean> cir) {
        DyeColor backgroundDye = ((CanvasSign)(Object)this).getBackgroundColor();
        if (backgroundDye != null && FarmersDelight.config.canvasSignDarkBackgroundList.get().contains(backgroundDye.getName())) {
            cir.setReturnValue(true);
        }
    }

}
