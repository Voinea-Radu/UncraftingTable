package me.lightdream.uncrafting_table;

import me.lightdream.uncrafting_table.blocks.ModBlocks;
import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGenerator;
import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGeneratorContainer;
import me.lightdream.uncrafting_table.blocks.PowerGenerator.PowerGeneratorTile;
import me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTableContainer;
import me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTableTile;
import me.lightdream.uncrafting_table.items.PortableUncraftingTable;
import me.lightdream.uncrafting_table.setup.ClientProxy;
import me.lightdream.uncrafting_table.setup.IProxy;
import me.lightdream.uncrafting_table.setup.ModSetup;
import me.lightdream.uncrafting_table.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

@Mod("uncrafting_table")
public class UncraftingTable {

    public static final String MOD_ID = "uncrafting_table";

    //TODO: Replace the deprecated use
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static ModSetup setup = new ModSetup();

    public UncraftingTable() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModSetup.init();
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlockRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new me.lightdream.uncrafting_table.blocks.UncraftingTable.UncraftingTable());
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
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new PowerGeneratorContainer(windowId, UncraftingTable.proxy.getClientWorld(), pos, UncraftingTable.proxy.getClientPlayer());
            })).setRegistryName("power_generator"));
            event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new UncraftingTableContainer(windowId, UncraftingTable.proxy.getClientWorld(), pos, UncraftingTable.proxy.getClientPlayer());
            })).setRegistryName("uncrafting_table"));
        }
    }

}
