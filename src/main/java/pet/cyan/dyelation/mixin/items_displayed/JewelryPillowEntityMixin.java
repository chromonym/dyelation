package pet.cyan.dyelation.mixin.items_displayed;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.freedinner.items_displayed.entity.custom.AbstractDisplayEntity;
import net.freedinner.items_displayed.entity.custom.jewelry_pillow.JewelryPillowEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import pet.cyan.dyelation.Color;
import pet.cyan.dyelation.interop.ItemsDisplayed;

@Mixin(JewelryPillowEntity.class)
public abstract class JewelryPillowEntityMixin extends AbstractDisplayEntity {

    protected JewelryPillowEntityMixin() {
        super(null, null);
    }

    @Shadow
    private static TrackedData<Byte> PILLOW_COLOR_TRACKER;

    @Overwrite
    public DyeColor getColor() {
        return DyeColor.byId((Byte)this.dataTracker.get(PILLOW_COLOR_TRACKER) & 31); // adding a Single Bit to what's permitted
    }

    @Overwrite
    public void setColor(DyeColor color) {
        byte b = (Byte)this.dataTracker.get(PILLOW_COLOR_TRACKER);
        this.dataTracker.set(PILLOW_COLOR_TRACKER, (byte)(b & 224 | color.getId() & 31)); // allowing that Single Bit to stay permitted
    }

    @Inject(method = "getEntityItem()Lnet/minecraft/item/Item;", at = @At("HEAD"), cancellable = true)
    public void addExtendedEntityItems(CallbackInfoReturnable<Item> cir) {
        DyeColor color = this.getColor();
        if (color.getId() > 15 && color.getId() < 32) {
            cir.setReturnValue(ItemsDisplayed.JEWELRY_PILLOWS.get(Color.valueOf(color.getName().toUpperCase())));
        }
    }
}
