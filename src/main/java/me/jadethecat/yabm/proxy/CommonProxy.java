package me.jadethecat.yabm.proxy;

import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.item.BackpackItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {

    }
    public void init(FMLInitializationEvent e) {

    }
    public void postInit(FMLPostInitializationEvent e) {

    }

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new BackpackItems());
    }
    public void registerRenders() {}

}
