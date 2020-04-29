package me.jadethecat.yabm.inventory;

import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.item.Backpack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

public class BackpackInventory implements ImplementedInventory {
    private ItemStack theStack;
    private DefaultedList<ItemStack> theList;
    private Inventory theInventory;
    private int theSlot;

    public BackpackInventory(ItemStack stack, Size size, PlayerInventory pInv, int slotId) {
        theStack = stack;
        theInventory = pInv;
        theSlot = slotId;
        switch (size) {
            case SMALL:
                theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
                break;
            case MEDIUM:
                theList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                break;
            case LARGE:
                theList = DefaultedList.ofSize(45, ItemStack.EMPTY);
                break;
            default:
                theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
                break;
        }
        if (stack.getTag().contains("Inventory")) 
            Inventories.fromTag(theStack.getTag().getCompound("Inventory"), theList);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return theList;
    }

    @Override
    public boolean canInsertInvStack(int slot, ItemStack stack, Direction side) {
        return !(stack.getItem() instanceof Backpack);
    }

    @Override
    public boolean isValidInvStack(int slot, ItemStack stack) {
        return !(stack.getItem() instanceof Backpack);
    }

    @Override
    public void markDirty() {
        CompoundTag invTag = theStack.getTag().getCompound("Inventory");
        if (theList != null) invTag = Inventories.toTag(invTag, theList);
        else {
            theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
            invTag = Inventories.toTag(invTag, theList);
        }
        theStack.getTag().put("Inventory", invTag);
        theInventory.setInvStack(theSlot, theStack);
        theInventory.markDirty();
    }

    @Override
    public void onInvClose(PlayerEntity playerEntity_1) {
        markDirty();
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }
}