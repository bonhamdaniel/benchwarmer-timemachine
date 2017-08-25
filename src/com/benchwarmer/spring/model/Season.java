package com.benchwarmer.spring.model;
// default package
// Generated Aug 5, 2017 5:24:43 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Season")
public class Season implements Comparable<Season> {
	@Id
	@Column(name="seasonid")
	private int seasonid;
	
	@Column(name="seasontype")
	private String seasontype;
	
	@Column(name="gp")
	private int gp;
	
	@Column(name="g")
	private int g;
	
	@Column(name="evg")
	private int evg;
	
	@Column(name="ppg")
	private int ppg;
	
	@Column(name="shg")
	private int shg;
	
	@Column(name="a")
	private int a;
	
	@Column(name="pim")
	private int pim;
	
	@Column(name="s")
	private int s;
	
	@Column(name="g_gp")
	private float GGp;
	
	@Column(name="evg_gp")
	private float evgGp;
	
	@Column(name="ppg_gp")
	private float ppgGp;
	
	@Column(name="shg_gp")
	private float shgGp;
	
	@Column(name="a_gp")
	private float AGp;
	
	@Column(name="pim_gp")
	private float pimGp;
	
	@Column(name="s_gp")
	private float SGp;

	public int getSeasonid() {
		return this.seasonid;
	}

	public void setSeasonid(int seasonid) {
		this.seasonid = seasonid;
	}

	public String getSeasontype() {
		return this.seasontype;
	}

	public void setSeasontype(String seasontype) {
		this.seasontype = seasontype;
	}

	public int getGp() {
		return this.gp;
	}

	public void setGp(int gp) {
		this.gp = gp;
	}

	public int getG() {
		return this.g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getEvg() {
		return this.evg;
	}

	public void setEvg(int evg) {
		this.evg = evg;
	}

	public int getPpg() {
		return this.ppg;
	}

	public void setPpg(int ppg) {
		this.ppg = ppg;
	}

	public int getShg() {
		return this.shg;
	}

	public void setShg(int shg) {
		this.shg = shg;
	}

	public int getA() {
		return this.a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getPim() {
		return this.pim;
	}

	public void setPim(int pim) {
		this.pim = pim;
	}

	public int getS() {
		return this.s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public float getGGp() {
		return this.GGp;
	}

	public void setGGp(float GGp) {
		this.GGp = GGp;
	}

	public float getEvgGp() {
		return this.evgGp;
	}

	public void setEvgGp(float evgGp) {
		this.evgGp = evgGp;
	}

	public float getPpgGp() {
		return this.ppgGp;
	}

	public void setPpgGp(float ppgGp) {
		this.ppgGp = ppgGp;
	}

	public float getShgGp() {
		return this.shgGp;
	}

	public void setShgGp(float shgGp) {
		this.shgGp = shgGp;
	}

	public float getAGp() {
		return this.AGp;
	}

	public void setAGp(float AGp) {
		this.AGp = AGp;
	}

	public float getPimGp() {
		return this.pimGp;
	}

	public void setPimGp(float pimGp) {
		this.pimGp = pimGp;
	}

	public float getSGp() {
		return this.SGp;
	}

	public void setSGp(float SGp) {
		this.SGp = SGp;
	}
	
	public int compareTo(Season season) {
		return season.seasonid - this.seasonid;
	}
	
	public static Comparator<Season> SeasonComparator = new Comparator<Season>() {
		public int compare(Season season1, Season season2) {
		 return season1.compareTo(season2);
		}
	};
}
