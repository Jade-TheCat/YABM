package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import me.jadethecat.yabm.inventory.BackpackInventory;
import me.jadethecat.yabm.item.Backpack;
import net.minecraft.container.SlotActionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class BackpackController extends CottonCraftingController {
    ItemStack theStack;
    int theSlot;
    public BackpackController(int syncId, PlayerInventory playerInventory, ItemStack stack, int size, int slotId) {
        super(RecipeType.SMELTING, syncId, playerInventory);
        theStack = stack;
        this.playerInventory = playerInventory;
        this.blockInventory = new BackpackInventory(theStack,  BackpackInventory.Size.values()[size], playerInventory, slotId);
        this.theSlot = slotId;
        WGridPanel rootPanel = (WGridPanel) getRootPanel();
        Text label = stack.hasCustomName() ? stack.getName() : new TranslatableText("gui.yabm.backpack");
        rootPanel.add(new WLabel(label, WLabel.DEFAULT_TEXT_COLOR), 0, 0);
        int rows = (int)Math.ceil(blockInventory.getInvSize() / 9.0);
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < 9; j++) {
                WItemSlot slot = WItemSlot.of(blockInventory, ((i-1)*9)+j);
                rootPanel.add(slot, j, i);
            }
        }

        rootPanel.add(new WLabel(new TranslatableText("container.inventory"), WLabel.DEFAULT_TEXT_COLOR), 0, rows+1);
        rootPanel.add(this.createPlayerInventoryPanel(), 0, rows+2);
        rootPanel.validate(this);
    }

    @Override
	public int getCraftingResultSlotIndex() {
		return -1;
	}

    @Override
    public ItemStack onSlotClick(int slotNumber, int button, SlotActionType action, PlayerEntity player) {
        if (slotNumber >= 0 && (action == SlotActionType.PICKUP || action == SlotActionType.PICKUP_ALL) && this.getStacks().get(slotNumber).getItem() instanceof Backpack)
            return ItemStack.EMPTY;
        else
            return super.onSlotClick(slotNumber, button, action, player);
    }
}