package me.jadethecat.yabm.inventory;


import me.jadethecat.yabm.item.ItemBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import scala.xml.PrettyPrinter;

import javax.annotation.Nonnull;

public class ContainerBackpack extends Container {
    InventoryBackpack inventory;
    public int numRows;
    public ContainerBackpack(InventoryPlayer playerInv, IItemHandler iItemHandler) {
        this.inventory = (InventoryBackpack)iItemHandler;
        this.numRows = inventory.getSlots() / 9;
        for (int j = 0; j < numRows; ++j) {
            for (int k = 0; k < 9; ++k) {
                addSlotToContainer(new SlotItemHandler(inventory, k + j * 9, 8 + k * 18, 18 + j * 18) {
                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack) {
                        return !(stack.getItem() instanceof ItemBackpack) && super.isItemValid(stack);
                    }
                });
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, (31 + (this.numRows * 18)) + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, (31 + (this.numRows * 18)) + 58));
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            int containerSlots = inventorySlots.size() - playerIn.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(stack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack1.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack1);
        }

        return stack;
    }


}
