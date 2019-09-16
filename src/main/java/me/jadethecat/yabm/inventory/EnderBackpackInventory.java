package me.jadethecat.yabm.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

public class EnderBackpackInventory implements ImplementedInventory {
    DefaultedList<ItemStack> theList;
    EnderComponent theComponent;

    public EnderBackpackInventory(EnderComponent component) {
        theComponent = component;
        theList = theComponent.getList();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return theList;
    }

    @Override
    public void markDirty() {
        theComponent.putList(theList);
    }

}