package me.kav.skillrunes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin implements Caching {
	// HELLO DECOMPILER! I see you've decompiled this plugin and are reading it, I dont care if you are, but I do ask one thing,
	// do NOT share the plugin with anybody else or send them a compiled .jar! Thanks!
	//
	//
	//
	//
	//

	@Override
	public void onEnable() {
		try {
		loadConfiguration();
		this.reloadConfig(); } catch (Exception e) {
			e.printStackTrace();
			System.out.println("[SkillRunes] Configuration error! Please check your string endings and spacing! Remember, no TABS are allowed!");
		}
		// check if we can read from config!
		try {
			System.out.println(this.getConfig().getString("Runes.runeofspeed.lore1"));
			System.out.println("[SkillRunes] Config accessiblity test passed!");
		} catch (Exception e) {
			this.getServer().getPluginManager().disablePlugin(this);
			e.printStackTrace();
			System.out.println("[SkillRunes] Config accessibility test failed! Disabling plugin");
		}
		this.getCommand("runes").setExecutor(new testCommand(this));
		// connect to metrics
		try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    } catch (IOException e) {
	        this.getLogger().info("Unable to submit to plugin metrics");
	    }
		// Initialize the runes then cache them in the hashmap.
		this.runeOfSpeed();
		this.runeOfArrowAffinity();
		this.runeOfBarraging();
		this.runeOfBlinding();
		this.runeOfBreathing();
		this.runeOfClarity();
		this.runeOfCrippling();
		this.runeOfDestruction();
		this.runeOfExtremePower();
		this.runeOfFlameThrowing();
		this.runeOfFlamingArrows();
		this.runeOfFlying();
		this.runeOfHaste();
		this.runeOfHealing();
		this.runeOfIncineration();
		this.runeOfInvisibility();
		this.runeOfLaunching();
		this.runeOfLeaping();
		this.runeOfLightning();
		this.runeOfLightningArrows();
		this.runeOfMinions();
		this.runeOfParalyzing();
		this.runeOfPoisonousArrows();
		this.runeOfProtection();
		this.runeOfRegeneration();
		this.runeOfRepair();
		this.runeOfRepellant();
		this.runeOfSickening();
		this.runeOfStrength();
		this.runeOfThorns();
		this.runeOfVampirism();
		this.runeOfWaterWalking();
		this.runeOfVolatileArrows();
		this.runeOfWither();
		// Check if effectLib is active
		if (this.getServer().getPluginManager().isPluginEnabled("HolographicDisplays")) {
			System.out.println("[SkillRunes] HoloDisplays found! Enabling support");
		} else if (!(this.getServer().getPluginManager().isPluginEnabled("HolographicDisplays"))) {
			System.out.println("[SkillRunes] HoloDisplays not found! Holograms will not be displayed ingame!");
		}
		if (this.hasParticles()) {
			System.out.println("[SkillRunes] EffectLib found! Enabling your effects!");
		} else if (!(this.hasParticles())) {
			System.out.println("[SkillRunes] EffectLib not found! Disabling particles");
		}
        // Test if WE and WG are active, test if one is active and not the other, etc
		if (this.testWE() == false && this.testWG() == false) {
			System.out.println("[SkillRunes] WorldEdit/WorldGuard not found! Disabling support");
			new PlayerListener(this);

		} else if (this.testWE() == true && this.testWG() == false) {
			System.out.println("[SkillRunes] WorldEdit found but no worldguard! You need both! Disabling support");
			new PlayerListener(this);
		} else if (this.testWG() == true && this.testWE() == false) {
			System.out.println("[SkillRunes] WorldGuard found but no WorldEdit! You need both! Disabling support");
			new PlayerListener(this);
		} else if (this.testWG() == true && this.testWE() == true) {
			new PlayerListenerWG(this);
			System.out.println(
					"[SkillRunes] Worldedit/WorldGuard Found! Enabling support! Make sure to flag your global region with SLEEP ALLOW if you want runes to work!");
		}

	}
    // Initialize the names that the playerlisteners check for
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
	public String alreadyactivemessage = this.getAlreadyActiveMessage();
	public String prefix = this.getPluginPrefix();
	public String usemessage = this.getUseMessage();
	public String nonregionmessage = this.getNonRegionMessage();
	public String disabledmessage = this.getDisabledMessage();
	public String again = this.getYouMayUseAgainMessage();

	
	
	//CONFIG HELP MENU
	public String helpline1 = this.coloredString("helplineone");
	public String helpline2 = this.coloredString("helplinetwo");
	public String helpline3 = this.coloredString("helplinethree");
	public String helpline4 = this.coloredString("helplinefour");
	public String helpline5 = this.coloredString("helplinefive");
	public String helpline6 = this.coloredString("helplinesix");
	public String helpline7 = this.coloredString("helplineseven");
	public String helpline8 = this.coloredString("helplineeight");
	public String helpline9 = this.coloredString("helplinenine");
	
	
	
	
	// METHODS BELOW FOR RUNE INITIALIZATION AND CONFIGURATION STRING/INT/BOOLEAN GETTERS!
	@Override
	public void onDisable() {
		System.out.println("[SkillRunes] Active hashmaps/lists have been cleared!");
        alreadyused.clear();
        nodmg.clear();
        nodmg1.clear();
        vampire.clear();
        explosions.clear();
        barrage.clear();
        explosivearrows.clear();
        molotov.clear();
        crippling.clear();
        lightning.clear();
        waterwalking.clear();
	}

	public String getUseMessage() {
		String i = "null";
		try {
			i = this.coloredString("consumemessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(
					"Configuration error! Please check your configuration for consumemessage, plugin has recovered");
			return ChatColor.RED + "As you use this mythical rune, it shatters into pieces.";
		}

	}
	public boolean hasHolo() {
		boolean i = false;
		if (this.getServer().getPluginManager().isPluginEnabled("HolographicDisplays")) {
			i = true;
			return i;
		} else {
			i = false;
			return i;
		}
	}
    public boolean isHoloEnabled(String string) {
    	boolean i = false;
    	try {
    		if (this.configBoolean("Runes."+string+".holograms")) {
    			i = true;
    			return i;
    		} else {
    			i = false;
    			return i;
    		}
    		
    	} catch (Exception e) {
    		i = false;
    		e.printStackTrace();
    		return i;
    	}
    	
    	
    	
    }
	public String getPluginPrefix() {
		String i = "null";
		try {
			i = this.coloredString("prefix");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(
					"Configuration error! please check your configuration for plugin prefix! Plugin has recovered!");
			return ChatColor.YELLOW + ChatColor.BOLD.toString() + "SkillRunes ";
		}
	}

	public String getDisabledMessage() {
		String i = "null";
		try {
			i = this.coloredString("disabledmessage");
			return i;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(
					"Configuration error! Please check your config file for disabledmessage, plugin has recovered");
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
			System.out.println(
					"Configuration error! please check your config file for alreadyactivemessage, plugin has recovered");
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
			System.out.println(
					"Configuration error! please check your config file for youmayuseagainmessage, plugin has recovered");
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
			System.out.println(
					"Configuration error! please check your config file for regionblockedmessage, plugin has recovered");
			return ChatColor.RED + "You may not use this rune in this region";
		}
	}

	public void loadConfiguration() {
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
	}

	public int configInt(String string) {
		try {
			int i = this.getConfig().getInt(string);
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config section" + string);
			return 20;
		}

	}

	public String coloredString(String string) {
		try {
			String message = this.getConfig().getString(string);
			String colored = ChatColor.translateAlternateColorCodes('&', message);
			return colored;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config section" + string);
			return "The server owner has some errors in his plugin configuration for SkillRunes! Please notify him/her";
		}
	}

	public boolean configBoolean(String string) {
		try {
			boolean i = this.getConfig().getBoolean(string);
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config section" + string);
			return true;
		}
	}

	public void giveRune(String rune, Player player) {
		try {
			ItemStack giverune = runes.get(rune.trim().toLowerCase());
			Inventory inventory = player.getInventory();
			inventory.addItem(giverune);
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error! The rune or player must be null!" + rune);
		}
	}

	public void runeOfSpeed() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			im.setDisplayName(this.coloredString("Runes.runeofspeed.name"));
			List<String> loreList = new ArrayList<String>();
			loreList.add(this.coloredString("Runes.runeofspeed.lore1"));// This
																		// is
																		// the
																		// first
																		// line
																		// of
																		// lore
			loreList.add(this.coloredString("Runes.runeofspeed.lore2"));// This
																		// is
																		// the
																		// second
																		// line
																		// of
																		// lore
			loreList.add(this.coloredString("Runes.runeofspeed.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofspeed", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading Rune of speed! Check your config?");
		}

	}

	public void runeOfBreathing() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			im.setDisplayName(this.coloredString("Runes.runeofbreathing.name"));
			List<String> loreList = new ArrayList<String>();
			loreList.add(this.coloredString("Runes.runeofbreathing.lore1"));// This
																			// is
																			// the
																			// first
																			// line
																			// of
																			// lore
			loreList.add(this.coloredString("Runes.runeofbreathing.lore2"));// This
																			// is
																			// the
																			// second
																			// line
																			// of
																			// lore
			loreList.add(this.coloredString("Runes.runeofbreathing.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofbreathing", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of breathing! Check your config?");
		}

	}

	public void runeOfStrength() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofstrength.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofstrength.lore1"));
			loreList.add(this.coloredString("Runes.runeofstrength.lore2"));
			loreList.add(this.coloredString("Runes.runeofstrength.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofstrength", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of strength! Check your config?");
		}

	}

	public void runeOfInvisibility() {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofinvisibility.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofinvisibility.lore1"));
			loreList.add(this.coloredString("Runes.runeofinvisibility.lore2"));
			loreList.add(this.coloredString("Runes.runeofinvisibility.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofinvisibility", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of invisibility! Check your config?");
		}

	}

	public void runeOfDestruction() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofdestruction.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofdestruction.lore1"));
			loreList.add(this.coloredString("Runes.runeofdestruction.lore2"));
			loreList.add(this.coloredString("Runes.runeofdestruction.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofdestruction", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of destruction! Check your config?");
		}

	}

	public void runeOfIncineration() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofincineration.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofincineration.lore1"));
			loreList.add(this.coloredString("Runes.runeofincineration.lore2"));
			loreList.add(this.coloredString("Runes.runeofincineration.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofincineration", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Incineration! Check your config?");
		}
	}

	public void runeOfLaunching() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeoflaunching.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeoflaunching.lore1"));
			loreList.add(this.coloredString("Runes.runeoflaunching.lore2"));
			loreList.add(this.coloredString("Runes.runeoflaunching.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeoflaunching", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Launching! Check your config?");
		}

	}

	public void runeOfSickening() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofsickening.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofsickening.lore1"));
			loreList.add(this.coloredString("Runes.runeofsickening.lore2"));
			loreList.add(this.coloredString("Runes.runeofsickening.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofsickening", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Sickening! Check your config?");
		}

	}

	public void runeOfHealing() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofhealing.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofhealing.lore1"));
			loreList.add(this.coloredString("Runes.runeofhealing.lore2"));
			loreList.add(this.coloredString("Runes.runeofhealing.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofhealing", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of healing! Check your config?");
		}

	}

	public void runeOfProtection() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofprotection.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofprotection.lore1"));
			loreList.add(this.coloredString("Runes.runeofprotection.lore2"));
			loreList.add(this.coloredString("Runes.runeofprotection.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofprotection", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of protection! Check your config?");
		}

	}

	public void runeOfBlinding() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofblinding.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofblinding.lore1"));
			loreList.add(this.coloredString("Runes.runeofblinding.lore2"));
			loreList.add(this.coloredString("Runes.runeofblinding.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofblinding", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Blinding! Check your config?");
		}

	}

	public void runeOfArrowAffinity() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofarrowaffinity.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore1"));
			loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore2"));
			loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore3"));
			loreList.add(this.coloredString("Runes.runeofarrowaffinity.lore4"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofarrowaffinity", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Arrow Affinity! Check your config?");
		}

	}

	public void runeOfClarity() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofclarity.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofclarity.lore1"));
			loreList.add(this.coloredString("Runes.runeofclarity.lore2"));
			loreList.add(this.coloredString("Runes.runeofclarity.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofclarity", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Clarity! Check your config?");
		}

	}

	public void runeOfWaterWalking() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofwaterwalking.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofwaterwalking.lore1"));
			loreList.add(this.coloredString("Runes.runeofwaterwalking.lore2"));
			loreList.add(this.coloredString("Runes.runeofwaterwalking.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofwaterwalking", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Water Walking! Check your config?");
		}
	}

	public void runeOfLightning() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeoflightning.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeoflightning.lore1"));
			loreList.add(this.coloredString("Runes.runeoflightning.lore2"));
			loreList.add(this.coloredString("Runes.runeoflightning.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeoflightning", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Lightning! Check your config?");
		}
	}

	public void runeOfExtremePower() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofextremepower.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofextremepower.lore1"));
			loreList.add(this.coloredString("Runes.runeofextremepower.lore2"));
			loreList.add(this.coloredString("Runes.runeofextremepower.lore3"));
			loreList.add(this.coloredString("Runes.runeofextremepower.lore4"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofextremepower", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Extreme Power! Check your config?");
		}

	}

	public void runeOfWither() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofwither.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofwither.lore1"));
			loreList.add(this.coloredString("Runes.runeofwither.lore2"));
			loreList.add(this.coloredString("Runes.runeofwither.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofwither", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Wither! Check your config?");
		}
	}

	public void runeOfVampirism() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofvampirism.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofvampirism.lore1"));
			loreList.add(this.coloredString("Runes.runeofvampirism.lore2"));
			loreList.add(this.coloredString("Runes.runeofvampirism.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofvampirism", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of vampirism! Check your config?");
		}
	}

	public void runeOfBarraging() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofbarraging.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofbarraging.lore1"));
			loreList.add(this.coloredString("Runes.runeofbarraging.lore2"));
			loreList.add(this.coloredString("Runes.runeofbarraging.lore2"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofbarraging", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of barraging! Check your config?");
		}
	}

	public void runeOfRegeneration() {
		;
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofregeneration.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofregeneration.lore1"));
			loreList.add(this.coloredString("Runes.runeofregeneration.lore2"));
			loreList.add(this.coloredString("Runes.runeofregeneration.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofregeneration", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of regeneration! Check your config?");
		}
	}

	public void runeOfVolatileArrows() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofvolatilearrows.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore1"));
			loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore2"));
			loreList.add(this.coloredString("Runes.runeofvolatilearrows.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofvolatilearrows", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of volatile arrows! Check your config?");
		}

	}

	public void runeOfThorns() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofthorns.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofthorns.lore1"));
			loreList.add(this.coloredString("Runes.runeofthorns.lore2"));
			loreList.add(this.coloredString("Runes.runeofthorns.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofthorns", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Thorns! Check your config?");
		}

	}

	public void runeOfRepair() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofrepair.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofrepair.lore1"));
			loreList.add(this.coloredString("Runes.runeofrepair.lore2"));
			loreList.add(this.coloredString("Runes.runeofrepair.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofrepair", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Repair! Check your config?");
		}

	}

	public void runeOfLeaping() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofleaping.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofleaping.lore1"));
			loreList.add(this.coloredString("Runes.runeofleaping.lore2"));
			loreList.add(this.coloredString("Runes.runeofleaping.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofleaping", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Leaping! Check your config?");
		}
	}

	public void runeOfFlying() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofflying.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofflying.lore1"));
			loreList.add(this.coloredString("Runes.runeofflying.lore2"));
			loreList.add(this.coloredString("Runes.runeofflying.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofflying", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Flying! Check your config?");
		}
	}

	public void runeOfFlamingArrows() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofflamingarrows.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofflamingarrows.lore1"));
			loreList.add(this.coloredString("Runes.runeofflamingarrows.lore2"));
			loreList.add(this.coloredString("Runes.runeofflamingarrows.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofflamingarrows", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Flaming Arrows! Check your config?");
		}
	}

	public void runeOfFlameThrowing() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofflamethrowing.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofflamethrowing.lore1"));
			loreList.add(this.coloredString("Runes.runeofflamethrowing.lore2"));
			loreList.add(this.coloredString("Runes.runeofflamethrowing.lore3"));
			loreList.add(this.coloredString("Runes.runeofflamethrowing.lore4"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofflamethrowing", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Flame Throwing! Check your config?");
		}
	}

	public void runeOfRepellant() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofrepellant.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofrepellant.lore1"));
			loreList.add(this.coloredString("Runes.runeofrepellant.lore2"));
			loreList.add(this.coloredString("Runes.runeofrepellant.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofrepellant", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Repellant! Check your config?");
		}
	}

	public void runeOfLightningArrows() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeoflightningarrows.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeoflightningarrows.lore1"));
			loreList.add(this.coloredString("Runes.runeoflightningarrows.lore2"));
			loreList.add(this.coloredString("Runes.runeoflightningarrows.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeoflightningarrows", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Lightning Arrows! Check your config?");
		}

	}

	public void runeOfPoisonousArrows() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();

			im.setDisplayName(this.coloredString("Runes.runeofpoisonarrows.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore1"));
			loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore2"));
			loreList.add(this.coloredString("Runes.runeofpoisonarrows.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofpoisonousarrows", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Poison Arrows! Check your config?");
		}
	}

	public void runeOfHaste() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();

			im.setDisplayName(this.coloredString("Runes.runeofhaste.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofhaste.lore1"));
			loreList.add(this.coloredString("Runes.runeofhaste.lore2"));
			loreList.add(this.coloredString("Runes.runeofhaste.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofhaste", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Haste! Check your config?");
		}
	}

	public void runeOfCrippling() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();

			im.setDisplayName(this.coloredString("Runes.runeofcrippling.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofcrippling.lore1"));
			loreList.add(this.coloredString("Runes.runeofcrippling.lore2"));
			loreList.add(this.coloredString("Runes.runeofcrippling.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofcrippling", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of Crippling! Check your config?");
		}
	}

	public void runeOfMinions() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();

			im.setDisplayName(this.coloredString("Runes.runeofminions.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofminions.lore1"));
			loreList.add(this.coloredString("Runes.runeofminions.lore2"));
			loreList.add(this.coloredString("Runes.runeofminions.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofminions", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of minions! Check your config?");
		}
	}

	public void runeOfParalyzing() {

		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = item.getItemMeta();
		try {
			List<String> loreList = new ArrayList<String>();
			im.setDisplayName(this.coloredString("Runes.runeofparalyzing.name"));
			loreList.clear();
			loreList.add(this.coloredString("Runes.runeofparalyzing.lore1"));
			loreList.add(this.coloredString("Runes.runeofparalyzing.lore2"));
			loreList.add(this.coloredString("Runes.runeofparalyzing.lore3"));
			im.setLore(loreList);
			item.setItemMeta(im);
			runes.put("runeofparalyzing", item);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error loading rune of paralyzing! Check your config?");
		}

	}

	public int getDelay(String string) {
		try {
			int i = this.configInt("Runes." + string + ".delay");
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config! Section Runes." + string + ".delay");
			return 10;
		}
	}

	public int getDuration(String string) {
		try {
			int i = this.configInt("Runes." + string + ".duration");
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config! Section Runes." + string + ".duration");
			return 15;
		}
	}

	public boolean isEnabled(String string) {
		try {
			boolean i = this.configBoolean("Runes." + string + ".enabled");
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config! section Runes." + string + ".enabled");
			return true;
		}
	}

	public int getAmplifier(String string) {
		try {
			int i = this.configInt("Runes." + string + ".amplifier");
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config! section Runes." + string + ".amplifier");
			return 0;
		}
	}

	public boolean particleson(String string) {
		try {
			boolean i = this.configBoolean("Runes." + string + ".particles");
			return i;
		} catch (Exception e) {
			System.out.println("[SkillRunes] Error in config! Section Runes." + string + ".particles");
			return true;
		}
	}

	public boolean hasParticles() {
		if (this.getServer().getPluginManager().isPluginEnabled("EffectLib")) {
			return true;
		} else {
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
		if (this.getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasWG() {
		if (this.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		} else {
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

}
