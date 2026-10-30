package net.hooverdawg.unvarnished.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.hooverdawg.unvarnished.Unvarnished;
import net.hooverdawg.unvarnished.block.ModBlocks;
import net.hooverdawg.unvarnished.food.ModFoods;
import net.hooverdawg.unvarnished.item.custom.ChiselItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static final Item ILLUMINITE = registerItem("illuminite", Item::new);
    public static final Item RAW_ILLUMINITE = registerItem("raw_illuminite", Item::new);

    public static final Item CHISEL = registerItem("chisel", properties -> new ChiselItem(properties
            .durability(32)));

    public static final Item STRAWBERRY = registerItem("strawberry", properties -> new Item(properties
            .food(ModFoods.STRAWBERRY, ModFoods.STRAWBERRY_CONSUMABLE)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.unvarnished.strawberry"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });

    public static final Item COMBUSTIBLE_SHROOM = registerItem("combustible_shroom", properties -> new Item(properties
            .stacksTo(16)));


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Unvarnished.LOGGER.info("Registering Items for " + Unvarnished.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(ILLUMINITE);
            output.accept(RAW_ILLUMINITE);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.accept(STRAWBERRY);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(CHISEL);
        });
    }
}
