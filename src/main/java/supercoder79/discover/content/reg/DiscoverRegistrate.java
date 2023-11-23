package supercoder79.discover.content.reg;

import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import supercoder79.discover.Discover;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DiscoverRegistrate extends AbstractRegistrate<DiscoverRegistrate> {
    private static final Map<RegistryEntry<?>, RegistryObject<CreativeModeTab>> TAB_LOOKUP = new IdentityHashMap<>();

    public static NonNullSupplier<DiscoverRegistrate> discover(String modid) {
        return NonNullSupplier
                .lazy(() -> new DiscoverRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get()
                        .getModEventBus()));
    }

    @Nullable
    protected RegistryObject<CreativeModeTab> currentTab;

    protected DiscoverRegistrate(String modid) {
        super(modid);
    }

    public static boolean isInCreativeTab(RegistryEntry<?> entry, RegistryObject<CreativeModeTab> tab) {
        return TAB_LOOKUP.get(entry) == tab;
    }

    @Nullable
    public DiscoverRegistrate setCreativeTab(RegistryObject<CreativeModeTab> tab) {
        currentTab = tab;
        return self();
    }

    public DiscoverRegistrate registerEventListeners(IEventBus bus) {
        return  super.registerEventListeners(bus);
    }

    public FluidBuilder<VirtualFluid, DiscoverRegistrate> virtualFluid(String name) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, Discover.id("fluid/" + name + "_still"),
                        Discover.id("fluid/" + name + "_flow"), DiscoverRegistrate::defaultFluidType, VirtualFluid::new));
    }

    public static FluidType defaultFluidType(FluidType.Properties properties, ResourceLocation stillTexture,
                                             ResourceLocation flowingTexture) {
        return new FluidType(properties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return stillTexture;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return flowingTexture;
                    }
                });
            }
        };
    }

    @Override
    protected <R, T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type,
                                                       Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator,
                                                       NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);
        if (currentTab != null) {
            TAB_LOOKUP.put(entry, currentTab);
        }
        return entry;
    }


    public static class VirtualFluidBuilder<T extends ForgeFlowingFluid, P> extends FluidBuilder<T, P> {

        public VirtualFluidBuilder(AbstractRegistrate<?> owner, P parent, String name, BuilderCallback callback,
                                   ResourceLocation stillTexture, ResourceLocation flowingTexture, FluidBuilder.FluidTypeFactory typeFactory,
                                   NonNullFunction<ForgeFlowingFluid.Properties, T> factory) {
            super(owner, parent, name, callback, stillTexture, flowingTexture, typeFactory, factory);
            source(factory);
        }

        @Override
        public NonNullSupplier<T> asSupplier() {
            return this::getEntry;
        }

    }
}
