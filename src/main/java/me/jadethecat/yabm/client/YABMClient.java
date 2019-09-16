package me.jadethecat.yabm.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.Identifier;
import me.jadethecat.yabm.gui.*;

public class YABMClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(new Identifier("yabm", "backpack"), (syncId, identifier, player, buf) -> new BackpackScreen(new BackpackController(syncId, player.inventory, buf.readItemStack(), buf.readInt(), buf.readInt()), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(new Identifier("yabm", "ender_backpack"), (syncId, identifier, player, buf) -> new EnderBackpackScreen(new EnderBackpackController(syncId, player.inventory, player), player));
    }
}