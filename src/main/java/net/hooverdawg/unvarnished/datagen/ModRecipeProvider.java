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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

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
                //List<ItemLike> ILLUMINITE_SMELTABLES = List.of(
                        //ModBlocks.ILLUMINITE_ORE,

                //this.oreSmelting(ILLUMINITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ILLUMINITE, 0.25f, 200, "illuminite");
                //this.oreBlasting(ILLUMINITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ILLUMINITE, 0.25f, 100, "illuminite");

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

                stairBuilder(ModBlocks.ILLUMINITE_STAIRS, Ingredient.of(ModBlocks.ILLUMINITE_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.ILLUMINITE_BLOCK), has(ModBlocks.ILLUMINITE_BLOCK))
                        .group("illuminite")
                        .save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ILLUMINITE_SLAB, ModBlocks.ILLUMINITE_BLOCK);

                stairBuilder(ModBlocks.CALCITE_STAIRS, Ingredient.of(Blocks.CALCITE))
                        .unlockedBy(getHasName(Blocks.CALCITE), has(Blocks.CALCITE))
                        .group("calcite")
                        .save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_SLAB, Blocks.CALCITE);

            }
        };
    }

    @Override
    public String getName() {
        return "Unvarnished Recipes";
    }
}
