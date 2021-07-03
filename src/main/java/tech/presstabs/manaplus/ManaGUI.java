package tech.presstabs.manaplus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class ManaGUI implements Listener {

    public static Inventory inv;

    //Templates
    private static final ItemStack defaultFiller = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
    private static final ItemStack defaultGreen = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    private static final ItemStack defaultRed = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);

    private static final ItemStack increaseMana = defaultGreen.clone();
    private static final ItemStack increaseMaxMana = defaultGreen.clone();
    private static final ItemStack increaseManaRegen = defaultGreen.clone();
    private static final ItemStack decreaseMana = defaultRed.clone();
    private static final ItemStack decreaseMaxMana = defaultRed.clone();
    private static final ItemStack decreaseManaRegen = defaultRed.clone();

    static {
        inv = Bukkit.createInventory(null, 27, "§6MANA GUI");

        //Using getSize instead of just 27 to get slightly closer to modularity
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, defaultFiller);
        }

        ItemMeta meta = defaultFiller.clone().getItemMeta();

        meta.setDisplayName("§aIncrease Mana");
        increaseMana.setItemMeta(meta);

        meta.setDisplayName("§aIncrease Max Mana");
        increaseMaxMana.setItemMeta(meta);

        meta.setDisplayName("§aIncrease Mana Regeneration");
        increaseManaRegen.setItemMeta(meta);

        meta.setDisplayName("§cDecrease Mana");
        decreaseMana.setItemMeta(meta);

        meta.setDisplayName("§cDecrease Max Mana");
        decreaseMaxMana.setItemMeta(meta);

        meta.setDisplayName("§cDecrease Mana Regeneration");
        decreaseManaRegen.setItemMeta(meta);

        inv.setItem(1, increaseMana);
        inv.setItem(4, increaseMaxMana);
        inv.setItem(7, increaseManaRegen);

        inv.setItem(19, decreaseMana);
        inv.setItem(22, decreaseMaxMana);
        inv.setItem(25, decreaseManaRegen);
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(inv.getName())) {
            return;
        }

        event.setCancelled(true);
        ItemStack item = inv.getItem(event.getSlot());

        UUID uuid = event.getWhoClicked().getUniqueId();
        ManaManager manager = ManaPlus.managerMap.get(uuid);

        if (increaseMana.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.mana += 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (increaseMaxMana.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.maxMana += 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (increaseManaRegen.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.manaRegenRate += 2.5F;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseMana.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.mana -= 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseMaxMana.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.maxMana -= 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseManaRegen.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
            manager.manaRegenRate -= 2.5F;
            ManaPlus.managerMap.put(uuid, manager);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!event.getInventory().getName().equals(inv.getName())) {
            return;
        }

        event.setCancelled(true);
        ItemStack item = event.getCursor();

        UUID uuid = event.getWhoClicked().getUniqueId();
        ManaManager manager = ManaPlus.managerMap.get(uuid);

        if (increaseMana == item) {
            manager.mana += 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (increaseMaxMana == item) {
            manager.maxMana += 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (increaseManaRegen == item) {
            manager.manaRegenRate += 2.5F;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseMana == item) {
            manager.mana -= 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseMaxMana == item) {
            manager.maxMana -= 1;
            ManaPlus.managerMap.put(uuid, manager);
        }
        else if (decreaseManaRegen == item) {
            manager.manaRegenRate -= 2.5F;
            ManaPlus.managerMap.put(uuid, manager);
        }
    }
}
