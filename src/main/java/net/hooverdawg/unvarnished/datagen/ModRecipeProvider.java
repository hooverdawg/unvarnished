package net.hooverdawg.unvarnished.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.hooverdawg.unvarnished.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> ILLUMINITE_SMELTABLES = List.of(
                        ModBlocks.ILLUMINITE_ORE,
                        ModBlocks.DEEPSLATE_ILLUMINITE_ORE,
                        ModBlocks.END_ILLUMINITE_ORE);

                this.oreSmelting(ILLUMINITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ILLUMINITE, 0.25f, 200, "illuminite");
                this.oreBlasting(ILLUMINITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ILLUMINITE, 0.25f, 100, "illuminite");

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.ILLUMINITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ILLUMINITE_BLOCK);

                shaped(RecipeCategory.TOOLS, ModItems.TROWEL)
                        .pattern("S  ")
                        .pattern(" CC")
                        .define('C', Items.COPPER_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                        .save(output);

                //shapeless(RecipeCategory.MISC, ModItems._____, 9)
                        //.requires(ModBlocks.______)
                        //.unlockedBy(getHasName(ModBlocks._____), has(ModBlocks.______))
                        //.group("_____")
                        //.save(output, "___");
            }
        };
    }

    @Override
    public String getName() {
        return "Unvarnished Recipes";
    }
}
