package me.alven.checker.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alven.checker.Main;
import me.alven.checker.util.Check;
import me.alven.checker.util.Checking;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class CmdTempBan {

	public static boolean ATempBan(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Send.PlName(sender);
			Send.TagMessage(sender, "&c/" +Check.PlFirstAlias() +" " +FileManager.getConfig().getString("SubCommands.tempban.name") +" " 
					+"[NICK] <TIME>" +" &7" +FileManager.getMessages().getString("SubCommands.tempban.desc"));
			return true;
		}
		if(args.length > 1) {
			if(args.length > 3) {
				Send.TagCMessage(sender, "Main.loargs");
				return true;
			}
			if(Bukkit.getPlayer(args[1]) != null) {
				if(Checking.contNick(Bukkit.getPlayer(args[1]))) {
					if(FileManager.getChecking().get(Bukkit.getPlayer(args[1]).getName()+".admin").equals(sender.getName())) {
						if(args.length == 3) {
							Player cheat = Bukkit.getPlayer(args[1]);
							cheat.teleport(((Location) FileManager.getChecking().get(cheat.getName() +".location")));
							Checking.remNick(cheat);
							if(FileManager.getConfig().getBoolean("SubCommands.tempban.ConsoleExec")) {
								Main.getInst().getServer().dispatchCommand(Bukkit.getConsoleSender()
										, Check.TimeBanCommander("SubCommands.tempban.exccom", sender, cheat, args[2]));
							} else {
								((Player) sender).performCommand(Check.TimeBanCommander("SubCommands.tempban.exccom", sender, cheat, args[2]));
							}
							if(Bukkit.getPlayer(args[1]) == null) {
								Send.ReplacePlayer(sender, "SubCommands.tempban.ampb", cheat.getName());
							} else {
								Send.ReplacePlayer(sender, "SubCommands.tempban.amnb", cheat.getName());
							}
							return true;
						} else {
							Send.TagCMessage(sender, "SubCommands.tempban.time");
							return true;
						}
					} else {
						Send.ReplacePlayer(sender, "SubCommands.tempban.plcheck", Bukkit.getPlayer(args[1]).getName());
						return true;
					}
				} else {
					Send.ReplacePlayer(sender, "SubCommands.tempban.nocheck", Bukkit.getPlayer(args[1]).getName());
					return true;
				}
			} else {
				Send.BadPlayer(sender, args[1]);
				return true;
			}
		}
		return false;
	}
}
