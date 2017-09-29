package com.sycatle.fastpot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sycatle.fastpot.commands.FastpotCommand;
import com.sycatle.fastpot.listeners.PotionListener;

	/**
 	* Created by Sycatle on 29/09/2017.
 	*/

public class FastPot extends JavaPlugin{
	
	private static FastPot instance;

	public static FastPot get() {
		return instance;
	}
	
	public void onEnable(){
		instance = this;
		
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("fastpot", 1.0);
		getConfig().addDefault("permission", "fastpot.command");
		saveConfig();
		
		reloadConfig();
		
		registerListeners();
		registerCommands();
	}
	
	
	public void onDisable(){
		instance = null;
	}

	private void registerCommands() {
		getCommand("fastpot").setExecutor(new FastpotCommand());
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PotionListener(), this);
	}
	
	public void setFastpotTo(Double values) {
		getConfig().set("fastpot", values);
		saveConfig();
		reloadConfig();
	}

	public Double getFastpotValue() {
		return getConfig().getDouble("fastpot");
	}
}