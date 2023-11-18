package supercoder79.discover.forge;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import supercoder79.discover.Discover;
import supercoder79.discover.content.items.DiscoverTab;

public class DiscoverBlocks {
    public static BlockEntry<Block> KIMBERLITE;
    public static BlockEntry<Block> ANTHRACITE;
    public static BlockEntry<Block> LIGNITE;
    public static BlockEntry<DropExperienceBlock> SULFUR_ORE;

    public static void register(CreateRegistrate registrate) {
        registrate = registrate.setCreativeTab(DiscoverTab.TAB);

        KIMBERLITE = registrate.block("kimberlite", Block::new).properties(
                p -> p.strength(4.5f, 6.0f).requiresCorrectToolForDrops()
        ).defaultLoot().simpleItem().register();

        ANTHRACITE = registrate.block("anthracite", Block::new).properties(
                p -> p.strength(4.5f, 3.0f).requiresCorrectToolForDrops()
        ).defaultLoot().simpleItem().register();

        LIGNITE = registrate.block("lignite", Block::new).properties(
                p -> p.strength(1.25f, 3.0f).requiresCorrectToolForDrops()
        ).defaultLoot().simpleItem().register();

        SULFUR_ORE = registrate.block("sulfur_ore", p -> new DropExperienceBlock(p, UniformInt.of(2, 5))).properties(
                p -> p.strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops()
        ).loot((lt, b) -> lt.add(b,
                        RegistrateBlockLootTables.createSilkTouchDispatchTable(b,
                                lt.applyExplosionDecay(b,
                                        LootItem.lootTableItem(DiscoverItems.SULFUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))))
        .lang("Brimstone Ore").simpleItem().register();
    }
}
