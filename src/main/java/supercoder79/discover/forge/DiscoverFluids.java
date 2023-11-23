package supercoder79.discover.forge;

import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import supercoder79.discover.content.reg.DiscoverRegistrate;

public class DiscoverFluids {
    public static FluidEntry<VirtualFluid> SULFURIC_ACID;

    public static void register(DiscoverRegistrate registrate) {
        SULFURIC_ACID = registrate.virtualFluid("sulfuric_acid")
                .lang("Oil Of Vitriol")
                .register();
    }
}
