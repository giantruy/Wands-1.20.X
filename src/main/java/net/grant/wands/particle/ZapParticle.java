package net.grant.wands.particle;

import net.grant.wands.ModParticles;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.client.texture.SpriteAtlasTexture;

public class ZapParticle extends SpriteBillboardParticle {

    private static Sprite sprite;

    // Constructor for the custom particle
    protected ZapParticle(ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
        super(world, x, y, z, dx, dy, dz);

        if (sprite == null) {
            sprite = loadTexture();  // Load the texture for the particle
        }

        this.setSprite(sprite);  // Set the sprite for the particle
        this.maxAge = 10;  // Set the lifetime of the particle
    }

    // Load the texture manually
    private Sprite loadTexture() {
        MinecraftClient client = MinecraftClient.getInstance();
        Identifier textureId = new Identifier("wands", "textures/particles/zap_particle.png");

        // Get the particle texture atlas
        SpriteAtlasTexture spriteAtlas = (SpriteAtlasTexture) client.getSpriteAtlas(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE);

        // Return the sprite from the atlas for this particle texture
        return spriteAtlas.getSprite(textureId);  // Fetch the sprite for the texture ID
    }

    @Override
    public ParticleTextureSheet getType() {
        return (ParticleTextureSheet) ModParticles.ZAP_PARTICLE;  // Your custom particle type
    }

    // Factory for creating particles
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
            return new ZapParticle(world, x, y, z, dx, dy, dz);  // Create and return a new instance of your custom particle
        }
    }
}
