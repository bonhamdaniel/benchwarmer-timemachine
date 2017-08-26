package com.benchwarmer.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.dao.GoalieSeasonsDAO;
import com.benchwarmer.spring.model.Goalieseasons;

@Component
public class GoalieSeasonsService {
	@Autowired
	private GoalieSeasonsDAO goalieSeasonsDAO;
	
	public GoalieSeasonsService() {}
	
	public GoalieSeasonsDAO getGoalieDAO() {
		return goalieSeasonsDAO;
	}
	
	public void setGoalieSeasonsDAO(GoalieSeasonsDAO goalieSeasonsDAO) {
		this.goalieSeasonsDAO = goalieSeasonsDAO;
	}
	
	public List<Goalieseasons> goalieList() {
		return goalieSeasonsDAO.goalieList();
	}
	
	public List<Goalieseasons> goalieList(int baseSeason, int minGP) {
		return goalieSeasonsDAO.goalieList(baseSeason, minGP);
	}
	
	public List<Goalieseasons> goalieSeasons(int playerid) {
		return goalieSeasonsDAO.goalieSeasons(playerid);
	}
}
