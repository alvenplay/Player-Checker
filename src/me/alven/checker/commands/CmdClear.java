package me.alven.checker.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alven.checker.util.Check;
import me.alven.checker.util.Checking;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class CmdClear {
	public static boolean AClear(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Send.PlName(sender);
			Send.TagMessage(sender, "&c/" +Check.PlFirstAlias() +" " +FileManager.getConfig().getString("SubCommands.clear.name") +" " +"[NICK]" +" &7" +FileManager.getMessages().
					getString("SubCommands.clear.desc"));
			return true;
		}
		if(args.length == 2) {
			if(Bukkit.getPlayer(args[1]) != null) {
				if(Checking.contNick(Bukkit.getPlayer(args[1]))) {
					if(FileManager.getChecking().get(Bukkit.getPlayer(args[1]).getName()+".admin").equals(sender.getName())) {
						Player cheat = Bukkit.getPlayer(args[1]);
						cheat.teleport(((Location) FileManager.getChecking().get(cheat.getName() +".location")));
						Checking.remNick(cheat);
						Send.TagCMessage(((CommandSender)cheat), "SubCommands.clear.plcl");
						Send.ReplacePlayer(sender, "SubCommands.clear.amcl", cheat.getName());
						return true;
					} else {
						Send.ReplacePlayer(sender, "SubCommands.clear.plcheck", Bukkit.getPlayer(args[1]).getName());
						return true;
					}
				} else {
					Send.ReplacePlayer(sender, "SubCommands.clear.nocheck", Bukkit.getPlayer(args[1]).getName());
					return true;
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
