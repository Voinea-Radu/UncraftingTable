package me.lightdream.uncrafting_table.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class PowerGenerator extends Block {

    public PowerGenerator()
    {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(8f,8f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)
        );

        setRegistryName("power_generator");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PowerGeneratorTile();
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

}
