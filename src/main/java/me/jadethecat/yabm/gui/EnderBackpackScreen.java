package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class EnderBackpackScreen extends CottonInventoryScreen<EnderBackpackGuiDescription> {
    public EnderBackpackScreen(EnderBackpackGuiDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
