package me.lightdream.uncrafting_table.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("uncrafting_table:uncrafting_table")
    public static UncraftingTable UNCRAFTING_TABLE;

    @ObjectHolder("uncrafting_table:power_generator")
    public static PowerGenerator POWER_GENERATOR;

    @ObjectHolder("uncrafting_table:power_generator")
    public static TileEntityType<PowerGeneratorTile> POWER_GENERATOR_TILE;
}
