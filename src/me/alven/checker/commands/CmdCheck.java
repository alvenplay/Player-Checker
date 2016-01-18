package me.alven.checker.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alven.checker.util.Check;
import me.alven.checker.util.Checking;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class CmdCheck {
	
	public static boolean ACheck(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Send.PlName(sender);
			Send.TagMessage(sender, "&c/" +Check.PlFirstAlias() +" " +FileManager.getConfig().getString("SubCommands.check.name") +" " +"[NICK]" +" &7" +FileManager.getMessages().
					getString("SubCommands.check.desc"));
			return true;
		}
		if(args.length == 2) {
			if(Bukkit.getPlayer(args[1]) != null) {
				if(!FileManager.getConfig().get("Main.checkroom").toString().isEmpty()) {
					if(!Checking.contNick(Bukkit.getPlayer(args[1]))) {
						Player cheat = Bukkit.getPlayer(args[1]);
						Checking.addNick(cheat, ((Player)sender));
						cheat.teleport(((Location) FileManager.getConfig().get("Main.checkroom")));
						Send.TagCMessage(((CommandSender)cheat), "SubCommands.check.chmess");
						if(FileManager.getConfig().getBoolean("SubbedCommands.check.AdmTp")) ((Player)sender).teleport(((Location) FileManager.getConfig().get("Main.checkroom")));
						Send.ReplacePlayer(sender, "SubCommands.check.admmess", cheat.getName());
						return true;
					} else {
						Send.ReplacePlayer(sender, "SubCommands.check.plcheck", Bukkit.getPlayer(args[1]).getName());
						return true;
					}
				} else {
					Send.TagCMessage(sender, "Main.noloc");
				}
			} else {
				Send.BadPlayer(sender, args[1]);
				return true;
			}
		}
		if(args.length > 2) {
			Send.TagCMessage(sender, "Main.loargs");
			return true;
		}
		return false;
	}
}
