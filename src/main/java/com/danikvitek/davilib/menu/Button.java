package com.danikvitek.davilib.menu;

import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * The class to implement menu buttons.
 * It is abstract to easily implement functionality at the instantiation
 * @see Menu
 */
public abstract class Button extends ItemStack {
    /**
     * @param material button material
     */
    protected Button(final @NotNull @NonNull Material material) {
        super(material);
    }

    /**
     * @param material button material
     * @param amount amount in slot
     */
    protected Button(final @NotNull @NonNull Material material, final int amount) {
        super(material, amount);
    }

    /**
     * @param itemStack button item stack
     */
    protected Button(final @NotNull @NonNull ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * The method to call on button click
     *
     * @param menu the menu of the button
     * @param event button click event
     */
    public abstract void onClick(Menu menu, InventoryClickEvent event);
}
