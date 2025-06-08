package net.grant.wands.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DragonWand extends Item {
    public DragonWand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Vec3d look = user.getRotationVec(1.0F); // Where the player is looking
            double dx = look.x;
            double dy = look.y;
            double dz = look.z;

            // Spawn the fireball a bit in front of the player
            Vec3d spawnPos = user.getPos().add(dx * 2, dy * 2 + user.getStandingEyeHeight(), dz * 2);

            DragonFireballEntity dragonFireball = new DragonFireballEntity(world, user, dx, dy, dz);
            dragonFireball.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

            // Give it velocity in the look direction
            double speed = 1.0; // Tweak for faster/slower fireball
            dragonFireball.setVelocity(dx * speed, dy * speed, dz * speed);

            world.spawnEntity(dragonFireball);
        }

        user.getItemCooldownManager().set(this, 20); // 1-second cooldown
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
