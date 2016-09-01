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
	if (alias.equals("mm") && args.length == 0) {
		player.sendMessage("yo yo yo");
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Rune of Speed");
		List<String> loreList = new ArrayList<String>();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");//This is the first line of lore
		loreList.add(ChatColor.GRAY + "To be granted endouring agility");//This is the second line of lore
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		PlayerInventory inventory = player.getInventory();
		inventory.addItem(item);
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Strength");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To give yourself brutal strength");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "Rune of Invisibility");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To hide yourself from your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_RED.toString()+ ChatColor.BOLD.toString() + "Rune of Destruction");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To unleash a fury of explosions");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Incineration");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To incinerate those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Rune of Launching");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To take off into the sky");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Rune of Sickening");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To sicken those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Healing");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To heal you to full health");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Rune of Protection");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spawn a protective shell around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.BLACK + ChatColor.BOLD.toString() + "Rune of Blinding");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To blind those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Rune of Lightning");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To strike those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Rune of Extreme Power");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "TO WREAK HAVOC ON YOUR ENEMIES");
		loreList.add(ChatColor.GRAY + "Right click to use");
		loreList.add(ChatColor.DARK_RED + "Special Rune");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "Rune of Wither");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To wither your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Rune of Vampirisim");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To gain health with hits");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Barraging");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To barrage your enemies with arrows");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Regeneration");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To regenerate your health");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Rune of Volatile Arrows");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To create explosions with arrow hits");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Thorns");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To injure those who dare hurt you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString() + "Rune of Repair");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To fully repair your armor");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Rune of Leaping");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To jump like a rabbit");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Linux's Cowboy Rune Of Gay");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Use this if you're a faggot");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Flying");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To gain flight powers for a short time");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Fire Spreading");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spread fire with your arrows");
		loreList.add(ChatColor.GRAY + "Right click to use");
		item.setItemMeta(im);
		inventory.addItem(item);
		String test = ChatColor.RED + "displayName";
		if (item.getItemMeta().getDisplayName().equals(test)) {
			player.sendMessage("no!");
		}
		
		} else if (alias.equals("mm") && args.length == 1 && args[0].equals("clearall")) {
			player.sendMessage(nodmg.toString());
			nodmg.clear();
			player.sendMessage(nodmg.toString());
			player.sendMessage(nodmg1.toString());
			nodmg1.clear();
			player.sendMessage(nodmg1.toString() );
			player.sendMessage(explosions.toString());
			explosions.clear();
			player.sendMessage(explosions.toString());
			player.sendMessage(vampire.toString());
			vampire.clear();
			player.sendMessage(vampire.toString());
			player.sendMessage(alreadyused.toString());
			alreadyused.clear();
			player.sendMessage(alreadyused.toString());
		}
		
	     
	
		
		
		
		
		
		
		
		
		
		
		return true;
	}
}