package me.jadethecat.yabm.inventory;

import me.jadethecat.yabm.YABM;
import nerdhub.cardinal.components.api.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class EnderBackpackComponent implements EnderComponent {
    private DefaultedList<ItemStack> theList;
    private PlayerEntity thePlayer;

    public EnderBackpackComponent(PlayerEntity player) {
        thePlayer = player;
        theList = DefaultedList.ofSize(27, ItemStack.EMPTY);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        CompoundTag inventory = tag.contains("EnderBackpack") ? tag.getCompound("EnderBackpack") : new CompoundTag();
        Inventories.fromTag(inventory, theList);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag inventory = new CompoundTag();
        tag.put("EnderBackpack", Inventories.toTag(inventory, theList));
        return tag;
    }

    @Override
    public DefaultedList<ItemStack> getList() {
        return theList;
    }

    @Override
    public void putList(DefaultedList<ItemStack> list) {
        theList = list;
    }

    @Override
    public Entity getEntity() {
        return thePlayer;
    }

    @Override
    public ComponentType<?> getComponentType() {
        return YABM.ENDER_BACKPACK_COMPONENT;
    }

    

}