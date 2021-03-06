package me.kav.skillrunes;

import java.io.IOException;
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
import org.bukkit.entity.Projectile;
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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

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

public class PlayerListener implements Listener, Caching {
	Main plugin;

	public PlayerListener(Main passedPlugin) {
		this.plugin = passedPlugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	// get string variables

	@EventHandler(priority = EventPriority.HIGH)
	public void onUse(PlayerInteractEvent event) {
		int explodeticks = plugin.configInt("Runes.runeofdestruction.explodeticks");
		Action act = event.getAction();
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
        if (alreadyused1.get(player.getName().toString()) == null) {
       	 alreadyused1.put(player.getName().toString(), 0);
        }
		int usedint = alreadyused1.get(player.getName().toString());
		int maxrunes = plugin.configInt("maxrunes");
		
		if (!(player == null)) {
			if (usedint >= maxrunes && player.getItemInHand().getType() == Material.NETHER_STAR) { 
				player.sendMessage(plugin.prefix + " " + plugin.alreadyactivemessage);
			} else {

			try {

				if (act == Action.RIGHT_CLICK_AIR || act == Action.RIGHT_CLICK_BLOCK) {
					if (player.getItemInHand().getType() == Material.NETHER_STAR
							&& player.getItemInHand().hasItemMeta()) {
                            
						
                             
						if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofdestruction)) {

							if (plugin.isEnabled("runeofdestruction")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								Location location = player.getEyeLocation();
								if (plugin.configBoolean("Runes.runeofdestruction.selfdamage") == false) {
									nodmg.add(player.getName().toString());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);

								TNTPrimed tnt = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
								Vector v = location.getDirection().multiply(0.1);
								tnt.setVelocity(v);
								tnt.setFuseTicks(explodeticks);
								TNTPrimed tnt2 = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
								Vector v2 = location.getDirection().multiply(0.1);
								tnt2.setVelocity(v2);
								tnt2.setFuseTicks(explodeticks);
								TNTPrimed tnt3 = (TNTPrimed) player.getWorld().spawn(location, TNTPrimed.class);
								Vector v3 = location.getDirection().multiply(0.1);
								tnt3.setVelocity(v3);
								tnt3.setFuseTicks(explodeticks);

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);
										if (plugin
												.configBoolean("Runes.runeofdestruction.selfdamage") == false) {
											try {
												nodmg.remove(player.getName().toString());
											} catch (Exception e) {
												nodmg.clear();
											}
										}

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofdestruction") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.nonregionmessage);
							}

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofclarity)) {
							if (plugin.isEnabled("runeofclarity")) {
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.getPluginPrefix() + " " + plugin.getUseMessage());
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);

								if (plugin.particleson("runeofclarity")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WaveEffect smokeEffect = new WaveEffect(em);
										smokeEffect.setEntity(player);

										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										smokeEffect.iterations = 4; // there
																	// is
																	// an
										smokeEffect.particle = ParticleEffect.SPELL_MOB; // effect
																							// here
										smokeEffect.color = Color.WHITE;
										smokeEffect.start();
									}
								}
								player.removePotionEffect(PotionEffectType.SLOW);
								player.removePotionEffect(PotionEffectType.HARM);
								player.removePotionEffect(PotionEffectType.WEAKNESS);
								player.removePotionEffect(PotionEffectType.WITHER);
								player.removePotionEffect(PotionEffectType.POISON);
								player.removePotionEffect(PotionEffectType.BLINDNESS);
								player.removePotionEffect(PotionEffectType.CONFUSION);
								player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								player.removePotionEffect(PotionEffectType.HUNGER);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.sendMessage(plugin.prefix + " "
										+ plugin.coloredString("Runes.runeofclarity.cleansingmessage"));

								new BukkitRunnable() {

									@Override
									public void run() {

										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofclarity") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofparalyze)) {
							if (plugin.isEnabled("runeofparalyzing")) {
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.getPluginPrefix() + " " + plugin.getUseMessage());
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);

								for (Entity e : player.getNearbyEntities(4, 256, 4)) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasHolo() && plugin.isHoloEnabled("runeofparalyzing")) {
											try {
												// TODO Copy this over
												// to everywhere else!
												final Hologram hologram = HologramsAPI.createHologram(plugin,
														found.getLocation().add(0.0, 2.0, 0.0));
												hologram.appendTextLine(ChatColor.BLUE
														+ ChatColor.BOLD.toString() + "Paralyzed");
												new BukkitRunnable() {
													int ticksRun;

													@Override
													public void run() {
														ticksRun++;
														hologram.teleport(
																found.getLocation().add(0.0, 2.0, 0.0));
														if (ticksRun > plugin.getDuration("runeofparalyzing")
																* 20) {
															hologram.delete();
															cancel();
														}
													}
												}.runTaskTimer(plugin, 1L, 1L);
											} catch (Exception e1) {
												e1.printStackTrace();
												System.out.println(
														"[SkillRunes] Unexpected error! Please report this to the developer");

											}
										}
										if (plugin.hasParticles()) {
											EffectManager em = new EffectManager(plugin);
											WarpEffect smokeEffect = new WarpEffect(em);
											if (plugin.particleson("runeofparalyzing")) {
												smokeEffect.setEntity(found);

												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 100 * 100; // there
																					// is
																					// an
																					// effect
																					// here
												smokeEffect.color = Color.AQUA;
												smokeEffect.start();
												new BukkitRunnable() {
													@Override
													public void run() {
														smokeEffect.cancel();
													}
												}.runTaskLater(this.plugin,
														plugin.getDuration("runeofparalyzing") * 20);
											}
										}

										found.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
												plugin.getDuration("runeofparalyzing") * 20, 10));
										found.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofparalyzing.paralyzemessage"));
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofparalyzing.paralyzermessage"));
									}

								}

								new BukkitRunnable() {

									@Override
									public void run() {

										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " "
												+ plugin.coloredString("Runes.runeofparalyzing.expiremessage"));
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofparalyzing") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeoflightningarrows)) {
							if (plugin.isEnabled("runeoflightningarrows")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.getPluginPrefix() + " " + plugin.getUseMessage());
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								lightning.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeoflightningarrows")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeoflightningarrows);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeoflightningarrows")
														* 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										try {
											lightning.remove(player.getName().toString());
											cancel();
										} catch (Exception e) {

										}
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeoflightningarrows") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {

										player.sendMessage(plugin.prefix + " " + plugin.again);
										cancel();

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeoflightningarrows") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofpoisonousarrows)) {
							if (plugin.isEnabled("runeofpoisonarrows")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								poison.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofpoisonarrows")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofpoisonousarrows);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofpoisonarrows") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										try {
											poison.remove(player.getName().toString());
											player.sendMessage(plugin.prefix + " " + plugin
													.coloredString("Runes.runeofpoisonarrows.expiremessage"));
											cancel();
										} catch (Exception e) {

										}

									}
								}.runTaskLater(this.plugin,
										plugin.configInt("Runes.runeofpoisonarrows.abilityduration") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);

										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofpoisonarrows"));

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofcrippling)) {
							if (plugin.isEnabled("runeofcrippling")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								crippling.add(player.getName().toString());
							}
							new BukkitRunnable() {
								public void run() {
									player.sendMessage(plugin.prefix + " " + plugin.again);
									alreadyused1.put(player.getName().toString(),
											alreadyused1.get(player.getName().toString()) - 1);
								}
							}.runTaskLater(this.plugin, plugin.getDelay("runeofcrippling") * 20);
							new BukkitRunnable() {
								public void run() {
									player.sendMessage(plugin.prefix + " "
											+ plugin.coloredString("Runes.runeofcrippling.expiremessage"));
									try {
										crippling.remove(player.getName().toString());
									} catch (Exception e) {

									}

								}
							}.runTaskLater(this.plugin,
									plugin.configInt("Runes.runeofcrippling.abilityduration") * 20);

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofrepellant)) {
							int radius = plugin.configInt("Runes.runeofrepellant.radius");
							if (plugin.isEnabled("runeofrepellant")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofrepellant")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofrepellant);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofrepellant") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofrepellant")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										ShieldEffect bleedEffect = new ShieldEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;
										bleedEffect.particle = ParticleEffect.SPELL_MOB;
										bleedEffect.color = Color.ORANGE;
										bleedEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												bleedEffect.cancel();
											}
										}.runTaskLater(this.plugin, plugin.getDuration("runeofrepellant") * 20);
									}
								}
								Player repeller = player;

								Integer task1 = Bukkit.getServer().getScheduler()
										.scheduleSyncRepeatingTask(this.plugin, new Runnable() {

											@Override
											public void run() {
												List<Entity> entities = player.getNearbyEntities(radius, radius,
														radius);
												for (Entity e : entities) {
													if (e.getType().isAlive()) {
														Vector dir = e.getLocation().getDirection();
														Vector vec = new Vector(dir.getX() * -1.5D, 1D,
																dir.getZ() * -1.5D);
														e.setVelocity(vec);
														if (e instanceof Player) {
															Player pp = (Player) e;
															pp.sendMessage(
																	plugin.prefix + " " + plugin.coloredString(
																			"Runes.runeofrepellant.repelmessage"));
															pp.setFallDistance(-100.0F);
														}
													}
												}
											}

										}, 10L, 5L);
								new BukkitRunnable() {
									@Override
									public void run() {
										player.sendMessage(plugin.prefix + " "
												+ plugin.coloredString("Runes.runeofrepellant.expiremessage"));
										Bukkit.getServer().getScheduler().cancelTask(task1);

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofrepellant") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofrepellant") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofflamethrowing)) {
							if (plugin.isEnabled("runeofflamethrowing")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								fireball.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofflamethrowing")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofflamethrowing);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofflamethrowing") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										fireball.remove(player.getName().toString());
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofflamethrowing.expiremessage"));
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofflamethrowing") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);

										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofflamethrowing") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeoffirespreading)) {
							if (plugin.isEnabled("runeofflamingarrows")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								molotov.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofflamingarrows")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeoffirespreading);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofflamingarrows") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofflamingarrows")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										ConeEffect smokeEffect = new ConeEffect(em);
										smokeEffect.setEntity(player);

										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										smokeEffect.iterations = 20 * 20; // there
																			// is
																			// an

										smokeEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												smokeEffect.cancel();
											}
										}.runTaskLater(this.plugin,
												plugin.getDuration("runeofflamingarrows") * 20);
									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										try {
											molotov.remove(player.getName().toString());
										} catch (Exception e) {

										}
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofflamingarrows.expiremessage"));
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofflamingarrows") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofflamingarrows") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofincineration)) {
							int firetime = plugin.configInt("Runes.runeofincineration.firetime") * 20;
							int radius = plugin.configInt("Runes.runeofincineration.radius");
							if (plugin.isEnabled("runeofincineration")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) - 1);

								for (Entity e : player.getNearbyEntities(radius, 256, radius)) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasHolo() && plugin.isHoloEnabled("runeofincineration")) {
											try {
												// TODO Copy this over
												// to everywhere else!
												final Hologram hologram = HologramsAPI.createHologram(plugin,
														found.getLocation().add(0.0, 2.0, 0.0));
												hologram.appendTextLine(ChatColor.RED
														+ ChatColor.BOLD.toString() + "Incinerated");
												new BukkitRunnable() {
													int ticksRun;

													@Override
													public void run() {
														ticksRun++;
														hologram.teleport(
																found.getLocation().add(0.0, 2.0, 0.0));
														if (ticksRun > plugin.configInt(
																"Runes.runeofincineration.firetime") * 20) {
															hologram.delete();
															cancel();
														}
													}
												}.runTaskTimer(plugin, 1L, 1L);
											} catch (Exception e1) {
												e1.printStackTrace();
												System.out.println(
														"[SkillRunes] Unexpected error! Please report this to the developer");

											}
										}
										if (plugin.particleson("runeofincineration")) {
											if (plugin.hasParticles()) {
												EffectManager em = new EffectManager(plugin);
												SmokeEffect smokeEffect = new SmokeEffect(em);

												smokeEffect.setEntity(found);

												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 20 * 20; // there
																					// is
																					// an
																					// effect
																					// here
												smokeEffect.color = Color.AQUA;
												smokeEffect.start();

												new BukkitRunnable() {
													@Override
													public void run() {
														smokeEffect.cancel();
													}
												}.runTaskLater(this.plugin,
														plugin.configInt("Runes.runeofincineration.firetime")
																* 20);
											}
										}
										found.setFireTicks(firetime);
										found.sendMessage(ChatColor.DARK_RED + "You have been incinerated by "
												+ ChatColor.RED + player.getName());
										player.sendMessage(ChatColor.DARK_RED + "You have incinerated "
												+ ChatColor.RED + found.getName());
									}
								}

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofincineration") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofspeed)) {
							if (plugin.isEnabled("runeofspeed") == true) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								if (plugin.particleson("runeofspeed") == true) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WarpEffect bleedEffect = new WarpEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;

										bleedEffect.particle = ParticleEffect.SPELL_MOB;
										bleedEffect.color = Color.AQUA;
										bleedEffect.start();

										Bukkit.getServer().getScheduler().runTaskLater(this.plugin,
												new Runnable() {

													@Override
													public void run() {
														bleedEffect.cancel();

													}

												}, plugin.getDuration("runeofspeed") * 20L);
									}
								}
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofspeed")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofspeed);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofspeed") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
										plugin.getDuration("runeofspeed") * 20,
										plugin.getAmplifier("runeofspeed")));
								player.sendMessage(plugin.prefix + ChatColor.AQUA + " " + plugin.usemessage);

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);

										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofspeed") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofstrength)) {
							if (plugin.isEnabled("runeofstrength")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofstrength")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofstrength);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofstrength") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofstrength")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WarpEffect bleedEffect = new WarpEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;
										bleedEffect.particle = ParticleEffect.SPELL_MOB;
										bleedEffect.color = Color.RED;
										bleedEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												bleedEffect.cancel();
											}
										}.runTaskLater(this.plugin, plugin.getDuration("runeofstrength") * 20);
									}
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,
										plugin.getDuration("runeofstrength") * 20,
										plugin.getAmplifier("runeofstrength")));

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofstrength") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofrepair)) {
							if (plugin.isEnabled("runeofrepair")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(ChatColor.AQUA
										+ " As you use this mythical rune, it shatters into pieces.");
								try {
									ItemStack a1 = player.getInventory().getBoots();
									a1.setDurability((short) 0);
									player.sendMessage(
											plugin.prefix + ChatColor.GREEN + " Your boots were repaired!");
								} catch (Exception exeption) {
									player.sendMessage(
											plugin.prefix + ChatColor.RED + " You weren't wearing any boots!");
								}
								try {
									ItemStack a2 = player.getInventory().getLeggings();
									a2.setDurability((short) 0);
									player.sendMessage(
											plugin.prefix + ChatColor.GREEN + " Your leggings were repaired!");
								} catch (Exception exeption) {
									player.sendMessage(plugin.prefix + ChatColor.RED
											+ " You weren't wearing any leggings!");
								}
								try {
									ItemStack a3 = player.getInventory().getChestplate();
									a3.setDurability((short) 0);
									player.sendMessage(
											plugin.prefix + ChatColor.GREEN + " Your chestplate was repaired!");
								} catch (Exception exception) {
									player.sendMessage(plugin.prefix + ChatColor.RED
											+ " You weren't wearing any chestplate!");
								}
								try {
									ItemStack a4 = player.getInventory().getHelmet();
									a4.setDurability((short) 0);
									player.sendMessage(
											plugin.prefix + ChatColor.GREEN + " Your helmet was repaired!");
								} catch (Exception exception) {
									player.sendMessage(
											plugin.prefix + ChatColor.RED + " You weren't wearing any helmet!");
								}
								try {
									player.updateInventory();
								} catch (Exception e) {
									System.out.println(e.toString());
								}
								player.sendMessage(plugin.prefix + " "
										+ plugin.coloredString("Runes.runeofrepair.repairmessage"));

								// repair

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofrepair"));

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofminions)) {
							if (plugin.isEnabled("runeofminions")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofminions")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofminions);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofminions") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								for (Entity e : player.getNearbyEntities(5, 256, 5)) {
									if (e instanceof Player) {

										Creature z = (Creature) e.getWorld().spawnEntity(e.getLocation(),
												EntityType.IRON_GOLEM);
										z.setTarget((LivingEntity) e);
										z.damage(plugin.configInt("Runes.runeofminions.miniondamage"));
										z.setHealth(plugin.configInt("Runes.runeofminions.minionhealth"));
										z.setCustomName(plugin.coloredString("Runes.runeofminions.minionname"));
										z.setCustomNameVisible(true);
										Integer task2 = Bukkit.getServer().getScheduler()
												.scheduleSyncRepeatingTask(this.plugin, new Runnable() {

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
										}.runTaskLater(this.plugin,
												plugin.configInt("Runes.runeofminions.minionlivetime") * 20);
										new BukkitRunnable() {
											public void run() {
												alreadyused1.put(player.getName().toString(),
														alreadyused1.get(player.getName().toString()) - 1);
												player.sendMessage(plugin.prefix + " " + plugin.again);
											}
										}.runTaskLater(this.plugin, plugin.getDelay("runeofminions"));
									}
								}

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofflying)) {
							if (plugin.isEnabled("runeofflying")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + ChatColor.AQUA + " " + plugin.usemessage);
								player.setAllowFlight(true);
								player.setFlying(true);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofflying")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofflying);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofflying") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);

										player.setFlying(false);
										player.setAllowFlight(false);
										player.sendMessage(plugin.prefix + " " + plugin.again);
										try {
											player.sendMessage(plugin.prefix + " "
													+ plugin.coloredString("Runes.runeofflying.expiremessage"));
										} catch (Exception e) {
											System.out.println(
													"There is an error in your config yml file! Thankfully, We have recovered! Please check the error");
											player.sendMessage(plugin.prefix + " " + plugin.getConfig()
													.getString("Rune.runeofflying.expiremessage"));
										}
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofflying") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofinvis)) {
							if (plugin.isEnabled("runeofinvisibility")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
										plugin.getDuration("runeofinvisibility") * 20, 0));
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + "" + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofinvisibility") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofvampirism)) {
							if (plugin.isEnabled("runeofvampirism")) {

								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								if (plugin.particleson("runeofvampirism")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WarpEffect bleedEffect = new WarpEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;

										bleedEffect.particle = ParticleEffect.SPELL_MOB;
										bleedEffect.color = Color.PURPLE;
										bleedEffect.start();

										Bukkit.getServer().getScheduler().runTaskLater(this.plugin,
												new Runnable() {

													@Override
													public void run() {
														bleedEffect.cancel();

													}

												}, plugin.getDuration("runeofvampirism") * 20L);
									}

								}
								vampire.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofvampirism")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofvampirism);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofvampirism") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										try {
											vampire.remove(player.getName().toString());
										} catch (Exception e) {

										}
										try {
											player.sendMessage(plugin.prefix + " " + plugin
													.coloredString("Runes.runeofvampirism.expiremessage"));
										} catch (Exception e) {
											System.out.println(
													"There is an error in your config yml file! Thankfully, We have recovered! Please check the error");
											player.sendMessage(plugin.prefix + " " + plugin.getConfig()
													.getString("Rune.runeofvampirism.expiremessage"));
										}

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofvampirism") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofvampirism") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofleaping)) {
							if (plugin.isEnabled("runeofleaping")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofleaping")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofleaping);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofleaping") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofleaping")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WarpEffect bleedEffect = new WarpEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 20 * 20;
										bleedEffect.particle = ParticleEffect.SPELL_MOB;
										bleedEffect.color = Color.BLUE;
										bleedEffect.start();
										Bukkit.getServer().getScheduler().runTaskLater(this.plugin,
												new Runnable() {

													@Override
													public void run() {
														bleedEffect.cancel();

													}

												}, plugin.getDuration("runeofleaping") * 20L);
									}
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,
										plugin.getDuration("runeofleaping") * 20,
										plugin.getAmplifier("runeofleaping")));
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										;
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofleaping") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofhealing)) {
							if (plugin.isEnabled("runeofhealing")) {

								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								if (plugin.particleson("runeofhealing")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										WarpEffect smokeEffect = new WarpEffect(em);
										smokeEffect.setEntity(player);

										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										smokeEffect.iterations = 1 * 20;
										// there is an effect here
										smokeEffect.particle = ParticleEffect.HEART;
										smokeEffect.start();
									}

								}

								player.setHealth(plugin.configInt("Runes.runeofhealing.healhearts"));
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);

								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofhealing") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofthorns)) {
							if (plugin.isEnabled("runeofthorns")) {

								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofthorns")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofthorns);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofthorns") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofthorns")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										ShieldEffect smokeEffect = new ShieldEffect(em);
										smokeEffect.setEntity(player);

										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										smokeEffect.iterations = 100 * 100;
										// there is an effect here
										smokeEffect.particle = ParticleEffect.SPELL_MOB;
										smokeEffect.color = Color.GREEN;
										smokeEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												smokeEffect.cancel();
											}
										}.runTaskLater(this.plugin, plugin.getDuration("runeofthorns") * 20);
									}
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								thorns.add(player.getName().toString());
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										try {
											thorns.remove(player.getName().toString());
										} catch (Exception e) {

										}
										player.sendMessage(plugin.prefix + " " + plugin.again);
										try {
											player.sendMessage(plugin.prefix + " "
													+ plugin.coloredString("Runes.runeofthorns.expiremessage"));
										} catch (Exception e) {
											System.out.println(e.toString());
											System.out.println(
													"You have an error in your config file! However, we recovered");
											player.sendMessage(plugin.prefix + " "
													+ "The effects of the runes of thorns have run out");
										}

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofthorns") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofbreathing)) {
							if (plugin.isEnabled("runeofbreathing")) {
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + ChatColor.GRAY + " " + plugin.usemessage);
								player.addPotionEffect(
										new PotionEffect(PotionEffectType.WATER_BREATHING, 400, 0));
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofbreathing")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofbreathing);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofbreathing") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.hasParticles()) {
									EffectManager em = new EffectManager(plugin);
									WarpEffect smokeEffect = new WarpEffect(em);
									smokeEffect.setEntity(player);

									// Bleeding takes 15 seconds
									// period * iterations = time of
									// effect
									smokeEffect.iterations = 10 * 20;
									smokeEffect.particle = ParticleEffect.SPELL_MOB;
									smokeEffect.color = Color.AQUA;
									smokeEffect.start();
									new BukkitRunnable() {

										@Override
										public void run() {
											alreadyused1.put(player.getName().toString(),
													alreadyused1.get(player.getName().toString()) - 1);
											player.sendMessage(plugin.prefix + " " + plugin.again);

										}
									}.runTaskLater(this.plugin, 400);
								}

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofprotection)) {
							if (plugin.isEnabled("runeofprotection")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								Location location = player.getLocation();
								location.getWorld();
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								nodmg1.add(player.getName().toString());
								if (plugin.particleson("runeofprotection")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										EarthEffect bleedEffect = new EarthEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;
										bleedEffect.color = Color.ORANGE;
										bleedEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												bleedEffect.cancel();

											}
										}.runTaskLater(this.plugin,
												plugin.getDuration("runeofprotection") * 20);
									}
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofprotection")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofprotection);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofprotection") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										try {
											nodmg1.remove(player.getName().toString());
										} catch (Exception e) {

										}
										try {
											player.sendMessage(plugin.prefix + " " + plugin
													.coloredString("Runes.runeofprotection.expiremessage"));
										} catch (Exception e) {
											System.out.println(
													"Exception occured, error in config, we have recovered though!"
															+ e.toString());
										}

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofprotection") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofprotection") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofpoison)) {
							if (plugin.isEnabled("runeofsickening")) {
								int radius = plugin.configInt("Runes.runeofsickening.radius");

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasHolo() && plugin.isHoloEnabled("runeofsickening")) {
											try {
												// TODO Copy this over
												// to everywhere else!
												final Hologram hologram = HologramsAPI.createHologram(plugin,
														found.getLocation().add(0.0, 2.0, 0.0));
												hologram.appendTextLine(ChatColor.DARK_PURPLE + "Poisoned");
												new BukkitRunnable() {
													int ticksRun;

													@Override
													public void run() {
														ticksRun++;
														hologram.teleport(
																player.getLocation().add(0.0, 2.0, 0.0));
														if (ticksRun > plugin.getDuration("runeofsickening")
																* 20) {
															hologram.delete();
															cancel();
														}
													}
												}.runTaskTimer(plugin, 1L, 1L);
											} catch (Exception e1) {
												e1.printStackTrace();
												System.out.println(
														"[SkillRunes] Unexpected error! Please report this to the developer");

											}
										}
										if (plugin.particleson("runeofsickening")) {
											if (plugin.hasParticles()) {
												EffectManager em = new EffectManager(plugin);
												WarpEffect smokeEffect = new WarpEffect(em);

												smokeEffect.setEntity(found);

												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 100 * 100;
												// there is an effect
												// here
												smokeEffect.particle = ParticleEffect.SPELL_MOB;
												smokeEffect.color = Color.PURPLE;
												smokeEffect.start();
												new BukkitRunnable() {
													@Override
													public void run() {
														smokeEffect.cancel();
													}
												}.runTaskLater(this.plugin,
														plugin.getDuration("runeofsickening") * 20);
											}
										}
										found.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
												plugin.getDuration("runeofsickening") * 20,
												plugin.getAmplifier("runeofsickening")));
										player.sendMessage(plugin.prefix + " "
												+ plugin.coloredString("Runes.runeofsickening.sickenermessage ")
												+ found.getName());
										found.sendMessage(plugin.prefix + " " + plugin.coloredString(
												"runes.runeofsickening.sickmessage " + found.getName()));
									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofsickening") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofillumination)) {
							if (plugin.isEnabled("runeoflaunching")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.getLocation();

								if (plugin.particleson("runeoflaunching")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										AtomEffect bleedEffect = new AtomEffect(em);
										bleedEffect.setEntity(event.getPlayer());
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										bleedEffect.iterations = 100 * 100;
										bleedEffect.color = Color.AQUA;
										bleedEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												bleedEffect.cancel();
											}
										}.runTaskLater(this.plugin,
												plugin.configInt("Runes.runeoflaunching.launchdelay") * 20);
									}
								}
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								player.sendMessage(plugin.prefix + " "
										+ plugin.coloredString("Runes.runeoflaunching.launchmessage"));
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeoflaunching")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofillumination);
										new BukkitRunnable() { // yes
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeoflaunching") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {

										player.setVelocity(new Vector(
												plugin.configInt("Runes.runeoflaunching.horizontalvector1"),
												plugin.configInt("Runes.runeoflaunching.upwardslaunchvector"),
												plugin.configInt("Runes.runeoflaunching.horizontalvector2")));
										player.setFallDistance(-100.0f);

									}
								}.runTaskLater(this.plugin,
										plugin.configInt("Runes.runeoflaunching.launchdelay") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeoflaunching") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofblinding)) {
							if (plugin.isEnabled("runeofblinding")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								for (Entity e : player.getNearbyEntities(
										plugin.configInt("Runes.runeofblinding.radius"), 256,
										plugin.configInt("Runes.runeofblinding.radius"))) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasHolo() && plugin.isHoloEnabled("runeofblinding")) {
											try {
												// TODO Copy this over
												// to everywhere else!
												final Hologram hologram = HologramsAPI.createHologram(plugin,
														found.getLocation().add(0.0, 2.0, 0.0));
												hologram.appendTextLine(ChatColor.DARK_GRAY + "Blinded");
												new BukkitRunnable() {
													int ticksRun;

													@Override
													public void run() {
														ticksRun++;
														hologram.teleport(
																player.getLocation().add(0.0, 2.0, 0.0));
														if (ticksRun > plugin.getDuration("runeofblinding")
																* 20) {
															hologram.delete();
															cancel();
														}
													}
												}.runTaskTimer(plugin, 1L, 1L);
											} catch (Exception e1) {
												e1.printStackTrace();
												System.out.println(
														"[SkillRunes] Unexpected error! Please report this to the developer");

											}
										}
										if (plugin.particleson("runeofblinding")) {
											if (plugin.hasParticles()) {
												EffectManager em = new EffectManager(plugin);
												WarpEffect smokeEffect = new WarpEffect(em);

												smokeEffect.setEntity(found);
												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 5;
												// there is an effect
												// here
												smokeEffect.particle = ParticleEffect.SPELL_MOB;
												smokeEffect.color = Color.BLACK;
												smokeEffect.start();
											}
										}
										found.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
												plugin.getDuration("runeofblinding") * 20, 0));
										player.sendMessage(plugin.prefix + " "
												+ plugin.coloredString("Runes.runeofblinding.blindermessage"));
										found.sendMessage(plugin.prefix + " "
												+ plugin.coloredString("Runes.runeofblinding.blindmessage"));
									}

								}
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofblinding") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeoflightning)) {
							if (plugin.isEnabled("runeoflightning")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								for (Entity e : player.getNearbyEntities(
										plugin.configInt("Runes.runeoflightning.radius"), 256,
										plugin.configInt("Runes.runeoflightning.radius"))) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasParticles()) {
											EffectManager em = new EffectManager(plugin);
											WaveEffect smokeEffect = new WaveEffect(em);
											if (plugin.particleson("runeoflightning")) {
												smokeEffect.setEntity(found);
												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 5;
												// there is an effect
												// here
												smokeEffect.particle = ParticleEffect.SPELL_MOB;
												smokeEffect.color = Color.YELLOW;
												smokeEffect.start();
											}
										}
										World world = player.getWorld();
										world.strikeLightning(found.getLocation());
										player.sendMessage(plugin.prefix + ChatColor.YELLOW
												+ " You have struck " + ChatColor.GOLD + found.getName());
										found.sendMessage(
												plugin.prefix + ChatColor.YELLOW + " You have been struck by "
														+ ChatColor.GOLD + player.getName());
									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeoflightning") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofwaterwalking)) {
							if (plugin.isEnabled("runeofwaterwalking")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								waterwalking.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofwaterwalking")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofwaterwalking);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofwaterwalking") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										try {
											waterwalking.remove(player.getName().toString());
										} catch (Exception e) {

										}
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofwaterwalking.expiremessage"));
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofwaterwalking") * 20);
								new BukkitRunnable() {
									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);
									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofwaterwalking") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofarrowaffinity)) {
							if (plugin.isEnabled("runeofarrowaffinity")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								arrows.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofarrowaffinity")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofarrowaffinity);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofarrowaffinity") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									public void run() {
										try {
											arrows.remove(player.getName().toString());
										} catch (Exception e) {

										}
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofarrowaffinity.expiremessage"));
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofarrowaffinity") * 20);
								new BukkitRunnable() {
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);
									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofarrowaffinity") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofbaraging)) {
							if (plugin.isEnabled("runeofbarraging")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								barrage.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofbarraging")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofbaraging);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofbarraging") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
							}
							new BukkitRunnable() {

								@Override
								public void run() {
									alreadyused1.put(player.getName().toString(),
											alreadyused1.get(player.getName().toString()) - 1);
									player.sendMessage(plugin.prefix + " " + plugin.again);

								}
							}.runTaskLater(this.plugin, plugin.getDelay("runeofbarraging") * 20);
							new BukkitRunnable() {

								@Override
								public void run() {
									try {
										barrage.remove(player.getName().toString());
									} catch (Exception e) {

									}
									player.sendMessage(plugin.prefix + " "
											+ plugin.coloredString("Runes.runeofbarraging.expiremessage"));

								}
							}.runTaskLater(this.plugin, plugin.getDuration("runeofbarraging") * 20);

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofextremepower)) {
							if (plugin.isEnabled("runeofextremepower")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " "
										+ plugin.coloredString("Runes.runeofextremepower.activatemessage"));
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								explosions.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofextremepower")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofextremepower);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofextremepower") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {

									@Override
									public void run() {
										try {
											explosions.remove(player.getName().toString());
										} catch (Exception e) {

										}
										player.sendMessage(plugin.prefix + " " + plugin
												.coloredString("Runes.runeofextremepower.expiremessage"));

									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofextremepower") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofextremepower") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofregeneration)) {
							if (plugin.isEnabled("runeofregeneration")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofregeneration")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofregeneration);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofregeneration") * 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								if (plugin.particleson("runeofregeneration")) {
									if (plugin.hasParticles()) {
										EffectManager em = new EffectManager(plugin);
										SmokeEffect smokeEffect = new SmokeEffect(em);
										smokeEffect.setEntity(player);
										// Bleeding takes 15 seconds
										// period * iterations = time of
										// effect
										smokeEffect.iterations = 100 * 100;
										// there is an effect here
										smokeEffect.particle = ParticleEffect.HEART;
										smokeEffect.start();
										new BukkitRunnable() {
											@Override
											public void run() {
												smokeEffect.cancel();
											}
										}.runTaskLater(this.plugin,
												plugin.getDuration("runeofregeneration") * 20);
									}
								}
								player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,
										plugin.getDuration("runeofregeneration") * 20,
										plugin.getAmplifier("runeofregeneration")));
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);
										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofregeneration"));

							}

						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofvolatilearrows)) {
							if (plugin.isEnabled("runeofvolatilearrows")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);
								explosivearrows.add(player.getName().toString());
								if (plugin.hasHolo() && plugin.isHoloEnabled("runeofvolatilearrows")) {
									try {
										// TODO Copy this over to
										// everywhere else!
										final Hologram hologram = HologramsAPI.createHologram(plugin,
												player.getLocation().add(0.0, 2.0, 0.0));
										hologram.appendTextLine(plugin.runeofvolatilearrows);
										new BukkitRunnable() {
											int ticksRun;

											@Override
											public void run() {
												ticksRun++;
												hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
												if (ticksRun > plugin.getDuration("runeofvolatilearrows")
														* 20) {
													hologram.delete();
													cancel();
												}
											}
										}.runTaskTimer(plugin, 1L, 1L);
									} catch (Exception e1) {
										e1.printStackTrace();
										System.out.println(
												"[SkillRunes] Unexpected error! Please report this to the developer");

									}
								}
								new BukkitRunnable() {
									@Override
									public void run() {
										try {
											explosivearrows.remove(player.getName().toString());
										} catch (Exception e) {

										}
									}
								}.runTaskLater(this.plugin, plugin.getDuration("runeofvolatilearrows") * 20);
								new BukkitRunnable() {

									@Override
									public void run() {
										alreadyused1.put(player.getName().toString(),
												alreadyused1.get(player.getName().toString()) - 1);

										player.sendMessage(plugin.prefix + " " + plugin.again);

									}
								}.runTaskLater(this.plugin, plugin.getDelay("runeofvolatilearrows") * 20);

							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofhaste)) {
							if (plugin.isEnabled("runeofhaste")) {
								if (1 == 1) {
									if (player.getInventory().getItemInHand().getAmount() == 1) {
										inventory.removeItem(player.getInventory().getItemInHand());
									}
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									player.sendMessage(plugin.prefix + " " + plugin.usemessage);
									alreadyused1.put(player.getName().toString(),
											alreadyused1.get(player.getName().toString()) + 1);
									if (plugin.hasHolo() && plugin.isHoloEnabled("runeofhaste")) {
										try {
											// TODO Copy this over to
											// everywhere else!
											final Hologram hologram = HologramsAPI.createHologram(plugin,
													player.getLocation().add(0.0, 2.0, 0.0));
											hologram.appendTextLine(plugin.runeofhaste);
											new BukkitRunnable() {
												int ticksRun;

												@Override
												public void run() {
													ticksRun++;
													hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
													if (ticksRun > plugin.getDuration("runeofhaste") * 20) {
														hologram.delete();
														cancel();
													}
												}
											}.runTaskTimer(plugin, 1L, 1L);
										} catch (Exception e1) {
											e1.printStackTrace();
											System.out.println(
													"[SkillRunes] Unexpected error! Please report this to the developer");

										}
									}
									if (plugin.particleson("runeofhaste")) {
										if (plugin.hasParticles()) {
											EffectManager em = new EffectManager(plugin);
											WarpEffect smokeEffect = new WarpEffect(em);
											smokeEffect.setEntity(player);
											// Bleeding takes 15 seconds
											// period * iterations =
											// time of
											// effect
											smokeEffect.iterations = 100 * 100;
											// there is an effect here
											smokeEffect.particle = ParticleEffect.SPELL_MOB;
											smokeEffect.color = Color.WHITE;
											smokeEffect.start();
											new BukkitRunnable() {
												@Override
												public void run() {
													smokeEffect.cancel();
												}
											}.runTaskLater(this.plugin, plugin.getDuration("runeofhaste") * 20);
										}
									}
									player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,
											plugin.getDuration("runeofhaste") * 20,
											plugin.getAmplifier("runeofhaste")));
									new BukkitRunnable() {
										@Override
										public void run() {
											alreadyused1.put(player.getName().toString(),
													alreadyused1.get(player.getName().toString()) - 1);
											player.sendMessage(plugin.prefix + " " + plugin.again);

										}
									}.runTaskLater(this.plugin, plugin.getDelay("runeofhaste") * 20);
								} else {
									player.sendMessage(plugin.prefix + " " + plugin.alreadyactivemessage);
								}
							} else {
								player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
							}
						} else if (player.getItemInHand().getItemMeta().getDisplayName()
								.equals(plugin.runeofwither)) {
							if (plugin.isEnabled("runeofwither")) {

								if (player.getInventory().getItemInHand().getAmount() == 1) {
									inventory.removeItem(player.getInventory().getItemInHand());
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								alreadyused1.put(player.getName().toString(),
										alreadyused1.get(player.getName().toString()) + 1);

								player.sendMessage(plugin.prefix + " " + plugin.usemessage);
								for (Entity e : player.getNearbyEntities(
										plugin.configInt("Runes.runeofwither.radius"), 256,
										plugin.configInt("Runes.runeofwither.radius"))) {
									if (e instanceof Player) {
										Player found = (Player) e;
										if (plugin.hasHolo() && plugin.isHoloEnabled("runeofwither")) {
											try {
												// TODO Copy this over
												// to everywhere else!
												final Hologram hologram = HologramsAPI.createHologram(plugin,
														player.getLocation().add(0.0, 2.0, 0.0));
												hologram.appendTextLine(ChatColor.GRAY + "Withered");
												new BukkitRunnable() {
													int ticksRun;

													@Override
													public void run() {
														ticksRun++;
														hologram.teleport(
																player.getLocation().add(0.0, 2.0, 0.0));
														if (ticksRun > plugin.getDuration("runeofwither")
																* 20) {
															hologram.delete();
															cancel();
														}
													}
												}.runTaskTimer(plugin, 1L, 1L);
											} catch (Exception e1) {
												e1.printStackTrace();
												System.out.println(
														"[SkillRunes] Unexpected error! Please report this to the developer");

											}
										}
										if (plugin.particleson("runeofwither")) {
											if (plugin.hasParticles()) {
												EffectManager em = new EffectManager(plugin);
												WaveEffect smokeEffect = new WaveEffect(em);

												smokeEffect.setEntity(found);
												// Bleeding takes 15
												// seconds
												// period * iterations =
												// time of
												// effect
												smokeEffect.iterations = 5;
												// there is an effect
												// here
												smokeEffect.particle = ParticleEffect.SPELL_MOB;
												smokeEffect.color = Color.BLACK;
												smokeEffect.start();
											}
										}
										found.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,
												plugin.getDuration("runeofwither") * 20,
												plugin.getAmplifier("runeofwither")));
										player.sendMessage(ChatColor.DARK_GRAY + "You have withered "
												+ ChatColor.GRAY + found.getName());
										found.sendMessage(ChatColor.DARK_GRAY + "You have been withered by "
												+ ChatColor.GRAY + player.getName());
									}

								}
									new BukkitRunnable() {

										@Override
										public void run() {
											alreadyused1.put(player.getName().toString(), alreadyused1.get(player.getName().toString())-1);
											player.sendMessage(plugin.prefix + " " + plugin.again);

										}
									}.runTaskLater(this.plugin, plugin.getDelay("runeofwither") * 20);
								
							}
						} else {
							player.sendMessage(plugin.prefix + " " + plugin.disabledmessage);
						}

					} else if (act == Action.RIGHT_CLICK_AIR || act == Action.RIGHT_CLICK_BLOCK || act == Action.LEFT_CLICK_AIR) {

						if (player.getInventory().getItemInHand().getType() == Material.AIR) {
							if (fireball.contains(player.getName().toString())) {
								player.launchProjectile(Fireball.class);

							}
						}
						if (player.getInventory().getItemInHand().getType() == Material.AIR) {
							if (arrows.contains(player.getName().toString())) {
								player.launchProjectile(Arrow.class);
							}
							
						}
					}
                             
				}
					
			} catch (Exception e) {
				String i = e.toString();
			}
			}
		} else {
			System.out.println("[SkillRunes] A null player tried to use a rune?");
		}
		
		
	}

	@EventHandler
	public void entityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player player = (Player) entity;

			if (nodmg.contains(player.getName().toString())) {

				event.setCancelled(true);

			}
			if (nodmg1.contains(player.getName().toString())) {

				event.setCancelled(true);

			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onMove(PlayerMoveEvent e) {
		HashMap<Player, Block> blocks = new HashMap<Player, Block>();
		if (waterwalking.contains(e.getPlayer().getName().toString())) {
			Location block = e.getPlayer().getLocation().add(0, -1, 0);
			if (block.getBlock().getType() == Material.WATER
					|| block.getBlock().getType() == Material.STATIONARY_WATER) {
				blocks.put(e.getPlayer(), block.getBlock());
				block.getBlock().setType(Material.GLASS);
				try {
					new BukkitRunnable() {
						public void run() {
							block.getBlock().setType(Material.WATER);
						}
					}.runTaskLater(this.plugin, 60);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		try {
			int healthgain = plugin.configInt("Runes.runeofvampirism.healthgain");
			if (event.getDamager() instanceof Player) {
				Player p = (Player) event.getDamager();
				
				if (event.getEntity() instanceof Player) {
					Player player = (Player) event.getEntity();
					if (explosions.contains(p.getName().toString())) {
						try {
							TNTPrimed tnt = (TNTPrimed) p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
							Vector v = p.getLocation().getDirection().multiply(0.1);
							tnt.setVelocity(v);
							tnt.setFuseTicks(1);

							World world = event.getEntity().getWorld();
							world.strikeLightning(event.getEntity().getLocation());
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					if (vampire.contains(p.getName().toString())) {
						// add holo displays shit here allahackbar
						if (plugin.hasHolo()) {
						Location location = player.getLocation();
						location.setY(location.getY()+1.5);
						Hologram hologram = HologramsAPI.createHologram(plugin,  location);
						TextLine textLine = hologram.appendTextLine(ChatColor.RED + "-1");
						Location location2 = p.getLocation();
						location2.setY(location2.getY()+1.5);
						Hologram hologram1 = HologramsAPI.createHologram(plugin,  location2);
						TextLine textLine1 = hologram1.appendTextLine(ChatColor.GREEN + "+1");
						new BukkitRunnable() {
							@Override
							public void run() {
								hologram.delete();
							}
						}.runTaskLater(this.plugin, 40);
						new BukkitRunnable() {
							@Override
							public void run() {
								hologram1.delete();
							}
						}.runTaskLater(this.plugin, 40);
						}
						
						
						Damageable d = (Damageable) p;
						double health = d.getHealth();
						double addhealth = health + healthgain;
						p.setHealth(addhealth);
					}

					if (crippling.contains(player.getName().toString())) {

						Player damager = (Player) event.getDamager();
						player.sendMessage(
								plugin.prefix + " " + plugin.coloredString("Runes.runeofcrippling.cripplemessage"));
						damager.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,
								plugin.getDuration("runeofcrippling") * 20, plugin.getAmplifier("runeofcrippling")));
						if (plugin.hasHolo() && plugin.isHoloEnabled("runeofcrippling")) {
							try {
							// TODO Copy this over to everywhere else!
							final Hologram hologram = HologramsAPI.createHologram(plugin, player.getLocation().add(0.0, 2.0, 0.0));
							hologram.appendTextLine(ChatColor.GRAY + "Crippled");
							new BukkitRunnable() {
								int ticksRun;
								@Override
								public void run(){ 
									ticksRun++;
									hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
									if (ticksRun > plugin.getDuration("runeofcrippling")*20){ 
										hologram.delete();
										cancel();
									}
								}
							}.runTaskTimer(plugin, 1L, 1L);
							} catch (Exception e1) {
								e1.printStackTrace();
								System.out.println("[SkillRunes] Unexpected error! Please report this to the developer");
								
							}
						}
					}

					if (nodmg.contains(p.getName().toString())) {
						event.setCancelled(true);
					}
					if (nodmg1.contains(p.getName().toString())) {
						event.setCancelled(true);
					}
					if (event.getEntity() instanceof Player) {

						if (thorns.contains(player.getName().toString())) {
							Player damager = (Player) event.getDamager();
							Damageable d = (Damageable) damager;
							try {
								int reducer = plugin.configInt("Runes.runeofthorns.thornsdamage");
								d.setHealth(d.getHealth() - reducer);

							} catch (Exception e) {
								System.out.println(e.toString());
								System.out.println(
										"You have a problem in your config file! Please check it! The plugin has recovered");
								d.setHealth(d.getHealth() - 1);
							}
							try {
								String damagemessage = plugin.coloredString("Runes.runeofthorns.damagermessage");
								damager.sendMessage(plugin.prefix + ChatColor.DARK_PURPLE + " " + damagemessage);
							} catch (Exception e) {
								System.out.println(e.toString());
								System.out.println("You have an error in your configuration! We recovered!");
								damager.sendMessage(plugin.prefix + " " + ChatColor.RED
										+ "You have been injured due to your opponent's active thorns rune!");
							}

						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	@EventHandler
	public void barrage(EntityShootBowEvent event) {
		int arrowsint = plugin.configInt("Runes.runeofbarraging.arrows");
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (barrage.contains(player.getName().toString())) {
				Entity mainArrow = event.getProjectile();

				for (int i = 1; i < arrowsint; i++) {
					mainArrow.getWorld().spawnArrow(mainArrow.getLocation(), mainArrow.getVelocity(), 3, 3)
							.setShooter(event.getEntity());

					mainArrow.getWorld().playEffect(mainArrow.getLocation(), org.bukkit.Effect.BLAZE_SHOOT, 0);
				}
			} else {
			}
		} else {
		}
	}

	
	@EventHandler
	public void explosive(ProjectileHitEvent event) {
		Entity entity = event.getEntity();
		if ((entity instanceof Arrow)) {
			Projectile p = event.getEntity();
			Arrow arrow = (Arrow) event.getEntity();
			Player shooter = (Player) p.getShooter();
			try {
				if ((shooter instanceof Player)) {
					Player player = (Player) shooter;

					if (explosivearrows.contains(player.getName().toString())) {

						arrow.getWorld().playEffect(arrow.getLocation(), org.bukkit.Effect.BLAZE_SHOOT, 0);
						TNTPrimed tnt = (TNTPrimed) player.getWorld().spawn(arrow.getLocation(), TNTPrimed.class);
						Vector v = arrow.getLocation().getDirection().multiply(0.1);
						tnt.setVelocity(v);
						tnt.setFuseTicks(3);
						arrow.remove();
					} else if (molotov.contains(player.getName().toString())) {
						final int RADIUS = 2;
						arrow.getLocation();
						final List<Block> burn = new ArrayList<Block>();
						int posX = arrow.getLocation().getBlockX();
						arrow.getLocation().getBlockY();
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
					} else if (lightning.contains(player.getName().toString())) {
						World world = arrow.getWorld();
						world.strikeLightning(arrow.getLocation());
					} else if (poison.contains(player.getName().toString())) {
						int radius = plugin.configInt("Runes.runeofpoisonarrows.radius");
						if (plugin.particleson("runeofpoisonarrows")) {
							EffectManager em = new EffectManager(plugin);
							ShieldEffect bleedEffect = new ShieldEffect(em);
							bleedEffect.setLocation(arrow.getLocation());
							// Bleeding takes 15 seconds
							// period * iterations = time of effect
							bleedEffect.iterations = 4;
							bleedEffect.particle = ParticleEffect.SPELL_MOB;
							bleedEffect.color = Color.PURPLE;
							bleedEffect.start();
						}
						List<Entity> entities = arrow.getNearbyEntities(radius, radius, radius);
						for (Entity e : entities) {
							if (e.getType().isAlive()) {
								if (e instanceof Player) {
									((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.POISON,
											plugin.getDuration("runeofpoisonarrows") * 20,
											plugin.getAmplifier("runeofpoisonarrows")));
								}
							}

						}
					}

				}
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
	}

}
