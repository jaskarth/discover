package supercoder79.discover.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import supercoder79.discover.forge.DiscoverBiomeModifiers;
import supercoder79.discover.forge.DiscoverConfigured;
import supercoder79.discover.forge.DiscoverPlaced;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DiscoverDynamicProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, DiscoverConfigured::bootstrap)
            .add(Registries.PLACED_FEATURE, DiscoverPlaced::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, DiscoverBiomeModifiers::bootstrap);

    public DiscoverDynamicProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("discover"));
    }


    @Override
    public String getName() {
        return "Dynamic registry content";
    }
}
