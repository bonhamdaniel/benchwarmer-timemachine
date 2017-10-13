package com.benchwarmer.spring.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.benchwarmer.spring.model.Player;
import com.benchwarmer.spring.model.Skaterseasons;

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
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayers(String positions) {
		Session session = sessionFactory.openSession();
		List<Player> players;
		switch(positions) {
			case("C"):	  players = session.createQuery("from Player where position = 'C'").getResultList();
			  			  break;
			case("LW"):	  players = session.createQuery("from Player where position = 'L'").getResultList();
			  			  break;
			case("RW"):	  players = session.createQuery("from Player where position = 'R'").getResultList();
			  		      break;
			case("W"):	  players = session.createQuery("from Player where (position = 'L' or position = 'R')").getResultList();
			  			  break;
			case("F"):	  players = session.createQuery("from Player where (position != 'D' and position != 'G')").getResultList();
			  			  break;
			case("D"):	  players = session.createQuery("from Player where position = 'D'").getResultList();
			  			  break;
			case("G"):	  players = session.createQuery("from Player where position = 'G'").getResultList();
			  		      break;
			default: 	  players = session.createQuery("from Player where position != 'G'").getResultList();
		}
		players.sort(Player.PlayerComparator);
		session.close();
		return players;
	}
}
