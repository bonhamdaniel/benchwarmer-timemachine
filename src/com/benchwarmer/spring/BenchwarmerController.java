package com.benchwarmer.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.benchwarmer.spring.service.GoalieSeasonsService;
import com.benchwarmer.spring.service.PlayerService;
import com.benchwarmer.spring.model.Goalieseasons;
import com.benchwarmer.spring.model.Player;
import com.benchwarmer.spring.service.SeasonService;
import com.benchwarmer.spring.model.Season;
import com.benchwarmer.spring.service.SkaterSeasonsService;
import com.benchwarmer.spring.model.Skaterseasons;

@Controller
@SessionAttributes({"playerbios", "searchableplayers", "seasons", "skater", "baseS", "targetS", "min", "sort", "pageNum", "players", "goalies"})
public class BenchwarmerController {
	@Autowired private SeasonService seasonService;
	@Autowired private SkaterSeasonsService skaterService;
	@Autowired private GoalieSeasonsService goalieService;
	@Autowired private PlayerService playerService;
	
	@ModelAttribute("playerbios") public List<Player> playerbios() { return playerService.playerList(); }
	@ModelAttribute("seasons") public List<Season> seasons() { return seasonService.seasonList(); }
	@ModelAttribute("baseS") public int baseS() { return 20162017; }
	@ModelAttribute("targetS") public int targetS() { return 20162017; }
	@ModelAttribute("min") public int min() { return 20; }
	@ModelAttribute("sort") public String sort() { return "pts"; }
	@ModelAttribute("pageNum") public int pageNum() { return 1; }
	@ModelAttribute("players") public List<ConvertedSkater> players() { return transformStats(skaterService.skaterList(20162017, 20), 20162017, 20162017); }
	@ModelAttribute("goalies") public List<ConvertedGoalie> goalies() { return transformGoalies(goalieService.goalieList(20162017, 20), 20162017, 20162017); }
	@ModelAttribute("searchableplayers")
	public Map<Integer, Player> searchableplayers(@ModelAttribute("playerbios") List<Player> playerbios) { 
		Map<Integer, Player> players = new HashMap<Integer, Player>();
		for(Player p : playerbios) players.put(p.getPlayerid(), p);
		return players;
	}
	
	@RequestMapping({"/timemachine", "/" })
	public String timemachine(@ModelAttribute("seasons") List<Season> seasons, @ModelAttribute("baseS") int baseS, @ModelAttribute("targetS") int targetS, @ModelAttribute("min") int min) {
		return "timemachine";
	}
	
