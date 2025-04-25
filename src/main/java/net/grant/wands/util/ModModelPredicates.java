package net.grant.wands.util;

import net.grant.wands.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.entity.LivingEntity; // Ensure you have this import

public class ModModelPredicates {
    public static void registerModelPredicates() {
        registerCustomBow(ModItems.WAND);
    }

    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("minecraft", "pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                // Correct method call here without passing `entity`
                return entity.getActiveItem() != stack ? 0.0F : (float) (stack.getMaxUseTime()) / 20.0F;
            }
        });

        ModelPredicateProviderRegistry.register(
                item,
                new Identifier("minecraft", "pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
