package pet.cyan.dyelation.mixin.hexical;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import miyucomics.hexical.casting.iota.DyeIota;
import pet.cyan.dyelation.DyeCommon;

@Mixin(value = DyeIota.class)
public class DyeIotaMixin {

    @Shadow(remap = false)
    @Mutable
    private static Map<String, Integer> MAP;
    
    @Inject(method = "<clinit>()V", at = @At("RETURN"))
    private static void addDyeDepotColors(CallbackInfo ci) {
        DyeCommon.doSomethingForAllColors(color -> {
            MAP.put(color.asString(), color.dye.getTextColor());
        });
    }

}
