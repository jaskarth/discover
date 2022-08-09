package supercoder79.discover.forge.data.recipe;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.contraptions.processing.HeatCondition;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.material.Fluids;
import supercoder79.discover.forge.DiscoverFluids;
import supercoder79.discover.forge.DiscoverItems;

public class DiscoverMixingRecipes extends DiscoverMachineRecipes {

    private final CreateRecipeProvider.GeneratedRecipe SULFURIC_ACID = create("sulfuric_acid", b -> b.require(Fluids.WATER, 500)
            .require(DiscoverItems.SULFUR.get())
            .output(DiscoverFluids.SULFURIC_ACID.get(), 500)
            .requiresHeat(HeatCondition.HEATED));

    public DiscoverMixingRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.MIXING;
    }
}
