package me.william278.crossserversync.bukkit.config;

import me.william278.crossserversync.Settings;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLoader {

    public static void loadSettings(FileConfiguration config) throws IllegalArgumentException {
        Settings.serverType = Settings.ServerType.BUKKIT;
        Settings.redisHost = config.getString("redis_settings.host", "localhost");
        Settings.redisPort = config.getInt("redis_settings.port", 6379);
        Settings.redisPassword = config.getString("redis_settings.password", "");

        Settings.syncInventories = config.getBoolean("synchronisation_settings.inventories", true);
        Settings.syncEnderChests = config.getBoolean("synchronisation_settings.ender_chests", true);
        Settings.syncHealth = config.getBoolean("synchronisation_settings.health", true);
        Settings.syncHunger = config.getBoolean("synchronisation_settings.hunger", true);
        Settings.syncExperience = config.getBoolean("synchronisation_settings.experience", true);
        Settings.syncPotionEffects = config.getBoolean("synchronisation_settings.potion_effects", true);
    }

}
