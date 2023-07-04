package dev.the456gamer.restrictedbackshulkers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.PluginCommand;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class RestrictedBackShulkers extends JavaPlugin {

  @Override
  public void onEnable() {

    Constructor<PluginCommand> constructor;
    PluginCommand rbsCommand;
    try {
      constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
      constructor.setAccessible(true);
      rbsCommand = constructor.newInstance("restrictedbackshulkers", this);
      RBSCommand tabExecutor = new RBSCommand();
      rbsCommand.setExecutor(tabExecutor);
      rbsCommand.setTabCompleter(tabExecutor);
      rbsCommand.setUsage("/<command> <cost>|default");
      rbsCommand.setDescription("set cost to negative to disable using as a backpack");
      rbsCommand.setPermission("restrictedbackshulkers.command.execute");
    } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
             IllegalAccessException e) {
      throw new RuntimeException(e);
    }
    PluginManager pluginManager = getServer().getPluginManager();

    pluginManager.registerEvents(new EventListener(), this);
    getServer().getCommandMap().register("restrictedbackshulkers", rbsCommand);

    pluginManager.addPermission(new Permission("restrictedbackshulkers.command.view", "View current value shulker custom cost with command.", PermissionDefault.FALSE));
    pluginManager.addPermission(new Permission("restrictedbackshulkers.command.execute", "access to tabcomplete and use command.", PermissionDefault.FALSE));
    pluginManager.addPermission(new Permission("restrictedbackshulkers.command.set", "set custom cost with command.", PermissionDefault.FALSE));
    Map<String,Boolean> childMap = new HashMap<String, Boolean>();
    childMap.put("restrictedbackshulkers.command.view", true);
        childMap.put("restrictedbackshulkers.command.execute", true);
            childMap.put("restrictedbackshulkers.command.set", true);
    pluginManager.addPermission(new Permission("restrictedbackshulkers.command", "full access to command", PermissionDefault.OP, childMap));
    pluginManager.addPermission(new Permission("restrictedbackshulkers.bypassnoopen", "allow opening shulkerboxes as backpacks, even if they normally prevent that", PermissionDefault.OP));
    
  }

}



