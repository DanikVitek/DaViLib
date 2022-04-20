package com.danikvitek.davilib.menu;

import org.jetbrains.annotations.Nullable;

/**
 * Interface for creating gird-based menus
 *
 * @param <M> Type of the extending class
 * @see ChestMenu
 */
public interface GridMenu<M extends Menu & GridMenu<M>> {
    /**
     * Sets the button using grid coordinates
     *
     * @param slotX horizontal coordinate of the slot (from left to right)
     * @param slotY vertical coordinate of the slot (from top to bottom)
     * @param button the button to set
     * @return this menu. Fluid API
     */
    M setButton(final int slotX, final int slotY, final @Nullable Button button);
}
