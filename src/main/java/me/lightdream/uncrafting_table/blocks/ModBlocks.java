package me.lightdream.uncrafting_table.blocks;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("uncrafting_table:uncrafting_table")
    public static UncraftingTable UNCRAFTING_TABLE;

    @ObjectHolder("uncrafting_table:power_generator")
    public static TileEntityType<UncraftingTableTile> UNCRAFTING_TABLE_TILE;

    @ObjectHolder("uncrafting_table:power_generator")
    public static ContainerType<UncraftingTableContainer> UNCRAFTING_TABLE_CONTAINER;

    @ObjectHolder("uncrafting_table:power_generator")
    public static PowerGenerator POWER_GENERATOR;

    @ObjectHolder("uncrafting_table:power_generator")
    public static TileEntityType<PowerGeneratorTile> POWER_GENERATOR_TILE;

    @ObjectHolder("uncrafting_table:power_generator")
    public static ContainerType<PowerGeneratorContainer> POWER_GENERATOR_CONTAINER;
}
