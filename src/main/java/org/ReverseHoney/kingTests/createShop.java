package org.ReverseHoney.kingTests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static org.ReverseHoney.kingTests.KingTests.*;

public class createShop implements Listener {
    public static final String shopDeletion = ChatColor.RED + "Delete a shop!";
    public static final List<UUID> shops = Collections.synchronizedList(new ArrayList<>());

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        int slot = event.getRawSlot();
        boolean hasShop = config.getBoolean("shops." + player.getUniqueId() + ".hasShop", false);

        if (event.getClickedInventory() != event.getView().getTopInventory()) return;
        event.setCancelled(true);
        if (title.equals(ChatColor.GRAY + "KingShop!")){
            if (slot == 2&& !hasShop){
                Inventory inv1 = Bukkit.createInventory(player, 54, ChatColor.GRAY + "Create a shop!");
                player.openInventory(inv1);
                event.setCancelled(true);
                ItemStack createShop = new ItemStack(Material.GOLD_BLOCK);
                ItemMeta csmeta = createShop.getItemMeta();
                csmeta.setDisplayName(ChatColor.YELLOW + "Create a shop!");
                createShop.setItemMeta(csmeta);
                inv1.setItem(22, createShop);

            } else if (slot == 6) {
                Inventory inv2 = Bukkit.createInventory(player, 54, shopDeletion);
                if (!hasShop){
                    player.closeInventory();
                    player.sendMessage(KingPrefix + ChatColor.RED + "You don't own a shop!");
                }else if (hasShop){
                    player.openInventory(inv2);
                    ItemStack shop = new ItemStack(Material.GOLD_BLOCK);
                    ItemMeta smeta = shop.getItemMeta();
                    smeta.setDisplayName(ChatColor.GRAY + player.getName() + "'s Shop!");
                    shop.setItemMeta(smeta);
                    inv2.setItem(22, shop);
                    if (slot == 22){
                        player.closeInventory();
                        player.sendMessage(KingPrefix + "§cYou deleted your shop!");
                        hasShop = false;
                    }
                }

            }else if (slot == 4){
                Inventory inv3 = Bukkit.createInventory(player,54, ChatColor.GRAY + "Manage your shop!");
                if (!hasShop){
                    player.closeInventory();
                    player.sendMessage(KingPrefix + ChatColor.RED + "You don't own a shop!");
                }else if (hasShop){
                    player.openInventory(inv3);
                    ItemStack shop = new ItemStack(Material.GOLD_BLOCK);
                    ItemMeta smeta = shop.getItemMeta();
                    smeta.setDisplayName(ChatColor.GRAY + player.getName() + "'s Shop!");
                    shop.setItemMeta(smeta);
                    inv3.setItem(22, shop);
                }
            }else if (slot == 2&&hasShop){
                player.closeInventory();
                player.sendMessage(KingPrefix + "§cYou already have a shop!");
            }
        }
        if (title.equals(ChatColor.GRAY + "Create a shop!")){
            if (slot == 22&&!hasShop){
                createShop(player);
                player.closeInventory();
                player.sendMessage(KingPrefix + "§aYou created a shop!");
                hasShop = true;
            }else{
                return;
            }
        }
        if (title.equals(ChatColor.GRAY + "Manage your shop!")){
            if (slot == 22){
                player.closeInventory();
                player.sendMessage(KingPrefix + "§cThis feature is currently worked on!");
            }
        }
        if (title.equals(shopDeletion)){
            if (slot == 22&&hasShop){
                player.closeInventory();
                player.sendMessage(KingPrefix + "§cYou deleted your shop!");
                hasShop = false;
            }
        }
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        String title = event.getView().getTitle();
        if (title.equals(shopDeletion)) {
            event.setCancelled(true);
        }
    }
    private void createShop(Player player) {
        config.set("shops." + player.getUniqueId() + ".hasShop", true);
        plugin.saveConfig();
    }
}
