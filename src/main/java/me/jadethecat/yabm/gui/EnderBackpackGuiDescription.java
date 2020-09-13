package me.jadethecat.yabm.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.inventory.EnderBackpackInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class EnderBackpackGuiDescription extends SyncedGuiDescription {
    public EnderBackpackGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(YABM.ENDER_BACKPACK_HANDLER_TYPE, syncId, playerInventory,
                new EnderBackpackInventory(YABM.ENDER_BACKPACK_COMPONENT.get(playerInventory.player)),
                getBlockPropertyDelegate(context));

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
            return new TranslatableText("gui.yabm.ender_backpack");
        }

        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            return new EnderBackpackGuiDescription(syncId, inv, ScreenHandlerContext.create(player.world,
                    player.getBlockPos()));
        }
    }
}
