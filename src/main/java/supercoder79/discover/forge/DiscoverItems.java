package supercoder79.discover.forge;

import com.simibubi.create.content.curiosities.CombustibleItem;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import supercoder79.discover.Discover;

public class DiscoverItems {

    public static ItemEntry<Item> CONSTANTAN_INGOT;
    public static ItemEntry<Item> SULFUR;

    public static ItemEntry<Item> TREATED_ASURINE;
    public static ItemEntry<Item> TREATED_CRIMSITE;
    public static ItemEntry<Item> TREATED_OCHRUM;
    public static ItemEntry<Item> TREATED_VERIDIUM;
    public static ItemEntry<Item> TREATED_KIMBERLITE;

    public static ItemEntry<Item> WASHED_ASURINE;
    public static ItemEntry<Item> WASHED_CRIMSITE;
    public static ItemEntry<Item> WASHED_OCHRUM;
    public static ItemEntry<Item> WASHED_VERIDIUM;
    public static ItemEntry<Item> WASHED_KIMBERLITE;

    public static ItemEntry<CombustibleItem> ANTHRACITE;
    public static ItemEntry<CombustibleItem> LIGNITE;

    public static void register(CreateRegistrate registrate) {
        registrate = registrate.creativeModeTab(() -> Discover.DISCOVER_TAB);

        CONSTANTAN_INGOT = registrate.item("constantan_ingot", Item::new)
                .lang("Induction Alloy Ingot")
                .register();

        SULFUR = registrate.item("sulfur", Item::new)
                .lang("Brimstone")
                .register();

        TREATED_ASURINE = registrate.item("treated_asurine", Item::new)
                .register();

        TREATED_CRIMSITE = registrate.item("treated_crimsite", Item::new)
                .register();

        TREATED_OCHRUM = registrate.item("treated_ochrum", Item::new)
                .register();

        TREATED_VERIDIUM = registrate.item("treated_veridium", Item::new)
                .register();

        TREATED_KIMBERLITE = registrate.item("treated_kimberlite", Item::new)
                .register();



        WASHED_ASURINE = registrate.item("washed_asurine", Item::new)
                .register();

        WASHED_CRIMSITE = registrate.item("washed_crimsite", Item::new)
                .register();

        WASHED_OCHRUM = registrate.item("washed_ochrum", Item::new)
                .register();

        WASHED_VERIDIUM = registrate.item("washed_veridium", Item::new)
                .register();

        WASHED_KIMBERLITE = registrate.item("washed_kimberlite", Item::new)
                .register();

        ANTHRACITE = registrate.item("anthracite_item", CombustibleItem::new)
                .onRegister(i -> i.setBurnTime(3200))
                .register();

        LIGNITE = registrate.item("lignite_item", CombustibleItem::new)
                .onRegister(i -> i.setBurnTime(800))
                .register();
    }
}
