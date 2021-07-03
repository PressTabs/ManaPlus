package tech.presstabs.manaplus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaPlus extends JavaPlugin implements Listener {
    public static Map<UUID, ManaManager> managerMap = new HashMap<>();

    @Override
    public void onEnable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            managerMap.put(p.getUniqueId(), new ManaManager(p.getUniqueId()));
        }

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new ManaGUI(), this);
        this.getCommand("servegui").setExecutor(new CmdExec());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                ManaManager manager = managerMap.get(p.getUniqueId());
                p.sendMessage(manager.mana + " " + manager.maxMana + " " + manager.manaRegenRate);
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        managerMap.clear();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        managerMap.put(event.getPlayer().getUniqueId(), new ManaManager(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        managerMap.put(event.getPlayer().getUniqueId(), new ManaManager(event.getPlayer().getUniqueId()));
    }
}
