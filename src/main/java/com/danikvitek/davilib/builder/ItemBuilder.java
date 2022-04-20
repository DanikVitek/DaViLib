package com.danikvitek.davilib.builder;

import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * The class for easy building of {@link ItemStack} and {@link ItemMeta}.
 */
public class ItemBuilder implements Builder<ItemStack> {
    private final ItemStack itemStack;
    @Getter
    private final ItemMeta itemMeta;

    /**
     * @param material the material of the builder you are building.
     * @param amount   the amount of items in the builder you are building.
     */
    public ItemBuilder(final @NotNull @NonNull Material material,
                       final int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * @param material the material of the builder you are building.
     */
    public ItemBuilder(final @NotNull Material material) {
        this(material, 1);
    }

    /**
     * @param baseItemStack the base builder stack the builder you are building.
     * @param amount        the amount of items in the builder you are building.
     */
    public ItemBuilder(final @NotNull @NonNull ItemStack baseItemStack,
                       final int amount) {
        this.itemStack = baseItemStack.clone();
        this.itemStack.setAmount(amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * @param baseItemStack the base builder stack the builder you are building.
     */
    public ItemBuilder(final @NotNull ItemStack baseItemStack) {
        this(baseItemStack, 1);
    }

    /**
     * Sets the display name of the builder, this name can be viewed by hovering over the
     * builder in your inventory or holding it in your hand.
     *
     * @param name the name to set for the builder. If {@code null} then the display name will be removed from ItemMeta
     * @return this ItemBuilder.
     */
    public ItemBuilder setDisplayName(final @Nullable String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * Sets the lore for this builder.
     *
     * @param lines the lines of the lore that will be set
     * @return this ItemBuilder
     */
    public ItemBuilder setLore(final String... lines) {
        this.itemMeta.setLore(List.of(lines));
        return this;
    }

    /**
     * Sets the lore for this builder.
     * Removes lore when given null.
     *
     * @param lore the lore that will be set
     * @return this ItemBuilder
     */
    public ItemBuilder setLore(final @Nullable List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Sets the custom model data.
     * <p>
     * CustomModelData is an integer that may be associated client side with a
     * custom builder model.
     *
     * @param customModelData the data to set, or null to clear
     * @return this ItemBuilder
     */
    public ItemBuilder setCustomModelData(final @Nullable Integer customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return this;
    }

    /**
     * Set all {@link Attribute}s and their {@link AttributeModifier}s.
     * To clear all currently set Attributes and AttributeModifiers use
     * null or an empty Multimap.
     * If not null nor empty, this will filter all entries that are not-null
     * and add them to the ItemStack.
     *
     * @param attributeModifiers the new Multimap containing the Attributes
     *                           and their AttributeModifiers
     * @return this ItemBuilder
     */
    public ItemBuilder setAttributesModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        this.itemMeta.setAttributeModifiers(attributeModifiers);
        return this;
    }

    /**
     * Sets the unbreakable tag. An unbreakable builder will not lose durability.
     *
     * @param unbreakable true if set unbreakable
     * @return this ItemBuilder
     */
    public ItemBuilder setUnbreakable(final boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the localized name.
     *
     * @param localizedName the name to set. If {@code null} then it will be removed from the ItemMeta
     * @return this ItemBuilder
     */
    public ItemBuilder setLocalizedName(final @Nullable String localizedName) {
        this.itemMeta.setLocalizedName(localizedName);
        return this;
    }

    /**
     * Tries to set the color of the armor.
     * If the ItemMeta is not an instance of LeatherArmorMeta then nothing will happen
     *
     * @param color the color to set. Setting it to null is equivalent to
     *     setting it to {@link ItemFactory#getDefaultLeatherColor()}.
     * @return this ItemBuilder
     */
    public ItemBuilder setColor(final @Nullable Color color) {
        if (this.itemMeta instanceof LeatherArmorMeta)
            ((LeatherArmorMeta) this.itemMeta).setColor(color);
        return this;
    }

    /**
     * Tries to set the owner of the skull.
     * If the ItemMeta is not an instance of SkullMeta then nothing will happen
     * <p>
     * Plugins should check that hasOwner() returns true before calling this
     * plugin.
     *
     * @param owner the new owner of the skull
     * @return this ItemBuilder
     */
    public ItemBuilder setOwningPlayer(final @Nullable OfflinePlayer owner) {
        if (this.itemMeta instanceof SkullMeta)
            ((SkullMeta) this.itemMeta).setOwningPlayer(owner);
        return this;
    }

    /**
     * @return the ItemStack that has been created.
     */
    public @NotNull ItemStack build() {
        this.updateItemMeta();
        return this.itemStack;
    }

    /**
     * Sets the resulting itemMeta into the itemStack
     */
    private void updateItemMeta() {
        this.itemStack.setItemMeta(this.itemMeta);
    }
}
