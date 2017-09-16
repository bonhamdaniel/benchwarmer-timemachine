/**
 * ConvertedSkater is used to transform and hold skater stats for the NHL Time Machine web application.
 * It provides functionality to calculate, sort, and retrieve era adjusted skater stats.
 * 
 * @author  Dan Bonham
 * @version 1.0.0
 * @since 	2017-08-26
 */
package com.benchwarmer.spring;

import java.util.Comparator;
import java.util.List;
import org.decimal4j.util.DoubleRounder;

public class ConvertedSkater implements Comparable<ConvertedSkater> {
	int rank = 0; // stores a skater's rank given the current sort criteria
	String playername; // stores a skater's name
	String position; // stores a skater's position
	long seasonid; // stores a season id for a skater's transformed stats
	int gp; // stores games played for a skater's transformed stats
	int evg; // stores even strength goals for a skater's transformed stats
	int eva; // stores even strength assists for a skater's transformed stats
	int ppg; // stores power play goals for a skater's transformed stats
	int ppa; // stores power play assists for a skater's transformed stats
	int shg; // stores short handed goals for a skater's transformed stats
	int sha; // stores short handed assists for a skater's transformed stats
	int pim; // stores penalty minutes for a skater's transformed stats
	int s; // stores shots for a skater's transformed stats
	int g; // stores goals for a skater's transformed stats
	int a; // stores assists for a skater's transformed stats
	int pts; // stores points for a skater's transformed stats
	int evp; // stores even strength points for a skater's transformed stats
	int ppp; // stores power play points for a skater's transformed stats
	int shp; // stores short handed points for a skater's transformed stats
	float gpg; // stores goals per game for a skater's transformed stats
	float apg; // stores assists per game for a skater's transformed stats
	float pperg; // stores points per game for a skater's transformed stats
	
	// default constructor
	public ConvertedSkater() {}
	
	// construct used when passed a skater's transformed stats for a season - sets initial values based on era adjustments
	public ConvertedSkater(String playername, String position, long seasonid, int gp, int evg, int eva, int ppg, int ppa, int shg, int sha, int pim, int s, int g, int a, int pts, int evp, int ppp, int shp, float gpg, float apg, float pperg) {
		this.playername = playername;
		this.position = position;
		this.seasonid = seasonid;
		this.gp = gp;
		this.evg = evg;
		this.eva = eva;
		this.ppg = ppg;
		this.ppa = ppa;
		this.shg = shg;
		this.sha = sha;
		this.pim = pim;
		this.s = s;
		this.g = g;
		this.a = a;
		this.pts = pts;
		this.evp = evp;
		this.ppp = ppp;
		this.shp = shp;
		this.gpg = gpg;
		this.apg = apg;
		this.pperg = pperg;
	} // CovertedSkater()
	
	// sums two instances of ConvertedSkater - used to compile career data for a skater
	public void addAll(List<ConvertedSkater> cs) {
		for(ConvertedSkater con : cs) { // loops through each season of skater data
			this.gp += con.gp;
			this.evg += con.evg;
			this.eva += con.eva;
			this.ppg += con.ppg;
			this.ppa += con.ppa;
			this.shg += con.shg;
			this.sha += con.sha;
			this.pim += con.pim;
			this.s += con.s;
			this.g += con.g;
			this.a += con.a;
			this.pts += con.pts;
			this.evp += con.evp;
			this.ppp += con.ppp;
			this.shp += con.shp;
		} // for (season)
	} // addAll()
	
	// setter method for rank variable
	public void setRank(int r) {
		this.rank = r;
	} // setRank()

	// setter method for playername variable
	public void setNameAndPosition(String name, String position) {
		this.playername = name;
		this.position = position;
	} // setName()

	// calculates and returns a player's career span
	public void setCareerSpan(int min, int max) {
		this.seasonid = ((min % 10000) -1) * 10000 + (max % 10000);
	} // setCareerSpan()

	// getter method for rank variable
	public int getRank() {
		return this.rank;
	} // getRank()

	// getter method for playername variable
	public String getPlayername() {
		return this.playername;
	} // getPlayername()

	// getter method for playername variable
	public String getPosition() {
		return this.position;
	} // getPlayername()

