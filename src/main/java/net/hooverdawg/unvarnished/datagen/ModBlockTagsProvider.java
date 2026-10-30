package net.hooverdawg.unvarnished.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ILLUMINITE_BLOCK)
                .add(ModBlocks.RAW_ILLUMINITE_BLOCK)
                .add(ModBlocks.ILLUMINITE_ORE)
                .add(ModBlocks.DEEPSLATE_ILLUMINITE_ORE)
                .add(ModBlocks.END_ILLUMINITE_ORE)
                .add(ModBlocks.MAGIC_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ILLUMINITE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DEEPSLATE_ILLUMINITE_ORE)
                .add(ModBlocks.END_ILLUMINITE_ORE);

    }
}
