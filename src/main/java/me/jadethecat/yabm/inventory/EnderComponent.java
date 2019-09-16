package me.jadethecat.yabm.inventory;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public interface EnderComponent extends EntitySyncedComponent {
    DefaultedList<ItemStack> getList();
    void putList(DefaultedList<ItemStack> list);
}