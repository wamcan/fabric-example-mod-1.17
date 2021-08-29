package net.fabricmc.wam;
/**
 * A simple {@code Inventory} implementation with only default methods + an item list getter.
 *
 * Originally by Juuz
 */
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface ImplementedInventory extends Inventory {
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     */
    DefaultedList<ItemStack> getItems();
    // Creation
    /**
     * Creates an inventory from the item list.
     */
    static ImplementedInventory of(DefaultedList<ItemStack> items) {
        return () -> items;
    }
    /**
     * Creates a new inventory with the size.
     */
    static ImplementedInventory ofSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }
    // Inventory
    /**
     * Returns the inventory size.
     */

    default int getInvSize() {
        return getItems().size();
    }
    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */

    default boolean isInvEmpty() {
        for (int i = 0; i < getInvSize(); i++) {
            ItemStack stack = getInvStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    /**
     * Gets the item in the slot.
     */

    default ItemStack getInvStack(int slot) {
        return getItems().get(slot);
    }
    /**
     * Takes a stack of the size from the slot.
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     */

    default ItemStack takeInvStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }
    /**
     * Removes the current stack in the {@code slot} and returns it.
     */

    default ItemStack removeInvStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }
    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     * <p>If the stack is too big for this inventory ({@link Inventory#getInvMaxStackAmount()}),
     * it gets resized to this inventory's maximum amount.
     */

    default void setInvStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getInvMaxStackAmount()) {
            stack.setCount(getInvMaxStackAmount());
        }
    }
    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    default void clear() {
        getItems().clear();
    }
    @Override
    default void markDirty() {
        // Override if you want behavior.
    }

    default boolean canPlayerUseInv(PlayerEntity player) {
        return true;
    }
}
