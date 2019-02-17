package me.jadethecat.yabm.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.client.GuiHandler;
import me.jadethecat.yabm.inventory.InventoryBackpack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemBackpack extends Item implements IBauble {
    int tier = 1;
    public ItemBackpack() {
        super();
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.MISC);
    }
    public ItemBackpack(int tier) {
        this();
        this.tier = tier;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return BackpackCapabilityProvider.getInventoryProvider(BackpackCapabilityProvider.getInventory(), () -> {
            InventoryBackpack ret = new InventoryBackpack(tier) {
                @Override
                public void markDirty() {
                    stack.setTagCompound(serializeNBT());
                }
            };
            if (stack.hasTagCompound()) {
                ret.deserializeNBT(stack.getTagCompound());
            }
            return ret;
        });
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!playerIn.isSneaking()) {
            playerIn.openGui(YABM.instance, GuiHandler.BACKPACK, worldIn, handIn.ordinal(), 0, 0);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.BODY;
    }
}
