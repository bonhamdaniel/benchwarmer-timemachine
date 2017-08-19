package com.benchwarmer.spring;

import java.util.Comparator;
import java.util.List;

import org.decimal4j.util.DoubleRounder;

public class ConvertedGoalie {
	int rank = 0;
	String playername;
	int seasonid;
	int gp;
	float toi;
	int w;
	int l;
	int sa;
	int sv;
	float gaa;
	float svpct;
	float sa60;
	
	public ConvertedGoalie() {}
	
	public ConvertedGoalie(String playername, int seasonid, int gp, float toi, int w, int l, int sa, int sv/*, float sa60, float gaa, float svpct*/) {
		this.playername = playername;
		this.seasonid = seasonid;
		this.gp = gp;
		this.toi = toi;
		this.w = w;
		this.l = l;
		this.sa = sa;
		this.sv = sv;
		//this.gaa = gaa;
		//this.svpct = svpct;
		//this.sa60 = sa60;
	}
	
	public void addAll(List<ConvertedGoalie> cs) {
		for(ConvertedGoalie con : cs) {
			this.gp += con.gp;
			this.toi += con.toi;
			this.w += con.w;
			this.l += con.l;
			this.sa += con.sa;
			this.sv += con.sv;
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
		return playername;
	}
	
	public int getSeasonid() {
		return seasonid;
	}
	
	public int getGp() {
		return gp;
	}
	
	public float getToi() {
		return (float)DoubleRounder.round(toi, 1);
	}
	
	public int getW() {
		return w;
	}
	
	public int getL() {
		return l;
	}
	
	public int getSa() {
		return sa;
	}
	
	public int getSv() {
		return sv;
	}
	
	public float getGaa() {
		return (float)DoubleRounder.round((1.0f*sa-1.0f*sv) / (toi*1.0f) * 60.0f, 2);
	}
	
	public float getSvpct() {
		return (float)DoubleRounder.round((1.0f*sv) / (1.0f*sa), 3);
	}
	
	public float getSa60() {
		return (float)DoubleRounder.round(1.0f*sa / toi * 60.0f, 1);
	}
	
	public int compareTo(ConvertedGoalie convertedGoalie) {
		return (int)(convertedGoalie.getGaa()*1000) - (int)(this.getGaa()*1000);
	}
	
	public int compareToName(ConvertedGoalie cs) {
		int i = 0;
		while (i < cs.playername.length() && i < this.playername.length()) {
			if (this.playername.charAt(i) != cs.playername.charAt(i))
				return this.playername.charAt(i) - cs.playername.charAt(i);
			i++;
		}
		if (cs.playername.length() < this.playername.length()) return 1;
		else return -1;
	}
	
	public static Comparator<ConvertedGoalie> Comparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
		 return convertedGoalie1.compareTo(convertedGoalie2);
		}
	};
	
	public static Comparator<ConvertedGoalie> PlayerComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie1.compareToName(convertedGoalie2);
		}
	};
	
	public static Comparator<ConvertedGoalie> GpComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.gp - convertedGoalie1.gp;
		}
	};
	
	public static Comparator<ConvertedGoalie> ToiComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getToi()*10) - (int)(convertedGoalie1.getToi()*10);
		}
	};
	
	public static Comparator<ConvertedGoalie> WComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.w - convertedGoalie1.w;
		}
	};
	
	public static Comparator<ConvertedGoalie> LComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.l - convertedGoalie1.l;
		}
	};
	
	public static Comparator<ConvertedGoalie> SaComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.sa - convertedGoalie1.sa;
		}
	};
	
	public static Comparator<ConvertedGoalie> SvComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return convertedGoalie2.sv - convertedGoalie1.sv;
		}
	};
	
	public static Comparator<ConvertedGoalie> GaaComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie1.getGaa()*1000) - (int)(convertedGoalie2.getGaa()*1000);
		}
	};
	
	public static Comparator<ConvertedGoalie> SvpctComparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getSvpct()*1000) - (int)(convertedGoalie1.getSvpct()*1000);
		}
	};
	
	public static Comparator<ConvertedGoalie> Sa60Comparator = new Comparator<ConvertedGoalie>() {
		public int compare(ConvertedGoalie convertedGoalie1, ConvertedGoalie convertedGoalie2) {
			return (int)(convertedGoalie2.getSa60()*1000) - (int)(convertedGoalie1.getSa60()*1000);
		}
	};
	
	public static Comparator<ConvertedGoalie> getSort(String column) {
		switch(column) {
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
		}
	}
}
