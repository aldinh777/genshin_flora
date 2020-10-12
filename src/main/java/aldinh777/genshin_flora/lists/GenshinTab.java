package aldinh777.genshin_flora.lists;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class GenshinTab extends CreativeTabs {

    public static final GenshinTab GENSHIN_TAB = new GenshinTab("genshin_flora");

    public GenshinTab(String label) {
        super(label);
    }

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Blocks.YELLOW_FLOWER);
    }
}
