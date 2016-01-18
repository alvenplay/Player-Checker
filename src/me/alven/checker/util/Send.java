package me.alven.checker.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Send {
	
	public static void TagCMessage(CommandSender sender, String msg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +FileManager.getMessages().getString(msg))
				.replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag")))));
	}
	
	public static void TagMessage(CommandSender sender, String msg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +msg)).replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag"))));
	}
	
	public static void BadArg(CommandSender sender, String arg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +FileManager.getMessages().getString("Main.badarg"))).replaceAll("%ARG%", arg).replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag"))));
	}
	
	public static void BadPlayer(CommandSender sender, String arg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +FileManager.getMessages().getString("Main.nopl")).replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag"))))
				.replaceAll("%PLAYER%", arg));
	}
	
	public static void ReplacePlayer(CommandSender sender, String path, String arg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +FileManager.getMessages().getString(path)))
				.replaceAll("%PLAYER%", arg).replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag"))));
	}
	
	public static void ReplaceAdmin(CommandSender sender, String path, String arg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', (FileManager.getMessages().getString("Main.Tag") +FileManager.getMessages().getString(path)))
				.replaceAll("%ADM%", arg).replaceAll("\n", ("\n" +FileManager.getMessages().getString("Main.Tag"))));
	}
	
	public static void Message(CommandSender sender, String msg) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	
	public static void Console(String info) {
		Bukkit.getLogger().info("[AChecker] "+info);
	}
	
	public static void PlName(CommandSender sender) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-----------------==&8[&6AChecker&8]&7==-----------------"));
	}
	
}
