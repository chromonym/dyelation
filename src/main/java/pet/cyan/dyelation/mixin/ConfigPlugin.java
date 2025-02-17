package pet.cyan.dyelation.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("pet.cyan.dyelation.mixin.another_furniture")) {
            return FabricLoader.getInstance().isModLoaded("another_furniture");
        } else if (mixinClassName.contains("pet.cyan.dyelation.mixin.bundlebackportish")) {
            return FabricLoader.getInstance().isModLoaded("bundle-backportish");
        } else if (mixinClassName.contains("pet.cyan.dyelation.mixin.farmersdelight")) {
            return FabricLoader.getInstance().isModLoaded("farmersdelight");
        } else if (mixinClassName.contains("pet.cyan.dyelation.mixin.items_displayed")) {
            return FabricLoader.getInstance().isModLoaded("items_displayed");
        } else if (mixinClassName.contains("pet.cyan.dyelation.mixin.spelunkery")) {
            return FabricLoader.getInstance().isModLoaded("spelunkery");
        } else if (mixinClassName.contains("pet.cyan.dyelation.mixin.verdant")) {
            return FabricLoader.getInstance().isModLoaded("verdant");
        } else {
            return true;
        }
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
    
}
