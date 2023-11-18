package supercoder79.discover.forge.data.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import supercoder79.discover.Discover;
import supercoder79.discover.forge.data.recipe.system.DiscoverRecipeProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


// Built off of Create's ProcessingRecipeGen
public abstract class DiscoverMachineRecipes extends DiscoverRecipeProvider {

	protected static final List<DiscoverMachineRecipes> GENERATORS = new ArrayList<>();

	public static void register(DataGenerator gen) {
		PackOutput out = gen.getPackOutput();
		GENERATORS.add(new DiscoverMixingRecipes(out));
		GENERATORS.add(new DiscoverCrushingRecipes(out));
		GENERATORS.add(new DiscoverSpoutRecipes(out));
		GENERATORS.add(new DiscoverWashingRecipes(out));

		gen.addProvider(true, new DataProvider() {

			@Override
			public String getName() {
				return "Discover's Machine Recipes";
			}

			@Override
			public CompletableFuture<?> run(CachedOutput cache) {
				GENERATORS.forEach(g -> {
					try {
						g.run(cache);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				return CompletableFuture.completedFuture(null);
			}
		});
	}

	public DiscoverMachineRecipes(PackOutput generator) {
		super(generator);
	}

	/**
	 * Create a processing recipe with a single itemstack ingredient, using its id
	 * as the name of the recipe
	 */
	protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String namespace,
																	 Supplier<ItemLike> singleIngredient, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		ProcessingRecipeSerializer<T> serializer = getSerializer();
		GeneratedRecipe generatedRecipe = c -> {
			ItemLike iItemProvider = singleIngredient.get();
			transform
				.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(),
					new ResourceLocation(namespace, ForgeRegistries.ITEMS.getKey(iItemProvider.asItem())
						.getPath())).withItemIngredients(Ingredient.of(iItemProvider)))
				.build(c);
		};
		all.add(generatedRecipe);
		return generatedRecipe;
	}

	/**
	 * Create a processing recipe with a single itemstack ingredient, using its id
	 * as the name of the recipe
	 */
	<T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
		UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return create("discover", singleIngredient, transform);
	}

	protected <T extends ProcessingRecipe<?>> GeneratedRecipe createWithDeferredId(Supplier<ResourceLocation> name,
		UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		ProcessingRecipeSerializer<T> serializer = getSerializer();
		GeneratedRecipe generatedRecipe =
			c -> transform.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), name.get()))
				.build(c);
		all.add(generatedRecipe);
		return generatedRecipe;
	}

	/**
	 * Create a new processing recipe, with recipe definitions provided by the
	 * function
	 */
	protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(ResourceLocation name,
		UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return createWithDeferredId(() -> name, transform);
	}

	/**
	 * Create a new processing recipe, with recipe definitions provided by the
	 * function
	 */
	<T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
		UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
		return create(Discover.id(name), transform);
	}

	protected abstract IRecipeTypeInfo getRecipeType();

	protected <T extends ProcessingRecipe<?>> ProcessingRecipeSerializer<T> getSerializer() {
		return getRecipeType().getSerializer();
	}

	protected Supplier<ResourceLocation> idWithSuffix(Supplier<ItemLike> item, String suffix) {
		return () -> {
			ResourceLocation registryName = ForgeRegistries.ITEMS.getKey(item.get().asItem());
			return Discover.id(registryName.getPath() + suffix);
		};
	}

//	@Override
//	public String getName() {
//		return "Discover's Machine Recipes: " + getRecipeType().getId()
//			.getPath();
//	}

}