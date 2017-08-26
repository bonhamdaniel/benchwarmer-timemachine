package com.benchwarmer.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.dao.PlayerDAO;
import com.benchwarmer.spring.model.Player;

@Component
public class PlayerService {
	@Autowired
	private PlayerDAO playerDAO;
	
	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}
	
	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	public List<Player> playerList() {
		return playerDAO.playerList();
	}
	
	public List<Player> getPlayer(int playerid) {
		return playerDAO.getPlayer(playerid);
	}
	
	public List<Player> getPlayer(String position) {
		return playerDAO.getPlayer(position);
	}
}
