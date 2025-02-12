package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import pet.cyan.dyelation.Color;

public class ModDatagen {
    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {}
    public static void blockLootTables(FabricBlockLootTableProvider provider, Color color) {}
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator, Color color) {}
    public static void itemModels(ItemModelGenerator itemModelGenerator, Color color) {}
    public static void langEnglish(TranslationBuilder builder, Color color) {}
}
