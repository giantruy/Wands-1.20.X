package net.grant.wands;

import net.grant.wands.particle.ZapParticle;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class ModParticles {

    // Declare your custom particle type
    public static final DefaultParticleType ZAP_PARTICLE = FabricParticleTypes.simple();

    // Register the particle type in the registry
    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier("wands", "zap_particle"), ZAP_PARTICLE);
    }

    // Register the client-side factory for the particle
//    public static void registerClient() {
//        // Registering the particle factory by passing the factory instance
//        ParticleFactoryRegistry.getInstance().register(ZAP_PARTICLE, new EndRodParticle.Factory());
//    }
}
