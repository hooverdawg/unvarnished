package net.hooverdawg.unvarnished.registries;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.hooverdawg.unvarnished.item.ModItems;

public class ModFuels {
    public static void registerModFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.COMBUSTIBLE_SHROOM, 1200);
        });
    }
} // 1 item is ~200 (time)