package tech.presstabs.manaplus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdExec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only playerz");
            return true;
        }

        Player player = ((Player) commandSender);
        if (s.equals("servegui")) {
            player.openInventory(ManaGUI.inv);
            return true;
        }
        return true;
    }
}
