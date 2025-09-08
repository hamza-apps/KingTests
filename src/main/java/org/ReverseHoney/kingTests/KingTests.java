package org.ReverseHoney.kingTests;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class KingTests extends JavaPlugin implements Listener {
    public static Plugin plugin;
    public static String KingPrefix = "§8[§cKingTrading§8] ";
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        for (Player player:Bukkit.getOnlinePlayers()){
            player.setGameMode(GameMode.CREATIVE);
        }
        plugin=this;
        config = plugin.getConfig();
        Bukkit.getPluginCommand("KingShop").setExecutor(new KingTrading());
        Bukkit.getPluginManager().registerEvents(new createShop(), this);
    }

    @Override
    public void onDisable(){
    }
}
