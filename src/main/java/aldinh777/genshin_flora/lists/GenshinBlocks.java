package aldinh777.genshin_flora.lists;

import aldinh777.genshin_flora.flowers.AquaticFlower;
import aldinh777.genshin_flora.flowers.FlamingFlower;
import aldinh777.genshin_flora.flowers.MistFlower;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import java.util.List;

public class GenshinBlocks {
    
    public static List<Block> LIST = Lists.newArrayList();

    public static Block FLAMING_FLOWER;
    public static Block MIST_FLOWER;
    public static Block LOTUS;

    public static void init() {
        FLAMING_FLOWER = new FlamingFlower("flaming_flower");
        MIST_FLOWER = new MistFlower("mist_flower");
        LOTUS = new AquaticFlower("lotus");
    }
}
