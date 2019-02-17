package me.jadethecat.yabm.client;

import baubles.api.BaublesApi;
import me.jadethecat.yabm.client.gui.GuiBackpack;
import me.jadethecat.yabm.inventory.ContainerBackpack;
import me.jadethecat.yabm.item.BackpackCapabilityProvider;
import me.jadethecat.yabm.item.ItemBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int BACKPACK = 0;
    @Nullable
    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case BACKPACK:
                if (x != -8) {
                    EnumHand hand = EnumHand.values()[x];
                    return new ContainerBackpack(player.inventory, player.getHeldItem(hand).getCapability(BackpackCapabilityProvider.getInventory(), null));
                } else {
                    ItemStack item = BaublesApi.getBaublesHandler(player).getStackInSlot(y);
                    if (item.getItem() instanceof ItemBackpack) {
                        return new ContainerBackpack(player.inventory, item.getCapability(BackpackCapabilityProvider.getInventory(), null));
                    } else {
                        return null;
                    }
                }

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case BACKPACK:
                return new GuiBackpack(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }
}
