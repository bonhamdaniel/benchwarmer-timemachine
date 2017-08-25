package com.benchwarmer.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.dao.SkaterSeasonsDAO;
import com.benchwarmer.spring.model.Skaterseasons;

@Component
public class SkaterSeasonsService {
	@Autowired
	private SkaterSeasonsDAO skaterSeasonsDAO;
	
	public SkaterSeasonsService() {}
	
	public SkaterSeasonsDAO getSkaterSeasonsDAO() {
		return skaterSeasonsDAO;
	}
	
	public void setSkaterSeasonsDAO(SkaterSeasonsDAO skaterSeasonsDAO) {
		this.skaterSeasonsDAO = skaterSeasonsDAO;
	}
	
	public List<Skaterseasons> skaterList() {
		return skaterSeasonsDAO.skaterList();
	}
	
	public List<Skaterseasons> skaterList(int baseSeason, int minGP) {
		return skaterSeasonsDAO.skaterList(baseSeason, minGP);
	}
	
	public List<Skaterseasons> playerSeasons(int playerid) {
		return skaterSeasonsDAO.playerSeasons(playerid);
	}
}
