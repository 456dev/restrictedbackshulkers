package dev.the456gamer.restrictedbackshulkers;

import java.util.concurrent.atomic.AtomicReference;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PDCUtil {

  static final NamespacedKey PDC_KEY = new NamespacedKey("456restrictedbackshulkers",
      "custom_open_cost");


  public static void removeCC(BlockState blockState) {
    if (blockState instanceof ShulkerBox shulkerBox) {
      shulkerBox.getPersistentDataContainer().remove(PDC_KEY);
    }
  }


  public static Double getCC(BlockState blockState) {
    if (blockState instanceof ShulkerBox shulkerBox) {
      return shulkerBox.getPersistentDataContainer()
          .get(PDC_KEY, PersistentDataType.DOUBLE);
    }
    return null;
  }

  public static boolean setCC(BlockState blockState, Double customCost) {
    if (customCost == null) {
      return false;
    }

    if (blockState instanceof ShulkerBox shulkerBox) {
      shulkerBox.getPersistentDataContainer().set(PDC_KEY, PersistentDataType.DOUBLE, customCost);
      return true;
    }
    return false;
  }


  public static void removeCC(ItemStack itemStack) {
    itemStack.editMeta(BlockStateMeta.class, blockStateMeta -> {
      final BlockState blockState = blockStateMeta.getBlockState();
      removeCC(blockState);
      blockStateMeta.setBlockState(blockState);
    });
  }

  public static Double getCC(ItemStack itemStack) {
    ItemMeta meta = itemStack.getItemMeta();
    if (meta instanceof BlockStateMeta blockStateMeta) {
      return getCC(blockStateMeta.getBlockState());
    }
    return null;
  }

  public static boolean setCC(ItemStack itemStack, Double customCost) {
    AtomicReference<Boolean> success = new AtomicReference<>(false);
    itemStack.editMeta(BlockStateMeta.class, blockStateMeta -> {
      final BlockState blockState = blockStateMeta.getBlockState();
      if (setCC(blockState, customCost)) {
        blockStateMeta.setBlockState(blockState);
        success.set(true);
      }
    });
    return success.get();
  }


}
