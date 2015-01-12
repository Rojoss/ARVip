package com.josroossien.arvip;

import com.clashwars.cwcore.utils.CWUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
	private ARVip arv;
	private Map<UUID, ARPlayer> players	= new HashMap<UUID, ARPlayer>();

	public PlayerManager(ARVip arv) {
		this.arv = arv;

        populate();
	}

	public void populate() {
		players.clear();
		
		//Players database
		// 0:ID 1:UUID 2:Name
		try {
			Statement statement = arv.getSql().createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM Players;");
			
			while (res.next()) {
				UUID uuid = UUID.fromString(res.getString("UUID"));
				if (!players.containsKey(uuid)) {
					ARPlayer cwp = new ARPlayer(uuid);
					players.put(uuid, cwp);
				}
				//players.get(uuid).setActiveClass(ClassType.fromString(res.getString("ActiveClass")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ARPlayer getPlayer(UUID uuid) {
		return players.get(uuid);
	}

	public ARPlayer getPlayer(String uuid) {
		return getPlayer(UUID.fromString(uuid));
	}

	public boolean removePlayer(UUID uuid) {
		return players.remove(uuid) != null;
	}

	public boolean removePlayer(String uuid) {
		return removePlayer(UUID.fromString(uuid));
	}

	public ARPlayer getOrCreatePlayer(UUID uuid) {
		ARPlayer player = players.get(uuid);
		if (player != null) {
			return player;
		}

		player = new ARPlayer(uuid);
		//Check for player in database.
		try {
			Statement statement = arv.getSql().createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM Players WHERE UUID='" + uuid + "';");
			if (!res.isBeforeFirst() ) {
				//New player with no data in the database yet.
				try {
					Statement statement2 = arv.getSql().createStatement();
					statement2.executeUpdate("INSERT INTO Players (UUID, Name) VALUES ('" + uuid.toString() + "', '" + player.getName() + "');");
				} catch (SQLException e) {
					player.sendMessage(CWUtil.formatARMsg("&cError connecting to the databse. Can't create VIP data."));
					e.printStackTrace();
				}
			} else {
				//Exisiting player so update the CWPlayer before returning it.
				while (res.next()) {
					//player.setActiveClass(ClassType.fromString(res.getString("ActiveClass")));
				}
			}
		} catch (SQLException e) {
			player.sendMessage(CWUtil.formatARMsg("&cError connecting to the databse. Can't load VIP data."));
			e.printStackTrace();
		}
		
		players.put(uuid, player);
		return player;
	}

	public ARPlayer getOrCreatePlayer(String uuid) {
		return getOrCreatePlayer(UUID.fromString(uuid));
	}
}
