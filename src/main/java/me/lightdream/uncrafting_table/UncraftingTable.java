package me.lightdream.uncrafting_table;

import me.lightdream.uncrafting_table.blocks.ModBlocks;
import me.lightdream.uncrafting_table.blocks.PowerGenerator;
import me.lightdream.uncrafting_table.blocks.PowerGeneratorTile;
import me.lightdream.uncrafting_table.items.PortableUncraftingTable;
import me.lightdream.uncrafting_table.setup.ClientProxy;
import me.lightdream.uncrafting_table.setup.IProxy;
import me.lightdream.uncrafting_table.setup.ModSetup;
import me.lightdream.uncrafting_table.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("uncrafting_table")
public class UncraftingTable {

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static ModSetup setup = new ModSetup();

    private static final Logger LOGGER = LogManager.getLogger();

    public UncraftingTable() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlockRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new me.lightdream.uncrafting_table.blocks.UncraftingTable());
            event.getRegistry().register(new PowerGenerator());
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties()
                                            .group(setup.itemGroup);

            //Items
            event.getRegistry().register(new PortableUncraftingTable());

            //Block Items
            event.getRegistry().register(new BlockItem(ModBlocks.UNCRAFTING_TABLE, properties).setRegistryName("uncrafting_table"));
            event.getRegistry().register(new BlockItem(ModBlocks.POWER_GENERATOR, properties).setRegistryName("power_generator"));
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(PowerGeneratorTile::new, ModBlocks.POWER_GENERATOR).build(null).setRegistryName("power_generator"));
        }
    }

}
