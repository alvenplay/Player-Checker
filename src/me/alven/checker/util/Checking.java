package me.alven.checker.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Checking {
	private static List<Player> nick = new ArrayList<Player>();

	public static void addNick(Player Pl, Player adm) {
		nick.add(Pl);
		FileManager.setChecking(Pl.getName()+".location", Pl.getLocation());
		FileManager.setChecking(Pl.getName()+".admin", adm.getName());
	}

	public static void remNick(Player Pl) {
		nick.remove(Pl);
		FileManager.setChecking(Pl.getName(), null);
	}

	public static boolean contNick(Player Pl) {
		if(nick.contains(Pl)) return true;
			return false;
	}
	
}
