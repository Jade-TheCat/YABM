package me.jadethecat.yabm;

import me.jadethecat.yabm.client.gui.GuiBackpack;
import me.jadethecat.yabm.inventory.ContainerBackpack;
import me.jadethecat.yabm.item.ItemBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class YABMGuiHandler implements IGuiHandler {
    public static final int BACKPACK = 0;
    @Nullable
    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case BACKPACK:
                return new ContainerBackpack(player.inventory, (ItemBackpack)player.getHeldItemMainhand().getItem());
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
