package pet.cyan.dyelation.mixin.bundlebackportish;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import archives.tater.bundlebackportish.BundleBackportishItems;
import archives.tater.bundlebackportish.BundleColoringRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.BundleBackportish;

@Mixin(BundleColoringRecipe.class)
public class BundleColoringRecipeMixin {

    @Shadow(remap = false)
    private static Map<Item, Item> COLORS = Map.ofEntries(
            Map.entry(Items.WHITE_DYE, BundleBackportishItems.WHITE_BUNDLE),
            Map.entry(Items.ORANGE_DYE, BundleBackportishItems.ORANGE_BUNDLE),
            Map.entry(Items.MAGENTA_DYE, BundleBackportishItems.MAGENTA_BUNDLE),
            Map.entry(Items.LIGHT_BLUE_DYE, BundleBackportishItems.LIGHT_BLUE_BUNDLE),
            Map.entry(Items.YELLOW_DYE, BundleBackportishItems.YELLOW_BUNDLE),
            Map.entry(Items.LIME_DYE, BundleBackportishItems.LIME_BUNDLE),
            Map.entry(Items.PINK_DYE, BundleBackportishItems.PINK_BUNDLE),
            Map.entry(Items.GRAY_DYE, BundleBackportishItems.GRAY_BUNDLE),
            Map.entry(Items.LIGHT_GRAY_DYE, BundleBackportishItems.LIGHT_GRAY_BUNDLE),
            Map.entry(Items.CYAN_DYE, BundleBackportishItems.CYAN_BUNDLE),
            Map.entry(Items.PURPLE_DYE, BundleBackportishItems.PURPLE_BUNDLE),
            Map.entry(Items.BLUE_DYE, BundleBackportishItems.BLUE_BUNDLE),
            Map.entry(Items.BROWN_DYE, BundleBackportishItems.BROWN_BUNDLE),
            Map.entry(Items.GREEN_DYE, BundleBackportishItems.GREEN_BUNDLE),
            Map.entry(Items.RED_DYE, BundleBackportishItems.RED_BUNDLE),
            Map.entry(Items.BLACK_DYE, BundleBackportishItems.BLACK_BUNDLE),
            // once again there's probably a better way to do this with bytecode stuff but
            Map.entry(Color.MAROON.dyeItem, BundleBackportish.BUNDLES.get(Color.MAROON)),
            Map.entry(Color.ROSE.dyeItem, BundleBackportish.BUNDLES.get(Color.ROSE)),
            Map.entry(Color.CORAL.dyeItem, BundleBackportish.BUNDLES.get(Color.CORAL)),
            Map.entry(Color.INDIGO.dyeItem, BundleBackportish.BUNDLES.get(Color.INDIGO)),
            Map.entry(Color.NAVY.dyeItem, BundleBackportish.BUNDLES.get(Color.NAVY)),
            Map.entry(Color.SLATE.dyeItem, BundleBackportish.BUNDLES.get(Color.SLATE)),
            Map.entry(Color.OLIVE.dyeItem, BundleBackportish.BUNDLES.get(Color.OLIVE)),
            Map.entry(Color.AMBER.dyeItem, BundleBackportish.BUNDLES.get(Color.AMBER)),
            Map.entry(Color.BEIGE.dyeItem, BundleBackportish.BUNDLES.get(Color.BEIGE)),
            Map.entry(Color.TEAL.dyeItem, BundleBackportish.BUNDLES.get(Color.TEAL)),
            Map.entry(Color.MINT.dyeItem, BundleBackportish.BUNDLES.get(Color.MINT)),
            Map.entry(Color.AQUA.dyeItem, BundleBackportish.BUNDLES.get(Color.AQUA)),
            Map.entry(Color.VERDANT.dyeItem, BundleBackportish.BUNDLES.get(Color.VERDANT)),
            Map.entry(Color.FOREST.dyeItem, BundleBackportish.BUNDLES.get(Color.FOREST)),
            Map.entry(Color.GINGER.dyeItem, BundleBackportish.BUNDLES.get(Color.GINGER)),
            Map.entry(Color.TAN.dyeItem, BundleBackportish.BUNDLES.get(Color.TAN))
    );
    
}
