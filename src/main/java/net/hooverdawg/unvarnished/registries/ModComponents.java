package net.hooverdawg.unvarnished.registries;

import com.mojang.serialization.Codec;
import net.hooverdawg.unvarnished.Unvarnished;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class ModComponents {
    public static final DataComponentType<Long> PLACING_SEED = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Unvarnished.MOD_ID, "placing_seed"),
            DataComponentType.<Long>builder()
                    .persistent(Codec.LONG)
                    .networkSynchronized(ByteBufCodecs.VAR_LONG)
                    .build());

    public static void registerModComponents() {
        // empty on purpose - calling loads class, registering component
    }
}
