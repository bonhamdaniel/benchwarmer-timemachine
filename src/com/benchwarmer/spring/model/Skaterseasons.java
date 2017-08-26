package com.benchwarmer.spring.model;
// default package
// Generated Aug 5, 2017 5:24:43 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Skaterseasons")
public class Skaterseasons {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "playerid")
	private int playerid;

	@Column(name = "seasonid")
	private int seasonid;

	@Column(name = "playername")
	private String playername;

	@Column(name = "gp")
	private int gp;

	@Column(name = "goals")
	private int goals;

	@Column(name = "assists")
	private int assists;

	@Column(name = "plusminus")
	private int plusminus;

	@Column(name = "pim")
	private int pim;

	@Column(name = "shots")
	private int shots;

	@Column(name = "ppg")
	private int ppg;

	@Column(name = "ppa")
	private int ppa;

	@Column(name = "shg")
	private int shg;

	@Column(name = "sha")
	private int sha;

	@Column(name = "position")
	private String position;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerid() {
		return this.playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public int getSeasonid() {
		return this.seasonid;
	}

	public void setSeasonid(int seasonid) {
		this.seasonid = seasonid;
	}

	public String getPlayername() {
		return this.playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public int getGp() {
		return this.gp;
	}

	public void setGp(int gp) {
		this.gp = gp;
	}

	public int getGoals() {
		return this.goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return this.assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getPlusminus() {
		return this.plusminus;
	}

	public void setPlusminus(int plusminus) {
		this.plusminus = plusminus;
	}

	public int getPim() {
		return this.pim;
	}

	public void setPim(int pim) {
		this.pim = pim;
	}

	public int getShots() {
		return this.shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public int getPpg() {
		return this.ppg;
	}

	public void setPpg(int ppg) {
		this.ppg = ppg;
	}

	public int getPpa() {
		return this.ppa;
	}

	public void setPpa(int ppa) {
		this.ppa = ppa;
	}

	public int getShg() {
		return this.shg;
	}

	public void setShg(int shg) {
		this.shg = shg;
	}

	public int getSha() {
		return this.sha;
	}

	public void setSha(int sha) {
		this.sha = sha;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
