package me.jadethecat.yabm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod.EventBusSubscriber(modid = "yabm")
public class BackpackItems {
    public static Item backpack;

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        backpack = new ItemBackpack().setUnlocalizedName("backpack").setCreativeTab(CreativeTabs.MISC).setRegistryName("backpack");
        e.getRegistry().registerAll(backpack);
    }
}
