package me.jadethecat.yabm.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class EnderBackpackInventory implements Inventory {
    DefaultedList<ItemStack> theList;
    EnderComponent theComponent;

    public EnderBackpackInventory(EnderComponent component) {
        theComponent = component;
        theList = theComponent.getList();
    }


    @Override
    public int size() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        return theList.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return theList.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack theStack = theList.get(slot);
        ItemStack returned = theStack.split(amount);
        theList.set(slot, theStack);
        return returned;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return theList.remove(slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        theList.set(slot, stack);
    }

    @Override
    public void markDirty() {
        theComponent.putList(theList);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return theComponent.getEntity().equals(player);
    }

    @Override
    public void clear() {
        theList.clear();
    }
}