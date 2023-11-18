package supercoder79.discover.forge.data.recipe;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import supercoder79.discover.forge.DiscoverBlocks;
import supercoder79.discover.forge.DiscoverFluids;
import supercoder79.discover.forge.DiscoverItems;

public class DiscoverSpoutRecipes extends DiscoverMachineRecipes {

    public final GeneratedRecipe TREATED_ASURINE = create("treated_asurine", b -> b.require(DiscoverFluids.SULFURIC_ACID.get(), 50)
            .require(AllPaletteStoneTypes.ASURINE.baseBlock.get())
            .output(DiscoverItems.TREATED_ASURINE.get()));

    public final GeneratedRecipe TREATED_CRIMSITE = create("treated_crimsite", b -> b.require(DiscoverFluids.SULFURIC_ACID.get(), 50)
            .require(AllPaletteStoneTypes.CRIMSITE.baseBlock.get())
            .output(DiscoverItems.TREATED_CRIMSITE.get()));

    public final GeneratedRecipe TREATED_OCHRUM = create("treated_ochrum", b -> b.require(DiscoverFluids.SULFURIC_ACID.get(), 50)
            .require(AllPaletteStoneTypes.OCHRUM.baseBlock.get())
            .output(DiscoverItems.TREATED_OCHRUM.get()));

    public final GeneratedRecipe TREATED_VERIDIUM = create("treated_veridium", b -> b.require(DiscoverFluids.SULFURIC_ACID.get(), 50)
            .require(AllPaletteStoneTypes.VERIDIUM.baseBlock.get())
            .output(DiscoverItems.TREATED_VERIDIUM.get()));

    public final GeneratedRecipe TREATED_KIMBERLITE = create("treated_kimberlite", b -> b.require(DiscoverFluids.SULFURIC_ACID.get(), 50)
            .require(DiscoverBlocks.KIMBERLITE.get())
            .output(DiscoverItems.TREATED_KIMBERLITE.get()));

    public DiscoverSpoutRecipes(PackOutput generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.FILLING;
    }
}
