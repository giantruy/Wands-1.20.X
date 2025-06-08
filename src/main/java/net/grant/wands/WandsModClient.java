package net.grant.wands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.grant.wands.entity.MagicBoltEntity;
import net.grant.wands.entity.ModEntities;
import net.grant.wands.ModParticles;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

public class WandsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntities.register();  // Register all entities
//        ModParticles.registerClient();
        // For this example, we will use the end rod particle behaviour.
        ParticleFactoryRegistry.getInstance().register(WandsMod.ZAP_PARTICLE, EndRodParticle.Factory::new);
        EntityRendererRegistry.register(WandsMod.MagicBoltEntityType, (context) ->
                new EntityRenderer<MagicBoltEntity>(context) {
                    @Override
                    public Identifier getTexture(MagicBoltEntity entity) {
                        return null;
                    }
                });
    }
}
