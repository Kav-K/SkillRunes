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
			plugin.runeOfSpeed(player);
			plugin.runeOfBarraging(player);
			plugin.runeOfBlinding(player);
			plugin.runeOfCrippling(player);
			plugin.runeOfDestruction(player);
			plugin.runeOfExtremePower(player);
			plugin.runeOfFlamingArrows(player);
			plugin.runeOfFlameThrowing(player);
			plugin.runeOfFlying(player);
			plugin.runeOfHaste(player);
			plugin.runeOfHealing(player);
			plugin.runeOfIncineration(player);
			plugin.runeOfInvisibility(player);
			plugin.runeOfLaunching(player);
			plugin.runeOfLeaping(player);
			plugin.runeOfLightningArrows(player);
			plugin.runeOfMinions(player);
			plugin.runeOfParalyzing(player);
			plugin.runeOfPoisonousArrows(player);
			plugin.runeOfProtection(player);
			plugin.runeOfRegeneration(player);
			plugin.runeOfRepair(player);
			plugin.runeOfRepellant(player);
             plugin.runeOfSickening(player);
             plugin.runeOfLightning(player);
             plugin.runeOfStrength(player);
             plugin.runeOfThorns(player);
             plugin.runeOfVampirism(player);
             plugin.runeOfVolatileArrows(player);
             plugin.runeOfWither(player);
             plugin.runeOfArrowAffinity(player);
             plugin.runeOfClarity(player);
             plugin.runeOfWaterWalking(player);
             plugin.runeOfBreathing(player);
		} else if (target == null) {
			player.sendMessage(ChatColor.RED + "That is not a valid target!");
		}
	
		
		} else if (alias.equals("runes") && args.length == 1 && args[0].equals("clearall")) {
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
			player.sendMessage(plugin.runeofbaraging.toString());
		}
		
	     
	
		
		
		
		
		
		
		
		
		
		
		return true;
	}
}