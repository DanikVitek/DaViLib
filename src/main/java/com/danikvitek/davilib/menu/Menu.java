package com.danikvitek.davilib.menu;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Base menu class for simple menu creation
 *
 * @apiNote Handle menu using {@link MenuHandler}
 * @see MenuHandler
 * @see GridMenu
 */
public class Menu {
    @Getter
    private Inventory inventory;
    private Map<Integer, Button> buttons;

    /**
     * @param inventory base inventory to create menu with.
     * @apiNote Better use empty menu as it will later be filled with buttons
     */
    @Contract(pure = true)
    public Menu(final @NotNull @NonNull Inventory inventory) {
        this.inventory = inventory;
        this.buttons = new HashMap<>();
    }

    /**
     * Sets the button at the specific slot
     *
     * @param slot   the slot to set button in. Typically, counted from top left corner to bottom right one
     * @param button the button to set in the slot
     * @return this Menu. Fluent API
     */
    public Menu setButton(final int slot, final @Nullable Button button) {
        if (button == null) return this.removeButton(slot);
        this.buttons.put(slot, button);
        return this;
    }

    /**
     * Removes the button from the specified slot
     *
     * @param slot the slot to remove button from. Typically, counted from top left corner to bottom right one
     * @return this Menu. Fluent API
     */
    public Menu removeButton(final int slot) {
        this.buttons.remove(slot);
        this.inventory.setItem(slot, null);
        return this;
    }

    void performClick(final @NotNull @NonNull InventoryClickEvent event) {
        if (this.buttons.get(event.getSlot()) != null) this.buttons.get(event.getSlot()).onClick(this, event);
    }

    private void loadButtons() {
        buttons.forEach(inventory::setItem);
    }

    void open(final @NotNull @NonNull Player player) {
        loadButtons();
        player.openInventory(inventory);
    }
}
