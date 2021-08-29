package net.fabricmc.wam;

import net.minecraft.block.BlockState;
import net.minecraft.block.CommandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PureBlockEntity extends BlockEntity implements ImplementedInventory{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void fromTag(NbtCompound tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag,items);
    }

    @Override
    public NbtCompound toTag(NbtCompound tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

    public PureBlockEntity(BlockPos pos, BlockState state){
        super(DemoMod.PURE_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PureBlockEntity be) {
    }

}
