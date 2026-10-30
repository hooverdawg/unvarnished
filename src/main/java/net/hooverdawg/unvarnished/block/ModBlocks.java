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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {

    public static final Block ILLUMINITE_BLOCK = registerBlock("illuminite_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.BELL)));
    public static final Block RAW_ILLUMINITE_BLOCK = registerBlock("raw_illuminite_block",
            properties -> new Block(properties.strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANCIENT_DEBRIS)));


    public static final Block ILLUMINITE_ORE = registerBlock("illuminite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,5), properties.strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final Block DEEPSLATE_ILLUMINITE_ORE = registerBlock("deepslate_illuminite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,6), properties.strength(4.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));
    public static final Block END_ILLUMINITE_ORE = registerBlock("end_illuminite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(4,8), properties.strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WET_SPONGE)), Component.translatable("tooltip.unvarnished.magic_block"));

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
            output.accept(ILLUMINITE_BLOCK);
            output.accept(RAW_ILLUMINITE_BLOCK);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(ILLUMINITE_ORE);
            output.accept(DEEPSLATE_ILLUMINITE_ORE);
            output.accept(END_ILLUMINITE_ORE);
        });
    }
}

//tile blocks
// terracotta shingles
// weaved wool
// different dye colours (more)
// mosaic for planks (not just bamboo)
// more