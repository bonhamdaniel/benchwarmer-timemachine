package com.benchwarmer.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.dao.SeasonDAO;
import com.benchwarmer.spring.model.Season;

@Component
public class SeasonService {
	@Autowired
	private SeasonDAO seasonDAO;
	
	public SeasonService() {}
	
	public SeasonDAO getSeasonDAO() {
		return seasonDAO;
	}
	
	public void setSeasonDAO(SeasonDAO seasonDAO) {
		this.seasonDAO = seasonDAO;
	}
	
	public List<Season> seasonList() {
		return seasonDAO.seasonList();
	}
	
	public List<Season> getSeason(int seasonID) {
		return seasonDAO.getSeason(seasonID);
	}
}
