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
	if (alias.equals("sr") && args.length == 3 && args[0].equals("give") && args[1].equals("all")) {
		Player target = Bukkit.getPlayerExact(args[2]);
		if (!(target == null)) {
			give.runeOfSpeed(target);
			give.runeOfBarraging(target);
			give.runeOfBlinding(target);
			give.runeOfCrippling(target);
			give.runeOfDestruction(target);
			give.runeOfExtremePower(target);
			give.runeOfFireSpreading(target);
			give.runeOfFlameThrowing(target);
			give.runeOfFlying(target);
			give.runeOfHaste(target);
			give.runeOfHealing(target);
			give.runeOfIncineration(target);
			give.runeOfInvisibility(target);
			give.runeOfLaunching(target);
			give.runeOfLeaping(target);
			give.runeOfLightningArrows(target);
			give.runeOfMinions(target);
			give.runeOfParalyzing(target);
			give.runeOfPoisonousArrows(target);
			give.runeOfProtection(target);
			give.runeOfRegeneration(target);
			give.runeOfRepair(target);
			give.runeOfRepellant(target);
             give.runeOfSickening(target);
             give.runeOfStrength(target);
             give.runeOfThorns(target);
             give.runeOfVampirism(target);
             give.runeOfVolatileArrows(target);
             give.runeOfWither(target);
             give.runeOfArrowAffinity(target);
             give.runeOfClarity(player);
             give.runeOfWaterWalking(player);
		} else if (target == null) {
			player.sendMessage(ChatColor.RED + "That is not a valid target!");
		}
	
		
		} else if (alias.equals("sr") && args.length == 1 && args[0].equals("clearall")) {
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