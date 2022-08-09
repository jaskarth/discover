package supercoder79.discover.content.reg;

import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.VirtualFluidBuilder;
import com.simibubi.create.repack.registrate.builders.FluidBuilder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import supercoder79.discover.Discover;
import com.simibubi.create.repack.registrate.util.nullness.NonNullSupplier;

public class DiscoverRegistrate extends CreateRegistrate {
    public static NonNullSupplier<CreateRegistrate> discover(String modid) {
        return NonNullSupplier
                .lazy(() -> new DiscoverRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get()
                        .getModEventBus()));
    }

    protected DiscoverRegistrate(String modid) {
        super(modid);
    }

    @Override
    public FluidBuilder<VirtualFluid, CreateRegistrate> virtualFluid(String name) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, Discover.id("fluid/" + name + "_still"),
                        Discover.id("fluid/" + name + "_flow"), null, VirtualFluid::new));
    }
}
