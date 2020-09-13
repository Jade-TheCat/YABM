package me.jadethecat.yabm;

import me.jadethecat.yabm.gui.EnderBackpackGuiDescription;
import me.jadethecat.yabm.gui.LargeBackpackGuiDescription;
import me.jadethecat.yabm.gui.MediumBackpackGuiDescription;
import me.jadethecat.yabm.gui.SmallBackpackGuiDescription;
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
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class YABM implements ModInitializer {
	// Create Items
	public static final Backpack BACKPACK = new Backpack(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
	public static final EnderBackpack ENDER_BACKPACK = new EnderBackpack(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

	// Create Component
	public static final ComponentType<EnderComponent> ENDER_BACKPACK_COMPONENT = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("yabm", "enderbackpack"), EnderComponent.class);
	public static ScreenHandlerType<SmallBackpackGuiDescription> SMALL_BACKPACK_HANDLER_TYPE;
	public static ScreenHandlerType<MediumBackpackGuiDescription> MED_BACKPACK_HANDLER_TYPE;
	public static ScreenHandlerType<LargeBackpackGuiDescription> LARGE_BACKPACK_HANDLER_TYPE;

	public static ScreenHandlerType<EnderBackpackGuiDescription> ENDER_BACKPACK_HANDLER_TYPE;

	@Override
	public void onInitialize() {
		// Register Items with Minecraft
		Registry.register(Registry.ITEM, new Identifier("yabm", "backpack"), BACKPACK);
		Registry.register(Registry.ITEM, new Identifier("yabm", "ender_backpack"), ENDER_BACKPACK);

		// Register GUI Controllers with LibGui
		SMALL_BACKPACK_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier("yabm", "small_backpack"),
				(syncId, inventory) -> new SmallBackpackGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));
		MED_BACKPACK_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier("yabm", "medium_backpack"),
				(syncId, inventory) -> new MediumBackpackGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));
		LARGE_BACKPACK_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier("yabm", "large_backpack"),
				(syncId, inventory) -> new LargeBackpackGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));

		ENDER_BACKPACK_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(new Identifier("yabm", "ender_backpack"),
				(syncId, inventory) -> new EnderBackpackGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));

		// Register Component with Cardinal Components
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(ENDER_BACKPACK_COMPONENT, new EnderBackpackComponent((player))));
		EntityComponents.setRespawnCopyStrategy(ENDER_BACKPACK_COMPONENT, new EnderCopyStrategy());
	}
}
