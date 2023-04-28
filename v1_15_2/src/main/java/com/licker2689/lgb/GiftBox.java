package com.licker2689.lgb;

import com.licker2689.lgb.commands.LGBCommand;
import com.licker2689.lgb.events.LGBEvent;
import com.licker2689.lpc.utils.ColorUtils;
import com.licker2689.lpc.utils.ConfigUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
public class GiftBox extends JavaPlugin {
    private static GiftBox plugin;
    public static String prefix;
    public static YamlConfiguration config;

    public static GiftBox getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = ColorUtils.applyColor(config.getString("Settings.prefix"));
        plugin.getServer().getPluginManager().registerEvents(new LGBEvent(), plugin);
        getCommand("선물박스").setExecutor(new LGBCommand());
    }

    @Override
    public void onDisable() {
    }
}
