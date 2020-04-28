package me.jadethecat.yabm.item;

import java.util.List;

import me.jadethecat.yabm.inventory.BackpackInventory;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Backpack extends Item {
    public Backpack(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) return new TypedActionResult<ItemStack>(ActionResult.PASS, playerEntity.getStackInHand(hand));

        ItemStack stack = playerEntity.getStackInHand(hand);
        if (!stack.isEmpty() && stack.getItem() instanceof Backpack) {
            BackpackInventory.Size size = null;
            if (!stack.hasTag()) {
                stack.setTag(new CompoundTag());
            }
            if (!stack.getTag().containsKey("Size")) {
                stack.getTag().putInt("Size", 0);
            } else {
                switch(stack.getTag().getInt("Size")) {
                    case 0:
                        size = BackpackInventory.Size.SMALL;
                        break;
                    case 1:
                        size = BackpackInventory.Size.MEDIUM;
                        break;
                    case 2:
                        size = BackpackInventory.Size.LARGE;
                        break;
                    default:
                        size = BackpackInventory.Size.SMALL;
                        break;
                }
                stack.getTag().putInt("Size", size.ordinal());
            }
            final int sizeInt = size != null ? size.ordinal() : 0;
            int slot = hand == Hand.MAIN_HAND ? playerEntity.inventory.selectedSlot : 40;
            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier("yabm", "backpack"), playerEntity, (buf) -> {
                buf.writeItemStack(stack);
                buf.writeInt(sizeInt);
                buf.writeInt(slot);
            });
        }

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
            TooltipContext context) {
        int size = stack.hasTag() && stack.getTag().containsKey("Size") ? stack.getTag().getInt("Size") : 0;
        switch (size) {
            case 0:
                tooltip.add(new TranslatableText("item.yabm.backpack.tooltip.small"));
                break;
            case 1:
                tooltip.add(new TranslatableText("item.yabm.backpack.tooltip.medium"));
                break;
            case 2:
                tooltip.add(new TranslatableText("item.yabm.backpack.tooltip.large"));
                break;
            default:
                tooltip.add(new TranslatableText("item.yabm.backpack.tooltip.small"));
                break;
        }
    }
}