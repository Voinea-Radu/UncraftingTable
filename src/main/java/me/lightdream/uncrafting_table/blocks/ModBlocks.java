package me.lightdream.uncrafting_table.blocks;

import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGenerator;
import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGeneratorContainer;
import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGeneratorTile;
import me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTable;
import me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTableContainer;
import me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTableTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    //Uncrafting Table
    @ObjectHolder("uncrafting_table:uncrafting_table")
    public static UncraftingTable UNCRAFTING_TABLE;

    @ObjectHolder("uncrafting_table:uncrafting_table")
    public static TileEntityType<UncraftingTableTile> UNCRAFTING_TABLE_TILE;

    @ObjectHolder("uncrafting_table:uncrafting_table")
    public static ContainerType<UncraftingTableContainer> UNCRAFTING_TABLE_CONTAINER;

    //Power Generator
    @ObjectHolder("uncrafting_table:power_generator")
    public static PowerGenerator POWER_GENERATOR;

    @ObjectHolder("uncrafting_table:power_generator")
    public static TileEntityType<PowerGeneratorTile> POWER_GENERATOR_TILE;

    @ObjectHolder("uncrafting_table:power_generator")
    public static ContainerType<PowerGeneratorContainer> POWER_GENERATOR_CONTAINER;
}
