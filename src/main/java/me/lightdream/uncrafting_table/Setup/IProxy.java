package me.lightdream.uncrafting_table.Setup;

import net.minecraft.world.World;

public interface IProxy
{
    void init();

    World getClientWorld();
}
