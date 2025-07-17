package me.plugin.customchat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ping implements Listener {
    private final JavaPlugin plugin;

    public Ping(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (message == null || message.trim().isEmpty()) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            String regex = "\\b" + Pattern.quote(playerName) + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                String highlightedMessage = matcher.replaceAll(ChatColor.RED + playerName + ChatColor.RESET);
                event.setMessage(highlightedMessage);
            }
        }
    }
}
