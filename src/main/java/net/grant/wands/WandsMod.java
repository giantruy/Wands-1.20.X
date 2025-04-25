package net.grant.wands;

import net.fabricmc.api.ModInitializer;

import net.grant.wands.item.ModItemGroups;
import net.grant.wands.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandsMod implements ModInitializer {
	public static final String MOD_ID = "wands";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
	}
}