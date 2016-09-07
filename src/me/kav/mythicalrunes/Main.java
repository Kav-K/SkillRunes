package me.kav.mythicalrunes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.slikey.effectlib.EffectManager;

public class Main extends JavaPlugin implements hashmaps {
	
	
	@Override
	public void onEnable() {
    loadConfiguration();
    this.reloadConfig();
    System.out.println(this.getConfig().getString("Runes.runeofspeed.lore1"));
	this.getCommand("runes").setExecutor(new testCommand(this));
	if (this.hasParticles()) {
		System.out.println("EffectLib found! Awesome! Enabling your effects!");
	} else if (!(this.hasParticles())) {
		System.out.println("EffectLub not found! Damn, Gotta disable particle effects now :(");
	}
	
	if (this.testWE() == false && this.testWG() == false) {
		System.out.println("WorldEdit/WorldGuard not found! Disabling support");
		new PlayerListener(this);
		
	} else if (this.testWE() == true && this.testWG() == false) {
		System.out.println("WorldEdit found but no worldguard! You need both!");
		new PlayerListener(this);
	} else if (this.testWG() == true && this.testWE() == false) {
		System.out.println("WorldGuard found but no WorldEdit! You need both!");
		new PlayerListener(this);
	} else if (this.testWG() == true && this.testWE() == true) {
		new PlayerListenerWG(this);
		System.out.println("WG/WE Found! Enabling support!");
	}
	


	
 	}
	

	public String runeofspeed = this.coloredString("Runes.runeofspeed.name");
	public String runeofstrength = this.coloredString("Runes.runeofstrength.name");
	public String runeofinvis = this.coloredString("Runes.runeofinvisibility.name");
	public String runeofdestruction = this.coloredString("Runes.runeofdestruction.name");
	public String runeofpoison = this.coloredString("Runes.runeofsickening.name");
	public String runeofillumination = this.coloredString("Runes.runeoflaunching.name");
	public String runeofincineration = this.coloredString("Runes.runeofincineration.name");
	public String runeofhealing = this.coloredString("Runes.runeofhealing.name");
	public String runeofprotection = this.coloredString("Runes.runeofprotection.name");
	public String runeofbreathing = this.coloredString("Runes.runeofbreathing.name");
	public String runeofblinding = this.coloredString("Runes.runeofblinding.name");
	public String runeoflightning = this.coloredString("Runes.runeoflightning.name");
	public String runeofextremepower = this.coloredString("Runes.runeofextremepower.name");
	public String runeofwither = this.coloredString("Runes.runeofwither.name");
	public String runeofvampirism = this.coloredString("Runes.runeofvampirism.name");
	public String runeofbaraging = this.coloredString("Runes.runeofbarraging.name");
	public String runeofregeneration = this.coloredString("Runes.runeofregeneration.name");
	public String runeofvolatilearrows = this.coloredString("Runes.runeofvolatilearrows.name");
	public String runeofthorns = this.coloredString("Runes.runeofthorns.name");
	public String runeofrepair = this.coloredString("Runes.runeofrepair.name");
	public String runeofleaping = this.coloredString("Runes.runeofleaping.name");
	public String runeofflying = this.coloredString("Runes.runeofflying.name");
	public String runeoffirespreading = this.coloredString("Runes.runeofflamingarrows.name");
	public String runeofflamethrowing = this.coloredString("Runes.runeofflamethrowing.name");
	public String runeofrepellant = this.coloredString("Runes.runeofrepellant.name");
	public String runeoflightningarrows = this.coloredString("Runes.runeoflightningarrows.name");
	public String runeofpoisonousarrows = this.coloredString("Runes.runeofpoisonarrows.name");
	public String runeofhaste = this.coloredString("Runes.runeofhaste.name");
    public String runeofcrippling = this.coloredString("Runes.runeofcrippling.name");
    public String runeofminions = this.coloredString("Runes.runeofminions.name");
    public String runeofparalyze = this.coloredString("Runes.runeofparalyzing.name");
    public String runeofarrowaffinity = this.coloredString("Runes.runeofarrowaffinity.name");
    public String runeofclarity = this.coloredString("Runes.runeofclarity.name");
    public String runeofwaterwalking = this.coloredString("Runes.runeofwaterwalking.name");
    public String disabledmessage = this.coloredString("disabledmessage");
    public String prefix = this.coloredString("prefix");
    public String alreadyactivemessage = this.coloredString("alreadyactivemessage");
    public String youmayuseagainmessage = this.coloredString("youmayuseagainmessage");
    public String usemessage = this.coloredString("consumemessage"); 
    public String nonregionmessage = this.coloredString("regionblockedmessage");

	
	//Add auto updater and licensing feature
	@Override
	public void onDisable() {
	

	}
	
