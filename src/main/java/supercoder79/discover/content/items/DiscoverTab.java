package supercoder79.discover.content.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import supercoder79.discover.forge.DiscoverItems;

public class DiscoverTab extends CreativeModeTab {
    public DiscoverTab() {
        super("discover");
    }

    @Override
    public ItemStack makeIcon() {
        return DiscoverItems.CONSTANTAN_INGOT.asStack();
    }
}
