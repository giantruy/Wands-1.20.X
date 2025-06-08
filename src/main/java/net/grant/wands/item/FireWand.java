package net.grant.wands.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireWand extends Item {
    public FireWand(Settings settings) {
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

            SmallFireballEntity fireball = new SmallFireballEntity(world, user, dx, dy, dz);
            fireball.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

            // Give it velocity in the look direction
            double speed = 1.0; // Tweak for faster/slower fireball
            fireball.setVelocity(dx * speed, dy * speed, dz * speed);

            world.spawnEntity(fireball);
        }

        user.getItemCooldownManager().set(this, 0); // 0.1-second cooldown
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
