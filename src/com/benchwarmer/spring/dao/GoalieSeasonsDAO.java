package com.benchwarmer.spring.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.model.Goalieseasons;

@Component
@Transactional
public class GoalieSeasonsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Goalieseasons> goalieList() {
		Session session = sessionFactory.openSession();
		List<Goalieseasons> goalies = session.createQuery("from Goalieseasons").getResultList();
		session.close();
		return goalies;
	}
	
	@SuppressWarnings("unchecked")
	public List<Goalieseasons> goalieList(int baseSeason, int minGP) {
		Session session = sessionFactory.openSession();
		List<Goalieseasons> goalies = session.createQuery("from Goalieseasons where seasonid = " + baseSeason + " and gp >= " + minGP).getResultList();
		session.close();
		return goalies;
	}
	
	@SuppressWarnings("unchecked")
	public List<Goalieseasons> goalieSeasons(int playerid) {
		Session session = sessionFactory.openSession();
		List<Goalieseasons> goalies = session.createQuery("from Goalieseasons where playerid = " + playerid).getResultList();
		session.close();
		return goalies;
	}
}
