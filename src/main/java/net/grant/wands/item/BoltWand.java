package net.grant.wands.item;

import net.grant.wands.WandsMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.ProjectileUtil;

import org.jetbrains.annotations.Nullable;

public class BoltWand extends Item {
    public BoltWand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d start = user.getEyePos();
        Vec3d direction = user.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(50.0)); // 50 block range

        if (!world.isClient) {
            EntityHitResult entityHit = raycastEntities(world, user, start, end);

            if (entityHit != null && entityHit.getEntity() instanceof LivingEntity target) {
                HitResult blockHit = world.raycast(new RaycastContext(
                        start, entityHit.getPos(),
                        RaycastContext.ShapeType.COLLIDER,
                        RaycastContext.FluidHandling.NONE,
                        user
                ));

                if (blockHit.getType() == HitResult.Type.MISS) {
                    // Kill target
                    target.kill();

                    // Play zap sound
                    world.playSound(
                            null,
                            target.getBlockPos(),
                            SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                            SoundCategory.PLAYERS,
                            0.5F, 2.0F
                    );
                }
            }
        } else {
            // Client-side: particles and bolt visual
            spawnZapParticles(start, end);
        }

        user.getItemCooldownManager().set(this, 1); // Cooldown (0.05s)
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Nullable
    private EntityHitResult raycastEntities(World world, PlayerEntity user, Vec3d start, Vec3d end) {
        Box box = user.getBoundingBox().stretch(end.subtract(start)).expand(1.0);
        return ProjectileUtil.getEntityCollision(
                world, user, start, end, box,
                entity -> entity.isAlive() && entity != user && entity instanceof LivingEntity
        );
    }

    private void spawnZapParticles(Vec3d start, Vec3d end) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        Vec3d direction = end.subtract(start);
        double length = direction.length();
        Vec3d step = direction.normalize().multiply(0.2); // Particle spacing
        int count = (int) (length / 0.5);

        Vec3d pos = start;

        double offsetY = 0;
        double offsetX = 0;
        double offsetZ = 0;
        for (int i = 0; i < count; i++) {
            // Add a small random offset to simulate zig-zag
            offsetX = (Math.random() - 0.5) * 0.25;
            offsetY = (Math.random() - 0.5) * 0.25;
            offsetZ = (Math.random() - 0.5) * 0.25;

            client.world.addParticle(
                    WandsMod.ZAP_PARTICLE,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    0, 0, 0
            );

            pos = pos.add(step);
        }

        // Bright spark at the impact point
        client.world.addParticle(
                WandsMod.ZAP_PARTICLE,
                pos.x + offsetX,
                pos.y + offsetY,
                pos.z + offsetZ,
                0, 0, 0
        );
    }
}




//package net.grant.wands.item;
//
//import net.minecraft.entity.player.PlayerEntity;
//import net.grant.wands.entity.MagicBoltEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.Hand;
//import net.minecraft.util.TypedActionResult;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.world.World;
//
//public class BoltWand extends Item {
//    public BoltWand(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        if (!world.isClient) {
//            Vec3d look = user.getRotationVec(1.0F); // Where the player is looking
//            double dx = look.x;
//            double dy = look.y;
//            double dz = look.z;
//
//            // Spawn the fireball a bit in front of the player
//            Vec3d spawnPos = user.getPos().add(dx * 2, dy * 2 + user.getStandingEyeHeight(), dz * 2);
//
//            MagicBoltEntity bolt = new MagicBoltEntity(world, user, dx, dy, dz);
//            bolt.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
//
//            // Give it velocity in the look direction
//            double speed = 1.0; // Tweak for faster/slower fireball
//            bolt.setVelocity(dx * speed, dy * speed, dz * speed);
//
//            world.spawnEntity(bolt);
//        }
//
//        user.getItemCooldownManager().set(this, 2); // 0.1-second cooldown
//        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
//    }
//}
