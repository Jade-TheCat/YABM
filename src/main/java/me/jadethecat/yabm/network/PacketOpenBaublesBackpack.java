package me.jadethecat.yabm.network;

import baubles.api.BaublesApi;
import io.netty.buffer.ByteBuf;
import me.jadethecat.yabm.YABM;
import me.jadethecat.yabm.client.GuiHandler;
import me.jadethecat.yabm.item.BackpackItems;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenBaublesBackpack implements IMessage, IMessageHandler<PacketOpenBaublesBackpack, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(PacketOpenBaublesBackpack message, MessageContext ctx) {
        IThreadListener main = (WorldServer) ctx.getServerHandler().player.world;
        main.addScheduledTask(() -> {
            ctx.getServerHandler().player.openContainer.onContainerClosed(ctx.getServerHandler().player);
            int slot = BaublesApi.isBaubleEquipped(ctx.getServerHandler().player, BackpackItems.smallBackpack);
            if (slot > -1) {
                ctx.getServerHandler().player.openGui(YABM.instance, GuiHandler.BACKPACK, ctx.getServerHandler().player.world, -8, slot, 0);
            }
            slot = BaublesApi.isBaubleEquipped(ctx.getServerHandler().player, BackpackItems.mediumBackpack);
            if (slot > -1) {
                ctx.getServerHandler().player.openGui(YABM.instance, GuiHandler.BACKPACK, ctx.getServerHandler().player.world, -8, slot, 0);
            }
            slot = BaublesApi.isBaubleEquipped(ctx.getServerHandler().player, BackpackItems.largeBackpack);
            if (slot > -1) {
                ctx.getServerHandler().player.openGui(YABM.instance, GuiHandler.BACKPACK, ctx.getServerHandler().player.world, -8, slot, 0);
            }
        });
        return null;
    }
}
