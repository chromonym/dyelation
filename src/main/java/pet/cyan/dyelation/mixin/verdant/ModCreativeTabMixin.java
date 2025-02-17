package pet.cyan.dyelation.mixin.verdant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.ordana.verdant.reg.ModBlocks;
import com.ordana.verdant.reg.ModCreativeTab;

import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.Verdant;

@Mixin(value = ModCreativeTab.class, remap = false)
public class ModCreativeTabMixin {
    @ModifyArg(method = "addItems(Lnet/mehvahdjukaar/moonlight/api/platform/RegHelper$ItemToTabEvent;)V", index = 3,
    at = @At(value = "INVOKE", target = "Lcom/ordana/verdant/reg/ModCreativeTab;after(Lnet/mehvahdjukaar/moonlight/api/platform/RegHelper$ItemToTabEvent;Lnet/minecraft/item/Item;Lnet/minecraft/registry/RegistryKey;[Ljava/util/function/Supplier;)V"))
    private static Supplier<?>[] addDyelationGlowsticks(Supplier<?>[] suppliers) {
        if (suppliers.length >= 17 && suppliers[8] == ModBlocks.RED_PRIMROSE) {
            ArrayList<Supplier<?>> list = new ArrayList<Supplier<?>>(Arrays.asList(suppliers));
            list.add(8, () -> Verdant.PRIMROSES.get(Color.MAROON));
            list.add(9, () -> Verdant.PRIMROSES.get(Color.ROSE));
            list.add(11, () -> Verdant.PRIMROSES.get(Color.CORAL));
            list.add(12, () -> Verdant.PRIMROSES.get(Color.GINGER));
            list.add(14, () -> Verdant.PRIMROSES.get(Color.TAN));
            list.add(15, () -> Verdant.PRIMROSES.get(Color.BEIGE));
            list.add(17, () -> Verdant.PRIMROSES.get(Color.AMBER));
            list.add(18, () -> Verdant.PRIMROSES.get(Color.OLIVE));
            list.add(20, () -> Verdant.PRIMROSES.get(Color.FOREST));
            list.add(22, () -> Verdant.PRIMROSES.get(Color.VERDANT));
            list.add(23, () -> Verdant.PRIMROSES.get(Color.TEAL));
            list.add(25, () -> Verdant.PRIMROSES.get(Color.MINT));
            list.add(26, () -> Verdant.PRIMROSES.get(Color.AQUA));
            list.add(29, () -> Verdant.PRIMROSES.get(Color.SLATE));
            list.add(30, () -> Verdant.PRIMROSES.get(Color.NAVY));
            list.add(31, () -> Verdant.PRIMROSES.get(Color.INDIGO));
            Supplier<?>[] newSuppliers = new Supplier[list.size()];
            return list.toArray(newSuppliers);
        }
        return suppliers;
    }
}
