package com.stlmpp.spigot.plugins.tasks;

import com.stlmpp.spigot.plugins.StlmppPlugin;
import com.stlmpp.spigot.plugins.utils.Chance;
import com.stlmpp.spigot.plugins.utils.Tick;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningTask extends BukkitRunnable {

  Random random = new Random();

  public LightningTask(StlmppPlugin plugin) {
    this.runTaskTimer(plugin, 0, Tick.fromSeconds(10));
  }

  @Override
  public void run() {
    if (Chance.of(50)) {
      return;
    }
    var world = Bukkit.getWorld("world_nether");
    if (world == null) {
      return;
    }
    var players = world.getPlayers();
    var playersSize = players.size();
    if (playersSize == 0) {
      return;
    }
    var randomPlayer = players.get(this.random.nextInt(playersSize));
    var playerLocation = randomPlayer.getLocation();
    var playerX = playerLocation.getBlockX();
    var playerY = playerLocation.getBlockY();
    var playerZ = playerLocation.getBlockZ();
    var lightningX = ThreadLocalRandom.current().nextInt(playerX - 50, playerX + 51);
    var lightningY = ThreadLocalRandom.current().nextInt(playerY - 10, playerY + 11);
    var lightningZ = ThreadLocalRandom.current().nextInt(playerZ - 50, playerZ + 51);
    Bukkit.broadcastMessage("Striking at X=" + lightningX + ", Y=" + lightningY + ", Z=" + lightningZ);
    var lightningLocation = new Location(world, lightningX, lightningY, lightningZ);
    world.strikeLightning(lightningLocation);
    if (Chance.of(50)) {
      var explosionPower = ThreadLocalRandom.current().nextInt(0, 6);
      world.createExplosion(lightningLocation, explosionPower, true, true);
    }
  }
}
