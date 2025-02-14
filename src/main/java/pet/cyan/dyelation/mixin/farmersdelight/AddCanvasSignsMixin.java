package pet.cyan.dyelation.mixin.farmersdelight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

@Mixin(ModBlockEntityTypes.class)
public class AddCanvasSignsMixin {

    // Hanging Sign Blocks (ceiling + wall)
    @Redirect(method = "lambda$static$7()Lnet/minecraft/block/entity/BlockEntityType;",
            at = @At(value = "INVOKE", target = "net/minecraft/block/entity/BlockEntityType$Builder.create(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;[Lnet/minecraft/block/Block;)Lnet/minecraft/block/entity/BlockEntityType$Builder;"))
    private static <T extends BlockEntity> BlockEntityType.Builder<T> appendMyHangingBlocks(BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks) {
        List<Block> blockList = new ArrayList<Block>(Arrays.asList(blocks));
        DyeCommon.doSomethingForAllColors(color -> {
            blockList.add(FarmersDelight.HANGING_CANVAS_SIGNS.get(color));
            blockList.add(FarmersDelight.WALL_HANGING_CANVAS_SIGNS.get(color));
        });
        Block[] newBlocks = new Block[blockList.size()];
        return BlockEntityType.Builder.create(factory, blockList.toArray(newBlocks));
    }

    // Regular Sign Blocks (standing + wall)
    @Redirect(method = "lambda$static$6()Lnet/minecraft/block/entity/BlockEntityType;",
            at = @At(value = "INVOKE", target = "net/minecraft/block/entity/BlockEntityType$Builder.create(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;[Lnet/minecraft/block/Block;)Lnet/minecraft/block/entity/BlockEntityType$Builder;"))
    private static <T extends BlockEntity> BlockEntityType.Builder<T> appendMySignBlocks(BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks) {
        List<Block> blockList = new ArrayList<Block>(Arrays.asList(blocks));
        DyeCommon.doSomethingForAllColors(color -> {
            blockList.add(FarmersDelight.CANVAS_SIGNS.get(color));
            blockList.add(FarmersDelight.WALL_CANVAS_SIGNS.get(color));
        });
        Block[] newBlocks = new Block[blockList.size()];
        return BlockEntityType.Builder.create(factory, blockList.toArray(newBlocks));
    }

}
