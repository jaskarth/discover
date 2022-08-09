package supercoder79.discover.content.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.function.Supplier;

public class CoalLayerConfig implements FeatureConfiguration {
    public static final Codec<CoalLayerConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("state").xmap(CoalLayerConfig::xmap, Supplier::get).forGetter(c -> c.state),
            Codec.LONG.fieldOf("value").forGetter(c -> c.value)
    ).apply(instance, CoalLayerConfig::new));

    public final Supplier<BlockState> state;
    public final long value;

    public CoalLayerConfig(Supplier<BlockState> state, long value) {
        this.state = state;
        this.value = value;
    }

    private static Supplier<BlockState> xmap(BlockState b) {
        return () -> b;
    }
}
