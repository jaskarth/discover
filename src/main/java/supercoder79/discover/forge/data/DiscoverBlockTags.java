package supercoder79.discover.forge.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import supercoder79.discover.forge.DiscoverBlocks;

import java.util.concurrent.CompletableFuture;

public class DiscoverBlockTags extends BlockTagsProvider {
    public DiscoverBlockTags(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper efh) {
        super(out, lookupProvider, "discover", efh);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
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
