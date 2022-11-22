package supercoder79.discover.forge;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import supercoder79.discover.content.worldgen.feature.CoalLayerConfig;

import java.util.List;

public class DiscoverPlaced {
    public static final DeferredRegister<PlacedFeature> PLACED = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, "discover");
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, "discover");

    public static RegistryObject<PlacedFeature> KIMBERLITE_PLACED;
    public static RegistryObject<PlacedFeature> ANTHRACITE_PLACED;
    public static RegistryObject<PlacedFeature> LIGNITE_PLACED;
    public static RegistryObject<PlacedFeature> SULFUR_ORE_PLACED;

    public static void register(IEventBus modBus) {
        KIMBERLITE_PLACED = buildKimberlite();
        // TODO: sulfur ore pools and lakes
        SULFUR_ORE_PLACED = buildSulfurOre();
        ANTHRACITE_PLACED = buildCoalLayer(DiscoverBlocks.ANTHRACITE, 79, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-8)), 32);
        LIGNITE_PLACED = buildCoalLayer(DiscoverBlocks.LIGNITE, 97, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(192)), 8);

        CONFIGURED.register(modBus);
        PLACED.register(modBus);
    }

    private static RegistryObject<PlacedFeature> buildKimberlite() {
        RegistryObject<ConfiguredFeature<?, ?>> configured = CONFIGURED.register("kimberlite_pipe", () -> new ConfiguredFeature<>(DiscoverFeatures.KIMBERLITE_PIPE.get(), NoneFeatureConfiguration.INSTANCE));

        return PLACED.register("kimberlite_pipe", () -> new PlacedFeature(configured.getHolder().get(),
                // TODO: needs minimum distance (region placement)
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, RarityFilter.onAverageOnceEvery(800),BiomeFilter.biome())
        ));
    }

    private static RegistryObject<PlacedFeature> buildCoalLayer(BlockEntry<Block> type, long unique, HeightRangePlacement height, int chance) {
        RegistryObject<ConfiguredFeature<?, ?>> configured = CONFIGURED.register(type.getId().getPath(),
                () -> new ConfiguredFeature<>(DiscoverFeatures.COAL_LAYER.get(), new CoalLayerConfig(type::getDefaultState, unique)));

        return PLACED.register(type.getId().getPath(), () -> new PlacedFeature(configured.getHolder().get(),
                List.of(InSquarePlacement.spread(), height, RarityFilter.onAverageOnceEvery(chance),BiomeFilter.biome())
        ));
    }

    private static RegistryObject<PlacedFeature> buildSulfurOre() {
        RegistryObject<ConfiguredFeature<?, ?>> configured = CONFIGURED.register("sulfur_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NETHERRACK, DiscoverBlocks.SULFUR_ORE.getDefaultState(), 8)));

        return PLACED.register("sulfur_ore", () -> new PlacedFeature(configured.getHolder().get(),
                // TODO: needs minimum distance (region placement)
                List.of(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome())
        ));
    }
}
