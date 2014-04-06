package com.empcraft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;

public class VSRPlotMe implements Listener {
	VoxelSniperRegions plugin;
	Plugin plotme;
	public VSRPlotMe(Plugin plotmePlugin, VoxelSniperRegions p3) {
		plotme = plotmePlugin;
    	plugin = p3;
    	
    }
	public Location[] getcuboid(Player player) {
		Location[] toreturn = new Location[2];
		
		Plot plotid = PlotManager.getPlotById(player.getLocation());
		if (plotid==null) {
			return null;
		}
		boolean isallowed = PlotManager.getPlotById(player.getLocation()).isAllowed(player.getName());
		if (isallowed) {
			toreturn[0] = new Location(player.getWorld(),PlotManager.bottomX(plotid.id,player.getWorld()),0,PlotManager.bottomZ(plotid.id,player.getWorld()));
			toreturn[1] = new Location(player.getWorld(),PlotManager.topX(plotid.id,player.getWorld()),256,PlotManager.topZ(plotid.id,player.getWorld()));
			return toreturn;
		}
		return null;
	}
	public String getid(Player player) {
		Plot plotid = PlotManager.getPlotById(player);
		if (plotid==null) {
			return null;
		}
		List<Player> players = PlotManager.getPlayersInPlot(player.getWorld(), plotid.id);
		if (plotid.getOwner().equalsIgnoreCase(player.getName())||players.contains(player)) {
			return plotid.id;
		}
		return null;
	}

}

