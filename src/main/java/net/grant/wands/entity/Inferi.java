package net.grant.wands.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;

public class Inferi extends WolfEntity {
    public Inferi(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }
}
