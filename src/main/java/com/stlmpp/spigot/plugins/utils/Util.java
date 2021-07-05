package com.stlmpp.spigot.plugins.utils;

import java.util.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class Util {

  public static final Map<Material, Material> toNetherMaterialMap = new HashMap<>();
  public static final Material toNetherDefaultMaterial = Material.NETHERRACK;

  static {
    toNetherMaterialMap.put(Material.ACACIA_LOG, Material.CRIMSON_STEM);
    toNetherMaterialMap.put(Material.BIRCH_LOG, Material.CRIMSON_STEM);
    toNetherMaterialMap.put(Material.JUNGLE_LOG, Material.CRIMSON_STEM);
    toNetherMaterialMap.put(Material.SPRUCE_LOG, Material.WARPED_STEM);
    toNetherMaterialMap.put(Material.DARK_OAK_LOG, Material.WARPED_STEM);
    toNetherMaterialMap.put(Material.OAK_LOG, Material.WARPED_STEM);
    toNetherMaterialMap.put(Material.ACACIA_LEAVES, Material.NETHER_WART_BLOCK);
    toNetherMaterialMap.put(Material.BIRCH_LEAVES, Material.NETHER_WART_BLOCK);
    toNetherMaterialMap.put(Material.JUNGLE_LEAVES, Material.NETHER_WART_BLOCK);
    toNetherMaterialMap.put(Material.SPRUCE_LEAVES, Material.WARPED_WART_BLOCK);
    toNetherMaterialMap.put(Material.DARK_OAK_LEAVES, Material.WARPED_WART_BLOCK);
    toNetherMaterialMap.put(Material.OAK_LEAVES, Material.WARPED_WART_BLOCK);
    toNetherMaterialMap.put(Material.STONE_BRICKS, Material.NETHER_BRICK);
    toNetherMaterialMap.put(Material.WATER, Material.LAVA);
    toNetherMaterialMap.put(Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE);
    toNetherMaterialMap.put(Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE);
    toNetherMaterialMap.put(Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE);
    toNetherMaterialMap.put(Material.EMERALD_ORE, Material.ANCIENT_DEBRIS);
    toNetherMaterialMap.put(Material.GOLD_ORE, Material.NETHER_GOLD_ORE);
    toNetherMaterialMap.put(Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE);
    toNetherMaterialMap.put(Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE);
    toNetherMaterialMap.put(Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE);
    toNetherMaterialMap.put(Material.IRON_BLOCK, Material.COPPER_BLOCK);
    toNetherMaterialMap.put(Material.DIAMOND_BLOCK, Material.COPPER_BLOCK);
    toNetherMaterialMap.put(Material.SAND, Material.SOUL_SAND);
    toNetherMaterialMap.put(Material.COPPER_BLOCK, Material.COPPER_BLOCK);
    toNetherMaterialMap.put(Material.GOLD_BLOCK, Material.GOLD_BLOCK);
  }

  public static final Set<Material> netherMaterials = new HashSet<>();

  static {
    netherMaterials.add(Material.ANCIENT_DEBRIS);
    netherMaterials.add(Material.BASALT);
    netherMaterials.add(Material.POLISHED_BASALT);
    netherMaterials.add(Material.SMOOTH_BASALT);
    netherMaterials.add(Material.BLACKSTONE);
    netherMaterials.add(Material.NETHER_BRICK_FENCE);
    netherMaterials.add(Material.GILDED_BLACKSTONE);
    netherMaterials.add(Material.GLOWSTONE);
    netherMaterials.add(Material.MAGMA_BLOCK);
    netherMaterials.add(Material.NETHER_BRICK);
    netherMaterials.add(Material.NETHER_BRICK_SLAB);
    netherMaterials.add(Material.NETHER_BRICK_STAIRS);
    netherMaterials.add(Material.NETHER_BRICK_WALL);
    netherMaterials.add(Material.NETHER_GOLD_ORE);
    netherMaterials.add(Material.NETHER_QUARTZ_ORE);
    netherMaterials.add(Material.QUARTZ_BLOCK);
    netherMaterials.add(Material.QUARTZ_BRICKS);
    netherMaterials.add(Material.QUARTZ_PILLAR);
    netherMaterials.add(Material.QUARTZ_SLAB);
    netherMaterials.add(Material.QUARTZ_STAIRS);
    netherMaterials.add(Material.CHISELED_QUARTZ_BLOCK);
    netherMaterials.add(Material.SMOOTH_QUARTZ);
    netherMaterials.add(Material.SMOOTH_QUARTZ_STAIRS);
    netherMaterials.add(Material.SMOOTH_QUARTZ_SLAB);
    netherMaterials.add(Material.NETHER_SPROUTS);
    netherMaterials.add(Material.NETHER_WART);
    netherMaterials.add(Material.NETHER_WART_BLOCK);
    netherMaterials.add(Material.NETHERRACK);
    netherMaterials.add(Material.POLISHED_BLACKSTONE);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_BRICKS);
    netherMaterials.add(Material.BLACKSTONE_SLAB);
    netherMaterials.add(Material.BLACKSTONE_STAIRS);
    netherMaterials.add(Material.CHISELED_POLISHED_BLACKSTONE);
    netherMaterials.add(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_BRICK_SLAB);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_BRICK_STAIRS);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_SLAB);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_STAIRS);
    netherMaterials.add(Material.POLISHED_BLACKSTONE_WALL);
    netherMaterials.add(Material.SHROOMLIGHT);
    netherMaterials.add(Material.SOUL_SAND);
    netherMaterials.add(Material.SOUL_SOIL);
  }

  public static int getFloor(World world, Location location) {
    Integer floor = Util.getFloor(world, location, false, 64);
    if (floor == null) {
      // This is never going to happen
      floor = -1;
    }
    return floor;
  }

  public static Integer getFloor(World world, Location location, boolean returnNullOnMaxIterations) {
    return Util.getFloor(world, location, returnNullOnMaxIterations, 64);
  }

  public static Integer getFloor(World world, Location location, boolean returnNullOnMaxIterations, int maxIterations) {
    var iteration = 0;
    var locationY = location.getBlockY();
    while (
      !world.getBlockAt(location.getBlockX(), locationY, location.getBlockZ()).getType().isSolid() &&
      iteration <= maxIterations
    ) {
      iteration++;
      locationY--;
    }
    return iteration > maxIterations && returnNullOnMaxIterations ? null : locationY;
  }

  public static int getCeiling(World world, Location location) {
    Integer floor = Util.getCeiling(world, location, false, 64);
    if (floor == null) {
      // This is never going to happen
      floor = -1;
    }
    return floor;
  }

  public static Integer getCeiling(World world, Location location, boolean returnNullOnMaxIterations) {
    return Util.getCeiling(world, location, returnNullOnMaxIterations, 64);
  }

  public static Integer getCeiling(
    World world,
    Location location,
    boolean returnNullOnMaxIterations,
    int maxIterations
  ) {
    var iteration = 0;
    var locationY = location.getBlockY();
    while (
      !world.getBlockAt(location.getBlockX(), locationY, location.getBlockZ()).getType().isSolid() &&
      iteration <= maxIterations
    ) {
      iteration++;
      locationY++;
    }
    return iteration > maxIterations && returnNullOnMaxIterations ? null : locationY;
  }

  public static void setMaterialsFromNames(@NotNull Set<Material> materialSet, @NotNull List<?> materialNames) {
    for (Object materialName : materialNames) {
      if (!(materialName instanceof String materialNameString)) {
        continue;
      }
      final var material = Material.getMaterial(materialNameString);
      if (material != null) {
        materialSet.add(material);
      }
    }
  }

  public static Material convertToNetherMaterial(Material material) {
    var netherMaterial = Util.toNetherMaterialMap.get(material);
    if (netherMaterial == null) {
      netherMaterial = Util.toNetherDefaultMaterial;
    }
    return netherMaterial;
  }

  public static boolean isFromNether(Material material) {
    return Util.netherMaterials.contains(material);
  }
}
