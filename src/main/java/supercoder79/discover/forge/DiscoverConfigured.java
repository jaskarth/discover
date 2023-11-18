package supercoder79.discover.forge;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import supercoder79.discover.Discover;
import supercoder79.discover.content.worldgen.feature.CoalLayerConfig;

public class DiscoverConfigured {
    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Discover.id(name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        ctx.register(key("kimberlite_pipe"), new ConfiguredFeature<>(DiscoverFeatures.KIMBERLITE_PIPE.get(), NoneFeatureConfiguration.INSTANCE));
        ctx.register(key("anthracite"), new ConfiguredFeature<>(DiscoverFeatures.COAL_LAYER.get(), new CoalLayerConfig(() -> DiscoverBlocks.ANTHRACITE.getDefaultState(), 79)));
        ctx.register(key("lignite"), new ConfiguredFeature<>(DiscoverFeatures.COAL_LAYER.get(), new CoalLayerConfig(() -> DiscoverBlocks.LIGNITE.getDefaultState(), 97)));
        ctx.register(key("sulfur_ore"), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(new BlockMatchTest(Blocks.NETHERRACK), DiscoverBlocks.SULFUR_ORE.getDefaultState(), 8)));
    }
}
