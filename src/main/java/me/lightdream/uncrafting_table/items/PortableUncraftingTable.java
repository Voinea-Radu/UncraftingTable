package me.lightdream.uncrafting_table.items;

import me.lightdream.uncrafting_table.UncraftingTable;
import net.minecraft.item.Item;

public class PortableUncraftingTable extends Item {

    public PortableUncraftingTable(){
        super(new Properties()
                .group(UncraftingTable.setup.itemGroup)
                .maxStackSize(1));
        setRegistryName("portable_uncrafting_table");
    }

}
