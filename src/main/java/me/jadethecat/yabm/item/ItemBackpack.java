package me.jadethecat.yabm.item;

import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.YABMGuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemBackpack extends Item implements ICapabilityProvider {
    private ItemStackHandler inventory = new ItemStackHandler(1);

    public ItemBackpack() {
        super();
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public boolean updateItemStackNBT(NBTTagCompound nbt) {
        nbt.setTag("Inventory", inventory.serializeNBT());
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        inventory.deserializeNBT(nbt.getCompoundTag("Inventory"));

        super.readNBTShareTag(stack, nbt);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            if (!playerIn.isSneaking()) {
                playerIn.openGui(YABM.instance, YABMGuiHandler.BACKPACK, worldIn, (int)Math.round(playerIn.posX), (int)Math.round(playerIn.posY), (int)Math.round(playerIn.posZ));
            } else {
                inventory.insertItem(0, new ItemStack(Items.DIAMOND, 4), false);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, itemStack);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : null;
    }
}
