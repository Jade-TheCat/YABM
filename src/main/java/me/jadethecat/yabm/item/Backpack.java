package me.jadethecat.yabm.item;

import java.util.List;

import me.jadethecat.yabm.gui.LargeBackpackGuiDescription;
import me.jadethecat.yabm.gui.MediumBackpackGuiDescription;
import me.jadethecat.yabm.gui.SmallBackpackGuiDescription;
import me.jadethecat.yabm.inventory.BackpackInventory;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Backpack extends Item implements NamedScreenHandlerFactory {
    public Backpack(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (hand == Hand.OFF_HAND) return new TypedActionResult<ItemStack>(ActionResult.FAIL, playerEntity.getStackInHand(hand));
        if (world.isClient) return new TypedActionResult<ItemStack>(ActionResult.PASS, playerEntity.getStackInHand(hand));
        ItemStack stack = playerEntity.getStackInHand(hand);
        if (!stack.isEmpty() && stack.getItem() instanceof Backpack) {
            BackpackInventory.Size size = null;
            if (!stack.hasTag()) {
                stack.setTag(new CompoundTag());
            }
            if (!stack.getTag().contains("Size")) {
                stack.getTag().putInt("Size", 0);
            } else {
                switch(stack.getTag().getInt("Size")) {
                    case 1:
                        size = BackpackInventory.Size.MEDIUM;
                        break;
                    case 2:
                        size = BackpackInventory.Size.LARGE;
                        break;
                    case 0:
                    default:
                        size = BackpackInventory.Size.SMALL;
                        break;
                }
                stack.getTag().putInt("Size", size.ordinal());
            }
            if (size != null) {
                switch (size) {
                    case SMALL:
                        playerEntity.openHandledScreen(new SmallBackpackGuiDescription.HandlerFactory());
                        break;
                    case MEDIUM:
                        playerEntity.openHandledScreen(new MediumBackpackGuiDescription.HandlerFactory());
                        break;
                    case LARGE:
                        playerEntity.openHandledScreen(new LargeBackpackGuiDescription.HandlerFactory());
                    default:
                        break;
                }
            } else {
                playerEntity.openHandledScreen(new SmallBackpackGuiDescription.HandlerFactory());
            }
        }

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
            TooltipContext context) {
        int size = stack.hasTag() && stack.getTag().contains("Size") ? stack.getTag().getInt("Size") : 0;
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

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SmallBackpackGuiDescription(syncId, inv, ScreenHandlerContext.create(player.world, player.getBlockPos()));
    }
}