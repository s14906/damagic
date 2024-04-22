package org.bochnacki.damagic;

import org.bochnacki.damagic.core.DamageListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Damagic extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        createConfig();
        enableCommands();
        if (config.getBoolean("enabled")) {
            getServer().getPluginManager().registerEvents(new DamageListener(), this);
        }
    }

    private void enableCommands() {
        Objects.requireNonNull(this.getCommand("damagic")).setExecutor(this);
    }

    private void createConfig() {
        config = getConfig();
        config.addDefault("enabled", true);
        config.addDefault("playerDamageInChat", true);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("help");
            suggestions.add("enable");
            suggestions.add("disable");
            suggestions.add("playerDamageInChat");
        } else if (args.length < 3 && args[0].toLowerCase().equals("playerdamageinchat")) {
            suggestions.add("enable");
            suggestions.add("disable");
        }
        return suggestions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command");
            return true;
        } else {
            if (args.length == 0 || args[0].equals("help".toLowerCase())) {
                sender.sendMessage("""
                        Damagic plugin usage:
                        Aliases: dmagic, dmgc
                        /damagic enable/disable - enables or disables the plugin
                        /damagic playerDamageInChat enable/disable - enables or disables notifications about damage done to you in the chat
                        """);
                return true;
            }
            if (args[0].equalsIgnoreCase("enable")) {
                config.set("enabled", true);
                Bukkit.getPluginManager().enablePlugin(this);
                sender.sendMessage("Damagic is enabled!");
                saveConfig();
                return true;
            }
            if (args[0].equalsIgnoreCase("disable")) {
                config.set("enabled", false);
                sender.sendMessage("Damagic is disabled!");
                saveConfig();
                return true;
            }
            if (args[0].equalsIgnoreCase("playerdamageinchat")) {
                if (args[1].equalsIgnoreCase("enable")) {
                    config.set("playerDamageInChat", true);
                    sender.sendMessage("Enabled player damage notification in chat!");
                    saveConfig();
                    return true;
                }
                if (args[1].equalsIgnoreCase("disable")) {
                    config.set("playerDamageInChat", false);
                    sender.sendMessage("Disabled player damage notification in chat!");
                    saveConfig();
                    return true;
                }

            }
        }
        sender.sendMessage("Unknown command, execute /damagic help or /dmgc help for more info");
        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info("Damagic disabled!");
    }
}
