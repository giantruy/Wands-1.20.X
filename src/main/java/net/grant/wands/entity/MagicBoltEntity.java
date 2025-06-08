package net.grant.wands.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.grant.wands.WandsMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MagicBoltEntity extends SmallFireballEntity {
    public MagicBoltEntity(EntityType<? extends SmallFireballEntity> entityType, World world) {
        super(entityType, world);
    }

//    public MagicBoltEntity(World world, LivingEntity owner) {
//        super(WandsMod.MagicBoltEntityType, owner, world); // null will be changed later
//    }
//
    public MagicBoltEntity(World world, LivingEntity owner, double x, double y, double z) {
        super(world, owner, x, y, z); // null will be changed later
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has do to with the snowball's particles. (OPTIONAL)
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.HAPPY_VILLAGER : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles. This method (as well as the previous one) are optional, so if you don't understand, don't include this one.
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
        int i = 2000000000;
        entity.damage(getDamageSources().magic(), (float)i); // deals damage

        if (entity instanceof LivingEntity livingEntity) { // checks if entity is an instance of LivingEntity (meaning it is not a boat or minecart)
            livingEntity.addStatusEffect((new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10, 255))); // applies a status effect
            livingEntity.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.5F, 1F); // plays a sound for the entity hit only
        }
    }
//    @Override
//    protected void onBlockCollision(BlockState state) { // called on collision with a block
//        super.onBlockCollision(state);
//        if (!this.getWorld().isClient) { // checks if the world is client
//            this.getWorld().sendEntityStatus(this, (byte)3); // particle?
//            this.kill(); // kills the projectile
//        }
//
//    }
    protected Item getDefaultItem() {
        return null; // We will configure this later, once we have created the ProjectileItem.
    }
}