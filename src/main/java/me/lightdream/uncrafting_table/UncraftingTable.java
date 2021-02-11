package me.lightdream.uncrafting_table;

import me.lightdream.uncrafting_table.blocks.*;
import me.lightdream.uncrafting_table.items.PortableUncraftingTable;
import me.lightdream.uncrafting_table.setup.ClientProxy;
import me.lightdream.uncrafting_table.setup.IProxy;
import me.lightdream.uncrafting_table.setup.ModSetup;
import me.lightdream.uncrafting_table.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
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

    public static final String MOD_ID = "uncrafting_table";

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
            event.getRegistry().register(TileEntityType.Builder.create(UncraftingTableTile::new, ModBlocks.UNCRAFTING_TABLE).build(null).setRegistryName("uncrafting_table"));
            //event.getRegistry().register(TileEntityType.Builder.create(UncraftingTableContainer::new, ModBlocks.UNCRAFTING_TABLE_TILE).build(null).setRegistryName("uncrafting_table"));
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new PowerGeneratorContainer(windowId, UncraftingTable.proxy.getClientWorld(), pos, inv, UncraftingTable.proxy.getClientPlayer());
            })).setRegistryName("power_generator"));
            event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new UncraftingTableContainer(windowId, UncraftingTable.proxy.getClientWorld(), pos, inv, UncraftingTable.proxy.getClientPlayer());
            })).setRegistryName("uncrafting_table"));
        }
    }

}
