package net.william278.husksync.util;

import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.BundleMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class InventoryModifier {
    public static ItemStack[] modifyInventory(ItemStack[] items, @NotNull Function<@Nullable ItemStack, Boolean> check, @NotNull Function<ItemStack, @Nullable ItemStack> function) {
        for (int i = 0; i < items.length; i++) {
            final ItemStack item = items[i];
            if (item != null) {
                if (item.getItemMeta() instanceof BlockStateMeta b && b.getBlockState() instanceof Container box) {
                    modifyInventory(box.getInventory().getContents(), check, function);
                    b.setBlockState(box);
                    item.setItemMeta(b);
                } else if (item.getItemMeta() instanceof BundleMeta bundle) {
                    bundle.setItems(Stream.of(
                            modifyInventory(bundle.getItems().toArray(ItemStack[]::new), check, function)
                    ).filter(Objects::nonNull).toList());
                    item.setItemMeta(bundle);
                }
            }
            if (check.apply(item)) {
                items[i] = function.apply(item);
            }
        }
        return items;
    }
}
