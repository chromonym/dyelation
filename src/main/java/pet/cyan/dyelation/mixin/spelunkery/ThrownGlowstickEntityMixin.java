package pet.cyan.dyelation.mixin.spelunkery;

import java.util.HashMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.ordana.spelunkery.entities.ThrownGlowstickEntity;

import net.mehvahdjukaar.moonlight.api.entity.ImprovedProjectileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.DyeCommon;
import pet.cyan.dyelation.interop.Spelunkery;

@Mixin(value = ThrownGlowstickEntity.class, remap = false)
public abstract class ThrownGlowstickEntityMixin extends ImprovedProjectileEntity {

    protected ThrownGlowstickEntityMixin() {
        super(null, null);
    }

    @Shadow
    private static TrackedData<Integer> DATA_GLOWSTICK_COLOR;

    @Mutable
    @Shadow
    private static HashMap<DyeColor, Block> DYE_COLOR_TO_BLOCK;
    
    @Inject(method = "<clinit>()V", at = @At("RETURN"))
    private static void dyeDepotDyeToBlock(CallbackInfo ci) {
        DyeCommon.doSomethingForAllColors(color -> {
            DYE_COLOR_TO_BLOCK.put(DyeColor.byId(color.dye.getId()), (Block)Spelunkery.GLOWSTICK_BLOCKS.get(color));
        });
    }

    @Redirect(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putByte(Ljava/lang/String;B)V"))
    public void WCDTNsixteenToThirtyTwo(NbtCompound compound, String key, byte value, @Local DyeColor color) {
        compound.putByte(key, color == null ? 32 : (byte)color.getId());
    }

    @Redirect(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At(value = "INVOKE", target = "Lcom/ordana/spelunkery/entities/ThrownGlowstickEntity;setColor(Lnet/minecraft/util/DyeColor;)V"))
    public void RCDFNsixteenToThirtyTwo(ThrownGlowstickEntity entity, DyeColor color, @Local int i) {
        entity.setColor(i == 32 ? null : DyeColor.byId(i));
    }

    @Redirect(method = "setColor(Lnet/minecraft/util/DyeColor;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/data/DataTracker;set(Lnet/minecraft/entity/data/TrackedData;Ljava/lang/Object;)V", ordinal = 1))
    public void SCsixteenToThirtyTwo(DataTracker tracker, TrackedData<Integer> trackedData, Object value) {
        dataTracker.set(trackedData, 32);
    }

    @Overwrite
    public DyeColor getColor() {
        int i = (Integer)this.dataTracker.get(DATA_GLOWSTICK_COLOR);
        return i == 32 ? null : DyeColor.byId(i);
    }

}
