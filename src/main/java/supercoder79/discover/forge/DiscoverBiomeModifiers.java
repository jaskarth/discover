package supercoder79.discover.forge;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import supercoder79.discover.Discover;

public class DiscoverBiomeModifiers {
    public static void bootstrap(BootstapContext<BiomeModifier> ctx) {
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, VanillaRegistries.createLookup());

        HolderGetter<PlacedFeature> placed = ctx.lookup(Registries.PLACED_FEATURE);
        BiomeModifier addSulfur = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.emptyNamed(ops.owner(Registries.BIOME).get(), BiomeTags.IS_NETHER),
                HolderSet.direct(placed
                        .getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, Discover.id("sulfur_ore")))),
                GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addKimberlite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.emptyNamed(ops.owner(Registries.BIOME).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placed
                        .getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, Discover.id("kimberlite_pipe")))),
                GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addAnthracite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.emptyNamed(ops.owner(Registries.BIOME).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placed
                        .getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, Discover.id("anthracite")))),
                GenerationStep.Decoration.RAW_GENERATION);

        BiomeModifier addLignite = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.emptyNamed(ops.owner(Registries.BIOME).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placed
                        .getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, Discover.id("lignite")))),
                GenerationStep.Decoration.RAW_GENERATION);

        ctx.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Discover.id("add_sulfur")), addSulfur);
        ctx.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Discover.id("add_kimberlite")), addKimberlite);
        ctx.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Discover.id("add_anthracite")), addAnthracite);
        ctx.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, Discover.id("add_lignite")), addLignite);
    }
}
