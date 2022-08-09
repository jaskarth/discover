package supercoder79.discover.forge;

import com.simibubi.create.content.contraptions.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.FluidEntry;

public class DiscoverFluids {
    public static FluidEntry<VirtualFluid> SULFURIC_ACID;

    public static void register(CreateRegistrate registrate) {
        SULFURIC_ACID = registrate.virtualFluid("sulfuric_acid")
                .lang("Oil Of Vitriol")
                .register();
    }
}
