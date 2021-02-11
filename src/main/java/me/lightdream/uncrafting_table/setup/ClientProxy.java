package me.lightdream.uncrafting_table.setup;

import me.lightdream.uncrafting_table.blocks.ModBlocks;
import me.lightdream.uncrafting_table.blocks.PowerGeneratorScreen;
import me.lightdream.uncrafting_table.blocks.UncraftingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.POWER_GENERATOR_CONTAINER, PowerGeneratorScreen::new);
        ScreenManager.registerFactory(ModBlocks.UNCRAFTING_TABLE_CONTAINER, UncraftingTableScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }

}
