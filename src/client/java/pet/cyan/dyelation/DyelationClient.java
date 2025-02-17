package pet.cyan.dyelation;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import pet.cyan.dyelation.rendering.AnotherFurnitureRenderer;
import pet.cyan.dyelation.rendering.VerdantRenderer;

public class DyelationClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.\
		FabricLoader fl = FabricLoader.getInstance();
		if (fl.isModLoaded("another_furniture") && !fl.isModLoaded("patchup")) { // another furniture already supported by patch up
			Dyelation.LOGGER.info("Another Furniture loaded, running client setup");
			AnotherFurnitureRenderer.initialize();
		}
		if (fl.isModLoaded("verdant")) {
			Dyelation.LOGGER.info("Verdant loaded, running client setup");
			VerdantRenderer.initialize();
		}
	}
}