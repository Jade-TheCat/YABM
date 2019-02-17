package me.jadethecat.yabm.item;

import me.jadethecat.yabm.inventory.InventoryBackpack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BackpackCapabilityProvider {

    @CapabilityInject(IItemHandler.class)
    private static Capability<InventoryBackpack> inventory;

    public static Capability<InventoryBackpack> getInventory() {
        return inventory;
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(InventoryBackpack.class, new Capability.IStorage<InventoryBackpack>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<InventoryBackpack> capability, InventoryBackpack instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<InventoryBackpack> capability, InventoryBackpack instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, InventoryBackpack::new);
    }

    public static <C> ICapabilityProvider getInventoryProvider(final Capability <C> cap, final Supplier<C> fac) {
        return new ICapabilityProvider() {
            @Override
            public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
                return capability == cap;
            }

            @Nullable
            @Override
            public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
                return hasCapability(capability, facing) ? cap.cast(fac.get()) : null;
            }
        };
    }
}
