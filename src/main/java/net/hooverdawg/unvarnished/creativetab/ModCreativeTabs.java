package net.hooverdawg.unvarnished.creativetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.hooverdawg.unvarnished.Unvarnished;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.hooverdawg.unvarnished.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab UNVARNISHED_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, "unvarnished_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ILLUMINITE))
                    .title(Component.translatable("creativemodetab.unvarnished.unvarnished_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ILLUMINITE);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.STRAWBERRY);
                        output.accept(ModItems.COMBUSTIBLE_SHROOM);
                        output.accept(ModItems.TROWEL);

                    }).build());

    public static final CreativeModeTab UNVARNISHED_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, "unvarnished_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ILLUMINITE_BLOCK))
                    .title(Component.translatable("creativemodetab.unvarnished.unvarnished_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.ILLUMINITE_BLOCK);
                        output.accept(ModBlocks.MAGIC_BLOCK);

                    }).build());

    public static void registerModCreativeTabs() {
        Unvarnished.LOGGER.info("Registering Creative Tabs for " + Unvarnished.MOD_ID);
    }
}
