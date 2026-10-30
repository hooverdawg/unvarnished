package net.hooverdawg.unvarnished.item.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hooverdawg.unvarnished.registries.ModComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;


public class TrowelItem extends Item {

    public TrowelItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        //collect hotbar slots that hold blocks
        List<ItemStack> candidates = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof BlockItem) candidates.add(stack);
        }
        if (candidates.isEmpty()) return InteractionResult.FAIL;

        //seed lives on trowel so client/server agree
        ItemStack trowel = context.getItemInHand();
        long seed = trowel.getOrDefault(ModComponents.PLACING_SEED, 0L);
        Random random = new Random(seed);
        trowel.set(ModComponents.PLACING_SEED, random.nextLong());

        ItemStack chosen = candidates.get(random.nextInt(candidates.size()));
        BlockItem blockItem = (BlockItem) chosen.getItem();

        BlockHitResult hit = new BlockHitResult(
                context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
        BlockPlaceContext placeContext = new BlockPlaceContext(player, context.getHand(), chosen, hit);

        return blockItem.place(placeContext);
    }
}