package me.alven.checker.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.alven.checker.util.Abstract;
import me.alven.checker.util.Check;
import me.alven.checker.util.FileManager;
import me.alven.checker.util.Send;

public class CmdsManager extends Abstract {

	public static void registerCommand() {
		CmdsManager cm = new CmdsManager("achecker", "/<command> <arg> [arg]", Check.PlDescrip(), Check.PlAliases());
		cm.register();
	}
	
	public CmdsManager(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			Send.PlName(sender);
			List<String> per = new ArrayList<String>();
			if(Check.hasPerms(sender, "SubCommands.check.perm"))
				per.add(FileManager.getConfig().getString("SubCommands.check.name") +" " +"[NICK]" +" &7" +FileManager.getMessages().
						getString("SubCommands.check.desc"));
			if(Check.hasPerms(sender, "SubCommands.clear.perm"))
				per.add(FileManager.getConfig().getString("SubCommands.clear.name") +" " +"[NICK]" +" &7" +FileManager.getMessages().
						getString("SubCommands.clear.desc"));
			if(Check.hasPerms(sender, "SubCommands.permban.perm"))
				per.add(FileManager.getConfig().getString("SubCommands.permban.name") +" " 
						+"[NICK]" +" &7" +FileManager.getMessages().getString("SubCommands.permban.desc"));
			if(Check.hasPerms(sender, "SubCommands.tempban.perm"))
				per.add(FileManager.getConfig().getString("SubCommands.tempban.name") +" " 
						+"[NICK] <TIME>" +" &7" +FileManager.getMessages().getString("SubCommands.tempban.desc"));
			if(Check.hasPerms(sender, "SubCommands.wspban.perm"))
				per.add(FileManager.getConfig().getString("SubCommands.wspban.name") +" " 
						+"[NICK] [TIME]" +" &7" +FileManager.getMessages().getString("SubCommands.wspban.desc"));
			if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.set.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm")) {
				if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.set.perm"))
					per.add(FileManager.getConfig().getString("SubCommands.loc.name") +" " +FileManager.getConfig().getString("SubCommands.loc.set.name")
						+" &7" +FileManager.getMessages().getString("SubCommands.loc.setdesc"));
				if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm"))
					per.add(FileManager.getConfig().getString("SubCommands.loc.name") +" " +FileManager.getConfig().getString("SubCommands.loc.get.name")
						+ " " +"[NICK]" +" &7" +FileManager.getMessages().getString("SubCommands.loc.getdesc"));
			}
			/*if(Check.hasPerms(sender, "SubCommands.set.perm")) {
			}
			if(Check.hasPerms(sender, "SubCommands.get.perm")) {
			}*/
			if(!per.isEmpty()) {
				for(String s : per)
					Send.TagMessage(sender, "&c/" +Check.PlFirstAlias()  +" " +s);
			} else {
				Send.TagCMessage(sender, "Main.NoComPerms");
			}
			return true;
		}
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.check.name"))) {
			if(Check.hasPerms(sender, "SubCommands.check.perm")) {
				CmdCheck.ACheck(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.clear.name"))) {
			if(Check.hasPerms(sender, "SubCommands.clear.perm")) {
				CmdClear.AClear(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.permban.name"))
				&& FileManager.getConfig().getBoolean("SubCommands.permban.enable")) {
			if(Check.hasPerms(sender, "SubCommands.permban.perm")) {
				CmdPermBan.APermBan(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.tempban.name"))
				&& FileManager.getConfig().getBoolean("SubCommands.tempban.enable")) {
			if(Check.hasPerms(sender, "SubCommands.tempban.perm")) {
				CmdTempBan.ATempBan(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.wspban.name"))
				&& FileManager.getConfig().getBoolean("SubCommands.wspban.enable")) {
			if(Check.hasPerms(sender, "SubCommands.wspban.perm")) {
				CmdWspBan.AWspBan(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
		}
		}
	//-------------------
		if(args[0].equalsIgnoreCase(FileManager.getConfig().getString("SubCommands.loc.name"))) {
			if(Check.hasPerms(sender, "SubCommands.loc.perm") || Check.hasPerms(sender, "SubCommands.loc.set.perm") || Check.hasPerms(sender, "SubCommands.loc.get.perm")) {
				CmdLocation.ALocation(sender, args);
				return true;
			} else {
				Send.TagCMessage(sender, "Main.NoPerms");
				return true;
			}
		}
	//-------------------
		/*TODO
		 * if(args[0].equalsIgnoreCase("set")) {
			
			return true;
		}
		if(args[0].equalsIgnoreCase("get")) {
			
			return true;
		}*/
		Send.BadArg(sender, args[0]);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return TabComplete.oTabComplete(sender, cmd, label, args);
	}
}
