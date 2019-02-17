package me.jadethecat.yabm.proxy;

import me.jadethecat.yabm.client.InputHandler;
import me.jadethecat.yabm.client.Keybinds;
import me.jadethecat.yabm.item.BackpackCapabilityProvider;
import me.jadethecat.yabm.item.BackpackItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        BackpackCapabilityProvider.register();
        Keybinds.register();
        MinecraftForge.EVENT_BUS.register(new InputHandler());
    }
    public void init(FMLInitializationEvent e) {

    }
    public void postInit(FMLPostInitializationEvent e) {

    }

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new BackpackItems());
    }

}
