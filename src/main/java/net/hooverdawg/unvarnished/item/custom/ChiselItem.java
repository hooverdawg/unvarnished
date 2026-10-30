package net.hooverdawg.unvarnished.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.STONE_BRICKS, Blocks.STONE,
                    Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS,
                    Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.BLACKSTONE,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.END_STONE_BRICKS, Blocks.END_STONE,
                    Blocks.TUFF, Blocks.TUFF_BRICKS,
                    Blocks.TUFF_BRICKS, Blocks.TUFF,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // right click block
        // change block a to b

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            // on server
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.unvarnished.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.unvarnished.chisel"));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}