	@RequestMapping(value = "/skaters")
	public String skaters(Model model) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("targetS", "20162017");
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		model.addAttribute("min", 20);
		return "skaters";
	}
	
	@RequestMapping(value = "/goalies")
	public String goalies(Model model) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("targetS", "20162017");
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		model.addAttribute("min", 20);
		return "goalies";
	}
	
	@RequestMapping(value = "/comparator")
	public String comparator(Model model, @ModelAttribute("playerbios") List<Player> playerbios, @ModelAttribute("searchableplayers") Map<Integer, Player> searchableplayers) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("p1", searchableplayers.get(8478550));
		model.addAttribute("p2", searchableplayers.get(8476453));
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		return "comparator";
	}
	
	@RequestMapping(value = "/skatertable", method = RequestMethod.GET)
	public String skatertable(@RequestParam Map<String, String> params, Model model, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("seasons") List<Season> seasons) {
		int targetSeason = Integer.parseInt(params.get("targetSeason"));
		int min = Integer.parseInt(params.get("min"));
		int baseSeason;
		if (params.get("baseSeason").equals("0")) {
			baseSeason = 0;
			players = new ArrayList<ConvertedSkater>();
			for (Season s : seasons)
				players.addAll(transformStats(skaterService.skaterList(s.getSeasonid(), min), s.getSeasonid(), targetSeason));
		}
		else {
			baseSeason = Integer.parseInt(params.get("baseSeason"));
			players = transformStats(skaterService.skaterList(baseSeason, min), baseSeason, targetSeason);
			if (params.containsKey("include")) {
				players.addAll(transformStats(skaterService.skaterList(targetSeason, min), targetSeason, targetSeason));
			}
		}
		sortSkaters(players, params.get("sort"));
		model.addAttribute("players", players);
		model.addAttribute("skaters", players.subList(0, 25));
		model.addAttribute("baseS", baseSeason);
		model.addAttribute("targetS", targetSeason);
		model.addAttribute("min", min);
		return "skatertable";
	}
	
	@RequestMapping(value = "/goalietable", method = RequestMethod.GET)
	public String goalietable(@RequestParam Map<String, String> params, Model model, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("seasons") List<Season> seasons) {
		int targetSeason = Integer.parseInt(params.get("targetSeason"));
		int min = Integer.parseInt(params.get("min"));
		int baseSeason;
		if (params.get("baseSeason").equals("0")) {
			baseSeason = 0;
			goalies = new ArrayList<ConvertedGoalie>();
			for (Season s : seasons)
				goalies.addAll(transformGoalies(goalieService.goalieList(s.getSeasonid(), min), s.getSeasonid(), targetSeason));
		}
		else {
			baseSeason= Integer.parseInt(params.get("baseSeason"));
			goalies = transformGoalies(goalieService.goalieList(baseSeason, min), baseSeason, targetSeason);
			if (params.containsKey("include")) {
				goalies.addAll(transformGoalies(goalieService.goalieList(targetSeason, min), targetSeason, targetSeason));
			}
		}
		sortGoalies(goalies, params.get("sort"));
		model.addAttribute("goalies", goalies);
		model.addAttribute("current", goalies.subList(0, 25));
		model.addAttribute("baseS", baseSeason);
		model.addAttribute("targetS", targetSeason);
		model.addAttribute("min", min);
		return "goalietable";
	}
	
	@RequestMapping(value = "/comptable", method = RequestMethod.GET)
	public String comptable(HttpServletRequest request, @RequestParam Map<String, String> params, Model model, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("searchableplayers") Map<Integer, Player> searchableplayers) {
		int baseSeason = Integer.parseInt(params.get("baseSeason"));
		Player p1 = searchableplayers.get(Integer.parseInt(params.get("player1")));
		Player p2 = searchableplayers.get(Integer.parseInt(params.get("player2")));
		if ((p1.getPosition().equals("G") && !p2.getPosition().equals("G")) || (p2.getPosition().equals("G") && !p1.getPosition().equals("G"))) {
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
		if (!p1.getPosition().equals("G")) {
			List<Skaterseasons> p1Seasons = skaterService.playerSeasons(p1.getPlayerid());
			List<Skaterseasons> p2Seasons = skaterService.playerSeasons(p2.getPlayerid());
			if (params.containsKey("include")) {
				players = transformStats(p1Seasons, baseSeason);
				players.addAll(transformStats(p2Seasons, baseSeason));
			}
			else {
				players = transformStats(p1Seasons, p2Seasons, baseSeason);
			}
			sortSkaters(players, params.get("sort"));
			model.addAttribute("targetS", baseSeason);
			model.addAttribute("p1", p1);
			model.addAttribute("p2", p2);
			model.addAttribute("players", players);
			if (players.size() > 25) model.addAttribute("skaters", players.subList(0, 25));
			else model.addAttribute("skaters", players);
			return "skatertable";
		}
		else {
			List<Goalieseasons> p1Seasons = goalieService.goalieSeasons(p1.getPlayerid());
			List<Goalieseasons> p2Seasons = goalieService.goalieSeasons(p2.getPlayerid());
			if (params.containsKey("include")) {
				goalies = transformGoalies(p1Seasons, baseSeason);
				goalies.addAll(transformGoalies(p2Seasons, baseSeason));
			}
			else {
				goalies = transformGoalies(p1Seasons, p2Seasons, baseSeason);
			}
			sortGoalies(goalies, params.get("sort"));
			model.addAttribute("targetS", baseSeason);
			model.addAttribute("p1", p1);
			model.addAttribute("p2", p2);
			model.addAttribute("goalies", goalies);
			if (goalies.size() > 25) model.addAttribute("current", goalies.subList(0, 25));
			else model.addAttribute("current", goalies);
			return "goalietable";
		}
	}
	
	@RequestMapping(value = "/sortskater", method = RequestMethod.GET)
	public String sortskater(Model model, @RequestParam Map<String, String> params, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("sort") String sort, @ModelAttribute("pageNum") int pageNum) {
		sort = params.get("sort");
		pageNum = Integer.parseInt(params.get("pageNum"));
		sortSkaters(players, sort);
		//int pageNum = Integer.parseInt(params.get("pageNum").replace("\u00a0", ""));
		if (players.size() > 25*pageNum) model.addAttribute("skaters", players.subList(25*pageNum-25, 25*pageNum));
		else model.addAttribute("skaters", players.subList(25*pageNum-25, players.size()));
		model.addAttribute("sort", sort);
		model.addAttribute("pageNum", pageNum);
		return "skatertable";
	}
	
	@RequestMapping(value = "/sortgoalie", method = RequestMethod.GET)
	public String sortgoalie(Model model, @RequestParam Map<String, String> params, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("sort") String sort, @ModelAttribute("pageNum") int pageNum) {
		sort = params.get("sort");
		pageNum = Integer.parseInt(params.get("pageNum"));
		sortGoalies(goalies, params.get("sort"));
		if (goalies.size() > 25*pageNum) model.addAttribute("current", goalies.subList(25*pageNum-25, 25*pageNum));
		else model.addAttribute("current", goalies.subList(25*pageNum-25, goalies.size()));
		model.addAttribute("sort", sort);
		model.addAttribute("pageNum", pageNum);
		return "goalietable";
	}
	
	public void sortSkaters(List<ConvertedSkater> skaters, String column) {
		skaters.sort(ConvertedSkater.getSort(column));
		for(int i = 0; i < skaters.size(); i++) skaters.get(i).setRank(i+1);
	}
	
	public void sortGoalies(List<ConvertedGoalie> goalies, String column) {
		goalies.sort(ConvertedGoalie.getSort(column));
		for(int i = 0; i < goalies.size(); i++) goalies.get(i).setRank(i+1);
	}
	
	public List<ConvertedSkater> transformStats(List<Skaterseasons> players, int baseS, int targetS) {
		Season baseSeason = seasonService.getSeason(baseS).get(0);
		Season targetSeason = seasonService.getSeason(targetS).get(0);
		float evAdj = targetSeason.getEvgGp() / baseSeason.getEvgGp();
		float ppAdj = targetSeason.getPpgGp() / baseSeason.getPpgGp();
		float shAdj = targetSeason.getShgGp() / baseSeason.getShgGp();
		float sAdj = targetSeason.getSGp() / baseSeason.getSGp();
		float pimAdj = targetSeason.getPimGp() / baseSeason.getPimGp();
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>();
		for(Skaterseasons p : players) {
			int evg = (int)((p.getGoals()-p.getPpg()-p.getShg())*evAdj);
			int eva = (int)((p.getAssists()-p.getPpa()-p.getSha())*evAdj);
			int ppg = (int)(p.getPpg()*ppAdj);
			int ppa = (int)(p.getPpa()*ppAdj);
			int shg = (int)(p.getShg()*shAdj);
			int sha = (int)(p.getSha()*shAdj);
			int pim = (int)(p.getPim()*pimAdj);
			int s = (int)(p.getShots()*sAdj);
			int g = evg + ppg + shg;
			int a = eva + ppa + sha;
			int pts = evg + ppg + shg + eva + ppa + sha;
			int evp = evg + eva;
			int ppp = ppg + ppa;
			int shp = shg + sha;
			float gpg = g / p.getGp();
			float apg = a / p.getGp();
			float pperg = pts / p.getGp();
			skaters.add(new ConvertedSkater(p.getPlayername(), p.getSeasonid(), p.getGp(), evg, eva, ppg, ppa, shg, sha, pim, s, g, a, pts, evp, ppp, shp, gpg, apg, pperg));
		}
		return skaters;
	}
	
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> players, int baseS, int targetS) {
		Season baseSeason = seasonService.getSeason(baseS).get(0);
		Season targetSeason = seasonService.getSeason(targetS).get(0);
		float sAdj = (1.0f*targetSeason.getSGp()) / (1.0f*baseSeason.getSGp());
		float svAdj = ((targetSeason.getS()*1.0f - 1.0f*targetSeason.getG()) / (1.0f*targetSeason.getS())) / ((baseSeason.getS()*1.0f - 1.0f*baseSeason.getG()) / (1.0f*baseSeason.getS()));
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>();
		for(Goalieseasons p : players) {
			if (p.getSa() > 0 && p.getSv() > 0) {
				int w = (int)(p.getW() - p.getSow());
				int l = (int)(p.getL() - p.getSol());
				int sa = (int)(p.getSa() * sAdj);
				float svpct = (1.0f*p.getSv())/(1.0f*p.getSa());
				int sv = (int)(1.0f*sa * svpct);
				sv = (int)(1.0f*sv * svAdj);
				goalies.add(new ConvertedGoalie(p.getPlayername(), p.getSeasonid(), p.getGp(), p.getToi(), w, l, sa, sv/*, sa60, gaa, svpct*/));
			}
		}
		return goalies;
	}
	
	public List<ConvertedSkater> transformStats(List<Skaterseasons> players, int baseS) {
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>();
		for (Skaterseasons p : players) {
			List<Skaterseasons> temp = new ArrayList<Skaterseasons>();
			temp.add(p);
			skaters.addAll(transformStats(temp, (p.getSeasonid()), baseS));
		}
		return skaters;
	}
	
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> players, int baseS) {
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>();
		for (Goalieseasons p : players) {
			List<Goalieseasons> temp = new ArrayList<Goalieseasons>();
			temp.add(p);
			goalies.addAll(transformGoalies(temp, (p.getSeasonid()), baseS));
		}
		return goalies;
	}
	
	public List<ConvertedSkater> transformStats(List<Skaterseasons> player1, List<Skaterseasons> player2, int baseS) {
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>();
		skaters.add(convertSkater(player1, baseS));
		skaters.add(convertSkater(player2, baseS));
		return skaters;
	}
	
	public ConvertedSkater convertSkater(List<Skaterseasons> skater, int baseS) {
		ConvertedSkater s = new ConvertedSkater();
		s.setName(skater.get(0).getPlayername());
		int minY = skater.get(0).getSeasonid();
		int maxY = skater.get(0).getSeasonid();
		for (Skaterseasons p : skater) {
			List<Skaterseasons> temp = new ArrayList<Skaterseasons>();
			temp.add(p);
			s.addAll(transformStats(temp, (p.getSeasonid()), baseS));
			if (p.getSeasonid() < minY) minY = p.getSeasonid();
			if (p.getSeasonid() > maxY) maxY = p.getSeasonid();
		}
		s.setCareerSpan(minY, maxY);
		return s;
	}
	
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> player1, List<Goalieseasons> player2, int baseS) {
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>();
		goalies.add(convertGoalie(player1, baseS));
		goalies.add(convertGoalie(player2, baseS));
		return goalies;
	}
	
	public ConvertedGoalie convertGoalie(List<Goalieseasons> goalie, int baseS) {
		ConvertedGoalie g = new ConvertedGoalie();
		g.setName(goalie.get(0).getPlayername());
		int minY = goalie.get(0).getSeasonid();
		int maxY = goalie.get(0).getSeasonid();
		for (Goalieseasons p : goalie) {
			List<Goalieseasons> temp = new ArrayList<Goalieseasons>();
			temp.add(p);
			g.addAll(transformGoalies(temp, (p.getSeasonid()), baseS));
			if (p.getSeasonid() < minY) minY = p.getSeasonid();
			if (p.getSeasonid() > maxY) maxY = p.getSeasonid();
		}
		g.setCareerSpan(minY, maxY);
		return g;
	}
}
