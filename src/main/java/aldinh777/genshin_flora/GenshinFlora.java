package aldinh777.genshin_flora;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GenshinFlora.MODID, name = GenshinFlora.NAME, version = GenshinFlora.VERSION)
public class GenshinFlora {
    public static final String MODID = "genshin_flora";
    public static final String NAME = "Genshin Flora";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @Mod.Instance
    public static GenshinFlora INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
