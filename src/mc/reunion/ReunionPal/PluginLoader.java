package mc.reunion.ReunionPal;

import mc.reunion.ReunionPal.advertiser.Advertiser;
import mc.reunion.ReunionPal.configuration.MessagesConfig;
import mc.reunion.ReunionPal.model.Commands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader extends JavaPlugin {
    // Prefix
    public static final String PREFIX = "§c§lReunionPal §7>> ";

    // instance
    public static PluginLoader instance;

    // FileConfigurations
    public MessagesConfig messagesConfig;

    // On plugin load
    @Override
    public void onLoad() {
        send("Loading plugin...");
        // TODO Cokolvek čo chceš načítať

        send("Loading default configuration");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        send("Loading messages configuration");
        messagesConfig = new MessagesConfig("messages",this);
       // messagesConfig.saveDefaultConfig();

    }

    // On plugin enable
    @Override
    public void onEnable() {
        instance = this;
        sendInfo();
        Advertiser.enable();

        // commands
        this.getCommand("pal").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        Advertiser.disable();
    }

    // Sends colorized message, will be more visible in console
    public static <T extends String> void send(T msg) {
        String msgs = msg.trim();
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + msgs);
    }
    // Exception sender
    public static <T extends Exception> void send(T clazz) {
        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7" + "Caught §cEXCEPTION: §7" + clazz.getMessage().trim());
        Bukkit.getServer().getConsoleSender().sendMessage("§cEXCEPTION §7StackTrace: ");
        clazz.printStackTrace();
    }
    public void sendInfo() {
        System.out.println(" ");
        send("§6SETTINGS");
        System.out.println(" ");
        send("§6Bossbar >");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Bossbar title: " + messagesConfig.getConfiguration().getString("bossbar.title"));
        send("Bossbar repeats every: " + messagesConfig.getConfiguration().getLong("bossbar.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("bossbar.repeat")/60)+"mins");
        System.out.println(" ");
        send("§6Chat >");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Chat title: " + messagesConfig.getConfiguration().getString("chat.title"));
        send("Chat repeats every: " + messagesConfig.getConfiguration().getLong("chat.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("chat.repeat")/60)+"mins");
        System.out.println(" ");
        send("§6Actiobar >");
        send("(Console does not support Unicode, so do not be scared if something is weird in title)");
        send("Actionbar title: " + messagesConfig.getConfiguration().getString("actionbar.title"));
        send("Actionbar repeats every: " + messagesConfig.getConfiguration().getLong("actionbar.repeat") + "secs");
        send("a.k.a " + (messagesConfig.getConfiguration().getLong("actionbar.repeat")/60)+"mins");
        System.out.println(" ");
        send("Useful, i know");
        System.out.println(" ");
    }

    public void reload() {
        Advertiser.disable();
        Advertiser.enable();
        sendInfo();
    }
}
