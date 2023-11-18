package supercoder79.discover.forge.data.recipe;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import supercoder79.discover.Discover;
import supercoder79.discover.forge.DiscoverBlocks;
import supercoder79.discover.forge.DiscoverItems;

public class DiscoverCrushingRecipes extends DiscoverMachineRecipes {
    private final GeneratedRecipe SULFUR_ORE = create("sulfur_ore", b -> b.duration(300)
            .require(DiscoverBlocks.SULFUR_ORE.get())
            .output(DiscoverItems.SULFUR.get(), 3)
            .output(0.5f, DiscoverItems.SULFUR.get(), 1)
            .output(0.75f, AllItems.EXP_NUGGET.get())
            .output(.125f, Blocks.NETHERRACK));

    private final GeneratedRecipe WASHED_ASURINE = create("washed_asurine", b -> b.duration(250)
            .require(DiscoverItems.WASHED_ASURINE.get())
            .output(0.45f, AllItems.CRUSHED_ZINC.get())
            .output(0.45f, AllItems.ZINC_NUGGET.get())
            .output(0.1f, DiscoverItems.SULFUR.get())
    );

    private final GeneratedRecipe WASHED_CRIMSITE = create("washed_crimsite", b -> b.duration(250)
            .require(DiscoverItems.WASHED_CRIMSITE.get())
            .output(0.55f, AllItems.CRUSHED_IRON.get())
            .output(0.55f, Items.IRON_NUGGET)
            .output(0.15f, AllItems.ZINC_NUGGET.get())
    );

    private final GeneratedRecipe WASHED_OCHRUM = create("washed_ochrum", b -> b.duration(250)
            .require(DiscoverItems.WASHED_OCHRUM.get())
            .output(0.35f, AllItems.CRUSHED_GOLD.get())
            .output(0.35f, Items.GOLD_NUGGET)
            .output(0.2f, AllItems.COPPER_NUGGET.get())
    );

    private final GeneratedRecipe WASHED_VERIDIUM = create("washed_veridium", b -> b.duration(250)
            .require(DiscoverItems.WASHED_VERIDIUM.get())
            .output(0.9f, AllItems.CRUSHED_COPPER.get())
            .output(0.9f, AllItems.COPPER_NUGGET.get())
            .output(0.1f, DiscoverItems.SULFUR.get())
    );

    private final GeneratedRecipe WASHED_KIMBERLITE = create("washed_kimberlite", b -> b.duration(250)
            .require(DiscoverItems.WASHED_KIMBERLITE.get())
            .output(0.005f, Items.DIAMOND)
            .output(0.05f, Items.FLINT)
            .output(0.02f, Items.IRON_NUGGET)
    );

    private final GeneratedRecipe KIMBERLITE = create("kimberlite", b -> b.duration(250)
            .require(DiscoverBlocks.KIMBERLITE.get())
            .output(0.04f, Items.FLINT)
            .output(0.02f, AllItems.COPPER_NUGGET.get())
    );


    private final GeneratedRecipe ANTHRACITE = create("anthracite", b -> b.duration(450)
            .require(DiscoverBlocks.ANTHRACITE.get())
            .output(DiscoverItems.ANTHRACITE.get(), 1)
            .output(0.5f, DiscoverItems.ANTHRACITE.get(), 1)
            .output(0.3f, Items.COAL, 1)
    );

    private final GeneratedRecipe LIGNITE = create("lignite", b -> b.duration(250)
            .require(DiscoverBlocks.LIGNITE.get())
            .output(DiscoverItems.LIGNITE.get(), 1)
            .output(0.5f, DiscoverItems.LIGNITE.get(), 1)
            .output(0.05f, Items.COAL, 1)
    );

    public DiscoverCrushingRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.CRUSHING;
    }
}
