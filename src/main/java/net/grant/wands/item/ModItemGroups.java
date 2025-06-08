package net.grant.wands.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.grant.wands.WandsMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup WAND_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WandsMod.MOD_ID, "wands"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wands"))
                    .icon(() -> new ItemStack(ModItems.D_CORE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.D_CORE);
                        entries.add(ModItems.P_CORE);
                        entries.add(ModItems.U_CORE);
                        entries.add(ModItems.FIREBALL_WAND);
                        entries.add(ModItems.DRAGON_WAND);
                        entries.add(ModItems.BOLT_WAND);


                    }).build());


    public static void registerItemGroups() {
        WandsMod.LOGGER.info("Registering Item Groups for " + WandsMod.MOD_ID);
    }
}