package org.ReverseHoney.kingTests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KingTrading implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player&&player.hasPermission("kingplayer")){
            Inventory inv = Bukkit.createInventory(player, 9, ChatColor.GRAY + "KingShop!");

            ItemStack manageShop = new ItemStack(Material.CHEST);
            ItemMeta manageMeta = manageShop.getItemMeta();
            manageMeta.setDisplayName("§6Manage your shop!");
            manageShop.setItemMeta(manageMeta);
            inv.setItem(4, manageShop);

            ItemStack shopCreation = new ItemStack(Material.GREEN_WOOL);
            ItemMeta itemMeta1 = shopCreation.getItemMeta();
            itemMeta1.setDisplayName("§aCreate a shop!");
            shopCreation.setItemMeta(itemMeta1);
            inv.setItem(2,shopCreation);

            ItemStack shopDeletion = new ItemStack(Material.RED_WOOL);
            ItemMeta itemMeta2 = shopDeletion.getItemMeta();
            itemMeta2.setDisplayName("§cDelete a shop!");
            shopDeletion.setItemMeta(itemMeta2);
            inv.setItem(6, shopDeletion);

            player.openInventory(inv);
        }
        return false;
    }
}
