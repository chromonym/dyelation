package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.ItemsDisplayed;

public class ItemsDisplayedDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemsDisplayed.JEWELRY_PILLOWS.get(color)).pattern(" * ").pattern("/w/")
        .input('*', Items.GOLD_NUGGET).input('/', Items.STICK).input('w', color.wool)
        .criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool)).offerTo(exporter);
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(ItemsDisplayed.MOD_NAME, color, "jewelry_pillow"), DyeCommon.parentWithTexturesModel(
            "minecraft:item/generated",
            "layer0", "dyelation:item/items_displayed/jewelry_pillow/"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(ItemsDisplayed.JEWELRY_PILLOWS.get(color), StringUtil.capitalize(color.asString())+" Jewelry Pillow");
    }
}
