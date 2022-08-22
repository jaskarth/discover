package supercoder79.discover;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import supercoder79.discover.content.items.DiscoverTab;
import supercoder79.discover.content.reg.DiscoverRegistrate;
import supercoder79.discover.forge.*;
import supercoder79.discover.forge.data.ExtraLangs;
import supercoder79.discover.forge.data.recipe.DiscoverCraftingRecipes;
import supercoder79.discover.forge.data.recipe.DiscoverMachineRecipes;

@Mod("discover")
public class Discover {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final NonNullSupplier<CreateRegistrate> registrate = DiscoverRegistrate.discover("discover");
    public static final DiscoverTab DISCOVER_TAB = new DiscoverTab();

    public Discover() {
        System.out.println("Registering discover!");
        CreateRegistrate r = registrate.get();
        DiscoverBlocks.register(r);
        DiscoverItems.register(r);
        DiscoverFluids.register(r);

        IEventBus b = FMLJavaModLoadingContext.get().getModEventBus();
        DiscoverFeatures.register(b);
        DiscoverPlaced.register(b);

        IEventBus f = MinecraftForge.EVENT_BUS;
        f.addListener(this::onBiomeLoad);

        b.addListener(this::gatherDatagen);
    }

    private void onBiomeLoad(BiomeLoadingEvent event) {
        Biome.BiomeCategory category = event.getCategory();
        BiomeGenerationSettingsBuilder generation = event.getGeneration();

        if (category == Biome.BiomeCategory.NETHER) {
            generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.SULFUR_ORE_PLACED.getHolder().get());

            return;
        }

        if (category == Biome.BiomeCategory.THEEND) {
            return;
        }

        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.KIMBERLITE_PLACED.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.ANTHRACITE_PLACED.getHolder().get());
        generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, DiscoverPlaced.LIGNITE_PLACED.getHolder().get());
    }

    private void gatherDatagen(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        gen.addProvider(new ExtraLangs(gen));
        gen.addProvider(new DiscoverCraftingRecipes(gen));

        DiscoverMachineRecipes.register(gen);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation("discover", path);
    }
}
