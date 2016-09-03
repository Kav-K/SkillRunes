package me.kav.mythicalrunes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.AtomEffect;
import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.effect.EarthEffect;
import de.slikey.effectlib.effect.ShieldEffect;
import de.slikey.effectlib.effect.SmokeEffect;
import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.effect.WaveEffect;
import de.slikey.effectlib.util.ParticleEffect;

import org.bukkit.Color;
import org.bukkit.Effect;

public class PlayerListener implements Listener, hashmaps {
	Main plugin;

	public PlayerListener(Main passedPlugin) {
		this.plugin = passedPlugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}



	

	// INITIALIZE THE VARIABLES FOR THE BLOCKS HERE \\

	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onUse(PlayerInteractEvent event) {
		Action act = event.getAction();
		if (act == Action.RIGHT_CLICK_AIR) {


			EffectManager em = new EffectManager(plugin);
			Player player = event.getPlayer();
			PlayerInventory inventory = player.getInventory();
			if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofdestruction)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.DARK_RED + "AS you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					Location location = player.getEyeLocation();
					nodmg.put(player, player);
					TNTPrimed tnt = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
					Vector v = location.getDirection().multiply(0.1);
					tnt.setVelocity(v);
					tnt.setFuseTicks(3);
					TNTPrimed tnt2 = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
					Vector v2 = location.getDirection().multiply(0.1);
					tnt2.setVelocity(v2);
					tnt2.setFuseTicks(3);
					TNTPrimed tnt3 = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
					Vector v3 = location.getDirection().multiply(0.1);
					tnt3.setVelocity(v3);
					tnt3.setFuseTicks(3);
					alreadyused.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 40);
					new BukkitRunnable() {

						@Override
						public void run() {
							nodmg.remove(player, player);

						}
					}.runTaskLater(this.plugin, 40);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}

			}else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofclarity)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.GREEN + "AS you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					WaveEffect smokeEffect = new WaveEffect(em);
					smokeEffect.setEntity(player);

					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations =2; // there is an
												smokeEffect.particle = ParticleEffect.SPELL_MOB;	// effect here
					smokeEffect.color = Color.WHITE;
					smokeEffect.start();
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.HARM);
					player.removePotionEffect(PotionEffectType.WEAKNESS);
					player.removePotionEffect(PotionEffectType.WITHER);
					player.removePotionEffect(PotionEffectType.POISON);
					player.removePotionEffect(PotionEffectType.BLINDNESS);
					player.removePotionEffect(PotionEffectType.CONFUSION);
					player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					player.removePotionEffect(PotionEffectType.HUNGER);
					player.sendMessage(ChatColor.GREEN + "You have been cleansed!");
					
					
					

					new BukkitRunnable() {

						@Override
						public void run() {

							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 100);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			}  
			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofparalyze)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.YELLOW + "AS you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					for (Entity e : player.getNearbyEntities(4, 256, 4)) {
						if (e instanceof Player) {
							WarpEffect smokeEffect = new WarpEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);

							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 20 * 20; // there is an
																// effect here
							smokeEffect.color = Color.AQUA;
							smokeEffect.start();
							 
							found.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
							found.sendMessage(ChatColor.AQUA + "You have been Paralyzed by " + ChatColor.DARK_BLUE
									+ player.getName());
							player.sendMessage(
									
									ChatColor.DARK_BLUE + "You have Paralyzed " + ChatColor.AQUA + found.getName());
						}
						
					}

					new BukkitRunnable() {

						@Override
						public void run() {

							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 100);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} 
			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeoflightningarrows)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.YELLOW + "AS you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					lightning.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							lightning.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 300);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}

			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofpoisonousarrows)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(
							ChatColor.DARK_PURPLE + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					poison.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							poison.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 300);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofcrippling)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(
							ChatColor.DARK_PURPLE + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					crippling.put(player, player);
				}
				new BukkitRunnable() {
					public void run() {
						player.sendMessage(ChatColor.RED + "The effects of the rune of crippling have run out");
						crippling.remove(player, player);
						alreadyused.remove(player, player);
					}
				}.runTaskLater(this.plugin, 300);
			}
			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofrepellant)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.GOLD + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					ShieldEffect bleedEffect = new ShieldEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 20 * 20;
					bleedEffect.particle = ParticleEffect.SPELL_MOB;
					bleedEffect.color = Color.ORANGE;
					bleedEffect.start();
					Player repeller = player;

					Integer task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin,
							new Runnable() {

								@Override
								public void run() {
									List<Entity> entities = player.getNearbyEntities(2, 0, 2);
									for (Entity e : entities) {
										if (e.getType().isAlive()) {
											Vector dir = e.getLocation().getDirection();
											Vector vec = new Vector(dir.getX() * -1.5D, 1D, dir.getZ() * -1.5D);
											e.setVelocity(vec);
											if (e instanceof Player) {
												Player pp = (Player) e;
												pp.sendMessage(ChatColor.GOLD + "You have been repelled by "
														+ ChatColor.YELLOW + repeller);
												pp.setFallDistance(-100.0F);
											}
										}
									}
								}

							}, 10L, 5L);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
							bleedEffect.cancel();
							Bukkit.getServer().getScheduler().cancelTask(task1);

						}
					}.runTaskLater(this.plugin, 300);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofflamethrowing)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					fireball.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							fireball.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeoffirespreading)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					molotov.put(player, player);
					ConeEffect smokeEffect = new ConeEffect(em);
					smokeEffect.setEntity(player);

					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations = 20 * 20; // there is an

					smokeEffect.start();

					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							molotov.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
							smokeEffect.cancel();

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofincineration)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							SmokeEffect smokeEffect = new SmokeEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);

							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 20 * 20; // there is an
																// effect here
							smokeEffect.color = Color.AQUA;
							smokeEffect.start();
							found.setFireTicks(160);
							found.sendMessage(ChatColor.DARK_RED + "You have been incinerated by " + ChatColor.RED
									+ player.getName());
							player.sendMessage(
									ChatColor.DARK_RED + "You have incinerated " + ChatColor.RED + found.getName());
						}
					}
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofspeed)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					WarpEffect bleedEffect = new WarpEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 20 * 20;
					bleedEffect.particle = ParticleEffect.SPELL_MOB;
					bleedEffect.color = Color.AQUA;
					bleedEffect.start();
					player.sendMessage(ChatColor.AQUA + "As you use this mythical rune, it shatters into pieces");
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 400);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofstrength)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					WarpEffect bleedEffect = new WarpEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 20 * 20;
					bleedEffect.particle = ParticleEffect.SPELL_MOB;
					bleedEffect.color = Color.RED;
					bleedEffect.start();
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 400);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofrepair)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.AQUA + "As you use this mythical rune, it shatters into pieces.");
					try {
						ItemStack a1 = player.getInventory().getBoots();
						a1.setDurability((short) 0);
						player.sendMessage(ChatColor.GREEN + "Your boots were repaired!");
					} catch (Exception exeption) {
						player.sendMessage(ChatColor.RED + "You weren't wearing any boots!");
					}
					try {
						ItemStack a2 = player.getInventory().getLeggings();
						a2.setDurability((short) 0);
						player.sendMessage(ChatColor.GREEN + "Your leggings were repaired!");
					} catch (Exception exeption) {
						player.sendMessage(ChatColor.RED + "You weren't wearing any boots!");
					}
					try {
						ItemStack a3 = player.getInventory().getChestplate();
						a3.setDurability((short) 0);
						player.sendMessage(ChatColor.GREEN + "Your chestplate was repaired!");
					} catch (Exception exception) {
						player.sendMessage(ChatColor.RED + "You weren't wearing any boots!");
					}
					try {
						ItemStack a4 = player.getInventory().getHelmet();
						a4.setDurability((short) 0);
						player.sendMessage(ChatColor.GREEN + "Your helmet was repaired!");
					} catch (Exception exception) {
						player.sendMessage(ChatColor.RED + "You weren't wearing any boots!");
					}

					player.updateInventory();
					player.sendMessage(ChatColor.GREEN + "You armor has been repaired to full durability!");

					// repair

					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 40);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofminions)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.sendMessage(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "As you use this mythical rune, it shatters into pieces.");
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							WarpEffect smokeEffect = new WarpEffect(em);
							Player found = (Player) e;
							Creature z = (Creature) e.getWorld().spawnEntity(e.getLocation(), EntityType.IRON_GOLEM);
							z.setTarget((LivingEntity) e);
							z.damage(5);
							Integer task2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin,
									new Runnable() {

										@Override
										public void run() {
											z.setTarget((LivingEntity) e);
													}
												
											
										

									}, 10L, 5L);
							new BukkitRunnable() {
								public void run() {
									z.remove();
									Bukkit.getServer().getScheduler().cancelTask(task2);
								}
							}.runTaskLater(this.plugin, 600);
							new BukkitRunnable() {
								public void run() {
									alreadyused.remove(player, player);
								}
							}.runTaskLater(this.plugin, 600);
						}
					}
					
				}
			}
			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofflying)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.AQUA + "As you use this mythical rune, it shatters into pieces.");
					player.setAllowFlight(true);
					player.setFlying(true);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);

							player.setFlying(false);
							player.setAllowFlight(false);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
							player.sendMessage(ChatColor.RED + "The effects of the rune of flying have run out");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofinvis)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.AQUA + "As you use this mythical rune, it shatters into pieces.");
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 0));
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 400);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofvampirism)) {
				if (!(alreadyused.containsKey(player))) {
					alreadyused.put(player, player);
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					vampire.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							vampire.remove(player, player);
							player.sendMessage(ChatColor.DARK_PURPLE + "The effects of Vampirism have run out.");

						}
					}.runTaskLater(this.plugin, 200);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofleaping)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					WarpEffect bleedEffect = new WarpEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 20 * 20;
					bleedEffect.particle = ParticleEffect.SPELL_MOB;
					bleedEffect.color = Color.BLUE;
					bleedEffect.start();
					player.sendMessage(ChatColor.BLUE + "As you use this mythical rune, it shatters into pieces");
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 2));
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 600);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofhealing)) {
				if (!(alreadyused.containsKey(player))) {
					alreadyused.put(player, player);
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					WarpEffect smokeEffect = new WarpEffect(em);
					smokeEffect.setEntity(player);

					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations = 1 * 20;
					// there is an effect here
					smokeEffect.particle = ParticleEffect.HEART;
					smokeEffect.start();
					player.setHealth(20);
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 40);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofthorns)) {
				if (!(alreadyused.containsKey(player))) {
					alreadyused.put(player, player);
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					ShieldEffect smokeEffect = new ShieldEffect(em);
					smokeEffect.setEntity(player);

					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations = 1 * 20;
					// there is an effect here
					smokeEffect.particle = ParticleEffect.SPELL_MOB;
					smokeEffect.color = Color.GREEN;
					smokeEffect.start();
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					thorns.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							thorns.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			}  else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofbreathing)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.GRAY + "As you use this mythical rune, it shatters into pieces");
					player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 400, 0));
					WarpEffect smokeEffect = new WarpEffect(em);
					smokeEffect.setEntity(player);

					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations = 10 * 20;
					smokeEffect.particle = ParticleEffect.SPELL_MOB;
					smokeEffect.color = Color.AQUA;
					smokeEffect.start();
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 400);

				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofprotection)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					Location location = player.getLocation();
					World world = location.getWorld();
					player.sendMessage(ChatColor.GOLD + "As you use this mythical rune, it shatters into pieces.");
					nodmg1.put(player, player);
					EarthEffect bleedEffect = new EarthEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 2 * 20;
					bleedEffect.color = Color.ORANGE;
					bleedEffect.start();
					alreadyused.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							nodmg1.remove(player, player);
							player.sendMessage(ChatColor.GOLD + "The effects of the rune of protection have run out.");

						}
					}.runTaskLater(this.plugin, 200);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofpoison)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(
							ChatColor.DARK_PURPLE + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							WarpEffect smokeEffect = new WarpEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);

							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 20 * 20;
							// there is an effect here
							smokeEffect.particle = ParticleEffect.SPELL_MOB;
							smokeEffect.color = Color.PURPLE;
							smokeEffect.start();
							found.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
							player.sendMessage(ChatColor.DARK_PURPLE + "You have sickened " + ChatColor.LIGHT_PURPLE
									+ found.getName());
							found.sendMessage(ChatColor.DARK_PURPLE + "You have been sickened by "
									+ ChatColor.LIGHT_PURPLE + player.getName());
						}
					}
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 400);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofillumination)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					alreadyused.put(player, player);
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.YELLOW + "As you use this mythical rune, it shatters into pieces.");
					Location location = player.getLocation();
					AtomEffect bleedEffect = new AtomEffect(em);
					bleedEffect.setEntity(event.getPlayer());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 3 * 20;
					bleedEffect.color = Color.AQUA;
					bleedEffect.start();

					player.sendMessage("Prepare to be launched..");
					new BukkitRunnable() {

						@Override
						public void run() {

							player.setVelocity(new Vector(0, 10, 0));
							player.setFallDistance(-100.0f);

						}
					}.runTaskLater(this.plugin, 60);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
					new BukkitRunnable() {

						@Override
						public void run() {
							nodmg1.remove(player, player);

						}
					}.runTaskLater(this.plugin, 120);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			}  else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofblinding)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.BLACK + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							WarpEffect smokeEffect = new WarpEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);
							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 5;
							// there is an effect here
							smokeEffect.particle = ParticleEffect.SPELL_MOB;
							smokeEffect.color = Color.BLACK;
							smokeEffect.start();
							found.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
							player.sendMessage(
									ChatColor.BLACK + "You have blinded " + ChatColor.GRAY + found.getName());
							found.sendMessage(
									ChatColor.BLACK + "You have been blinded by " + ChatColor.GRAY + player.getName());
						}

					}
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 100);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeoflightning)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.YELLOW + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							WaveEffect smokeEffect = new WaveEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);
							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 3;
							// there is an effect here
							smokeEffect.particle = ParticleEffect.SPELL_MOB;
							smokeEffect.color = Color.YELLOW;
							smokeEffect.start();
							World world = player.getWorld();
							world.strikeLightning(found.getLocation());
							player.sendMessage(
									ChatColor.YELLOW + "You have struck " + ChatColor.GOLD + found.getName());
							found.sendMessage(
									ChatColor.YELLOW + "You have been struck by " + ChatColor.GOLD + player.getName());
						}
					}
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 60);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofwaterwalking)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.AQUA + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					waterwalking.put(player, player);
					new BukkitRunnable() {
						public void run() {
							alreadyused.remove(player, player);
							waterwalking.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
						}
					}.runTaskLater(this.plugin, 600);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} 
			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofarrowaffinity)) {
				if (!(alreadyused.containsKey(player))) {

					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.YELLOW + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					arrows.put(player, player);
					new BukkitRunnable() {
						public void run() {
							alreadyused.remove(player, player);
							arrows.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
						}
					}.runTaskLater(this.plugin, 300);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			}
			 else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofbaraging)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.GREEN + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					barrage.put(player, player);
				}
				new BukkitRunnable() {

					@Override
					public void run() {
						alreadyused.remove(player, player);
						player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

					}
				}.runTaskLater(this.plugin, 400);
				new BukkitRunnable() {

					@Override
					public void run() {
						barrage.remove(player, player);
						player.sendMessage(ChatColor.RED + "The effects of barrage have worn off..");

					}
				}.runTaskLater(this.plugin, 400);

			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofextremepower)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(
							ChatColor.RED + ChatColor.BOLD.toString() + "You have been gifted with extreme power.. ");
					alreadyused.put(player, player);
					explosions.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							explosions.remove(player, player);

						}
					}.runTaskLater(this.plugin, 200);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 220);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofregeneration)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					SmokeEffect smokeEffect = new SmokeEffect(em);
					smokeEffect.setEntity(player);
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					smokeEffect.iterations = 6 * 20;
					// there is an effect here
					smokeEffect.particle = ParticleEffect.HEART;
					smokeEffect.start();
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);

				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofvolatilearrows)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					explosivearrows.put(player, player);
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							explosivearrows.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
							player.sendMessage(ChatColor.RED + "The effects of volatile arrows have rune out..");

						}
					}.runTaskLater(this.plugin, 200);

				}
			} else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofhaste)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.sendMessage(ChatColor.RED + "As you use this mythical rune, it shatters into pieces.");
					alreadyused.put(player, player);
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 400, 1));
					new BukkitRunnable() {
						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");
							
						}
					}.runTaskLater(this.plugin, 400);
				}
			}

			else if (player.getItemInHand().getItemMeta().getDisplayName().equals(plugin.runeofwither)) {
				if (!(alreadyused.containsKey(player))) {
					if (player.getInventory().getItemInHand().getAmount() == 1) {
						inventory.removeItem(player.getInventory().getItemInHand());
					}
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					alreadyused.put(player, player);
					player.sendMessage(ChatColor.DARK_GRAY + ChatColor.BOLD.toString()
							+ "As you use this mythical rune it shatters into pieces.");
					for (Entity e : player.getNearbyEntities(5, 256, 5)) {
						if (e instanceof Player) {
							WaveEffect smokeEffect = new WaveEffect(em);
							Player found = (Player) e;
							smokeEffect.setEntity(found);
							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							smokeEffect.iterations = 5;
							// there is an effect here
							smokeEffect.particle = ParticleEffect.SPELL_MOB;
							smokeEffect.color = Color.BLACK;
							smokeEffect.start();
							found.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 0));
							player.sendMessage(
									ChatColor.BLACK + "You have blinded " + ChatColor.GRAY + found.getName());
							found.sendMessage(
									ChatColor.BLACK + "You have been blinded by " + ChatColor.GRAY + player.getName());
						}

					}
					new BukkitRunnable() {

						@Override
						public void run() {
							alreadyused.remove(player, player);
							player.sendMessage(ChatColor.GREEN + "You may use a rune again!");

						}
					}.runTaskLater(this.plugin, 200);
				} else {
					player.sendMessage(ChatColor.RED + "You already have a rune active!");
				}
			}
		} else if (act == Action.RIGHT_CLICK_AIR || act == Action.RIGHT_CLICK_BLOCK || act == Action.LEFT_CLICK_AIR
				|| act == Action.LEFT_CLICK_BLOCK) {

			Player player = event.getPlayer();
			if (player.getInventory().getItemInHand().getType() == Material.AIR) {
				if (fireball.containsKey(player)) {
					player.launchProjectile(Fireball.class);

				}
			} if (player.getInventory().getItemInHand().getType() == Material.AIR) {
				if (arrows.containsKey(player)) {
					player.launchProjectile(Arrow.class);
				}
			}
		}
	}

	@EventHandler
	public void entityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player player = (Player) entity;

			if (nodmg.containsKey(player)) {

				event.setCancelled(true);

			}
			if (nodmg1.containsKey(player)) {

				event.setCancelled(true);

			}
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMove(PlayerMoveEvent e) {
	 HashMap<Player, Block> blocks = new HashMap<Player, Block>();
	 if (waterwalking.containsKey(e.getPlayer())) {
		 Location block = e.getPlayer().getLocation().add(0, -1, 0);
		 if (block.getBlock().getType() == Material.WATER || block.getBlock().getType() == Material.STATIONARY_WATER) {
			 blocks.put(e.getPlayer(), block.getBlock());
			 block.getBlock().setType(Material.GLASS);
			 new BukkitRunnable() {
				 public void run() {
					 block.getBlock().setType(Material.WATER);
				 }
			 }.runTaskLater(this.plugin, 60);
		 }
	 }
	 


		
		
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {

		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if (explosions.containsKey(p)) {
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2f, false);

				World world = event.getEntity().getWorld();
				world.strikeLightning(event.getEntity().getLocation());

			}
			if (vampire.containsKey(p)) {
				Damageable d = (Damageable) p;
				double health = d.getHealth();
				double addhealth = health + 1;
				p.setHealth(addhealth);
			}
			if (nodmg.containsKey(p)) {
				event.setCancelled(true);
			}
			if (nodmg1.containsKey(p)) {
				event.setCancelled(true);
			}
			if (event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
				if (thorns.containsKey(player)) {
					Player damager = (Player) event.getDamager();
					Damageable d = (Damageable) damager;
					d.setHealth(d.getHealth() - 1);
					damager.sendMessage(
							ChatColor.DARK_PURPLE + "You have been injured due to your opponent's active thorns rune!");

				} else if (crippling.containsKey(player)) {
					Player damager = (Player) event.getDamager();
					damager.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 0));
				}
			}
		}
	}

	@EventHandler
	public void barrage(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (barrage.containsKey(player)) {
				Entity mainArrow = event.getProjectile();

				for (int i = 1; i < 4; i++) {
					mainArrow.getWorld().spawnArrow(mainArrow.getLocation(), mainArrow.getVelocity(), 3, 3)
							.setShooter(event.getEntity());

					mainArrow.getWorld().playEffect(mainArrow.getLocation(), org.bukkit.Effect.LARGE_SMOKE, 0);
				}
			} else {
				String i = "placeholder";
			}
		} else {
			String i = "placeholder";
		}
	}

	@EventHandler
	public void explosive(ProjectileHitEvent event) {
		Entity entity = event.getEntity();
		if ((entity instanceof Arrow)) {
			Arrow arrow = (Arrow) entity;
			Entity shooter = arrow.getShooter();
			if ((shooter instanceof Player)) {
				Player player = (Player) shooter;

				if (explosivearrows.containsKey(player)) {

					arrow.getWorld().playEffect(arrow.getLocation(), org.bukkit.Effect.LARGE_SMOKE, 0);
					TNTPrimed tnt = (TNTPrimed) player.getWorld().spawn(arrow.getLocation(), TNTPrimed.class);
					Vector v = arrow.getLocation().getDirection().multiply(0.1);
					tnt.setVelocity(v);
					tnt.setFuseTicks(3);
					arrow.remove();

				} else if (molotov.containsKey(player)) {
					final int RADIUS = 2;
					Location center = arrow.getLocation();
					final List<Block> burn = new ArrayList<Block>();
					int posX = arrow.getLocation().getBlockX();
					int posY = arrow.getLocation().getBlockY();
					int posZ = arrow.getLocation().getBlockZ();
					int x = arrow.getLocation().getBlockX();
					int y = arrow.getLocation().getBlockY();
					int z = arrow.getLocation().getBlockZ();
					for (x = -RADIUS; x <= RADIUS; x++) {
						for (z = -RADIUS; z <= RADIUS; z++) {
							Block block = event.getEntity().getWorld().getBlockAt(posX + x, y, posZ + z);
							if (block.getType() != Material.AIR)
								continue;
							block.setType(Material.FIRE);
							burn.add(block);

						}
					}

					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

						@Override
						public void run() {
							for (Block block : burn)
								if (block.getType() == Material.FIRE)
									block.setType(Material.AIR);
						}

					}, 60L);
				} else if (lightning.containsKey(player)) {
					World world = arrow.getWorld();
					world.strikeLightning(arrow.getLocation());
				} else if (poison.containsKey(player)) {
					EffectManager em = new EffectManager(plugin);
					ShieldEffect bleedEffect = new ShieldEffect(em);
					bleedEffect.setLocation(arrow.getLocation());
					// Bleeding takes 15 seconds
					// period * iterations = time of effect
					bleedEffect.iterations = 4;
					bleedEffect.particle = ParticleEffect.SPELL_MOB;
					bleedEffect.color = Color.PURPLE;
					bleedEffect.start();
					List<Entity> entities = arrow.getNearbyEntities(2, 0, 2);
					for (Entity e : entities) {
						if (e.getType().isAlive()) {
							if (e instanceof Player) {

								((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
							}
						}
					}

				}
			}
		}
	}

}
