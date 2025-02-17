package pet.cyan.dyelation;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.TagKey;
import pet.cyan.dyelation.datagen.*;

public class DyelationDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(DyeRecipeGenerator::new);
		pack.addProvider(DyeBlockLootTables::new);
		pack.addProvider(DyeItemTagGenerator::new);
		pack.addProvider(DyeBlockTagGenerator::new);
		pack.addProvider(DyeModelGenerator::new);
		pack.addProvider(DyeEnglishLangGenerator::new);
	}

	private static class DyeRecipeGenerator extends FabricRecipeProvider {
		private DyeRecipeGenerator(FabricDataOutput generator) {
			super(generator);
		}
		@Override
		public void generate(Consumer<RecipeJsonProvider> exporter) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.recipes(exporter, color);
				FarmersDelightDatagen.recipes(exporter, color);
				ItemsDisplayedDatagen.recipes(exporter, color);
				SpelunkeryDatagen.recipes(exporter, color);
				VerdantDatagen.recipes(exporter, color);
			});
		}
	}

	private static class DyeBlockLootTables extends FabricBlockLootTableProvider {
		protected DyeBlockLootTables(FabricDataOutput dataOutput) {
			super(dataOutput);
		}
		@Override
		public void generate() {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.blockLootTables(this, color);
				FarmersDelightDatagen.blockLootTables(this, color);
				SpelunkeryDatagen.blockLootTables(this, color);
				VerdantDatagen.blockLootTables(this, color);
			});
		}
	}

	public static class DyeItemTagGenerator extends FabricTagProvider<Item> {
		public DyeItemTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.ITEM, registriesFuture);
		}
		@Override
		protected void configure(WrapperLookup arg) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.itemTags(this, color);
				FarmersDelightDatagen.itemTags(this, color);
				SpelunkeryDatagen.itemTags(this, color);
				VerdantDatagen.itemTags(this, color);
			});
		}
		public FabricTagBuilder getOrCreateItemTagBuilder(TagKey<Item> tagKey) {
			return getOrCreateTagBuilder(tagKey);
		}
	}

	public static class DyeBlockTagGenerator extends FabricTagProvider<Block> {
		public DyeBlockTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.BLOCK, registriesFuture);
		}
		@Override
		protected void configure(WrapperLookup arg) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.blockTags(this, color);
				FarmersDelightDatagen.blockTags(this, color);
				VerdantDatagen.blockTags(this, color);
			});
		}
		public FabricTagBuilder getOrCreateBlockTagBuilder(TagKey<Block> tagKey) {
			return getOrCreateTagBuilder(tagKey);
		}
	}

	private static class DyeModelGenerator extends FabricModelProvider {
		public DyeModelGenerator(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.blockModels(blockStateModelGenerator, color);
				FarmersDelightDatagen.blockModels(blockStateModelGenerator, color);
				SpelunkeryDatagen.blockModels(blockStateModelGenerator, color);
				VerdantDatagen.blockModels(blockStateModelGenerator, color);
			});

		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.itemModels(itemModelGenerator, color);
				BundleBackportishDatagen.itemModels(itemModelGenerator, color);
				FarmersDelightDatagen.itemModels(itemModelGenerator, color);
				ItemsDisplayedDatagen.itemModels(itemModelGenerator, color);
				SpelunkeryDatagen.itemModels(itemModelGenerator, color);
				VerdantDatagen.itemModels(itemModelGenerator, color);
			});
		}
	}

	private static class DyeEnglishLangGenerator extends FabricLanguageProvider {

		private DyeEnglishLangGenerator(FabricDataOutput dataGenerator) {
			super(dataGenerator, "en_us");
		}

		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.langEnglish(translationBuilder, color);
				BundleBackportishDatagen.langEnglish(translationBuilder, color);
				FarmersDelightDatagen.langEnglish(translationBuilder, color);
				ItemsDisplayedDatagen.langEnglish(translationBuilder, color);
				SpelunkeryDatagen.langEnglish(translationBuilder, color);
				VerdantDatagen.langEnglish(translationBuilder, color);
			});
			try {
				Path path = dataOutput.getModContainer().findPath("assets/dyelation/lang/en_us.fixes.json").get();
				translationBuilder.add(path);
			} catch (Exception e) {
				throw new RuntimeException("Failed to add existing language file!", e);
			}
		}
		
	}

}
