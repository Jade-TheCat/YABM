package me.jadethecat.yabm.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE;

    public PacketHandler() {

    }

    public static void init() {
        INSTANCE.registerMessage(PacketOpenBaublesBackpack.class, PacketOpenBaublesBackpack.class, 0, Side.SERVER);
    }
    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("yabm".toLowerCase());
    }
}
