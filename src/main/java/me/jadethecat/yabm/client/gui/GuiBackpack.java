package me.jadethecat.yabm.client.gui;

import me.jadethecat.yabm.inventory.ContainerBackpack;
import me.jadethecat.yabm.item.BackpackItems;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBackpack extends GuiContainer {
    private InventoryPlayer playerInv;
    private int inventoryRows;
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");

    public GuiBackpack(Container inventorySlotsIn, InventoryPlayer playerInv) {
        super(inventorySlotsIn);
        this.playerInv = playerInv;
        this.inventoryRows = ((ContainerBackpack)inventorySlotsIn).numRows;
        this.ySize = 114 + this.inventoryRows * 18;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BG_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format( "ui.backpack.name");
        fontRenderer.drawString(name, 8, 6, 0x404040);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 0x404040);
    }
}
