package net.hooverdawg.unvarnished.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.hooverdawg.unvarnished.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        //blockModelGenerators.createTrivialCube(ModBlocks.ILLUMINITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.MAGIC_BLOCK);

        blockModelGenerators.family(Blocks.CALCITE)
                        .stairs(ModBlocks.CALCITE_STAIRS)
                        .slab(ModBlocks.CALCITE_SLAB);

        blockModelGenerators.family(ModBlocks.ILLUMINITE_BLOCK)
                .stairs(ModBlocks.ILLUMINITE_STAIRS)
                .slab(ModBlocks.ILLUMINITE_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.ILLUMINITE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.STRAWBERRY, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COMBUSTIBLE_SHROOM, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
