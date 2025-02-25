package pet.cyan.dyelation.mixin.hexical;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.api.casting.iota.NullIota;
import miyucomics.hexical.casting.patterns.colors.OpGetDye;
import net.minecraft.block.entity.SignBlockEntity;

@Mixin(value = OpGetDye.class)
public class OpGetDyeMixin {
    
    @Redirect(method = "processVec3d(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/server/world/ServerWorld;)Lat/petrak/hexcasting/api/casting/iota/Iota;",
    at = @At(value = "INVOKE", target = "Lkotlin/jvm/internal/Intrinsics;checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V"))
    private void dontCheckNullThatThere(Object obj, String str) {}

    @Inject(method = "processVec3d(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/server/world/ServerWorld;)Lat/petrak/hexcasting/api/casting/iota/Iota;",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/SignBlockEntity;getFrontText()Lnet/minecraft/block/entity/SignText;"), cancellable = true)
    private void checkNullHereInstead(CallbackInfoReturnable<Iota> cir, @Local SignBlockEntity sign) {
        if (sign == null) {
            cir.setReturnValue((Iota)(new NullIota()));
        }
    }

}
