package net.grant.wands.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.grant.wands.WandsMod;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item D_CORE = registerItem("dragon_heartstring", new Item(new FabricItemSettings()));
    public static final Item P_CORE = registerItem("phoenix_feather", new Item(new FabricItemSettings()));
    public static final Item U_CORE = registerItem("unicorn_hair", new Item(new FabricItemSettings()));
    public static final Item WAND = registerItem("wand",new BowItem(new Item.Settings().maxDamage(500)));

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
