package me.jadethecat.yabm;

import me.jadethecat.yabm.gui.BackpackController;
import me.jadethecat.yabm.gui.EnderBackpackController;
import me.jadethecat.yabm.inventory.EnderBackpackComponent;
import me.jadethecat.yabm.inventory.EnderComponent;
import me.jadethecat.yabm.inventory.EnderCopyStrategy;
import me.jadethecat.yabm.item.Backpack;
import me.jadethecat.yabm.item.EnderBackpack;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class YABM implements ModInitializer {
	// Create Items
	public static final Backpack BACKPACK = new Backpack(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
	public static final EnderBackpack ENDER_BACKPACK = new EnderBackpack(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

	// Create Component
	public static final ComponentType<EnderComponent> ENDER_BACKPACK_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("yabm", "enderbackpack"), EnderComponent.class);
	@Override
	public void onInitialize() {
		// Register Items with Minecraft
		Registry.register(Registry.ITEM, new Identifier("yabm", "backpack"), BACKPACK);
		Registry.register(Registry.ITEM, new Identifier("yabm", "ender_backpack"), ENDER_BACKPACK);

		// Register GUI Controllers with LibGui
		ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier("yabm", "backpack"), (syncId, id, player, buf) -> new BackpackController(syncId, player.inventory, buf.readItemStack(), buf.readInt(), buf.readInt()));
		ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier("yabm", "ender_backpack"), (syncId, id, player, buf) -> new EnderBackpackController(syncId, player.inventory, player));

		// Register Component with Cardinal Components
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(ENDER_BACKPACK_COMPONENT, new EnderBackpackComponent((player))));
		EntityComponents.setRespawnCopyStrategy(ENDER_BACKPACK_COMPONENT, new EnderCopyStrategy());
	}
}
