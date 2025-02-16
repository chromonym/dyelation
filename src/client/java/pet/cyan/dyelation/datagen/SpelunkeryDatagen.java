package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import com.ordana.spelunkery.reg.ModItems;
import com.ordana.spelunkery.reg.ModTags;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.util.StringUtil;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;
import pet.cyan.dyelation.interop.Spelunkery;

public class SpelunkeryDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Spelunkery.GLOWSTICK_ITEMS.get(color), 8).pattern("###").pattern("#X#").pattern("###")
        .input('#', ModTags.GLOWSTICKS).input('X',color.dyeItem)
        .criterion(FabricRecipeProvider.hasItem(ModItems.GLOWSTICK.get()), FabricRecipeProvider.conditionsFromItem(ModItems.GLOWSTICK.get()))
        .group("glowsticks").offerTo(exporter);
    }
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {
        provider.addDrop(Spelunkery.GLOWSTICK_BLOCKS.get(color));
    }
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {
        blockStateModelGenerator.registerRod(Spelunkery.GLOWSTICK_BLOCKS.get(color));
        blockStateModelGenerator.modelCollector.accept(ModelIds.getBlockModelId(Spelunkery.GLOWSTICK_BLOCKS.get(color)), DyeCommon.parentWithTexturesModel(
            "spelunkery:block/glowstick",
            "1", "dyelation:block/spelunkery/glowstick/"+color.asString(),
            "particle", "dyelation:block/spelunkery/glowstick/"+color.asString()));
        // have to use the default model names here bc i can't be bothered reimplementing registerRod
    }
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {
        itemModelGenerator.writer.accept(DyeCommon.getModdedItemModelID(Spelunkery.MOD_NAME, color, "glowstick"), DyeCommon.parentWithTexturesModel(
            "minecraft:item/handheld", "layer0", "dyelation:item/spelunkery/glowstick/"+color.asString()));
    }
    public static void langEnglish(TranslationBuilder builder, Color color) {
        builder.add(Spelunkery.GLOWSTICK_BLOCKS.get(color), StringUtil.capitalize(color.asString())+" Glowstick");
    }
    //public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {}
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(ModTags.GLOWSTICKS).add(Spelunkery.GLOWSTICK_ITEMS.get(color));
    }
}
