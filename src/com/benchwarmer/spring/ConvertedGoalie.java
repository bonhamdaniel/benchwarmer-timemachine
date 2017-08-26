/**
 * ConvertedGoalie is used to transform and hold goalie stats for the NHL Time Machine web application.
 * It provides functionality to calculate, sort, and retrieve era adjusted goalie stats.
 * 
 * @author  Dan Bonham
 * @version 1.0.0
 * @since 	2017-08-26
 */
package com.benchwarmer.spring;

import java.util.Comparator;
import java.util.List;
import org.decimal4j.util.DoubleRounder;

public class ConvertedGoalie {
	int rank = 0; // stores a goalie's rank given the current sort criteria
	String playername; // stores a goalie's name
	int seasonid; // stores a season id for a goalie's transformed stats
	int gp; // stores the gp for a goalie's transformed stats
	float toi; // stores the toi for a goalie's transformed stats
	int w; // stores the w for a goalie's transformed stats
	int l; // stores the l for a goalie's transformed stats
	int sa; // stores the sa for a goalie's transformed stats
	int sv; // stores the sv for a goalie's transformed stats
	float gaa; // stores the gaa for a goalie's transformed stats
	float svpct; // stores the svpct for a goalie's transformed stats
	float sa60; // stores the sa60 for a goalie's transformed stats
	
	// default constructor
	public ConvertedGoalie() {}
	
	// constructor used when passed a transformed goalieseasons record - initializes all summarized data
	public ConvertedGoalie(String playername, int seasonid, int gp, float toi, int w, int l, int sa, int sv) {
		this.playername = playername;
		this.seasonid = seasonid;
		this.gp = gp;
		this.toi = toi;
		this.w = w;
		this.l = l;
		this.sa = sa;
		this.sv = sv;
	} // constructor ConvertedGoalie()
	
	// adds instances of ConvertedGoalie - used to compile career stats
	public void addAll(List<ConvertedGoalie> cs) {
		for(ConvertedGoalie con : cs) { // loops through each instance of ConvertedGoalie and sums data fields
			this.gp += con.gp;
			this.toi += con.toi;
			this.w += con.w;
			this.l += con.l;
			this.sa += con.sa;
			this.sv += con.sv;
		} // for (ConvertedGoalie)
	} // addAll()
	
	// setter method for rank variable
	public void setRank(int r) {
		this.rank = r;
	} // setRank()
	
	// setter method for playername variable
	public void setName(String name) {
		this.playername = name;
	} // setName()
	
	// calculates and returns a goalie's career span
	public void setCareerSpan(int min, int max) {
		this.seasonid = ((min % 10000) -1) * 10000 + (max % 10000);
	} // setCareerSpan()
	
	// getter method for rank variable
	public int getRank() {
		return this.rank;
	} // getRank()
	
	// getter method for playername variable
	public String getPlayername() {
		return playername;
	} // getPlayername()
	
	// getter method for seasonid variable
	public int getSeasonid() {
		return seasonid;
	} // getSeasonid()
	
	// getter method for gp variable
	public int getGp() {
		return gp;
	} // getGp()
	
	// getter method for toi variable
	public float getToi() {
		return (float)DoubleRounder.round(toi, 1);
	} // getToi()
	
	// getter method for w variable
	public int getW() {
		return w;
	} // getW()
	
	// getter method for l variable
	public int getL() {
		return l;
	} // getL()
	
	// getter method for sa variable
	public int getSa() {
		return sa;
	} // getSa()
	
	// getter method for sv variable
	public int getSv() {
		return sv;
	} // getSv()
	
	// getter method for gaa variable
	public float getGaa() {
		return (float)DoubleRounder.round((1.0f*sa-1.0f*sv) / (toi*1.0f) * 60.0f, 2);
	} // getGaa()
	
	// getter method for svpct variable
	public float getSvpct() {
		return (float)DoubleRounder.round((1.0f*sv) / (1.0f*sa), 3);
	} // getSvpct()
	
	// getter method for sa60 variable
	public float getSa60() {
		return (float)DoubleRounder.round(1.0f*sa / toi * 60.0f, 1);
	} // getSa60()
	
