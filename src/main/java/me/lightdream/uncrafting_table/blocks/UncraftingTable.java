package me.lightdream.uncrafting_table.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class UncraftingTable extends Block {

    public UncraftingTable()
    {
        super(Properties.create(Material.WOOD)
                .hardnessAndResistance(2f,2f)
                .sound(SoundType.WOOD)
                .harvestTool(ToolType.AXE)
                .harvestLevel(1)
        );

        setRegistryName("uncrafting_table");
    }

}
