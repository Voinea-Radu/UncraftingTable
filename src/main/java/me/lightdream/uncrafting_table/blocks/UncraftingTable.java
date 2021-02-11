package me.lightdream.uncrafting_table.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

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
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new UncraftingTableTile();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack){
        if(entity!=null)
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos,entity)));
    }

    public static Direction getFacingFromEntity(BlockPos pos, LivingEntity entity){
        return Direction.getFacingFromVector((float) (entity.getPosX() - pos.getX()) , (float) (entity.getPosY() - pos.getY()) , (float) (entity.getPosZ() - pos.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof INamedContainerProvider)
            {
                System.out.println("Open the GUI for Uncrafting Table");
                NetworkHooks.openGui((ServerPlayerEntity) player,(INamedContainerProvider) tileEntity, tileEntity.getPos());
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

    }

}
