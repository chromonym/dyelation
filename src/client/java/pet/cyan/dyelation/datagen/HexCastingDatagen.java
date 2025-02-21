package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import at.petrak.hexcasting.common.lib.HexItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;

public class HexCastingDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HexItems.DYE_PIGMENTS.get(DyeColor.byId(color.dye.getId())))
        .pattern(" D ").pattern("DCD").pattern(" D ")
        .input('D', HexItems.AMETHYST_DUST).input('C', color.dyeItem)
        .criterion(FabricRecipeProvider.hasItem(HexItems.AMETHYST_DUST), FabricRecipeProvider.conditionsFromItem(HexItems.AMETHYST_DUST))
        .offerTo(exporter, "hexcasting/dye_colorizer_"+color.asString());
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(Identifier.of("hexcasting", "item/dye_colorizer_"+color.asString()), DyeCommon.parentWithTexturesModel("minecraft:item/generated",
        "layer0", "hexcasting:item/colorizer/dye_"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(HexItems.DYE_PIGMENTS.get(DyeColor.byId(color.dye.getId())), StringUtil.capitalize(color.asString())+" Pigment");
    }
}