	// compares ConvertedGoalie instances, returning value indicating ascending order of goals against average
	public int compareTo(ConvertedGoalie convertedGoalie) {
		return (int)(convertedGoalie.getGaa()*1000) - (int)(this.getGaa()*1000);
	} // compareTo
	
	// compares ConvertedGoalie instances based on playername, returning value indicating ascending order
	public int compareToName(ConvertedGoalie cs) {
		int i = 0;
		while (i < cs.playername.length() && i < this.playername.length()) { // loops until order can be determined
			if (this.playername.charAt(i) != cs.playername.charAt(i)) // tests if current index can determine order
				return this.playername.charAt(i) - cs.playername.charAt(i); // returns result of comparison
			i++; // increments counter
		} // while
		if (cs.playername.length() < this.playername.length()) return 1; // determines order based on length of name
		else return -1;
	} // compareToName()
	
	// implements sort for ConvertedGoalie collections based on goals against average
	public static Comparator<ConvertedGoalie> Comparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
		 return convertedGoalie1.compareTo(convertedGoalie2);
		}
	}; // Comparator (gaa)
	
	// implements sort for ConvertedGoalie collections based on playername
	public static Comparator<ConvertedGoalie> PlayerComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie1.compareToName(convertedGoalie2);
		}
	}; // Comparator (playername)
	
	// implements sort for ConvertedGoalie collections based on games played
	public static Comparator<ConvertedGoalie> GpComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.gp - convertedGoalie1.gp;
		}
	}; // Comparator (gp)
	
	// implements sort for ConvertedGoalie collections based on time on ice
	public static Comparator<ConvertedGoalie> ToiComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getToi()*10) - (int)(convertedGoalie1.getToi()*10);
		}
	}; // Comparator (toi)
	
	// implements sort for ConvertedGoalie collections based on wins
	public static Comparator<ConvertedGoalie> WComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.w - convertedGoalie1.w;
		}
	}; // Comparator (w)
	
	// implements sort for ConvertedGoalie collections based on losses
	public static Comparator<ConvertedGoalie> LComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.l - convertedGoalie1.l;
		}
	}; // Comparator (l)
	
	// implements sort for ConvertedGoalie collections based on shots against
	public static Comparator<ConvertedGoalie> SaComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.sa - convertedGoalie1.sa;
		}
	}; // Comparator (sa)
	
	
	// implements sort for ConvertedGoalie collections based on saves
	public static Comparator<ConvertedGoalie> SvComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.sv - convertedGoalie1.sv;
		}
	}; // Comparator (sv)
	
	// implements sort for ConvertedGoalie collections based on goals against average
	public static Comparator<ConvertedGoalie> GaaComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie1.getGaa()*1000) - (int)(convertedGoalie2.getGaa()*1000);
		}
	}; // Comparator (gaa)
	
	// implements sort for ConvertedGoalie collections based on save percentage
	public static Comparator<ConvertedGoalie> SvpctComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getSvpct()*1000) - (int)(convertedGoalie1.getSvpct()*1000);
		}
	}; // Comparator (svpct)
	
	// implements sort for ConvertedGoalie collections based on shots against per 60
	public static Comparator<ConvertedGoalie> Sa60Comparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getSa60()*1000) - (int)(convertedGoalie1.getSa60()*1000);
		}
	}; // Comparator (sa60)
	
	// returns the appropriate Comparator object based on the passed column value
	public static Comparator<ConvertedGoalie> getSort(String column) {
		switch(column) { // determines and returns appropriate Comparator object
			case("player"): return ConvertedGoalie.PlayerComparator;
			case("gp"): return ConvertedGoalie.GpComparator;
			case("toi"): return ConvertedGoalie.ToiComparator;
			case("w"): return ConvertedGoalie.WComparator;
			case("l"): return ConvertedGoalie.LComparator;
			case("sa"): return ConvertedGoalie.SaComparator;
			case("sv"): return ConvertedGoalie.SvComparator;
			case("gaa"): return ConvertedGoalie.GaaComparator;
			case("svpct"): return ConvertedGoalie.SvpctComparator;
			case("sa60"): return ConvertedGoalie.Sa60Comparator;
			default: return ConvertedGoalie.GaaComparator;
		} // switch (column)
	} // getSort()
} // Class ConvertedGoalie
