package me.plugin.customchat;

import org.bukkit.plugin.java.JavaPlugin;

public class CustomChat extends JavaPlugin {

    @Override
    public void onEnable() {
        new Ping(this);
        getServer().getPluginManager().registerEvents(new QueueExit(), this);

        getLogger().info("Плагин CustomChat включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин CustomChat отключен.");
    }
}
