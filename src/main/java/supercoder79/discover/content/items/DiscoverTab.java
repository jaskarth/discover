package supercoder79.discover.content.items;

import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import supercoder79.discover.Discover;
import supercoder79.discover.forge.DiscoverItems;

public final class DiscoverTab {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "discover");

    public static final RegistryObject<CreativeModeTab> TAB = REGISTER.register("discover",
            () -> CreativeModeTab.builder()
                    .title(Components.translatable("itemGroup.discover"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> DiscoverItems.CONSTANTAN_INGOT.asStack())
                    .displayItems((p, o) -> {
                        for (RegistryEntry<Block> entry : Discover.REGISTRATE.get().getAll(Registries.BLOCK)) {
                            o.accept(entry.get());
                        }

                        for (RegistryEntry<Item> entry : Discover.REGISTRATE.get().getAll(Registries.ITEM)) {
                            o.accept(entry.get());
                        }
                    })
                    .build());
}
