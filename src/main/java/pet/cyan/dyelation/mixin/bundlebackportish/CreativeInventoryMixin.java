package pet.cyan.dyelation.mixin.bundlebackportish;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import archives.tater.bundlebackportish.BundleBackportishItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.BundleBackportish;

@Mixin(BundleBackportishItems.class)
public class CreativeInventoryMixin {
    @Inject(method = "register()V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void overrideModifyEntries(CallbackInfo ci) {
        // i could look at the bytecode but that's too much effort and prone to breaking
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(listener -> {
            listener.addAfter(Items.BUNDLE,
                BundleBackportishItems.WHITE_BUNDLE,
                BundleBackportishItems.LIGHT_GRAY_BUNDLE,
                BundleBackportishItems.GRAY_BUNDLE,
                BundleBackportishItems.BLACK_BUNDLE,
                BundleBackportishItems.BROWN_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.MAROON),
                BundleBackportish.BUNDLES.get(Color.ROSE),
                BundleBackportishItems.RED_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.CORAL),
                BundleBackportish.BUNDLES.get(Color.GINGER),
                BundleBackportishItems.ORANGE_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.TAN),
                BundleBackportish.BUNDLES.get(Color.BEIGE),
                BundleBackportishItems.YELLOW_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.AMBER),
                BundleBackportish.BUNDLES.get(Color.OLIVE),
                BundleBackportishItems.LIME_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.FOREST),
                BundleBackportishItems.GREEN_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.VERDANT),
                BundleBackportish.BUNDLES.get(Color.TEAL),
                BundleBackportishItems.CYAN_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.MINT),
                BundleBackportish.BUNDLES.get(Color.AQUA),
                BundleBackportishItems.LIGHT_BLUE_BUNDLE,
                BundleBackportishItems.BLUE_BUNDLE,
                BundleBackportish.BUNDLES.get(Color.SLATE),
                BundleBackportish.BUNDLES.get(Color.NAVY),
                BundleBackportish.BUNDLES.get(Color.INDIGO),
                BundleBackportishItems.PURPLE_BUNDLE,
                BundleBackportishItems.MAGENTA_BUNDLE,
                BundleBackportishItems.PINK_BUNDLE
            );
        });
        ci.cancel();
    }
}
