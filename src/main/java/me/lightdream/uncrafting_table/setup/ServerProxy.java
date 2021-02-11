package me.lightdream.uncrafting_table.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy{

    @Override
    public void init() {

    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("You can only run this on clients!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("You can only run this on clients!");
    }

}
