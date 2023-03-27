package supercoder79.discover;

import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import supercoder79.discover.content.items.DiscoverTab;
import supercoder79.discover.content.reg.DiscoverRegistrate;
import supercoder79.discover.forge.*;
import supercoder79.discover.forge.data.DiscoverBlockTags;
import supercoder79.discover.forge.data.ExtraLangs;
import supercoder79.discover.forge.data.recipe.DiscoverCraftingRecipes;
import supercoder79.discover.forge.data.recipe.DiscoverMachineRecipes;

import java.util.Map;

@Mod("discover")
public class Discover {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final NonNullSupplier<CreateRegistrate> registrate = DiscoverRegistrate.discover("discover");
    public static final DiscoverTab DISCOVER_TAB = new DiscoverTab();


    public Discover() {
        LOGGER.info("Registering discover!");
        CreateRegistrate r = registrate.get();
        DiscoverBlocks.register(r);
        DiscoverItems.register(r);
        DiscoverFluids.register(r);

        IEventBus b = FMLJavaModLoadingContext.get().getModEventBus();
        DiscoverFeatures.register(b);
        DiscoverPlaced.register(b);

        IEventBus f = MinecraftForge.EVENT_BUS;
        registrate.get().registerEventListeners(b);
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
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        BiomeModifier addSulfur = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_NETHER),
            HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get()
                    .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, DiscoverPlaced.SULFUR_ORE_PLACED.getId()))),
            GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addKimberlite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get()
                        .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, DiscoverPlaced.KIMBERLITE_PLACED.getId()))),
                GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addAnthracite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get()
                        .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, DiscoverPlaced.ANTHRACITE_PLACED.getId()))),
                GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addLignite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get()
                        .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, DiscoverPlaced.LIGNITE_PLACED.getId()))),
                GenerationStep.Decoration.RAW_GENERATION);

        gen.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
                gen, existingFileHelper, "discover", ops, ForgeRegistries.Keys.BIOME_MODIFIERS, Map.of(
                        id("add_sulfur"), addSulfur,
                        id("add_kimberlite"), addKimberlite,
                        id("add_anthracite"), addAnthracite,
                        id("add_lignite"), addLignite
                )));

        gen.addProvider(event.includeServer(), new ExtraLangs(gen));
        gen.addProvider(event.includeServer(), new DiscoverCraftingRecipes(gen));
        gen.addProvider(event.includeServer(), new DiscoverBlockTags(gen, existingFileHelper));

        DiscoverMachineRecipes.register(gen);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation("discover", path);
    }
}
