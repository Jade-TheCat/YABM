package me.jadethecat.yabm;

import me.jadethecat.yabm.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = "yabm", name = "YABM", version = "0.0.1a")
public class YABM {
    @Mod.Instance
    public static YABM instance;

    public static int modGuiIndex = 0;

    public static final int GUI_BACKPACK_INV = modGuiIndex++;

    private static Logger logger;

    @SidedProxy(clientSide = "me.jadethecat.yabm.proxy.ClientProxy", serverSide = "me.jadethecat.yabm.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        proxy.preInit(e);

        proxy.registerEventHandlers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        logger.info("Backpack, Backpack!");
        proxy.init(e);
        proxy.registerRenders();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new YABMGuiHandler());
    }
}

