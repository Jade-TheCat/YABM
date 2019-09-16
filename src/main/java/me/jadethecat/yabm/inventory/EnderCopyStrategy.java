package me.jadethecat.yabm.inventory;

import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;

public class EnderCopyStrategy implements RespawnCopyStrategy<EnderComponent> {

    @Override
    public void copyForRespawn(EnderComponent from, EnderComponent to, boolean lossless, boolean keepInventory) {
        RespawnCopyStrategy.copy(from, to);
    }


}