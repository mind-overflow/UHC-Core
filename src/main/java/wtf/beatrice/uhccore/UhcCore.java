package wtf.beatrice.uhccore;

import wtf.beatrice.uhccore.commands.UhcCoreCommand;
import wtf.beatrice.uhccore.completers.InfoCompleter;
import wtf.beatrice.uhccore.listeners.*;
import wtf.beatrice.uhccore.utils.Debugger;
import wtf.beatrice.uhccore.utils.configuration.FileUtils;
import wtf.beatrice.uhccore.utils.MessageUtils;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UhcCore extends JavaPlugin
{
    // Instantiate a Debugger for this class.
    private Debugger debugger = new Debugger(getClass().getName());

    // Initializing needed variables.
    public static Logger logger;
    private PluginManager pluginManager;

    private static UhcCore instance;

    public static UhcCore getInstance()
    { return instance; }

    // Method called when the plugin is being loaded.
    @Override
    public void onEnable()
    {
        instance = this;

        // We need to run this in a task, because the plugin has to be initialized AFTER all the worlds are loaded.
        // todo: after a few months, this sounds like a badly implemented idea
        // ^ i agree, why the hell is this happening?
        getServer().getScheduler().runTask(this, ()->
        {

            // Give the initialized variables their respective values. Absolutely don't do this before as they will look good in the IDE but will result null.
            logger = getLogger();
            pluginManager = getServer().getPluginManager();

            // Check and report if the Debugger is enabled (the method itself does the checking). Would do it before but we need the logger to be initialized! :(
            debugger.sendDebugMessage(Level.WARNING, "---[ DEBUGGER IS ENABLED! ]---");
            debugger.sendDebugMessage(Level.WARNING, "---[ INITIALIZING PLUGIN ]---");
            debugger.sendDebugMessage(Level.INFO, "Logger and PluginManager already initialized.");

            // Register instances and give them the plugin parameter (this, because this class is the JavaPlugin) so they can access all of its info.
            debugger.sendDebugMessage(Level.INFO, "Instantiating some classes that need to access to plugin data...");
            FileUtils fileUtilsInstance = new FileUtils(this);
            UhcCoreCommand uhcCoreCommandInstance = new UhcCoreCommand(this);
            MessageUtils messageUtilsInstance = new MessageUtils(this);
            PlayerChatListener playerChatListenerInstance = new PlayerChatListener(this);
            PlayerDeathRespawnListener playerDeathRespawnListenerInstance = new PlayerDeathRespawnListener(this);
            PlayerMoveListener playerMoveListenerInstance = new PlayerMoveListener(this);
            debugger.sendDebugMessage(Level.INFO, "Done instantiating classes!");


            // Check if all needed files exist and work correctly, also loading their YAMLs.
            debugger.sendDebugMessage(Level.INFO, "Checking files...");
            FileUtils.checkFiles();
            debugger.sendDebugMessage(Level.INFO, "Done checking files!");

        /*
        Load all the YAML files. We are already loading them in FileUtils's checkFiles() method but we are loading them singularly.
        With this method we are sure that all the files get successfully loaded. Better twice than never...
         */
            debugger.sendDebugMessage(Level.INFO, "Reloading YAML config...");
            FileUtils.reloadYamls();
            debugger.sendDebugMessage(Level.INFO, "Done!");

            // Register Listeners
            debugger.sendDebugMessage(Level.INFO, "Registering listeners...");
            pluginManager.registerEvents(new PlayerJoinListener(), this);
            pluginManager.registerEvents(playerDeathRespawnListenerInstance, this);
            pluginManager.registerEvents(new PlayerInteractListener(), this);
            pluginManager.registerEvents(playerChatListenerInstance, this);
            pluginManager.registerEvents(new PlayerHitListener(), this);
            pluginManager.registerEvents(playerMoveListenerInstance, this);
            debugger.sendDebugMessage(Level.INFO, "Done registering listeners!");

            // Register Commands
            debugger.sendDebugMessage(Level.INFO, "Registering commands...");
            getCommand("uhc").setExecutor(uhcCoreCommandInstance);
            getCommand("uhc").setTabCompleter(new InfoCompleter());
            debugger.sendDebugMessage(Level.INFO, "Done registering commands!");

            // Send success output message to console.
            logger.log(Level.INFO, "Plugin " + getDescription().getName() + " Successfully Loaded!");
            debugger.sendDebugMessage(Level.WARNING, "---[ INITIALIZATION DONE ]---");
        });

    }

    // Method called when the plugin is being unloaded.
    @Override
    public void onDisable() {
        debugger.sendDebugMessage(Level.WARNING, "---[ DEBUGGER IS ENABLED! ]---");
        debugger.sendDebugMessage(Level.WARNING, "---[ DISABLING PLUGIN ]---");
        logger.log(Level.INFO, "Plugin " + getDescription().getName() + " Successfully Unloaded!");
        debugger.sendDebugMessage(Level.WARNING, "---[ PLUGIN DISABLED ]---");
    }


}