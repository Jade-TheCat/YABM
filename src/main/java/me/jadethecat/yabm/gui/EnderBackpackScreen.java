package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.client.CottonScreen;
import net.minecraft.entity.player.PlayerEntity;

public class EnderBackpackScreen extends CottonScreen<EnderBackpackController> {

    public EnderBackpackScreen(EnderBackpackController container, PlayerEntity player) {
        super(container, player);
    }


}