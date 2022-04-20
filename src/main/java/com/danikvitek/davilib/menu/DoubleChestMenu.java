package com.danikvitek.davilib.menu;

import org.jetbrains.annotations.Nullable;

/**
 * Abstract class for chest-based menus
 *
 * @param <M> type of the extending class
 */
public abstract class DoubleChestMenu<M extends DoubleChestMenu<M>> extends ChestMenu<M> {
    /**
     * @param title title of the menu. If {@code null} then no title will be set
     */
    protected DoubleChestMenu(final @Nullable String title) {
        super(title, 6);
    }
}
