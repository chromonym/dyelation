package pet.cyan.dyelation.mixin.spelunkery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.ordana.spelunkery.reg.ModCreativeTabs;
import com.ordana.spelunkery.reg.ModItems;

import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.Spelunkery;

@Mixin(ModCreativeTabs.class)
public class ModCreativeTabsMixin {
    
    @ModifyArg(method = "registerItemsToTabs(Lnet/mehvahdjukaar/moonlight/api/platform/RegHelper$ItemToTabEvent;)V", index = 3,
    at = @At(value = "INVOKE", target = "Lcom/ordana/spelunkery/reg/ModCreativeTabs;after(Lnet/mehvahdjukaar/moonlight/api/platform/RegHelper$ItemToTabEvent;Lnet/minecraft/item/Item;Lnet/minecraft/registry/RegistryKey;[Ljava/util/function/Supplier;)V"))
    private static Supplier<?>[] addDyelationGlowsticks(Supplier<?>[] suppliers) {
        if (suppliers.length >= 17 && suppliers[1] == ModItems.WHITE_GLOWSTICK) {
            ArrayList<Supplier<?>> list = new ArrayList<Supplier<?>>(Arrays.asList(suppliers));
            list.add(6, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.MAROON));
            list.add(7, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.ROSE));
            list.add(9, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.CORAL));
            list.add(10, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.GINGER));
            list.add(12, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.TAN));
            list.add(13, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.BEIGE));
            list.add(15, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.AMBER));
            list.add(16, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.OLIVE));
            list.add(18, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.FOREST));
            list.add(20, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.VERDANT));
            list.add(21, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.TEAL));
            list.add(23, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.MINT));
            list.add(24, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.AQUA));
            list.add(27, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.SLATE));
            list.add(28, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.NAVY));
            list.add(29, () -> Spelunkery.GLOWSTICK_ITEMS.get(Color.INDIGO));
            Supplier<?>[] newSuppliers = new Supplier[list.size()];
            return list.toArray(newSuppliers);
        }
        return suppliers;
    }

}
