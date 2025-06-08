package net.grant.wands;

import net.fabricmc.api.ModInitializer;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.grant.wands.entity.MagicBoltEntity;
import net.grant.wands.entity.ModEntities;
import net.grant.wands.item.ModItemGroups;
import net.grant.wands.item.ModItems;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandsMod implements ModInitializer {
	public static final String MOD_ID = "wands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType ZAP_PARTICLE = FabricParticleTypes.simple();
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
		ModEntities.register();
//		net.grant.wands.ModParticles.register();
		// This DefaultParticleType gets called when you want to use your particle in code.
//		public static final DefaultParticleType ZAP_PARTICLE = FabricParticleTypes.simple();

		// Register our custom particle type in the mod initializer.
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "zap_particle"), ZAP_PARTICLE);

	}
	public static final EntityType<MagicBoltEntity> MagicBoltEntityType = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(WandsMod.MOD_ID, "bolt_entity"),
			FabricEntityTypeBuilder.<MagicBoltEntity>create(SpawnGroup.MISC, MagicBoltEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);
}