package com.danikvitek.davilib.menu;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Utility class for handling menus
 * @apiNote Do not forget to register events from {@link MenuHandler#getListeners()}
 */
public class MenuHandler {
    private final ConcurrentMap<UUID, Menu> openedMenus = new ConcurrentHashMap<>();

    /**
     * Opens the menu for the player
     *
     * @param player the player to open menu for
     * @param menu   the menu to open
     */
    public void openMenu(final @NotNull @NonNull Player player,
                         final @NotNull @NonNull Menu menu) {
        openedMenus.put(player.getUniqueId(), menu);
        menu.open(player);
    }

    /**
     * Close the currently opened menu of the specified player
     *
     * @param player the player to close menu for
     */
    public void closeMenu(final @NotNull @NonNull Player player) {
        this.closeMenu(player.getUniqueId());
    }

    /**
     * Close the currently opened menu of the specified player
     *
     * @param playerId the id of the player to close menu for
     */
    public void closeMenu(final @NotNull @NonNull UUID playerId) {
        if (hasMenuClosed(playerId)) return;

        openedMenus.remove(playerId);
        Player player = Bukkit.getPlayer(playerId);
        if (player == null) return;
        player.closeInventory();
    }

    /**
     * Checks if the player has menu closed
     *
     * @param playerId the id of the player to check
     * @return {@code true} if the player has no menu opened
     */
    public boolean hasMenuClosed(final @NotNull @NonNull UUID playerId) {
        return openedMenus.isEmpty() || !openedMenus.containsKey(playerId);
    }

    /**
     * Checks if the player has menu opened
     *
     * @param playerId the id of the player to check
     * @return {@code true} if the player has a menu opened
     */
    public boolean hasMenuOpened(final @NotNull @NonNull UUID playerId) {
        return !openedMenus.isEmpty() && openedMenus.containsKey(playerId);
    }

    /**
     * @param player the player to get a menu of
     * @return Maybe the opened menu of the player
     */
    public Optional<Menu> getMenu(final @NotNull @NonNull Player player) {
        return Optional.ofNullable(openedMenus.get(player.getUniqueId()));
    }

    /**
     * @param playerId the player to get a menu of
     * @return Maybe the opened menu of the player
     */
    public Optional<Menu> getMenu(final @NotNull @NonNull UUID playerId) {
        return Optional.ofNullable(openedMenus.get(playerId));
    }

    /**
     * Closes all the opened menus
     */
    public void closeAll() {
        openedMenus.keySet().forEach(this::closeMenu);
    }

    /**
     * @return Listeners for handling main menu events
     */
    public Listener getListeners() {
        return new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                if (e.getClickedInventory() == null) return;

                Player player = (Player) e.getWhoClicked();
                if (hasMenuClosed(player.getUniqueId())) return;

                Menu menu = openedMenus.get(player.getUniqueId());
                if (e.getClickedInventory().equals(e.getView().getTopInventory())) menu.performClick(e);
            }

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent e) {
                closeMenu(e.getPlayer().getUniqueId());
            }

            @EventHandler
            public void onPlayerLeave(PlayerQuitEvent e) {
                closeMenu(e.getPlayer().getUniqueId());
            }

            @EventHandler
            public void onPlayerDeath(PlayerDeathEvent e) {
                closeMenu(e.getEntity().getUniqueId());
            }
        };
    }
}