package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonScreen;
import net.minecraft.entity.player.PlayerEntity;

public class BackpackScreen extends CottonScreen<BackpackController> {
    public BackpackScreen(BackpackController container, PlayerEntity player) {
        super(container, player);
    }
}