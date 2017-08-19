package com.benchwarmer.spring;

import java.util.Comparator;
import java.util.List;

import org.decimal4j.util.DoubleRounder;

import com.benchwarmer.spring.model.Player;

public class ConvertedSkater implements Comparable<ConvertedSkater> {
	int rank = 0;
	String playername;
	long seasonid;
	int gp;
	int evg;
	int eva;
	int ppg;
	int ppa;
	int shg;
	int sha;
	int pim;
	int s;
	int g;
	int a;
	int pts;
	int evp;
	int ppp;
	int shp;
	float gpg;
	float apg;
	float pperg;
	
	public ConvertedSkater() {}
	
	public ConvertedSkater(String playername, long seasonid, int gp, int evg, int eva, int ppg, int ppa, int shg, int sha, int pim, int s, int g, int a, int pts, int evp, int ppp, int shp, float gpg, float apg, float pperg) {
		this.playername = playername;
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
	}
	
	public void addAll(List<ConvertedSkater> cs) {
		for(ConvertedSkater con : cs) {
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
		}
	}
	
	public void setRank(int r) {
		this.rank = r;
	}
	
	public void setName(String name) {
		this.playername = name;
	}
	
	public void setCareerSpan(int min, int max) {
		this.seasonid = ((min % 10000) -1) * 10000 + (max % 10000);
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public String getPlayername() {
		return this.playername;
	}
	
	public long getSeasonid() {
		return this.seasonid;
	}
	
	public int getGp() {
		return this.gp;
	}
	
	public int getG() {
		return this.g;
	}
	
	public int getA() {
		return this.a;
	}
	
	public int getP() {
		return this.a + this.g;
	}
	
	public int getPim() {
		return this.pim;
	}
	
	public int getS() {
		return this.s;
	}
	
	public double getGGP() {
		return DoubleRounder.round(1.0 * this.g / this.gp * 1.0, 3);
	}
	
	public double getAGP() {
		return DoubleRounder.round(1.0 * this.a / this.gp * 1.0, 3);
	}
	
	public double getPGP() {
		return DoubleRounder.round(1.0 * this.getP() / this.gp * 1.0, 3);
	}
	
	public int getPpg() {
		return this.ppg;
	}
	
	public int getPpa() {
		return this.ppa;
	}
	
	public int getPpp() {
		return this.ppp;
	}
	
	public int getEvg() {
		return this.evg;
	}
	
	public int getEva() {
		return this.eva;
	}
	
	public int getEvp() {
		return this.evp;
	}
	
	public int getShg() {
		return this.shg;
	}
	
	public int getSha() {
		return this.sha;
	}
	
	public int getShp() {
		return this.shp;
	}
	
	public int compareTo(ConvertedSkater convertedSkater) {
		return convertedSkater.pts - this.pts;
	}
	
	public int compareToName(ConvertedSkater cs) {
		int i = 0;
		while (i < cs.playername.length() && i < this.playername.length()) {
			if (this.playername.charAt(i) != cs.playername.charAt(i))
				return this.playername.charAt(i) - cs.playername.charAt(i);
			i++;
		}
		if (cs.playername.length() < this.playername.length()) return 1;
		else return -1;
	}
	
	public static Comparator<ConvertedSkater> Comparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
		 return convertedSkater1.compareTo(convertedSkater2);
		}
	};
	
	public static Comparator<ConvertedSkater> PlayerComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater1.compareToName(convertedSkater2);
		}
	};
	
	public static Comparator<ConvertedSkater> GPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.gp - convertedSkater1.gp;
		}
	};
	
	public static Comparator<ConvertedSkater> GComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.g - convertedSkater1.g;
		}
	};
	
	public static Comparator<ConvertedSkater> AComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.a - convertedSkater1.a;
		}
	};
	
	public static Comparator<ConvertedSkater> PtsComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.pts - convertedSkater1.pts;
		}
	};
	
	public static Comparator<ConvertedSkater> PimComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.pim - convertedSkater1.pim;
		}
	};
	
	public static Comparator<ConvertedSkater> SComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.s - convertedSkater1.s;
		}
	};
	
	public static Comparator<ConvertedSkater> GGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getGGP()*1000) - (int)(convertedSkater1.getGGP()*1000);
		}
	};
	
	public static Comparator<ConvertedSkater> AGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getAGP()*1000) - (int)(convertedSkater1.getAGP()*1000);
		}
	};
	
	public static Comparator<ConvertedSkater> PGPComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return (int)(convertedSkater2.getPGP()*1000) - (int)(convertedSkater1.getPGP()*1000);
		}
	};
	
	public static Comparator<ConvertedSkater> EvgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.evg - convertedSkater1.evg;
		}
	};
	
	public static Comparator<ConvertedSkater> EvaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.eva - convertedSkater1.eva;
		}
	};
	
	public static Comparator<ConvertedSkater> EvpComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.evp - convertedSkater1.evp;
		}
	};
	
	public static Comparator<ConvertedSkater> PpgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppg - convertedSkater1.ppg;
		}
	};
	
	public static Comparator<ConvertedSkater> PpaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppa - convertedSkater1.ppa;
		}
	};
	
	public static Comparator<ConvertedSkater> PppComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.ppp - convertedSkater1.ppp;
		}
	};
	
	public static Comparator<ConvertedSkater> ShgComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.shg - convertedSkater1.shg;
		}
	};
	
	public static Comparator<ConvertedSkater> ShaComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.sha - convertedSkater1.sha;
		}
	};
	
	public static Comparator<ConvertedSkater> ShpComparator = new Comparator<ConvertedSkater>() {
		public int compare(ConvertedSkater convertedSkater1, ConvertedSkater convertedSkater2) {
			return convertedSkater2.shp - convertedSkater1.shp;
		}
	};
	
	public static Comparator<ConvertedSkater> getSort(String column) {
		System.out.println("The column is ......... " + column);
		switch(column) {
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
		}
	}
}
