package me.alven.checker.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.alven.checker.Main;
import me.alven.checker.util.Check;
import me.alven.checker.util.Checking;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class ListManager implements Listener {

	@EventHandler(priority=EventPriority.LOWEST)
	public void onMove(PlayerMoveEvent e) {
		if(FileManager.getConfig().getBoolean("Main.blockmove"))
			if(Checking.contNick(e.getPlayer())){
				Send.TagCMessage(((CommandSender) e.getPlayer()), "Main.check");
				e.setCancelled(true);
			}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onDrop(PlayerDropItemEvent e) {
		if(FileManager.getConfig().getBoolean("Main.blockmove"))
			if(Checking.contNick(e.getPlayer())){
				Send.TagCMessage(((CommandSender) e.getPlayer()), "Main.check");
				e.setCancelled(true);
			}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onQuit(PlayerQuitEvent e) {
		if(FileManager.getConfig().getBoolean("Main.quitban"))
			if(Checking.contNick(e.getPlayer())){
				Player sender = Bukkit.getPlayer(FileManager.getChecking().getString(e.getPlayer().getName() +".admin"));
				e.getPlayer().teleport(((Location) FileManager.getChecking().get(e.getPlayer().getName() +".location")));
				Checking.remNick(e.getPlayer());
				if(FileManager.getConfig().getBoolean("Main.ConsoleExec")) {
					Main.getInst().getServer().dispatchCommand(Bukkit.getConsoleSender()
							, Check.BanCommanderl("Main.quitBan", e.getPlayer()));
				} else {
					((Player) sender).performCommand(Check.BanCommanderl("Main.quitBan", e.getPlayer()));
				}
				Send.ReplacePlayer(sender, "SubCommands.permban.ampb", e.getPlayer().getName());
			}
	}
}