	// getter method for seasonid variable
	public long getSeasonid() {
		return this.seasonid;
	} // getSeasonid()/

	// getter method for gp variable
	public int getGp() {
		return this.gp;
	} // getGp()

	// getter method for g variable
	public int getG() {
		return this.g;
	} // getG()

	// getter method for assists variable
	public int getA() {
		return this.a;
	} // getA()

	// getter method for p variable
	public int getP() {
		return this.a + this.g;
	} // getP()

	// getter method for pim variable
	public int getPim() {
		return this.pim;
	} // getPim()

	// getter method for s variable
	public int getS() {
		return this.s;
	} // getS()

	// getter method for gpg variable
	public double getGGP() {
		return DoubleRounder.round(1.0 * this.g / this.gp * 1.0, 3);
	} // getGGP()

	// getter method for apg variable
	public double getAGP() {
		return DoubleRounder.round(1.0 * this.a / this.gp * 1.0, 3);
	} // getAPG()

	// getter method for pperg variable
	public double getPGP() {
		return DoubleRounder.round(1.0 * this.getP() / this.gp * 1.0, 3);
	} // getPGP()

	// getter method for ppg variable
	public int getPpg() {
		return this.ppg;
	} // getPpg()

	// getter method for ppa variable
	public int getPpa() {
		return this.ppa;
	} // getPpa()

	// getter method for ppp variable
	public int getPpp() {
		return this.ppp;
	} // getPpp()

	// getter method for evg variable
	public int getEvg() {
		return this.evg;
	} // getEvg()

	// getter method for eva variable
	public int getEva() {
		return this.eva;
	} // getEva()

	// getter method for evp variable
	public int getEvp() {
		return this.evp;
	} // getEvp()

	// getter method for sg variable
	public int getShg() {
		return this.shg;
	} // getShg()

	// getter method for sha variable
	public int getSha() {
		return this.sha;
	} // getSha()

	// getter method for shp variable
	public int getShp() {
		return this.shp;
	} // getShp()

	// compares ConvertedSkater instances, returning value indicating descending order of points
	public int compareTo(ConvertedSkater convertedSkater) {
		return convertedSkater.pts - this.pts;
	} // compareTo(pts)
	
	// compares ConvertedSkater instances based on playername, returning value indicating ascending order
	public int compareToName(ConvertedSkater cs) {
		int i = 0;
		while (i < cs.playername.length() && i < this.playername.length()) { // loops through names until order can be determined
			if (this.playername.charAt(i) != cs.playername.charAt(i)) // checks if order can be determined by current index
				return this.playername.charAt(i) - cs.playername.charAt(i); // returns result when applicable
			i++; // increments loop counter
		} // while
		if (cs.playername.length() < this.playername.length()) return 1; // determines result based on differences in name length
		else return -1;
	} // compareToName()
	
