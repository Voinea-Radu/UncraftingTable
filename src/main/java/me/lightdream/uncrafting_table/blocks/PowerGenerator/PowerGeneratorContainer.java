package me.lightdream.uncrafting_table.blocks.PowerGenerator;

import me.lightdream.uncrafting_table.blocks.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

import java.util.Objects;

import static me.lightdream.uncrafting_table.blocks.ModBlocks.POWER_GENERATOR_CONTAINER;

public class PowerGeneratorContainer extends Container {

    private final TileEntity tileEntity;
    private final PlayerEntity playerEntity;
    private final IItemHandler playerInventory;

    public PowerGeneratorContainer(int id, World world, BlockPos pos, PlayerEntity playerEntity) {
        super(POWER_GENERATOR_CONTAINER, id);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = playerEntity;
        this.playerInventory = new InvWrapper(playerEntity.inventory);

        assert tileEntity != null;
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 82, 24)));

        layoutPlayerInventorySlots(10,70);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos()), playerEntity, ModBlocks.POWER_GENERATOR);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx){
        for(int i=0;i<amount;i++){
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy){
        for(int j=0;j<verAmount;j++){
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        //return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow){
        addSlotBox(playerInventory,9,leftCol,topRow,9,18,3,18);
        topRow += 58;
        addSlotRange(playerInventory,0,leftCol,topRow,9,18);
    }

}
