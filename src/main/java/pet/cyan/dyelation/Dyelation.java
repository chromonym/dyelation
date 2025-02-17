package pet.cyan.dyelation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import pet.cyan.dyelation.interop.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dyelation implements ModInitializer {
	public static final String MOD_ID = "dyelation";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FabricLoader fl = FabricLoader.getInstance();
		if (fl.isModLoaded("another_furniture") && !fl.isModLoaded("patchup")) { // another furniture already supported by patch up
			LOGGER.info("Another Furniture loaded, running common setup");
			AnotherFurniture.initialize();
		}
		if (fl.isModLoaded("bundle-backportish")) {
			LOGGER.info("Bundle Backport-ish loaded, running setup");
			BundleBackportish.initialize();
		}
		if (fl.isModLoaded("desire")) {
			LOGGER.info("Desire loaded, running setup");
			Desire.initialize();
		}
		if (fl.isModLoaded("farmersdelight")) {
			LOGGER.info("Farmer's Delight loaded, running setup");
			FarmersDelight.initialize();
		}
		if (fl.isModLoaded("items_displayed")) {
			LOGGER.info("Items Displayed loaded, running setup");
			ItemsDisplayed.initialize();
		}
		if (fl.isModLoaded("spelunkery")) {
			LOGGER.info("Spelunkery loaded, running setup");
			Spelunkery.initialize();
		}
		if (fl.isModLoaded("verdant")) {
			LOGGER.info("Verdant loaded, running setup");
			Verdant.initialize();
		}
	}
}