	// implements sort for ConvertedSkater collections based on points
	public static Comparator<ConvertedSkater> Comparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
		 return convertedSkater1.compareTo(convertedSkater2);
		}
	}; // Comparator (points)

	// implements sort for ConvertedSkater collections based on playername
	public static Comparator<ConvertedSkater> PlayerComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater1.compareToName(convertedSkater2);
		}
	}; // Comparator (playername)

	// implements sort for ConvertedSkater collections based on games played
	public static Comparator<ConvertedSkater> GPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.gp - convertedSkater1.gp;
		}
	}; // Comparator (gp)

	// implements sort for ConvertedSkater collections based on goals
	public static Comparator<ConvertedSkater> GComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.g - convertedSkater1.g;
		}
	}; // Comparator (g)

	// implements sort for ConvertedSkater collections based on assists
	public static Comparator<ConvertedSkater> AComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.a - convertedSkater1.a;
		}
	}; // Comparator (a)

	// implements sort for ConvertedSkater collections based on points
	public static Comparator<ConvertedSkater> PtsComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.pts - convertedSkater1.pts;
		}
	}; // Comparator (pts)

	// implements sort for ConvertedSkater collections based on penalty minutes
	public static Comparator<ConvertedSkater> PimComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.pim - convertedSkater1.pim;
		}
	}; // Comparator (pim)

	// implements sort for ConvertedSkater collections based on shots
	public static Comparator<ConvertedSkater> SComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.s - convertedSkater1.s;
		}
	}; // Comparator (s)
	
	// implements sort for ConvertedSkater collections based on goals per game
	public static Comparator<ConvertedSkater> GGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getGGP()*1000) - (int)(convertedSkater1.getGGP()*1000);
		}
	}; // Comparator (gpg)
	
	// implements sort for ConvertedSkater collections based on assists per game
	public static Comparator<ConvertedSkater> AGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getAGP()*1000) - (int)(convertedSkater1.getAGP()*1000);
		}
	}; // Comparator (apg)
	
	// implements sort for ConvertedSkater collections based on points per game
	public static Comparator<ConvertedSkater> PGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getPGP()*1000) - (int)(convertedSkater1.getPGP()*1000);
		}
	}; // Comparator (pperg)
	
	// implements sort for ConvertedSkater collections based on even strength goals
	public static Comparator<ConvertedSkater> EvgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.evg - convertedSkater1.evg;
		}
	}; // Comparator (evg)
	
	// implements sort for ConvertedSkater collections based on even strength assists
	public static Comparator<ConvertedSkater> EvaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.eva - convertedSkater1.eva;
		}
	}; // Comparator (eva)
	
	// implements sort for ConvertedSkater collections based on even strength points
	public static Comparator<ConvertedSkater> EvpComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.evp - convertedSkater1.evp;
		}
	}; // Comparator (evp)
	
	// implements sort for ConvertedSkater collections based on power play goals
	public static Comparator<ConvertedSkater> PpgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppg - convertedSkater1.ppg;
		}
	}; // Comparator (ppg)
	
	// implements sort for ConvertedSkater collections based on power play assists
	public static Comparator<ConvertedSkater> PpaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppa - convertedSkater1.ppa;
		}
	}; // Comparator (ppa)
	
	// implements sort for ConvertedSkater collections based on power play points
	public static Comparator<ConvertedSkater> PppComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppp - convertedSkater1.ppp;
		}
	}; // Comparator (ppp)
	
	// implements sort for ConvertedSkater collections based on short handed goals
	public static Comparator<ConvertedSkater> ShgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.shg - convertedSkater1.shg;
		}
	}; // Comparator (shg)
	
	// implements sort for ConvertedSkater collections based on short handed assists
	public static Comparator<ConvertedSkater> ShaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.sha - convertedSkater1.sha;
		}
	}; // Comparator (sha)
	
	// implements sort for ConvertedSkater collections based on short handed points
	public static Comparator<ConvertedSkater> ShpComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.shp - convertedSkater1.shp;
		}
	}; // Comparator (shp)
	
	// returns the appropriate Comparator object based on the passed column value
	public static Comparator<ConvertedSkater> getSort(String column) {
		switch(column) { // determines and returns the appropriate Comparator object
			case("player"): return ConvertedSkater.PlayerComparator;
			case("gp"): return ConvertedSkater.GPComparator;
			case("g"): return ConvertedSkater.GComparator;
			case("a"): return ConvertedSkater.AComparator;
			case("pts"): return ConvertedSkater.PtsComparator;
			case("pim"): return ConvertedSkater.PimComparator;
			case("s"): return ConvertedSkater.SComparator;
			case("ggp"): return ConvertedSkater.GGPComparator;
			case("agp"): return ConvertedSkater.AGPComparator;
			case("pgp"): return ConvertedSkater.PGPComparator;
			case("evg"): return ConvertedSkater.EvgComparator;
			case("eva"): return ConvertedSkater.EvaComparator;
			case("evp"): return ConvertedSkater.EvpComparator;
			case("ppg"): return ConvertedSkater.PpgComparator;
			case("ppa"): return ConvertedSkater.PpaComparator;
			case("ppp"): return ConvertedSkater.PppComparator;
			case("shg"): return ConvertedSkater.ShgComparator;
			case("sha"): return ConvertedSkater.ShaComparator;
			case("shp"): return ConvertedSkater.ShpComparator;
			default: return ConvertedSkater.PtsComparator;
		} // switch (column)
	} // getSort()
} // Class ConvertedSkater