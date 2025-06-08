package net.grant.wands.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.grant.wands.item.BoltWand;
import net.grant.wands.WandsMod;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.entity.passive.WolfEntity;

public class ModItems {
    public static final Item D_CORE = registerItem("dragon_core", new Item(new FabricItemSettings()));
    public static final Item P_CORE = registerItem("blaze_core", new Item(new FabricItemSettings()));
    public static final Item U_CORE = registerItem("nether_core", new Item(new FabricItemSettings()));
    public static final Item FIREBALL_WAND = Registry.register(
            Registries.ITEM,
            new Identifier(WandsMod.MOD_ID, "fireball_wand"),
            new FireWand(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON))
    );
    public static final Item DRAGON_WAND = Registry.register(
            Registries.ITEM,
            new Identifier(WandsMod.MOD_ID, "dragon_wand"),
            new DragonWand(new Item.Settings().maxCount(1).rarity(Rarity.RARE))
    );
    public static final Item BOLT_WAND = Registry.register(
            Registries.ITEM,
            new Identifier(WandsMod.MOD_ID, "bolt_wand"),
            new net.grant.wands.item.BoltWand(new Item.Settings().maxCount(1).rarity(Rarity.EPIC))
    );
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(D_CORE);
        entries.add(P_CORE);
        entries.add(U_CORE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WandsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WandsMod.LOGGER.info("Registering Mod Items for " + WandsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
