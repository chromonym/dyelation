package pet.cyan.dyelation.datagen;

import java.util.function.Consumer;

import net.blay09.mods.waystones.block.ModBlocks;
import net.blay09.mods.waystones.tag.ModBlockTags;
import net.blay09.mods.waystones.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.DyelationDataGenerator.DyeBlockTagGenerator;
import pet.cyan.dyelation.DyelationDataGenerator.DyeItemTagGenerator;

public class WaystonesDatagen {

    public static void recipes(Consumer<RecipeJsonProvider> exporter, Color color) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.scopedSharestones[color.dye.getId()].asItem())
        .input(color.dyeItem).input(ModItemTags.SHARESTONES)
        .criterion(FabricRecipeProvider.hasItem(ModBlocks.sharestone), FabricRecipeProvider.conditionsFromItem(ModBlocks.sharestone))
        .offerTo(exporter, "waystones/"+color.asString()+"_sharestone");
    }
    public static void blockTags(DyeBlockTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateBlockTagBuilder(ModBlockTags.SHARESTONES).add(ModBlocks.scopedSharestones[color.dye.getId()]);
        tagProvider.getOrCreateBlockTagBuilder(ModBlockTags.DYED_SHARESTONES).add(ModBlocks.scopedSharestones[color.dye.getId()]);
        tagProvider.getOrCreateBlockTagBuilder(ModBlockTags.IS_TELEPORT_TARGET).add(ModBlocks.scopedSharestones[color.dye.getId()]);
        tagProvider.getOrCreateBlockTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.scopedSharestones[color.dye.getId()]);
    }
    public static void itemTags(DyeItemTagGenerator tagProvider, Color color) {
        tagProvider.getOrCreateItemTagBuilder(ModItemTags.SHARESTONES).add(ModBlocks.scopedSharestones[color.dye.getId()].asItem());
        tagProvider.getOrCreateItemTagBuilder(ModItemTags.DYED_SHARESTONES).add(ModBlocks.scopedSharestones[color.dye.getId()].asItem());
    }
    
}
