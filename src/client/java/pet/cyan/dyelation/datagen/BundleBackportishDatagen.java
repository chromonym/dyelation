package pet.cyan.dyelation.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.ItemModelGenerator;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.BundleBackportish;

public class BundleBackportishDatagen {
    //public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {}
    //public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {}
    //public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {}
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(BundleBackportish.MOD_NAME, color, "bundle"), () -> {
            JsonObject bundle = new JsonObject();
            bundle.addProperty("parent", "minecraft:item/generated");
            JsonObject overrides = new JsonObject();
            overrides.addProperty("model", "dyelation:item/bundle-backportish/"+color.asString()+"_bundle_filled");
            JsonObject predicate = new JsonObject();
            predicate.addProperty("bundle-backportish:filled", 1.0E-7);
            overrides.add("predicate", predicate);
            JsonArray oArray = new JsonArray();
            oArray.add(overrides);
            bundle.add("overrides", oArray);
            JsonObject textures = new JsonObject();
            textures.addProperty("layer0", "dyelation:item/bundle-backportish/bundle/"+color.asString());
            bundle.add("textures",textures);
            return bundle;
        });
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(BundleBackportish.MOD_NAME, color, "bundle_filled"),
            DyeCommon.parentWithTexturesModel("dyelation:item/bundle-backportish/"+color.asString()+"_bundle",
            "layer0", "dyelation:item/bundle-backportish/bundle/"+color.asString()+"_filled"));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(BundleBackportish.BUNDLES.get(color), StringUtil.capitalize(color.asString())+" Bundle");
    }
}
