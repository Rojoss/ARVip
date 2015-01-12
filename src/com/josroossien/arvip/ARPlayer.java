package com.josroossien.arvip;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ARPlayer {
	private ARVip arv;
	private UUID uuid;



	public ARPlayer(UUID uuid) {
		this.arv = ARVip.inst();
		this.uuid = uuid;
	}

	public ARVip getPlugin() {
		return arv;
	}
	
	
	//#######################
	//### Custom methods. ###
	//#######################
	public UUID getUUID() {
		return uuid;
	}
	

	
	

	//#######################
	//### Bukkit methods. ###
	//#######################
	public Player getPlayer() {
		return Bukkit.getPlayer(getUUID());
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(getUUID());
	}
	
	public boolean isOnline() {
		return getPlayer() != null;
	}
	
	public void sendMessage(String msg) {
		getPlayer().sendMessage(msg);
	}
	
	public String getName() {
		return getOfflinePlayer().getName();
	}
	
	
	
	//Compare ARPlayers with eachother by UUID
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ARPlayer) {
            ARPlayer other = (ARPlayer)obj;

			return other.getUUID() == getUUID();
		}
		return false;
	}
}
