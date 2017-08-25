package com.benchwarmer.spring.model;
// default package
// Generated Aug 5, 2017 5:24:43 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Goalieseasons")
public class Goalieseasons {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "playerid")
	private int playerid;

	@Column(name = "seasonid")
	private int seasonid;

	@Column(name = "playername")
	private String playername;

	@Column(name = "toi")
	private float toi;

	@Column(name = "sa")
	private int sa;

	@Column(name = "sv")
	private int sv;

	@Column(name = "w")
	private int w;

	@Column(name = "l")
	private int l;

	@Column(name = "sow")
	private int sow;

	@Column(name = "sol")
	private int sol;

	@Column(name = "otw")
	private int otw;

	@Column(name = "otl")
	private int otl;

	@Column(name = "gp")
	private int gp;

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

	public float getToi() {
		return this.toi;
	}

	public void setToi(float toi) {
		this.toi = toi;
	}

	public int getSa() {
		return this.sa;
	}

	public void setSa(int sa) {
		this.sa = sa;
	}

	public int getSv() {
		return this.sv;
	}

	public void setSv(int sv) {
		this.sv = sv;
	}

	public int getW() {
		return this.w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getL() {
		return this.l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getSow() {
		return this.sow;
	}

	public void setSow(int sow) {
		this.sow = sow;
	}

	public int getSol() {
		return this.sol;
	}

	public void setSol(int sol) {
		this.sol = sol;
	}

	public int getOtw() {
		return this.otw;
	}

	public void setOtw(int otw) {
		this.otw = otw;
	}

	public int getOtl() {
		return this.otl;
	}

	public void setOtl(int otl) {
		this.otl = otl;
	}

	public int getGp() {
		return this.gp;
	}

	public void setGp(int gp) {
		this.gp = gp;
	}

}
