package supercoder79.discover.forge.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import supercoder79.discover.forge.DiscoverBlocks;

public class DiscoverBlockTags extends BlockTagsProvider {
    public DiscoverBlockTags(DataGenerator p_126511_, ExistingFileHelper efh) {
        super(p_126511_, "discover", efh);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                DiscoverBlocks.KIMBERLITE.get(), DiscoverBlocks.LIGNITE.get(), DiscoverBlocks.ANTHRACITE.get(), DiscoverBlocks.SULFUR_ORE.get()
        );

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                DiscoverBlocks.KIMBERLITE.get(), DiscoverBlocks.ANTHRACITE.get(), DiscoverBlocks.SULFUR_ORE.get()
        );

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                DiscoverBlocks.LIGNITE.get()
        );
    }
}
