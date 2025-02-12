package pet.cyan.dyelation;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFItemTags;

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
import pet.cyan.dyelation.datagen.AnotherFurnitureDatagen;
import pet.cyan.dyelation.interop.AnotherFurniture;

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
			});
		}
	}

	private static class DyeItemTagGenerator extends FabricTagProvider<Item> {
		public DyeItemTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.ITEM, registriesFuture);
		}
		@Override
		protected void configure(WrapperLookup arg) {
			DyeCommon.doSomethingForAllColors(color -> {
				// ANOTHER FURNITURE
				getOrCreateTagBuilder(AFItemTags.STOOLS).add(AnotherFurniture.STOOLS.get(color).asItem());
				getOrCreateTagBuilder(AFItemTags.CURTAINS).add(AnotherFurniture.CURTAINS.get(color).asItem());
				getOrCreateTagBuilder(AFItemTags.LAMPS).add(AnotherFurniture.LAMPS.get(color).asItem());
				getOrCreateTagBuilder(AFItemTags.SOFAS).add(AnotherFurniture.SOFAS.get(color).asItem());
				getOrCreateTagBuilder(AFItemTags.TALL_STOOLS).add(AnotherFurniture.TALL_STOOLS.get(color).asItem());
			});
		}
	}

	private static class DyeBlockTagGenerator extends FabricTagProvider<Block> {
		public DyeBlockTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.BLOCK, registriesFuture);
		}
		@Override
		protected void configure(WrapperLookup arg) {
			DyeCommon.doSomethingForAllColors(color -> {
				// ANOTHER FURNITURE
				getOrCreateTagBuilder(AFBlockTags.STOOLS).add(AnotherFurniture.STOOLS.get(color));
				getOrCreateTagBuilder(AFBlockTags.CURTAINS).add(AnotherFurniture.CURTAINS.get(color));
				getOrCreateTagBuilder(AFBlockTags.LAMPS).add(AnotherFurniture.LAMPS.get(color)).add(AnotherFurniture.LAMP_CONNECTORS.get(color));
				getOrCreateTagBuilder(AFBlockTags.SOFAS).add(AnotherFurniture.SOFAS.get(color));
				getOrCreateTagBuilder(AFBlockTags.TALL_STOOLS).add(AnotherFurniture.TALL_STOOLS.get(color));
			});
		}
	}

	private static class DyeModelGenerator extends FabricModelProvider {
		public DyeModelGenerator(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			DyeCommon.doSomethingForAllColors(color -> {
				// ANOTHER FURNITURE
				AnotherFurnitureDatagen.blockModels(blockStateModelGenerator, color);
			});

		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			DyeCommon.doSomethingForAllColors(color -> {
				AnotherFurnitureDatagen.itemModels(itemModelGenerator, color);
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
