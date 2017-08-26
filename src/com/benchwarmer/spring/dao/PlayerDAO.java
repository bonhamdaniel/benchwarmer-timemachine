package com.benchwarmer.spring.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.model.Player;

@Component
@Transactional
public class PlayerDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Player> playerList() {
		Session session = sessionFactory.openSession();
		List<Player> players = session.createQuery("from Player").getResultList();
		players.sort(Player.PlayerComparator);
		session.close();
		return players;
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayer(int playerID) {
		Session session = sessionFactory.openSession();
		List<Player> player = session.createQuery("from Player where playerid = " + playerID).getResultList();
		session.close();
		return player;
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayer(String position) {
		Session session = sessionFactory.openSession();
		List<Player> player = session.createQuery("from Player where position = " + position).getResultList();
		session.close();
		return player;
	}
}
