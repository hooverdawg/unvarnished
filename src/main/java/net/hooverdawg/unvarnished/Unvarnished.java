package net.hooverdawg.unvarnished;

import net.fabricmc.api.ModInitializer;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.hooverdawg.unvarnished.creativetab.ModCreativeTabs;
import net.hooverdawg.unvarnished.item.ModItems;
import net.hooverdawg.unvarnished.registries.ModFuels;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unvarnished implements ModInitializer {
	public static final String MOD_ID = "unvarnished";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeTabs.registerModCreativeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModFuels.registerModFuels();
	}
}
