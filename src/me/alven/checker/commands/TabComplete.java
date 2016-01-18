package me.alven.checker.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alven.checker.util.FileManager;

public class TabComplete {
	private static List<String> arg1 = new ArrayList<String>();
	private static List<String> arg2 = new ArrayList<String>();
	private static List<String> repl = new ArrayList<String>();
	
	public static List<String> oTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> retur = new ArrayList<String>();
		if(args.length == 1) {
			for(String s : arg1) {
				if(s.startsWith(args[0])){
					retur.add(s);
				}
				if(s.isEmpty()) {
					 retur.addAll(arg1);
				}
			}
			return retur;
		}
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.name"))) {
				for(String s : arg2) {
					if(s.startsWith(args[1])) {
						retur.add(s);
					}
					if(s.isEmpty()) {
						retur.addAll(arg2);
					}
				}
				return retur;
			}
			if(repl.contains(args[0])) {
				List<String> nick = new ArrayList<String>();
				for(Player s : Bukkit.getOnlinePlayers())
					nick.add(s.getName());
				return nick;
			}
		}
		if(args.length == 3) {
			if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.name"))) {
				if(arg2.contains(args[1])) {
					List<String> nick = new ArrayList<String>();
					for(Player s : Bukkit.getOnlinePlayers())
						nick.add(s.getName());
					return nick;
				}
			}
		}
		return retur;
		
	}
	
	public TabComplete() {
		arg1.add(FileManager.getConfig().getString("SubCommands.check.name"));
		arg1.add(FileManager.getConfig().getString("SubCommands.clear.name"));
		arg1.add(FileManager.getConfig().getString("SubCommands.permban.name"));
		arg1.add(FileManager.getConfig().getString("SubCommands.tempban.name"));
		arg1.add(FileManager.getConfig().getString("SubCommands.wspban.name"));
		arg1.add(FileManager.getConfig().getString("SubCommands.loc.name"));
		arg2.add(FileManager.getConfig().getString("SubCommands.loc.set.name"));
		arg2.add(FileManager.getConfig().getString("SubCommands.loc.get.name"));
		repl.addAll(arg1);
		repl.remove(arg1.size()-1);
	}
}
