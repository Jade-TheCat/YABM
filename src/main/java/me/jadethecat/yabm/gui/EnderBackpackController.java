package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.inventory.EnderBackpackInventory;
import me.jadethecat.yabm.inventory.EnderComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;

public class EnderBackpackController extends CottonScreenController {

    public EnderBackpackController(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        super(RecipeType.SMELTING, syncId, playerInventory);
        EnderComponent component = YABM.ENDER_BACKPACK_COMPONENT.get(player);
        this.blockInventory = new EnderBackpackInventory(component);
        WGridPanel rootPanel = (WGridPanel) getRootPanel();
        rootPanel.add(new WLabel(new TranslatableText("gui.yabm.ender_backpack"), WLabel.DEFAULT_TEXT_COLOR), 0, 0);
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 9; j++) {
                WItemSlot slot = WItemSlot.of(blockInventory, ((i-1)*9)+j);
                rootPanel.add(slot, j, i);
            }
        }

        rootPanel.add(new WLabel(new TranslatableText("container.inventory"), WLabel.DEFAULT_TEXT_COLOR), 0, 4);
        rootPanel.add(this.createPlayerInventoryPanel(), 0, 5);
        rootPanel.validate(this);
    }
    
    @Override
	public int getCraftingResultSlotIndex() {
		return -1;
	}
}