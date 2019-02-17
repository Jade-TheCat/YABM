package me.jadethecat.yabm.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = "yabm")
public class BackpackItems {
    public static Item smallBackpack;
    public static Item mediumBackpack;
    public static Item largeBackpack;

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        smallBackpack = new ItemBackpack().setUnlocalizedName("small_backpack").setRegistryName("small_backpack");
        mediumBackpack = new ItemBackpack(3).setUnlocalizedName("medium_backpack").setRegistryName("medium_backpack");
        largeBackpack = new ItemBackpack(6).setUnlocalizedName("large_backpack").setRegistryName("large_backpack");
        e.getRegistry().registerAll(smallBackpack, mediumBackpack, largeBackpack);
    }
}
