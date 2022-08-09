package supercoder79.discover.forge.data.recipe;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import supercoder79.discover.forge.DiscoverItems;

public class DiscoverWashingRecipes extends DiscoverMachineRecipes {
    public final GeneratedRecipe WASHED_ASURINE = create("treated_asurine", b -> b.require(DiscoverItems.TREATED_ASURINE.get())
            .output(DiscoverItems.WASHED_ASURINE.get())
            .output(0.2f, Items.IRON_NUGGET));

    public final GeneratedRecipe WASHED_CRIMSITE = create("treated_crimsite", b -> b.require(DiscoverItems.TREATED_CRIMSITE.get())
            .output(DiscoverItems.WASHED_CRIMSITE.get())
            .output(0.1f, Items.GOLD_NUGGET));

    public final GeneratedRecipe WASHED_OCHRUM = create("treated_ochrum", b -> b.require(DiscoverItems.TREATED_OCHRUM.get())
            .output(DiscoverItems.WASHED_OCHRUM.get())
            .output(0.05f, DiscoverItems.SULFUR.get()));

    public final GeneratedRecipe WASHED_VERIDIUM = create("treated_veridium", b -> b.require(DiscoverItems.TREATED_VERIDIUM.get())
            .output(DiscoverItems.WASHED_VERIDIUM.get())
            .output(0.3f, AllItems.ZINC_NUGGET.get()));

    public final GeneratedRecipe WASHED_KIMBERLITE = create("treated_kimberlite", b -> b.require(DiscoverItems.TREATED_KIMBERLITE.get())
            .output(DiscoverItems.WASHED_KIMBERLITE.get())
            .output(0.05f, AllItems.COPPER_NUGGET.get()));

    public DiscoverWashingRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.SPLASHING;
    }
}
