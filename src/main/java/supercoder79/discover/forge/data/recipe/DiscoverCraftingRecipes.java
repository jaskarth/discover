package supercoder79.discover.forge.data.recipe;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import supercoder79.discover.forge.DiscoverItems;
import supercoder79.discover.forge.data.recipe.system.DiscoverRecipeProvider;
import supercoder79.discover.forge.data.recipe.system.DiscoverShapedRecipeBuilder;

import java.util.function.Consumer;

public class DiscoverCraftingRecipes extends DiscoverRecipeProvider {
    public DiscoverCraftingRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        DiscoverShapedRecipeBuilder.shaped("anthracite_torch", Blocks.TORCH, 8)
                .define('#', Items.STICK)
                .define('X', DiscoverItems.ANTHRACITE.get())
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_stone_pickaxe", has(Items.STONE_PICKAXE))
                .save(consumer);

        DiscoverShapedRecipeBuilder.shaped("lignite_torch", Blocks.TORCH, 2)
                .define('#', Items.STICK)
                .define('X', DiscoverItems.LIGNITE.get())
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_stone_pickaxe", has(Items.STONE_PICKAXE))
                .save(consumer);
    }
}
