package net.hooverdawg.unvarnished.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.hooverdawg.unvarnished.item.ModItems;
import net.hooverdawg.unvarnished.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ILLUMINITE)
                .add(Items.IRON_INGOT)
                .add(Items.COAL)
                .add(ModItems.COMBUSTIBLE_SHROOM);
    }
}
