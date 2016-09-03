package me.kav.mythicalrunes;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import me.kav.mythicalrunes.Main;

public class give extends JavaPlugin {
	static Main plugin;

	public give(Main instance) {
		plugin = instance;

		

	}
	
	
	public static void runeOfSpeed(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Rune of Speed");
		List<String> loreList = new ArrayList<String>();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");//This is the first line of lore
		loreList.add(ChatColor.GRAY + "To be granted endouring agility");//This is the second line of lore
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
	}
   public static void runeOfStrength(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
	   im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Strength");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To give yourself brutal strength");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);

   }
   public static void runeOfInvisibility(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "Rune of Invisibility");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To hide yourself from your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public static void runeOfDestruction(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_RED.toString()+ ChatColor.BOLD.toString() + "Rune of Destruction");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To unleash a fury of explosions");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public static void runeOfIncineration(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Incineration");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To incinerate those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public static void runeOfLaunching(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Rune of Launching");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To take off into the sky");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
		
		
		
		
   }
   public static void runeOfSickening(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Rune of Sickening");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To sicken those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfHealing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Healing");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To heal you to full health");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfProtection(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Rune of Protection");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spawn a protective shell around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);
		
		inventory.addItem(item);
   }
   public static void runeOfBlinding(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.BLACK + ChatColor.BOLD.toString() + "Rune of Blinding");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To blind those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfArrowAffinity(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "Rune of Arrow Affinity");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To shoot arrows from your hand");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfClarity(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Clarity");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To clear all negative potion effects");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfWaterWalking(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.AQUA + ChatColor.BOLD.toString() + "Rune of Water Walking");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To walk on water");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfLightning(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Rune of Lightning");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To strike those around you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfExtremePower(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Rune of Extreme Power");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "TO WREAK HAVOC ON YOUR ENEMIES");
		loreList.add(ChatColor.GRAY + "Right click to use");
		loreList.add(ChatColor.DARK_RED + "Special Rune");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfWither(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "Rune of Wither");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To wither your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfVampirism(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Rune of Vampirisim");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To gain health with hits");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfBarraging(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Barraging");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To barrage your enemies with arrows");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfRegeneration(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Regeneration");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To regenerate your health");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfVolatileArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Rune of Volatile Arrows");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To create explosions with arrow hits");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfThorns(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Thorns");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To injure those who dare hurt you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfRepair(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString() + "Rune of Repair");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To fully repair your armor");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfLeaping(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Rune of Leaping");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To jump like a rabbit");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfFlying(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Rune of Flying");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To gain flight powers for a short time");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfFireSpreading(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Rune of Fire Spreading");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spread fire with your arrows");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfFlameThrowing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Rune of Flame Throwing");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spread flames to your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfRepellant(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Rune of Repellant");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To repel those near you");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfLightningArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Rune of Lightning Arrows");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To strike lightning on arrow hits!");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfPoisonousArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		im.setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Rune of Poisonous Arrows");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To poison those near your shots");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfHaste(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		
		
		im.setDisplayName(ChatColor.GRAY + ChatColor.BOLD.toString() + "Rune of Haste");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To mine with power");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfCrippling(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		
		im.setDisplayName(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "Rune of Crippling");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To cripple those you hit");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfMinions(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();

		
		
		im.setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Rune of Minions");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To spawn helpers where your enemies stand");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public static void runeOfParalyzing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(ChatColor.DARK_BLUE + ChatColor.BOLD.toString() + "Rune of Paralyzing");
		loreList.clear();
		loreList.add(ChatColor.GRAY + "Harness the power embedded in this mythical rune");
		loreList.add(ChatColor.GRAY + "To paralize your enemies");
		loreList.add(ChatColor.GRAY + "Right click to use");
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
  public static int getconfigint(String string) {
	  int i = plugin.getConfig().getInt(string);
	  return i;
	  
	  
  }
  public static String getColoredString(String string) {
	  String message = plugin.getConfig().getString(string).toString();
	  String colored = ChatColor.translateAlternateColorCodes('&', message);
	  return colored;
  }
   
   
}
