package me.alven.checker.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alven.checker.util.Check;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class CmdLocation {
	
	public static boolean ALocation(CommandSender sender, String[] args) {
		if(args.length == 1) {
			Send.PlName(sender);
			if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.set.perm"))
				Send.TagMessage(sender, "&c/" +Check.PlFirstAlias() +" " +FileManager.getConfig().getString("SubCommands.loc.name") +" " +FileManager.getConfig().getString("SubCommands.loc.set.name")
					+" &7" +FileManager.getMessages().getString("SubCommands.loc.setdesc"));
			if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm"))
				Send.TagMessage(sender, "&c/" +Check.PlFirstAlias()  +" " +FileManager.getConfig().getString("SubCommands.loc.name") +" " +FileManager.getConfig().getString("SubCommands.loc.get.name")
					+ " " +"[NICK]" +" &7" +FileManager.getMessages().getString("SubCommands.loc.getdesc"));
			return true;
		} else
		if(args.length == 2) {
			if(args[1].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.set.name"))) {
				if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.set.perm")) {
					Location l = ((Player)sender).getLocation();
					FileManager.setConfig("Main.checkroom", l);
					Send.TagCMessage(sender, "SubCommands.loc.setroom");
					return true;
				} else {
					Send.TagCMessage(sender, "Main.NoPerms");
					return true;
				}
			} else
			if(args[1].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.get.name"))) {
				if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm")) {
					if(!FileManager.getConfig().get("Main.checkroom").toString().isEmpty()) {
						((Player)sender).teleport((Location) FileManager.getConfig().get("Main.checkroom"));
						Send.TagCMessage(sender, "SubCommands.loc.tp");
						return true;	
					} else {
						Send.TagCMessage(sender, "Main.noloc");
						return true;
					}
				} else {
					Send.TagCMessage(sender, "Main.NoPerms");
					return true;
				}
			} else {
				Send.BadArg(sender, args[1]);
				return true;
			}
		}
		if(args.length == 3) {
			if(args[1].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.get.name"))) {
				if(Bukkit.getPlayer(args[2]) != null) {
					if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm")) {
						if(!FileManager.getConfig().get("Main.checkroom").toString().isEmpty()) {
							Player p = Bukkit.getPlayer(args[2]);
							p.teleport(((Location)FileManager.getConfig().get("Main.checkroom")));
							Send.ReplaceAdmin(((CommandSender) p), "SubCommands.loc.pltp", sender.getName());
							Send.ReplacePlayer(sender, "SubCommands.loc.adtp", p.getName());
							return true;
						} else {
							Send.TagCMessage(sender, "Main.noloc");
							return true;
						}
				} else {
					Send.TagCMessage(sender, "Main.NoPerms");
					return true;
				}
				} else {
					Send.BadPlayer(sender, args[2]);
					return true;
				}
			} else {
				Send.BadArg(sender, args[1]);
				return true;
			}
		} else {
			Send.TagCMessage(sender, "Main.loargs");
			return true;
		}
	}
}
