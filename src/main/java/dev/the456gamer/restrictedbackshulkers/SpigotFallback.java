package dev.the456gamer.restrictedbackshulkers;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotFallback extends JavaPlugin {

  @Override
  public void onEnable() {
    getLogger().severe("This plugin is not compatible with bukkit/spigot. Please use Paper instead.");
    setEnabled(false);
  }
}
