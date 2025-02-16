package pet.cyan.dyelation.mixin.spelunkery;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.llamalad7.mixinextras.sugar.Local;
import com.ordana.spelunkery.items.GlowstickItem;

import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.Spelunkery;

@Mixin(value = GlowstickItem.class, remap = false)
public class GlowstickItemMixin {
    
    @Inject(method = "lambda$new$0()Lcom/google/common/collect/BiMap;", at = @At("RETURN"))
    private static void itemToDyeDepotDye(CallbackInfoReturnable<BiMap<?,?>> cir, @Local ImmutableBiMap.Builder<Item, DyeColor> builder) {
        DyeCommon.doSomethingForAllColors(color -> {
            builder.put((Item)Spelunkery.GLOWSTICK_ITEMS.get(color), DyeColor.byId(color.dye.getId()));
        });
    }

}
