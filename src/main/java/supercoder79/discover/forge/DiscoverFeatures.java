package supercoder79.discover.forge;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import supercoder79.discover.content.worldgen.feature.CoalLayerConfig;
import supercoder79.discover.content.worldgen.feature.CoalLayerFeature;
import supercoder79.discover.content.worldgen.feature.KimberlitePipeFeature;

public class DiscoverFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "discover");

    public static RegistryObject<KimberlitePipeFeature> KIMBERLITE_PIPE;
    public static RegistryObject<CoalLayerFeature> COAL_LAYER;

    public static void register(IEventBus modBus) {
        KIMBERLITE_PIPE = register("kimberlite_pipe", new KimberlitePipeFeature(NoneFeatureConfiguration.CODEC));
        COAL_LAYER = register("coal_layer", new CoalLayerFeature(CoalLayerConfig.CODEC));

        FEATURES.register(modBus);
    }

    private static <T extends Feature<?>> RegistryObject<T> register(String name, T feature) {
        return FEATURES.register(name, () -> feature);
    }
}
