package supercoder79.discover.content.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Random;

public class CoalLayerFeature extends Feature<CoalLayerConfig> {
    public CoalLayerFeature(Codec<CoalLayerConfig> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<CoalLayerConfig> context) {
        WorldGenLevel world = context.level();
        Random random = context.random();
        BlockPos pos = context.origin();

        int radius = 16;

        CoalLayerConfig config = context.config();
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(world.getSeed() + config.value));
        NormalNoise noise = NormalNoise.create(worldgenrandom, -4, 1.0D);

        int height = 12 + random.nextInt(9);
        int[] mapheight = new int[height];
        int coalLayers = height / 3;
        coalLayers += random.nextInt(3);

        int idxNext = 0;
        for (int i = 0; i < coalLayers; i++) {
            idxNext += random.nextInt(12);

            mapheight[idxNext %= height] = -1;
        }

        for (int i = 0; i < mapheight.length / 2; i += 2) {
            if (mapheight[i * 2] == -1 && mapheight[i * 2 + 1] != -1) {
                mapheight[i * 2 + 1] = -1;
            }
        }

        for (int i = 0; i < mapheight.length; i++) {
            if (mapheight[i] == 0 && random.nextInt(3) > 0) {
                mapheight[i] = random.nextInt(2) + 1;
            }
        }

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double dx = x / (double) radius;
                double dz = z / (double) radius;

                if (dx * dx + dz * dz < 1) {
                    double offset = noise.getValue(pos.getX() + x, 0, pos.getZ() + z) * 8;

                    for (int i = 0; i < height; i++) {
                        BlockPos local = new BlockPos(pos.getX() + x, pos.getY() + i + (int)offset, pos.getZ() + z);

                        if (isValidToRemove(world.getBlockState(local))) {
                            int mapstate = mapheight[i];

                            if (mapstate == -1) {
                                world.setBlock(local, config.state.get(), 3);
                            } else if (mapstate == 1) {
                                world.setBlock(local, Blocks.ANDESITE.defaultBlockState(), 3);
                            } else if (mapstate == 2) {
                                world.setBlock(local, Blocks.GRANITE.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private static boolean isValidToRemove(BlockState state) {
        return state.is(BlockTags.BASE_STONE_OVERWORLD);
    }
}
