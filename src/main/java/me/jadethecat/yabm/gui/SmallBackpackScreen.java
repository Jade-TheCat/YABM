package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class SmallBackpackScreen extends CottonInventoryScreen<SmallBackpackGuiDescription> {
    public SmallBackpackScreen(SmallBackpackGuiDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
