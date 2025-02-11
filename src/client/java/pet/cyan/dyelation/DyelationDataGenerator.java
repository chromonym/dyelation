package pet.cyan.dyelation;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.starfish_studios.another_furniture.block.CurtainBlock;
import com.starfish_studios.another_furniture.block.StoolBlock;
import com.starfish_studios.another_furniture.block.properties.HorizontalConnectionType;
import com.starfish_studios.another_furniture.registry.AFBlockTags;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import com.starfish_studios.another_furniture.registry.AFItemTags;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.When;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
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
	}

	public static Supplier<JsonElement> parentWithTexturesModel(String parent, String... textureNames) {
		JsonObject modelFile = new JsonObject();
		modelFile.addProperty("parent", parent);
		JsonObject textures = new JsonObject();
		for (var i = 0; i<textureNames.length; i+=2) {
			textures.addProperty(textureNames[i], textureNames[i+1]);
		}
		modelFile.add("textures", textures);
		return () -> modelFile;
	}

	private static class DyeRecipeGenerator extends FabricRecipeProvider {
		private DyeRecipeGenerator(FabricDataOutput generator) {
			super(generator);
		}
		@Override
		public void generate(Consumer<RecipeJsonProvider> exporter) {
			// ANOTHER FURNITURE
			DyeCommon.doSomethingForAllColors(color -> {
				// ANOTHER FURNITURE
				// stools
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.STOOLS.get(color), 3).pattern("pwp").pattern("s s")
					.input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
					.criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
					.group("stools").offerTo(exporter);
				// stools (dyeing)
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.STOOLS.get(color)).input(color.dyeItem).input(AFItemTags.STOOLS)
					.criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_STOOL.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_STOOL.get())).group("stools")
					.offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.STOOLS.get(color))+"_dyeing");
				// curtains
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.CURTAINS.get(color), 3).pattern("ss").pattern("ww").pattern("ww")
					.input('w', color.wool).input('s', Items.STICK)
					.criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
					.group("curtains").offerTo(exporter);
				// curtains (dyeing)
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.CURTAINS.get(color)).input(color.dyeItem).input(AFItemTags.CURTAINS)
					.criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_CURTAIN.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_CURTAIN.get())).group("curtains")
					.offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.CURTAINS.get(color))+"_dyeing");
				// lamps
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.LAMPS.get(color), 3).pattern(" w ").pattern("wtw").pattern(" s ")
					.input('w', color.wool).input('s', Items.STICK).input('t', Items.TORCH)
					.criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
					.group("lamps").offerTo(exporter);
				// lamps (dyeing)
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.LAMPS.get(color)).input(color.dyeItem).input(AFItemTags.LAMPS)
					.criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_LAMP.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_LAMP.get())).group("lamps")
					.offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.LAMPS.get(color))+"_dyeing");
				// sofas
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.SOFAS.get(color), 3).pattern("pw ").pattern("pww").pattern("s s")
					.input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
					.criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
					.group("sofas").offerTo(exporter);
				// sofas (dyeing)
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.SOFAS.get(color)).input(color.dyeItem).input(AFItemTags.SOFAS)
					.criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_SOFA.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_SOFA.get())).group("sofas")
					.offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.SOFAS.get(color))+"_dyeing");
				// tall stools
				ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.TALL_STOOLS.get(color), 3).pattern("pwp").pattern("sss").pattern("s s")
					.input('p', ItemTags.PLANKS).input('w', color.wool).input('s', Items.STICK)
					.criterion(FabricRecipeProvider.hasItem(color.wool), FabricRecipeProvider.conditionsFromItem(color.wool))
					.group("tall_stools").offerTo(exporter);
				// tall stools (dyeing)
				ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AnotherFurniture.TALL_STOOLS.get(color)).input(color.dyeItem).input(AFItemTags.TALL_STOOLS)
					.criterion(FabricRecipeProvider.hasItem(AFBlocks.WHITE_TALL_STOOL.get()), FabricRecipeProvider.conditionsFromItem(AFBlocks.WHITE_TALL_STOOL.get())).group("tall_stools")
					.offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(AnotherFurniture.TALL_STOOLS.get(color))+"_dyeing");
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
				// ANOTHER FURNITURE
				addDrop(AnotherFurniture.STOOLS.get(color));
				addDrop(AnotherFurniture.CURTAINS.get(color), dropsWithProperty(AnotherFurniture.CURTAINS.get(color), CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP));
				addDrop(AnotherFurniture.LAMPS.get(color));
				addDrop(AnotherFurniture.SOFAS.get(color));
				addDrop(AnotherFurniture.TALL_STOOLS.get(color));
				addDrop(AnotherFurniture.LAMP_CONNECTORS.get(color), AnotherFurniture.LAMPS.get(color));
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
				getOrCreateTagBuilder(AFBlockTags.LAMPS).add(AnotherFurniture.LAMPS.get(color));
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
				// stools
				blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(AnotherFurniture.STOOLS.get(color))
				.with(When.create().set(StoolBlock.LOW, false), BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool")))
				.with(When.create().set(StoolBlock.LOW, true), BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool", "low")))
				);
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool"), parentWithTexturesModel(
					"another_furniture:block/template/stool",
					"bottom", "another_furniture:block/stool/bottom",
					"legs", "another_furniture:block/stool/legs",
					"side", "dyelation:block/another_furniture/stool/"+color.asString()+"_side",
					"top", "dyelation:block/another_furniture/stool/"+color.asString()+"_top"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool", "low"), parentWithTexturesModel(
					"another_furniture:block/template/stool_low",
					"bottom", "another_furniture:block/stool/bottom",
					"legs", "another_furniture:block/stool/legs",
					"side", "dyelation:block/another_furniture/stool/"+color.asString()+"_side",
					"top", "dyelation:block/another_furniture/stool/"+color.asString()+"_top"));
				blockStateModelGenerator.registerParentedItemModel(AnotherFurniture.STOOLS.get(color), DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "stool"));

				// curtains
				blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(AnotherFurniture.CURTAINS.get(color))
					// north
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.MIDDLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top")))
					.with(When.create().set(CurtainBlock.FACING, Direction.NORTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom")))
					// east
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.MIDDLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					.with(When.create().set(CurtainBlock.FACING, Direction.EAST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R90))
					// south
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.MIDDLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					.with(When.create().set(CurtainBlock.FACING, Direction.SOUTH).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R180))
					// west
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.LEFT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.RIGHT).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.MIDDLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.UP).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					.with(When.create().set(CurtainBlock.FACING, Direction.WEST).set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.OPEN, false),
						BlockStateVariant.create().put(VariantSettings.MODEL, DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom"))
						.put(VariantSettings.Y, VariantSettings.Rotation.R270))
					// adirectional
					.with(When.create().set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.SINGLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("minecraft","block/air")))
					.with(When.create().set(CurtainBlock.VERTICAL_CONNECTION_TYPE, Direction.DOWN).set(CurtainBlock.HORIZONTAL_CONNECTION_TYPE, HorizontalConnectionType.MIDDLE).set(CurtainBlock.OPEN, true),
						BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of("minecraft","block/air")))
				);
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_bottom"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_closed_bottom",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_closed_bottom"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "closed_top"), parentWithTexturesModel(
						"another_furniture:block/template/curtain_closed_top",
						"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_closed_top"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_bottom"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_left_bottom",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_left_bottom"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "left_top"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_left_top",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_left_top"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "middle"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_middle",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_middle"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_bottom"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_right_bottom",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_right_bottom"));
				blockStateModelGenerator.modelCollector.accept(DyeCommon.getModdedBlockModelID(AnotherFurniture.MOD_NAME, color, "curtain", "right_top"), parentWithTexturesModel(
					"another_furniture:block/template/curtain_right_top",
					"curtain", "dyelation:block/another_furniture/curtain/"+color.asString()+"_right_top"));
			});
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			//
		}
	}

}
