package me.jadethecat.yabm.proxy;

import me.jadethecat.yabm.item.BackpackItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "yabm")
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerItemRenderer(BackpackItems.smallBackpack, 0 , "small_backpack");
        registerItemRenderer(BackpackItems.mediumBackpack, 0 , "medium_backpack");
        registerItemRenderer(BackpackItems.largeBackpack, 0 , "large_backpack");
    }


    public static void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("yabm" + ":" + id, "inventory"));
    }
}
