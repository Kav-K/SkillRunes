package me.kav.mythicalrunes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class testCommand implements CommandExecutor, hashmaps {
	Main plugin;

	public testCommand(Main passedPlugin) {
		this.plugin = passedPlugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
	Player player = (Player)sender;
	if (alias.equals("runes") && args.length == 3 && args[0].equals("give") && args[1].equals("all")) {
		Player target = Bukkit.getPlayerExact(args[2]);
		if (!(target == null)) {
			if (player.hasPermission("runes.give.all") || player.isOp()) {
			player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You gave all the runes to " + target.getName().toString());
			target.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You have been given all the runes!");
			plugin.runeOfSpeed(target);
			plugin.runeOfBarraging(target);
			plugin.runeOfBlinding(target);
			plugin.runeOfCrippling(target);
			plugin.runeOfDestruction(target);
			plugin.runeOfExtremePower(target);
			plugin.runeOfFlamingArrows(target);
			plugin.runeOfFlameThrowing(target);
			plugin.runeOfFlying(target);
			plugin.runeOfHaste(target);
			plugin.runeOfHealing(target);
			plugin.runeOfIncineration(target);
			plugin.runeOfInvisibility(target);
			plugin.runeOfLaunching(target);
			plugin.runeOfLeaping(target);
			plugin.runeOfLightningArrows(target);
			plugin.runeOfMinions(target);
			plugin.runeOfParalyzing(target);
			plugin.runeOfPoisonousArrows(target);
			plugin.runeOfProtection(target);
			plugin.runeOfRegeneration(target);
			plugin.runeOfRepair(target);
			plugin.runeOfRepellant(target);
             plugin.runeOfSickening(target);
             plugin.runeOfLightning(target);
             plugin.runeOfStrength(target);
             plugin.runeOfThorns(target);
             plugin.runeOfVampirism(target);
             plugin.runeOfVolatileArrows(target);
             plugin.runeOfWither(target);
             plugin.runeOfArrowAffinity(target);
             plugin.runeOfClarity(target);
             plugin.runeOfWaterWalking(target);
             plugin.runeOfBreathing(target);
			} else if (!(player.isOp()) && !(player.hasPermission("runes.give.all"))) {
				player.sendMessage(plugin.prefix + " " + ChatColor.RED + "You don't have permission!");
			}
		} else {
			player.sendMessage(ChatColor.RED + "That is not a valid target!");
		}
	
		} else if (alias.equals("runes") && args.length == 3 && args[0].equals("give")) {
			if (player.hasPermission("runes.give.single") || player.isOp()) {
				Player player1 = Bukkit.getPlayerExact(args[1]);
				String rune = args[2];
				
				if (rune.equals("runeofspeed") && player1 != null) {
					plugin.runeOfSpeed(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				} else if (rune.equals("runeofstrength") && player1 != null) {
					plugin.runeOfStrength(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofbarraging") && player1 != null) {
					plugin.runeOfBarraging(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofblinding") && player1 != null) {
					plugin.runeOfBlinding(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofcrippling") && player1 != null) {
					plugin.runeOfCrippling(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofdestruction") && player1 != null) {
					plugin.runeOfDestruction(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofextremepower") && player1 != null) {
					plugin.runeOfExtremePower(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofflamingarrows") && player1 != null) {
					plugin.runeOfFlamingArrows(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofFlameThrowing") && player1 != null) {
					plugin.runeOfFlameThrowing(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofflying") && player1 != null) {
					plugin.runeOfFlying(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofhaste") && player1 != null) {
					plugin.runeOfHaste(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofHealing") && player1 != null) {
					plugin.runeOfHealing(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofincineration") && player1 != null) {
					plugin.runeOfIncineration(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofinvisibility") && player1 != null) {
					plugin.runeOfInvisibility(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeoflaunching") && player1 != null) {
					plugin.runeOfLaunching(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofleaping") && player1 != null) {
					plugin.runeOfLeaping(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeoflightningarrows") && player1 != null) {
					plugin.runeOfLightningArrows(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofminions") && player1 != null) {
					plugin.runeOfMinions(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofparalyzing") && player1 != null) {
					plugin.runeOfParalyzing(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofpoisonousarrows") && player1 != null) {
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
					plugin.runeOfPoisonousArrows(player1);
				}else if (rune.equals("runeofprotection") && player1 != null) {
					plugin.runeOfProtection(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofregeneration") && player1 != null) {
					plugin.runeOfRegeneration(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofrepair") && player1 != null) {
					plugin.runeOfRepair(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofrepellant") && player1 != null) {
					plugin.runeOfRepellant(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofsickening") && player1 != null) {
					plugin.runeOfSickening(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeoflightning") && player1 != null) {
					plugin.runeOfLightning(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofthorns") && player1 != null) {
					plugin.runeOfThorns(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofvampirism") && player1 != null) {
					plugin.runeOfVampirism(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofvolatilearrows") && player1 != null) {
					plugin.runeOfVolatileArrows(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofwither") && player1 != null) {
					plugin.runeOfWither(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofarrowaffinity") && player1 != null) {
					plugin.runeOfArrowAffinity(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofclarity") && player1 != null) {
					plugin.runeOfClarity(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofwaterwalking") && player1 != null) {
					plugin.runeOfWaterWalking(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				}else if (rune.equals("runeofbreathing") && player1 != null) {
					plugin.runeOfBreathing(player1);
					player.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "Gave a rune to " + player1.getName().toString());
					player1.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You recieved a rune!");
				} else {
					player.sendMessage(ChatColor.RED + "Invalid player or syntax!");
				}
				
				
				
				
				
			} else if (!(player.hasPermission("runes.give.single")) || !(player.isOp())) {
				player.sendMessage(plugin.prefix + ChatColor.RED + " You dont have permission!");
			}
			
					
						
					
				
				
				
				
			
		} else if (alias.equals("runes") && args.length == 1 && args[0].equals("list")) {
			player.sendMessage(plugin.prefix + " " + ChatColor.RED + "http://pastebin.com/EmjERWuA <- For a list and explanation of ALL runes");
			
			
			
		} else if (alias.equalsIgnoreCase("Runes") && args.length == 1 && args[0].equals("help")) {
			if (player.hasPermission("runes.help") || player.isOp()){
				player.sendMessage("================== " +ChatColor.YELLOW+"SkillRunes " +ChatColor.WHITE+ "================");
				player.sendMessage(ChatColor.YELLOW + "/runes help - " + ChatColor.RED + "Shows this page");
				player.sendMessage(ChatColor.YELLOW + "/runes list - " + ChatColor.RED + "List all runes");
				player.sendMessage(ChatColor.YELLOW + "/runes give all <player> - " + ChatColor.RED + "Give all runes to a player");
				player.sendMessage(ChatColor.YELLOW + "/runes give <player> <rune> - " + ChatColor.RED + "Give a rune to a player");
				player.sendMessage("================== " +ChatColor.YELLOW+"SkillRunes " +ChatColor.WHITE+ "================");
			} else {
				player.sendMessage(plugin.prefix + " " + ChatColor.RED + "You dont have permission!");
			}
		}
		else {
			player.sendMessage(plugin.prefix + " " + ChatColor.RED + "Invalid syntax!");
		}
		
	     
	
		
		
		
		
		
		
		
		
		
		
		return true;
	
	}
	
}