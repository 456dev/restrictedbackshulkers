package dev.the456gamer.restrictedbackshulkers;

import static dev.the456gamer.restrictedbackshulkers.PDCUtil.removeCC;
import static dev.the456gamer.restrictedbackshulkers.PDCUtil.setCC;
import static net.kyori.adventure.text.Component.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RBSCommand implements TabExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender,
      org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (sender instanceof Player player) {
      Double customCost;
      switch (args.length) {
        case 0 -> {
          // read
          if (player.hasPermission("restrictedbackshulkers.command.view")) {
            customCost = PDCUtil.getCC(player.getInventory().getItemInMainHand());
            player.sendMessage(text((customCost != null ? customCost.toString() : "Default")));
            return true;
          } else {
            player.sendMessage(text("NO Perm for this", NamedTextColor.RED));
            return true;
          }

        }
        case 1 -> {
          // write
          if (player.hasPermission("restrictedbackshulkers.command.set")) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
              case "clear", "reset", "default" -> {
                removeCC(player.getInventory().getItemInMainHand());
                player.sendMessage(text("tried resetting it to default"));
              }
              default -> {
                try {
                  customCost = Double.parseDouble(args[0]);
                  player.sendMessage(text(
                      setCC(player.getInventory().getItemInMainHand(), customCost) ? "Success ("
                          + customCost + ")" : "Fail"));
                } catch (NumberFormatException numberFormatException) {
                  player.sendMessage(text("Unknown Argument"));
                  return false;
                }
              }
            }

            return true;
          } else {
            player.sendMessage(text("NO Perm for this", NamedTextColor.RED));
            return true;
          }
        }
        default -> {
          player.sendMessage(text("Wrong number of args"));
          return false;
        }
      }
    } else {
      sender.sendMessage(text("Player Only"));
      return false;
    }
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender,
      org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
    return new ArrayList<>();
  }
}
