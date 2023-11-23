package supercoder79.discover;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import supercoder79.discover.content.items.DiscoverTab;
import supercoder79.discover.content.reg.DiscoverRegistrate;
import supercoder79.discover.forge.*;
import supercoder79.discover.forge.data.DiscoverBlockTags;
import supercoder79.discover.forge.data.DiscoverDynamicProvider;
import supercoder79.discover.forge.data.ExtraLangs;
import supercoder79.discover.forge.data.recipe.DiscoverCraftingRecipes;
import supercoder79.discover.forge.data.recipe.DiscoverMachineRecipes;

import java.util.concurrent.CompletableFuture;

@Mod("discover")
public class Discover {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final NonNullSupplier<DiscoverRegistrate> REGISTRATE = DiscoverRegistrate.discover("discover");


    public Discover() {
        LOGGER.info("Registering discover!");

        IEventBus b = FMLJavaModLoadingContext.get().getModEventBus();
        DiscoverTab.REGISTER.register(b);

        DiscoverRegistrate r = REGISTRATE.get();
        DiscoverBlocks.register(r);
        DiscoverItems.register(r);
        DiscoverFluids.register(r);

        DiscoverFeatures.register(b);

        IEventBus f = MinecraftForge.EVENT_BUS;
//        f.addListener(this::onBiomeLoad);

        b.addListener(this::gatherDatagen);
    }

//    private void onBiomeLoad(BiomeLoadingEvent event) {
//        Biome.BiomeCategory category = event.getCategory();
//        BiomeGenerationSettingsBuilder generation = event.getGeneration();
//
//        if (category == Biome.BiomeCategory.NETHER) {
//            generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.SULFUR_ORE_PLACED.getHolder().get());
//
//            return;
//        }
//
//        if (category == Biome.BiomeCategory.THEEND) {
//            return;
//        }
//
//        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.KIMBERLITE_PLACED.getHolder().get());
//        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.ANTHRACITE_PLACED.getHolder().get());
//        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.LIGNITE_PLACED.getHolder().get());
//    }

    private void gatherDatagen(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());

        gen.addProvider(event.includeServer(), new DiscoverDynamicProvider(gen.getPackOutput(), provider));

        gen.addProvider(event.includeServer(), new ExtraLangs(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new DiscoverCraftingRecipes(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new DiscoverBlockTags(gen.getPackOutput(), provider, existingFileHelper));

        DiscoverMachineRecipes.register(gen);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation("discover", path);
    }
}
