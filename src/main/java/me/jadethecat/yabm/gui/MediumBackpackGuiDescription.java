package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.inventory.BackpackInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class MediumBackpackGuiDescription extends SyncedGuiDescription {
    public MediumBackpackGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(YABM.MED_BACKPACK_HANDLER_TYPE, syncId, playerInventory, new BackpackInventory(
                playerInventory.getStack(playerInventory.selectedSlot), BackpackInventory.Size.MEDIUM, playerInventory,
                playerInventory.selectedSlot), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(150,150);
        for (int i = 0; i < 27; i++) {
            WItemSlot itemSlot = WItemSlot.of(blockInventory, i);
            root.add(itemSlot, i%9, (i/9)+1);
        }

        root.add(this.createPlayerInventoryPanel(), 0, 5);

        root.validate(this);
    }
    public static class HandlerFactory implements NamedScreenHandlerFactory {
        @Override
        public Text getDisplayName() {
            return new TranslatableText("gui.yabm.backpack");
        }

        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            return new MediumBackpackGuiDescription(syncId, inv, ScreenHandlerContext.create(player.world, player.getBlockPos()));
        }
    }
}
