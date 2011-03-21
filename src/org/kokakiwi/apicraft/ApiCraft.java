package org.kokakiwi.apicraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.kokakiwi.apicraft.ApiCraftListener;
import org.kokakiwi.apicraft.net.*;

public class ApiCraft extends JavaPlugin {
	
	public Logger logger = Logger.getLogger("Minecraft.ApiCraft");
	public PluginDescriptionFile pdf;
	public WebServer webserver;
	private Configuration config;
	private ApiCraftListener aListener = new ApiCraftListener();

	@Override
	public void onDisable() {
		logger.info("Stopping Web Server...");
		webserver.stop();
		logger.info("ApiCraft is disabled!");
	}

	@Override
	public void onEnable() {
		pdf = getDescription();
		
		config = new Configuration(new File(getDataFolder(), "config.yml"));
		
		if(!getDataFolder().exists())
			getDataFolder().mkdirs();
		
		if(!new File(getDataFolder(), "cache/").exists())
			new File(getDataFolder(), "cache/").mkdirs();
		
		if(!new File(getDataFolder(), "config.yml").exists())
		{
			try {
				new File(getDataFolder(), "config.yml").createNewFile();
				config.setProperty("webServerPort", 6561);
				config.save();
			} catch (IOException e) {
				logger.severe("ApiCraft : Error during creating config file!");
				e.printStackTrace();
				getServer().getPluginManager().disablePlugin(this);
			}
		}
		
		config.load();
		
		logger.info("ApiCraft : Starting the webserver...");
		try {
			webserver = new WebServer(this, config.getInt("webServerPort", 6561));
			logger.info("ApiCraft : Web server started!");
		} catch (IOException e) {
			getServer().getPluginManager().disablePlugin(this);
		}
		
		getServer().getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, aListener, Priority.Normal, this);
		
		logger.info(pdf.getName() + " v" + pdf.getVersion() + " is enabled!");
	}

}
