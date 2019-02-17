package me.jadethecat.yabm.client;

import me.jadethecat.yabm.network.PacketHandler;
import me.jadethecat.yabm.network.PacketOpenBaublesBackpack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class InputHandler {

    @SubscribeEvent
    public void onKeyInput(TickEvent.PlayerTickEvent e) {
        if (e.side == Side.CLIENT && e.phase == TickEvent.Phase.START && Keybinds.openBackpack.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesBackpack());
        }
    }
}
