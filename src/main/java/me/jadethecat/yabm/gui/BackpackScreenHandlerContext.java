package me.jadethecat.yabm.gui;

import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.BiFunction;

public class BackpackScreenHandlerContext implements ScreenHandlerContext {
    @Override
    public <T> Optional<T> run(BiFunction<World, BlockPos, T> function) {
        return Optional.empty();
    }
}
