package me.lightdream.uncrafting_table.Setup;

import net.minecraft.world.World;

public class ServerProxy implements IProxy{

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("You can only run this on clients!");
    }

}
