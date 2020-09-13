package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class LargeBackpackScreen extends CottonInventoryScreen<LargeBackpackGuiDescription> {
    public LargeBackpackScreen(LargeBackpackGuiDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
