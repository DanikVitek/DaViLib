package com.danikvitek.davilib.menu;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

/**
 * Abstract class for chest-based menus
 *
 * @param <M> type of the extending class
 * @see SingleChestMenu
 * @see DoubleChestMenu
 */
public abstract class ChestMenu<M extends ChestMenu<M>> extends Menu implements GridMenu<M> {
    private final int rowsCount;

    /**
     * @param title the title of the menu. If {@code null} then no title will be sed
     * @param rowsCount amount of rows in the menu
     */
    protected ChestMenu(final @Nullable String title, final int rowsCount) {
        super(title != null
                ? Bukkit.createInventory(null, getSlotCount(rowsCount), title)
                : Bukkit.createInventory(null, getSlotCount(rowsCount))
        );
        this.rowsCount = rowsCount;
    }

    /**
     * Sets the button using grid coordinates
     *
     * @param slotX horizontal coordinate of the slot (from left to right)
     * @param slotY vertical coordinate of the slot (from top to bottom)
     * @param button the button to set
     * @return this menu. Fluid API
     */
    @SuppressWarnings("unchecked")
    @Override
    public M setButton(int slotX, int slotY, @Nullable Button button) {
        if (slotY < 0 || slotY >= this.rowsCount || slotX < 0 || slotX >= 9)
            throw new IllegalArgumentException(String.format("Illegal coordinates (%d, %d)", slotX, slotY));
        return (M) this.setButton(slotY * 9 + slotX, button);
    }

    private static int getSlotCount(int rowsCount) {
        if (1 <= rowsCount && rowsCount <= 6) return rowsCount * 9;
        else throw new IllegalArgumentException("Rows count must be from 1 to 6");
    }
}
