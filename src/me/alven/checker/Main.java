package me.alven.checker;

import org.bukkit.plugin.java.JavaPlugin;

import me.alven.checker.commands.CmdsManager;
import me.alven.checker.commands.TabComplete;
import me.alven.checker.listeners.ListManager;
import me.alven.checker.util.FileManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	@Override
	public void onLoad() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		new FileManager();
		CmdsManager.registerCommand();
		new TabComplete();
		getServer().getPluginManager().registerEvents(new ListManager(), getInst());
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public static Main getInst() {
		if(instance == null) return new Main();
		return instance;
	}
}
