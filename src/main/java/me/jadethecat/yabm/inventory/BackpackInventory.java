package me.jadethecat.yabm.inventory;

import me.jadethecat.yabm.item.Backpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class BackpackInventory implements Inventory {
    private ItemStack theStack;
    private DefaultedList<ItemStack> theList;
    private Inventory theInventory;
    private int theSlot;

    public BackpackInventory(ItemStack stack, Size size, PlayerInventory pInv, int slotId) {
        theStack = stack;
        theInventory = pInv;
        theSlot = slotId;
        switch (size) {
            case MEDIUM:
                theList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                break;
            case LARGE:
                theList = DefaultedList.ofSize(45, ItemStack.EMPTY);
                break;
            case SMALL:
            default:
                theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
                break;
        }

        if (stack.getOrCreateTag().contains("Inventory"))
            Inventories.fromTag(theStack.getTag().getCompound("Inventory"), theList);
    }

    @Override
    public int size() {
        return theList.size();
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
        CompoundTag invTag = new CompoundTag();
        if (theStack.getOrCreateTag().contains("Inventory"))
            invTag = theStack.getTag().getCompound("Inventory");
        if (theList != null) invTag = Inventories.toTag(invTag, theList);
        else {
            theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
            invTag = Inventories.toTag(invTag, theList);
        }
        theStack.getTag().put("Inventory", invTag);
        theInventory.setStack(theSlot, theStack);
        theInventory.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return theInventory.canPlayerUse(player);
    }

    @Override
    public void clear() {
        theList.clear();
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }
}