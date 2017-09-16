package com.benchwarmer.spring.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.model.Skaterseasons;

@Component
@Transactional
public class SkaterSeasonsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Skaterseasons> skaterList() {
		Session session = sessionFactory.openSession();
		List<Skaterseasons> skaters = session.createQuery("from Skaterseasons").getResultList();
		session.close();
		return skaters;
	}
	
	@SuppressWarnings("unchecked")
	public List<Skaterseasons> skaterList(int baseSeason, int minGP, String positions) {
		Session session = sessionFactory.openSession();
		List<Skaterseasons> skaters;
		switch(positions) {
			case("All") : skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP).getResultList();
								  break;
			case("C"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and position = 'C'").getResultList();
			  					  break;
			case("LW"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and position = 'L'").getResultList();
			  					  break;
			case("RW"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and position = 'R'").getResultList();
			  				      break;
			case("W"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and (position = 'L' or position = 'R')").getResultList();
			  					  break;
			case("F"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and position != 'D'").getResultList();
			  					  break;
			case("D"):			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP + " and position = 'D'").getResultList();
			  					  break;
			default: 			  skaters = session.createQuery("from Skaterseasons where seasonid = " + baseSeason + " and gp >= " + minGP).getResultList();
		}
		session.close();
		return skaters;
	}
	
	@SuppressWarnings("unchecked")
	public List<Skaterseasons> playerSeasons(int playerid) {
		Session session = sessionFactory.openSession();
		List<Skaterseasons> skaters = session.createQuery("from Skaterseasons where playerid = " + playerid).getResultList();
		session.close();
		return skaters;
	}
}
