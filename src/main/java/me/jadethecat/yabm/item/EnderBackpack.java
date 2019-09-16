package me.jadethecat.yabm.item;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EnderBackpack extends Item {

    public EnderBackpack(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) return new TypedActionResult<ItemStack>(ActionResult.PASS, player.getStackInHand(hand));

        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isEmpty() && stack.getItem() instanceof EnderBackpack) {
            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier("yabm", "ender_backpack"), player, (buf) -> {});
        }
        
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, stack);
    }
}