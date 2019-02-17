package me.jadethecat.yabm.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds {

    public static KeyBinding openBackpack;

    public static void register() {
        openBackpack = new KeyBinding("key.openBackpack", Keyboard.KEY_V, "key.categories.yabm");
        ClientRegistry.registerKeyBinding(openBackpack);
    }
}
