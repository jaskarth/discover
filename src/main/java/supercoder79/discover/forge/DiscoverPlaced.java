package supercoder79.discover.forge;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import supercoder79.discover.Discover;

import java.util.List;

public class DiscoverPlaced {
    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Discover.id(name));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> ckey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Discover.id(name));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = ctx.lookup(Registries.CONFIGURED_FEATURE);

        ctx.register(key("kimberlite_pipe"), new PlacedFeature(
                configured.getOrThrow(ckey("kimberlite_pipe")),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, RarityFilter.onAverageOnceEvery(800),BiomeFilter.biome())
        ));

        ctx.register(key("anthracite"), new PlacedFeature(
                configured.getOrThrow(ckey("anthracite")),
                List.of(InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-8)),
                        RarityFilter.onAverageOnceEvery(32),BiomeFilter.biome())
        ));

        ctx.register(key("lignite"), new PlacedFeature(
                configured.getOrThrow(ckey("lignite")),
                List.of(InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(192)),
                        RarityFilter.onAverageOnceEvery(8),BiomeFilter.biome())
        ));

        ctx.register(key("sulfur_ore"), new PlacedFeature(configured.getOrThrow(ckey("sulfur_ore")),
                List.of(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome())
        ));
    }
}
