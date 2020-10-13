package aldinh777.genshin_flora.lists;

import aldinh777.genshin_flora.flowers.ElementalFlower;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import java.util.List;

public class GenshinBlocks {
    
    public static List<Block> LIST = Lists.newArrayList();

    public static Block FLAMING_FLOWER;

    public static void init() {
        FLAMING_FLOWER = new ElementalFlower("flaming_flower");
    }
}
