package aldinh777.genshin_flora.flowers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class AquaticFlower extends DoubleFlower {

    public AquaticFlower(String name) {
        super(name, Material.LEAVES);
    }

    @Nonnull
    @Override
    public EnumOffsetType getOffsetType() {
        return EnumOffsetType.NONE;
    }

    @Nonnull
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean canPlaceBlockAt(@Nonnull World worldIn, @Nonnull BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos.down());
        return super.canPlaceBlockAt(worldIn, pos) && this.canSustainBush(state) && this.surroundedByWater(worldIn, pos);
    }

    @Override
    protected void checkAndDropBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            boolean flag = state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = flag ? this : worldIn.getBlockState(blockpos).getBlock();
            Block block1 = flag ? worldIn.getBlockState(blockpos1).getBlock() : this;

            // Forge move above the setting to air.
            if (!flag) {
                this.dropBlockAsItem(worldIn, pos, state, 0);
            }

            if (block == this) {
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }

            if (block1 == this) {
                worldIn.setBlockState(blockpos1, Blocks.WATER.getDefaultState(), 3);
            }
        }
    }

    @Override
    protected boolean canSustainBush(@Nonnull IBlockState state) {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT;
    }

    @Override
    public boolean canBlockStay(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state) {
        boolean flag = state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER;
        return super.canBlockStay(worldIn, pos, state) && (flag || this.surroundedByWater(worldIn, pos));
    }

    @Override
    public void onBlockHarvested(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state, @Nonnull EntityPlayer player) {
        if (state.getValue(HALF) == BlockDoublePlant.EnumBlockHalf.UPPER) {
            if (worldIn.getBlockState(pos.down()).getBlock() == this) {
                if (player.capabilities.isCreativeMode) {
                    worldIn.setBlockState(pos.down(), Blocks.WATER.getDefaultState());
                } else {
                    destroyBlock(worldIn, pos.down());
                }
            }
        } else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    private void destroyBlock(World world, BlockPos pos) {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isAir(iblockstate, world, pos)) {
            world.playEvent(2001, pos, Block.getStateId(iblockstate));

            block.dropBlockAsItem(world, pos, iblockstate, 0);

            world.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
        }
    }

    private boolean surroundedByWater(World world, BlockPos pos) {
        IBlockState north = world.getBlockState(pos.north());
        IBlockState south = world.getBlockState(pos.south());
        IBlockState east = world.getBlockState(pos.east());
        IBlockState west = world.getBlockState(pos.west());

        return isWaterOrThis(north) && isWaterOrThis(south) && isWaterOrThis(east) && isWaterOrThis(west);
    }

    private boolean isWaterOrThis(IBlockState state) {
        return state.getBlock() == Blocks.WATER || state.getBlock() == this;
    }
}
