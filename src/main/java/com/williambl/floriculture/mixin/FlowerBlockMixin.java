package com.williambl.floriculture.mixin;

import com.williambl.floriculture.Floriculture;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(FlowerBlock.class)
public class FlowerBlockMixin extends PlantBlock implements Fertilizable {

    public FlowerBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (
                Floriculture.FLOWER_SPREAD_ALLOWED.contains(world.getBlockState(pos.offset(Direction.DOWN)).getBlock())
                && !Floriculture.UNSPREADABLE_FLOWERS.contains(this)
                && world.getLightLevel(pos) >= 1
        ) {
            spread(state, world, pos, random);
        }
    }

    protected void spread(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int flowersAround = 5+random.nextInt(2);

        for (BlockPos blockPos : BlockPos.iterate(pos.add(-2, -1, -2), pos.add(2, 1, 2))) {
            if (world.getBlockState(blockPos).getBlock() == this) {
                --flowersAround;
                if (flowersAround <= 0) {
                    return;
                }
            }
        }

        BlockPos spreadPos = pos.add(random.nextInt(4) - 2, random.nextInt(2) - 1, random.nextInt(4) - 2);

        if (world.isAir(spreadPos) && state.canPlaceAt(world, spreadPos)) {
            world.setBlockState(spreadPos, state, 2);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return !(Floriculture.UNSPREADABLE_FLOWERS.contains(this) || Floriculture.FLOWER_SPREAD_DISALLOWED.contains(world.getBlockState(pos.down()).getBlock()));
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return random.nextBoolean();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        for (int i = 0; i < 5; i++) {
            spread(state, world, pos, random);
        }
    }
}
