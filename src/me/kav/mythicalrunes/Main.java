package me.kav.mythicalrunes;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;

public class Main extends JavaPlugin implements hashmaps {
	
	
	@Override
	public void onEnable() {
    loadConfiguration();
    this.reloadConfig();
	this.getCommand("sr").setExecutor(new testCommand(this));
	new PlayerListener(this);
 	}
	
	
	
	
	//Add auto updater and licensing feature
	@Override
	public void onDisable() {

	}
	public void loadConfiguration() {
       this.getConfig().options().copyDefaults(true);
       this.saveDefaultConfig();
	}
		    

	
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (alias.equalsIgnoreCase("hellohello")) {
			Player player = (Player) sender;
			player.sendMessage("success");
			player.sendMessage(getConfig().getList("bannedwords").toString());
		}
		
		
		
		
		return false;
	}
}