	public String getUseMessage(){
		String i = "null";
		try {
			i = this.coloredString("consumemessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Configuration error! Please check your configuration for consumemessage, plugin has recovered");
			return ChatColor.RED + "As you use this mythical rune, it shatters into pieces.";
		}
		
		
		
		
	}
	
	public String getDisabledMessage() {
		String i = "null";
		try {
			i = this.coloredString("disabledmessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Configuration error! Please check your config file for disabledmessage, plugin has recovered");
			return ChatColor.RED + "This rune is disabled!";
		}	
	}
	public String getNonRegionMessage() {
		String i = "null";
		try {
			i = this.coloredString("regionblockedmessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Configuration error! please check your config file for alreadyactivemessage, plugin has recovered");
			return ChatColor.RED + "You already have a rune active!";
			
		}
		
		
		
	}
	public String getYouMayUseAgainMessage() {
		String i = "null";
		try {
			i = this.coloredString("youmayuseagainmessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Configuration error! please check your config file for youmayuseagainmessage, plugin has recovered");
			return ChatColor.GREEN + "You may now use a rune again!";
		}
		
	}
	public String getAlreadyActiveMessage() {
		String i = "null";
		try {
			i = this.coloredString("alreadyactivemessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Configuration error! please check your config file for regionblockedmessage, plugin has recovered");
			return ChatColor.RED + "You may not use this rune in this region";
		}
	}
	public void loadConfiguration() {
       this.getConfig().options().copyDefaults(true);
       this.saveDefaultConfig();
	}
	public  int configInt(String string) {
		  int i = this.getConfig().getInt(string);
		  return i;
		  
		  
	  } 
	  public  String coloredString(String string) {
		  String message = this.getConfig().getString(string);
		  String colored = ChatColor.translateAlternateColorCodes('&', message);
		  return colored;
	  }
    public  boolean configBoolean(String string) {
  	  boolean i = this.getConfig().getBoolean(string);
  	  return i;
    }
    public  void runeOfSpeed(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(this.coloredString("Runes.runeofspeed.name"));
		List<String> loreList = new ArrayList<String>();
		loreList.add(this.coloredString("Runes.runeofspeed.lore1"));//This is the first line of lore
		loreList.add(this.coloredString("Runes.runeofspeed.lore2"));//This is the second line of lore
		loreList.add(this.coloredString("Runes.runeofspeed.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
	}
    public  void runeOfBreathing(Player player) {
		Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(this.coloredString("Runes.runeofbreathing.name"));
		List<String> loreList = new ArrayList<String>();
		loreList.add(this.coloredString("Runes.runeofbreathing.lore1"));//This is the first line of lore
		loreList.add(this.coloredString("Runes.runeofbreathing.lore2"));//This is the second line of lore
		loreList.add(this.coloredString("Runes.runeofbreathing.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
	}
   public  void runeOfStrength(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
	   im.setDisplayName(this.coloredString("Runes.runeofstrength.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofstrength.lore1"));
		loreList.add(this.coloredString("Runes.runeofstrength.lore2"));
		loreList.add(this.coloredString("Runes.runeofstrength.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);

   }
   public  void runeOfInvisibility(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofinvisibility.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofinvisibility.lore1"));
		loreList.add(this.coloredString("Runes.runeofinvisibility.lore2"));
		loreList.add(this.coloredString("Runes.runeofinvisibility.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public  void runeOfDestruction(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofdestruction.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofdestruction.lore1"));
		loreList.add(this.coloredString("Runes.runeofdestruction.lore2"));
		loreList.add(this.coloredString("Runes.runeofdestruction.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public  void runeOfIncineration(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofincineration.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofincineration.lore1"));
		loreList.add(this.coloredString("Runes.runeofincineration.lore2"));
		loreList.add(this.coloredString("Runes.runeofincineration.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
   }
   public  void runeOfLaunching(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeoflaunching.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeoflaunching.lore1"));
		loreList.add(this.coloredString("Runes.runeoflaunching.lore2"));
		loreList.add(this.coloredString("Runes.runeoflaunching.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		inventory.addItem(item);
		
		
		
		
   }
   public  void runeOfSickening(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofsickening.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofsickening.lore1"));
		loreList.add(this.coloredString("Runes.runeofsickening.lore2"));
		loreList.add(this.coloredString("Runes.runeofsickening.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfHealing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofhealing.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofhealing.lore1"));
		loreList.add(this.coloredString("Runes.runeofhealing.lore2"));
		loreList.add(this.coloredString("Runes.runeofhealing.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfProtection(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofprotection.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofprotection.lore1"));
		loreList.add(this.coloredString("Runes.runeofprotection.lore2"));
		loreList.add(this.coloredString("Runes.runeofprotection.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);
		
		inventory.addItem(item);
   }
   public  void runeOfBlinding(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofblinding.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofblinding.lore1"));
		loreList.add(this.coloredString("Runes.runeofblinding.lore2"));
		loreList.add(this.coloredString("Runes.runeofblinding.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfArrowAffinity(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofarrowaffinity.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore1"));
		loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore2"));
		loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore3"));
		loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore4"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfClarity(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofclarity.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofclarity.lore1"));
		loreList.add(this.coloredString("Runes.runeofclarity.lore2"));
		loreList.add(this.coloredString("Runes.runeofclarity.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfWaterWalking(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofwaterwalking.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofwaterwalking.lore1"));
		loreList.add(this.coloredString("Runes.runeofwaterwalking.lore2"));
		loreList.add(this.coloredString("Runes.runeofwaterwalking.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfLightning(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeoflightning.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeoflightning.lore1"));
		loreList.add(this.coloredString("Runes.runeoflightning.lore2"));
		loreList.add(this.coloredString("Runes.runeoflightning.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfExtremePower(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofextremepower.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofextremepower.lore1"));
		loreList.add(this.coloredString("Runes.runeofextremepower.lore2"));
		loreList.add(this.coloredString("Runes.runeofextremepower.lore3"));
		loreList.add(this.coloredString("Runes.runeofextremepower.lore4"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfWither(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofwither.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofwither.lore1"));
		loreList.add(this.coloredString("Runes.runeofwither.lore2"));
		loreList.add(this.coloredString("Runes.runeofwither.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfVampirism(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofvampirism.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofvampirism.lore1"));
		loreList.add(this.coloredString("Runes.runeofvampirism.lore2"));
		loreList.add(this.coloredString("Runes.runeofvampirism.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfBarraging(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofbarraging.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofbarraging.lore1"));
		loreList.add(this.coloredString("Runes.runeofbarraging.lore2"));
		loreList.add(this.coloredString("Runes.runeofbarraging.lore2"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfRegeneration(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofregeneration.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofregeneration.lore1"));
		loreList.add(this.coloredString("Runes.runeofregeneration.lore2"));
		loreList.add(this.coloredString("Runes.runeofregeneration.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfVolatileArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofvolatilearrows.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore1"));
		loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore2"));
		loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfThorns(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofthorns.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofthorns.lore1"));
		loreList.add(this.coloredString("Runes.runeofthorns.lore2"));
		loreList.add(this.coloredString("Runes.runeofthorns.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfRepair(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofrepair.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofrepair.lore1"));
		loreList.add(this.coloredString("Runes.runeofrepair.lore2"));
		loreList.add(this.coloredString("Runes.runeofrepair.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfLeaping(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofleaping.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofleaping.lore1"));
		loreList.add(this.coloredString("Runes.runeofleaping.lore2"));
		loreList.add(this.coloredString("Runes.runeofleaping.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfFlying(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofflying.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofflying.lore1"));
		loreList.add(this.coloredString("Runes.runeofflying.lore2"));
		loreList.add(this.coloredString("Runes.runeofflying.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfFlamingArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofflamingarrows.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofflamingarrows.lore1"));
		loreList.add(this.coloredString("Runes.runeofflamingarrows.lore2"));
		loreList.add(this.coloredString("Runes.runeofflamingarrows.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfFlameThrowing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofflamethrowing.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofflamethrowing.lore1"));
		loreList.add(this.coloredString("Runes.runeofflamethrowing.lore2"));
		loreList.add(this.coloredString("Runes.runeofflamethrowing.lore3"));
		loreList.add(this.coloredString("Runes.runeofflamethrowing.lore4"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfRepellant(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofrepellant.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofrepellant.lore1"));
		loreList.add(this.coloredString("Runes.runeofrepellant.lore2"));
		loreList.add(this.coloredString("Runes.runeofrepellant.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfLightningArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeoflightningarrows.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeoflightningarrows.lore1"));
		loreList.add(this.coloredString("Runes.runeoflightningarrows.lore2"));
		loreList.add(this.coloredString("Runes.runeoflightningarrows.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfPoisonousArrows(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		im.setDisplayName(this.coloredString("Runes.runeofpoisonarrows.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore1"));
		loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore2"));
		loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfHaste(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		
		
		im.setDisplayName(this.coloredString("Runes.runeofhaste.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofhaste.lore1"));
		loreList.add(this.coloredString("Runes.runeofhaste.lore2"));
		loreList.add(this.coloredString("Runes.runeofhaste.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfCrippling(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		
		
		im.setDisplayName(this.coloredString("Runes.runeofcrippling.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofcrippling.lore1"));
		loreList.add(this.coloredString("Runes.runeofcrippling.lore2"));
		loreList.add(this.coloredString("Runes.runeofcrippling.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfMinions(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();

		
		
		im.setDisplayName(this.coloredString("Runes.runeofminions.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofminions.lore1"));
		loreList.add(this.coloredString("Runes.runeofminions.lore2"));
		loreList.add(this.coloredString("Runes.runeofminions.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public  void runeOfParalyzing(Player player) {
	   Inventory inventory = player.getInventory();
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		im.setDisplayName(this.coloredString("Runes.runeofparalyzing.name"));
		loreList.clear();
		loreList.add(this.coloredString("Runes.runeofparalyzing.lore1"));
		loreList.add(this.coloredString("Runes.runeofparalyzing.lore2"));
		loreList.add(this.coloredString("Runes.runeofparalyzing.lore3"));
		im.setLore(loreList);
		item.setItemMeta(im);

		inventory.addItem(item);
   }
   public int getDelay(String string) {
	   int i =this.configInt("Runes."+string+".delay");
	   return i;
   }
   public int getDuration(String string) {
	   int i = this.configInt("Runes."+string+".duration");
	   return i;
   }
   public boolean isEnabled(String string) {
	   boolean i = this.configBoolean("Runes."+string+".enabled");
	   return i;
   }
   public int getAmplifier(String string) {
	   int i = this.configInt("Runes."+string+".amplifier");
	   return i;
   }
   public boolean particleson(String string) {
	   boolean i = this.configBoolean("Runes."+string+".particles");
	   return i;
   }
   public boolean hasParticles() {
	   if ( this.getServer().getPluginManager().isPluginEnabled("EffectLib")) {
			return true;
		}
	   else{
		   return false;
	   }
   }
   public World getPlayerWorld(Player player) {
	   World asd = player.getWorld();
	   return asd;
   }
   public Location getPlayerLocation(Player player) {
	   Location asd = player.getLocation();
	   return asd;
   }
   public boolean hasWE() {
	   if ( this.getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
			return true;
		}
	   else{
		   return false;
	   }
   }
   public boolean hasWG() {
	   if ( this.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		}
	   else{
		   return false;
	   }
   }

	   
	   
	   
	   
	   
	   
   
  public WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
  public boolean testWG() {
	  Plugin plugin2 = getServer().getPluginManager().getPlugin("WorldGuard");
	  
	  if (plugin2 == null) {
		  return false;
	  } else {
		  return true;
	  }
	  
  }
  public boolean testWE() {
	  Plugin plugin3 = getServer().getPluginManager().getPlugin("WorldEdit");
	  if (plugin3 == null) {
		  return false;
	  } else {
		  return true;
	  }
  }
		    

	
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (alias.equalsIgnoreCase("hellohello")) {
			Player player = (Player) sender;
			player.sendMessage(this.getConfig().getString("Runes.runeofspeed.lore2"));
		}
		
		
		
		
		return false;
	}
}
