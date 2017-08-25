package com.benchwarmer.spring.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.benchwarmer.spring.model.Season;

@Component
@Transactional
public class SeasonDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Season> seasonList() {
		Session session = sessionFactory.openSession();
		List<Season> seasons = session.createQuery("from Season").getResultList();
		seasons.sort(Season.SeasonComparator);
		session.close();
		return seasons;
	}
	
	@SuppressWarnings("unchecked")
	public List<Season> getSeason(int seasonID) {
		Session session = sessionFactory.openSession();
		List<Season> season = session.createQuery("from Season where seasonid = " + seasonID).getResultList();
		session.close();
		return season;
	}
}
