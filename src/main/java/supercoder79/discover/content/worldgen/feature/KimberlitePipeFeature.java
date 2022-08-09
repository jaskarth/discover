package supercoder79.discover.content.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import supercoder79.discover.forge.DiscoverBlocks;

import java.util.Random;

public class KimberlitePipeFeature extends Feature<NoneFeatureConfiguration> {
    public KimberlitePipeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        Random random = context.random();
        BlockPos pos = context.origin();

//        System.out.println("-------------- " + pos);

        double radius = 8 + random.nextInt(4);
        int radMax = (int)radius;

        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(world.getSeed()));
        NormalNoise noise = NormalNoise.create(worldgenrandom, -4, 1.0D);

        int max = world.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, pos.getX(), pos.getZ());
        for (int x = -radMax; x <= radMax; x++) {
            for (int z = -radMax; z <= radMax; z++) {
                int heightAt = world.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, pos.getX() + x, pos.getZ() + z);

                if (heightAt > max) {
                    max = heightAt;
                }
            }
        }

        pos = pos.atY(max);

        // + 64
        int depth = world.getMinBuildHeight();

        for (int y = max; y > depth; y--) {
            for (int x = -radMax; x <= radMax; x++) {
                for (int z = -radMax; z <= radMax; z++) {
                    double dx = x / radius;
                    double dz = z / radius;

                    BlockPos local = new BlockPos(pos.getX() + x, y, pos.getZ() + z);
                    if (dx * dx + dz * dz < 1 + (noise.getValue(local.getX(), local.getY(), local.getZ()) * 0.08)) {

                        if (isValidToRemove(world.getBlockState(local))) {
                            if (y == world.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, local.getX(), local.getZ()) - 1) {
                                if (world.getBlockState(local.above()).isAir() && random.nextInt(32) != 0) {
                                    world.setBlock(local, Blocks.AIR.defaultBlockState(), 3);
                                }
                            } else {
                                BlockState state = buildState(y, random);

                                if (state != null) {
                                    world.setBlock(local, state, 3);

                                    if (!state.getFluidState().isEmpty()) {
                                        world.scheduleTick(local, state.getFluidState().getType(), 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            radius -= (1 / 24.0);
        }

        return true;
    }

    // Nullable
    private static BlockState buildState(int y, Random random) {
        if (y < -8) {
            if (random.nextInt(320) == 0) {
                return Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState();
            }
        }

        if (random.nextInt(8) == 0) {
            return null;
        }

        if (random.nextInt(36) == 0) {
            return Blocks.LAVA.defaultBlockState();
        }

        return DiscoverBlocks.KIMBERLITE.getDefaultState();
    }

    private static boolean isValidToRemove(BlockState state) {
        return state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
    }
}
