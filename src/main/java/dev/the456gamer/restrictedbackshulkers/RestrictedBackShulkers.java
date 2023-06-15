package dev.the456gamer.restrictedbackshulkers;

import java.io.IOException;
import java.nio.file.Path;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

public final class RestrictedBackShulkers extends JavaPlugin {

  CommentedConfigurationNode root;

  HoconConfigurationLoader configLoader;



  @Override
  public void onEnable() {
    // start cmi hook
    //register event listners, command listners

    // Plugin startup logic

    configLoader = HoconConfigurationLoader.builder()
        .path(Path.of("config.conf")) // Set where we will load and save to
        .build();

    try {
      root = configLoader.load();
    } catch (ConfigurateException e) {
      getLogger().severe("An error occurred while loading this configuration: " + e.getMessage());
      if (e.getCause() != null) {
        e.getCause().printStackTrace();
      }
      setEnabled(false);
      return;
    }


  }

  @Override
  public void onDisable() {

    root = null;
    // Plugin shutdown logic
  }


  // save config
  // reload from file

  // upgrade / handle version changes
}
