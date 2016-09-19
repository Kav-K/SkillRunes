package me.kav.skillrunes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testCommand implements CommandExecutor, Caching {
	Main plugin;

	public testCommand(Main passedPlugin) {
		this.plugin = passedPlugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

		if (alias.equals("runes") && args.length == 3 && args[0].equals("give") && args[1].equals("all")) {
			Player target = Bukkit.getPlayerExact(args[2]);
			if (!(target == null)) {
				if (sender.hasPermission("runes.give.all") || sender.isOp()) {
					sender.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You gave all the runes to "
							+ target.getName().toString());
					target.sendMessage(plugin.prefix + " " + ChatColor.GREEN + "You have been given all the runes!");
					plugin.giveRune("runeofspeed", target);
					plugin.giveRune("runeofbarraging", target);
					plugin.giveRune("runeofblinding", target);
					plugin.giveRune("runeofcrippling", target);
					plugin.giveRune("runeofdestruction", target);
					plugin.giveRune("runeofextremepower", target);
					plugin.giveRune("runeofflamingarrows", target);
					plugin.giveRune("runeofflamethrowing", target);
					plugin.giveRune("runeofflying", target);
					plugin.giveRune("runeofhaste", target);
					plugin.giveRune("runeofhealing", target);
					plugin.giveRune("runeofincineration", target);
					plugin.giveRune("runeofinvisibility", target);
					plugin.giveRune("runeoflaunching", target);
					plugin.giveRune("runeofleaping", target);
					plugin.giveRune("runeoflightningarrows", target);
					plugin.giveRune("runeofminions", target);
					plugin.giveRune("runeofparalyzing", target);
					plugin.giveRune("runeofpoisonousarrows", target);
					plugin.giveRune("runeofprotection", target);
					plugin.giveRune("runeofregeneration", target);
					plugin.giveRune("runeofrepair", target);
					plugin.giveRune("runeofrepellant", target);
					plugin.giveRune("runeofsickening", target);
					plugin.giveRune("runeoflightning", target);
					plugin.giveRune("runeofstrength", target);
					plugin.giveRune("runeofthorns", target);
					plugin.giveRune("runeofvampirism", target);
					plugin.giveRune("runeofvolatilearrows", target);
					plugin.giveRune("runeofwither", target);
					plugin.giveRune("runeofarrowaffinity", target);
					plugin.giveRune("runeofclarity", target);
					plugin.giveRune("runeofwaterwalking", target);
					plugin.giveRune("runeofbreathing", target);
				} else if (!(sender.isOp()) && !(sender.hasPermission("runes.give.all"))) {
					sender.sendMessage(plugin.prefix + " " + ChatColor.RED + "You don't have permission!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "That is not a valid target!");
			}

		} else if (alias.equals("runes") && args.length == 3 && args[0].equals("give")) {
			if (sender.hasPermission("runes.give.single") || sender.isOp()) {
				Player player1 = Bukkit.getPlayerExact(args[1]);
				String rune = args[2];

				try {
					if (!(player1 == null)) {
						plugin.giveRune(rune.toLowerCase(), player1);
						if (runelist.contains(rune.toLowerCase())) {
							sender.sendMessage(plugin.prefix + ChatColor.RED + " You gave " + rune + " to "
									+ player1.getName().toString());
							player1.sendMessage(plugin.prefix + ChatColor.RED + " You recieved " + rune);

						} else {
							sender.sendMessage(plugin.prefix + ChatColor.RED + " Invalid rune!");
						}
					} else {
						sender.sendMessage(plugin.prefix + ChatColor.RED + " Invalid player!");
					}
				} catch (Exception e) {
					sender.sendMessage("Invalid player or rune name! Do /runes list for a list of the runes!");
				}

			} else if (!(sender.hasPermission("runes.give.single")) || !(sender.isOp())) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + " You dont have permission!");
			}

		} else if (alias.equals("runes") && args.length == 1 && args[0].equals("list")) {
			sender.sendMessage(plugin.prefix + " " + ChatColor.RED
					+ "http://pastebin.com/EmjERWuA <- For a list and explanation of ALL runes");

		} else if (alias.equalsIgnoreCase("Runes") && args.length == 1 && args[0].equals("help")) {
			if (sender.hasPermission("runes.help") || sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "================== " + ChatColor.YELLOW + "SkillRunes "
						+ ChatColor.RED + "================");
				sender.sendMessage(ChatColor.YELLOW + "/runes help - " + ChatColor.RED + "Shows this page");
				sender.sendMessage(ChatColor.YELLOW + "/runes list - " + ChatColor.RED + "List all runes");
				sender.sendMessage(ChatColor.YELLOW + "/runes give all <player> - " + ChatColor.RED
						+ "Give all runes to a player");
				sender.sendMessage(ChatColor.YELLOW + "/runes give <player> <rune> - " + ChatColor.RED
						+ "Give a rune to a player");
				sender.sendMessage(ChatColor.YELLOW + "/runes reload - " + ChatColor.RED
						+ "Reload config and plugin");
				sender.sendMessage(ChatColor.RED + "================== " + ChatColor.YELLOW + "SkillRunes "
						+ ChatColor.RED + "================");
			} else {
				sender.sendMessage(plugin.prefix + " " + ChatColor.RED + "You dont have permission!");
			}
		} else if (alias.equalsIgnoreCase("runes") && args.length == 1 && args[0].equals("reload")) {
			if (sender.hasPermission("runes.reload")) {
			plugin.getServer().getPluginManager().disablePlugin(plugin); 
			plugin.getServer().getPluginManager().enablePlugin(plugin);
			System.out.print("[SkillRunes] Plugin has been reloaded");
			sender.sendMessage(plugin.prefix + ChatColor.RED + " Plugin has been reloaded! Please note that some active runes may have been cancelled!");
			
			} else if (!(sender.hasPermission("runes.reload"))) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + " No permission!");
			}
		}
		else {
			sender.sendMessage(plugin.prefix + " " + ChatColor.RED + "Invalid syntax!");
		}

		return true;

	}

}