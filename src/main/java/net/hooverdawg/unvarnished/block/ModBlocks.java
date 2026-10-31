package net.hooverdawg.unvarnished.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.hooverdawg.unvarnished.Unvarnished;
import net.hooverdawg.unvarnished.block.custom.MagicBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {

    public static final Block ILLUMINITE_BLOCK = registerBlock("illuminite_block",
            properties -> new Block(properties.strength(1.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final Block ILLUMINITE_STAIRS = registerBlock("illuminite_stairs",
            properties -> new StairBlock(ILLUMINITE_BLOCK.defaultBlockState(), properties.strength(1.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final Block ILLUMINITE_SLAB = registerBlock("illuminite_slab",
            properties -> new SlabBlock(properties.strength(1.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WET_SPONGE)), Component.translatable("tooltip.unvarnished.magic_block"));

    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            properties -> new StairBlock(Blocks.CALCITE.defaultBlockState(), properties.strength(0.75f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            properties -> new SlabBlock(properties.strength(0.75f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name)))));
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, Component... tooltips) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name))));
        registerBlockItem(name, toRegister, tooltips);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Component... tooltips) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name)))) {
                    @Override
                    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        for(var component : tooltips) {
                            builder.accept(component);
                        }
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                    }
                });
    }

    public static void registerModBlocks() {
        Unvarnished.LOGGER.info("Registering Blocks for " + Unvarnished.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.insertAfter(Blocks.AMETHYST_BLOCK, ILLUMINITE_BLOCK);
            output.insertAfter(Blocks.CHISELED_TUFF_BRICKS, Blocks.CALCITE);
            output.insertAfter(Blocks.CALCITE, CALCITE_STAIRS);
            output.insertAfter(CALCITE_STAIRS, CALCITE_SLAB);
        });
    }
}