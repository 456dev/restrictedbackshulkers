package dev.the456gamer.restrictedbackshulkers;

import static dev.the456gamer.restrictedbackshulkers.PDCUtil.getCC;
import static dev.the456gamer.restrictedbackshulkers.PDCUtil.setCC;

import com.Zrips.CMI.events.CMIBackpackOpenEvent;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class EventListener implements Listener {

  // if 0, free open. if <0 prevent open, if >0, custom cost


  Logger logger = RestrictedBackShulkers.getPlugin(RestrictedBackShulkers.class).getLogger();

  @EventHandler
  public void onBackpackOpen(CMIBackpackOpenEvent backpackEvent) {
    Double customCost = getCC(backpackEvent.getShulkerBox());

    if (customCost == null) {
      return;
    }
    if (customCost < 0) {
      if (!backpackEvent.getPlayer().hasPermission("restrictedbackshulkers.bypassnoopen")) {
        backpackEvent.setCancelled(true);
        logger.info("prevented backpack open for %s due to itemmeta".formatted(backpackEvent.getPlayer().getName()));
      }
    } else {
      backpackEvent.setCostToOpen(customCost);
      logger.info("applied custom backpack cost for %s due to itemmeta (%f)".formatted(backpackEvent.getPlayer().getName(), customCost));
    }
  }


  @EventHandler
  public void playerBlockDestroyEvent(BlockDropItemEvent breakBlockEvent) {
    Double customCost = PDCUtil.getCC(breakBlockEvent.getBlockState());
    breakBlockEvent.getItems().forEach(item -> setCC(item.getItemStack(), customCost));
  }

  @EventHandler
  public void blockBlockDestroyEvent(BlockBreakBlockEvent breakBlockEvent) {
    Double customCost = PDCUtil.getCC(breakBlockEvent.getBlock().getState());
    breakBlockEvent.getDrops().forEach(itemStack -> setCC(itemStack, customCost));
  }

}
