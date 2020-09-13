package me.jadethecat.yabm.client;

import me.jadethecat.yabm.YABM;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.util.Identifier;
import me.jadethecat.yabm.gui.*;

public class YABMClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.<SmallBackpackGuiDescription, SmallBackpackScreen>register(YABM.SMALL_BACKPACK_HANDLER_TYPE,
                (gui, inventory, title) -> new SmallBackpackScreen(gui, inventory.player, title));
        ScreenRegistry.<MediumBackpackGuiDescription, MediumBackpackScreen>register(YABM.MED_BACKPACK_HANDLER_TYPE,
                (gui, inventory, title) -> new MediumBackpackScreen(gui, inventory.player, title));
        ScreenRegistry.<LargeBackpackGuiDescription, LargeBackpackScreen>register(YABM.LARGE_BACKPACK_HANDLER_TYPE,
                (gui, inventory, title) -> new LargeBackpackScreen(gui, inventory.player, title));

        ScreenRegistry.<EnderBackpackGuiDescription, EnderBackpackScreen>register(YABM.ENDER_BACKPACK_HANDLER_TYPE,
                (gui, inventory, title) -> new EnderBackpackScreen(gui, inventory.player, title));
    }
}