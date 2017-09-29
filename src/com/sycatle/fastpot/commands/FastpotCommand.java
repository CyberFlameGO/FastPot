package com.sycatle.fastpot.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sycatle.fastpot.FastPot;

	/**
	 * Created by Sycatle on 29/09/2017.
	 */

public class FastpotCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission(FastPot.get().getConfig().get("permission").toString())) {
			if (args.length == 0) {
				sender.sendMessage("§cCorrect usage: /fastpot <get/set/reload> <values>");
			} else if (args.length >= 1) {
				if (args[0].equals("get")) {
					sender.sendMessage("§aFastpot values: " + FastPot.get().getFastpotValue());
				} else if (args[0].equals("set")) {
					if (args.length >= 2) {
						Double arg = NumberUtils.toDouble(args[1], -1D);
						
						if (arg < 0D) {
							sender.sendMessage("§cInvalid Fastpot value.");
							return true;
						}
						
						sender.sendMessage("§aFastpot of " + Bukkit.getServerName() + " set to " + arg + ".");
						FastPot.get().setFastpotTo(arg);
					} else {
						sender.sendMessage("§cCorrect usage: /fastpot <set> <values>");
					}
				} else if (args[0].equals("reload")) {
					FastPot.get().reloadConfig();
					sender.sendMessage("§aFastpot configuration has been reloaded.");
				} else {
					sender.sendMessage("§cCorrect usage: /fastpot <get/set/reload> <values>");
				}
			} else {
				sender.sendMessage("§cCorrect usage: /fastpot <get/set/reload> <values>");
			}
		} else {
			sender.sendMessage("§cNo permission.");
		}
		return false;
	}
}