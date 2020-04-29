package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class EnderBackpackScreen extends CottonInventoryScreen<EnderBackpackController> {

    public EnderBackpackScreen(EnderBackpackController container, PlayerEntity player) {
        super(container, player);
    }


}