package me.alven.checker.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;

import me.alven.checker.Main;

public class FileManager {

	private static YamlConfiguration config;
	private static YamlConfiguration messages;
	private static YamlConfiguration checking;
	
	public FileManager() {
		if(getFolder().exists())
			getFolder().mkdir();
		if(!getFile("config.yml").exists())
			Main.getInst().saveResource("config.yml", true);
		if(!getFile("messages.yml").exists())
			Main.getInst().saveResource("messages.yml", true);
		if(!getFile("checking.yml").exists())
			Main.getInst().saveResource("checking.yml", true);
		
		config = YamlConfiguration.loadConfiguration(getFile("config.yml"));
		messages = YamlConfiguration.loadConfiguration(getFile("messages.yml"));
		checking = YamlConfiguration.loadConfiguration(getFile("checking.yml"));
		
		regPerms();
	}
	
	private static File getFolder() {
		return Main.getInst().getDataFolder();
	}
	
	private static File getFile(String name) {
		return new File(getFolder(), (name));
	}

	public static YamlConfiguration getConfig() {
		return config;
	}

	public static YamlConfiguration getMessages() {
		return messages;
	}

	public static YamlConfiguration getChecking() {
		return checking;
	}

	public static void setConfig(String path, Object value) {
		config.set(path, value);
		try {
			config.save(getFile("config.yml"));
		} catch (IOException e) {e.printStackTrace();}
		config = YamlConfiguration.loadConfiguration(getFile("config.yml"));
	}

	public static void setMessages(String path, Object value) {
		messages.set(path, value);
		try {
			messages.save(getFile("messages.yml"));
		} catch (IOException e) {e.printStackTrace();}
		messages = YamlConfiguration.loadConfiguration(getFile("messages.yml"));
	}

	public static void setChecking(String path, Object value) {
		checking.set(path, value);
		try {
			checking.save(getFile("checking.yml"));
		} catch (IOException e) {e.printStackTrace();}
		checking = YamlConfiguration.loadConfiguration(getFile("checking.yml"));
	}
	
	private static void regPerms() {
		List<String> noDupli = new ArrayList<String>();
		noDupli.add(getConfig().getString("SubCommands.check.perm"));
		noDupli.add(getConfig().getString("SubCommands.clear.perm"));
		noDupli.add(getConfig().getString("SubCommands.permban.perm"));
		noDupli.add(getConfig().getString("SubCommands.tempban.perm"));
		noDupli.add(getConfig().getString("SubCommands.wspban.perm"));
		noDupli.add(getConfig().getString("SubCommands.loc.perm"));
		noDupli.add(getConfig().getString("SubCommands.loc.set.perm"));
		noDupli.add(getConfig().getString("SubCommands.loc.get.perm"));
		//noDupli.add(getConfig().getString("SubCommands.set.perm"));
		//noDupli.add(getConfig().getString("SubCommands.get.perm"));
		Set<String> s = new LinkedHashSet<String>(noDupli);
		noDupli.clear();
		noDupli.addAll(s);
		for(String e : noDupli)
			Main.getInst().getServer().getPluginManager().addPermission(new Permission(e));
	}
}
