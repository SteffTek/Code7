package de.Code7.SmartPhone;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.Code7.Main.PlayerManager;

public class AppChecker {

	private File Apps = new File("plugins/Code7/SmartPhone", "Apps.yml");
	private File AppConfig = new File("plugins/Code7/SmartPhone", "AppConfig.yml");
	
	private FileConfiguration appconfig = YamlConfiguration.loadConfiguration(this.Apps);
	private FileConfiguration config = YamlConfiguration.loadConfiguration(this.AppConfig);
	
	public boolean CheckApps(Player p, String app){
		if(appconfig.getBoolean("Apps." + "." + app) == true){
			if(config.get("Users." + PlayerManager.getUUID(p.getName()) + "." + app) != ""){
				return true;
			}
		}
		return false;
	}
}
