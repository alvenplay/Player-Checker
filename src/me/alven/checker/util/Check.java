package me.alven.checker.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Check {
	
	public static String PlFirstAlias() {
		if(FileManager.getConfig().getStringList("Main.Aliases").isEmpty()) return "acheck";
			return FileManager.getConfig().getStringList("Main.Aliases").get(0);
	}
	
	public static String PlDescrip() {
		if(FileManager.getConfig().getString("Main.PlDescr").isEmpty()) return "Check Player";
			return FileManager.getConfig().getString("Main.PlDescr");
	}
	
	public static List<String> PlAliases() {
		if(FileManager.getConfig().getStringList("Main.Aliases").isEmpty()) return Arrays.asList("");
			return FileManager.getConfig().getStringList("Main.Aliases");
	}
	
	public static String TimeBanCommander(String path, CommandSender adm, Player cheat, String time) {
		return FileManager.getConfig().getString(path).replaceAll("%ADM%", adm.getName()).replaceAll("%PLAYER%", cheat.getName()).replaceAll("%TIME%", time);
	}
	
	public static String BanCommander(String path, CommandSender adm, Player cheat) {
		return FileManager.getConfig().getString(path).replaceAll("%ADM%", adm.getName()).replaceAll("%PLAYER%", cheat.getName()
				.replaceAll("%TIME%", ""));
	}
	
	public static String BanCommanderl(String path, Player cheat) {
		Player adm = Bukkit.getPlayer(FileManager.getChecking().getString(cheat.getName()+".admin"));
		return FileManager.getConfig().getString(path).replaceAll("%ADM%", adm.getName()).replaceAll("%PLAYER%", cheat.getName()
				.replaceAll("%TIME%", ""));
	}
	
	public static boolean hasPerms(CommandSender sender, String path) {
		if(sender.isOp()) return true;
		if(sender.hasPermission(FileManager.getConfig().getString(path))) return true;
		return false;
	}
}
