package me.jadethecat.yabm.inventory;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryBackpack extends ItemStackHandler {
    public InventoryBackpack() {super(1);}
    public InventoryBackpack( int numRows ) {
        super(9 * numRows);
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        markDirty();
    }

    public void markDirty() {}
}
