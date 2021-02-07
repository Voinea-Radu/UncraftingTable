package me.lightdream.uncrafting_table.setup;

import me.lightdream.uncrafting_table.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("uncrafting_table") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.UNCRAFTING_TABLE);
        }
    };

    public static void init(){

    }

}
