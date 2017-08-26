package com.benchwarmer.spring.model;

import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player implements Comparable<Player> {
	@Id
	@Column(name="playerid")
	private int playerid;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="playername")
	private String playerName;
	
	@Column(name="birthdate")
	private String birthdate;
	
	@Column(name="country")
	private String country;
	
	@Column(name="height")
	private String height;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="hand")
	private String hand;
	
	@Column(name="position")
	private String position;
	
	public int getPlayerid() {
		return this.playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getHand() {
		return this.hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public int compareTo(Player player) {
		int i = 0;
		while (i < player.playerName.length() && i < this.playerName.length()) {
			if (this.playerName.charAt(i) != player.playerName.charAt(i))
				return this.playerName.charAt(i) - player.playerName.charAt(i);
			i++;
		}
		if (player.playerName.length() < this.playerName.length()) return 1;
		else return -1;
	}
	
	public static Comparator<Player> PlayerComparator = new Comparator<Player>() {
		public int compare(Player player1, Player player2) {
		 return player1.compareTo(player2);
		}
	};
}
