/**
 * BenchwarmerController is the main controller for the NHL Time Machine web application.
 * It handles all request mapping, as well as flow control and access to utility classes.
 * 
 * @author  Dan Bonham
 * @version 1.0.0
 * @since 	2017-08-26
 */
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
	
	@ModelAttribute("playerbios") public List<Player> playerbios() { return playerService.playerList(); } // used to populate player select inputs
	@ModelAttribute("seasons") public List<Season> seasons() { return seasonService.seasonList(); } // used to populate season select inputs
	@ModelAttribute("baseS") public int baseS() { return 20162017; } // holds base season for stat transformation - the season to transform
	@ModelAttribute("targetS") public int targetS() { return 20162017; } // holds target season for stat transformation - the production levels to transform to
	@ModelAttribute("min") public int min() { return 20; } // used to filter results by a minimum number of player games played
	@ModelAttribute("sort") public String sort() { return "pts"; } // used to hold the column currently used to sort the data
	@ModelAttribute("pageNum") public int pageNum() { return 1; } // used to hold the current table data page - pagination
	@ModelAttribute("players") public List<ConvertedSkater> players() { return transformStats(skaterService.skaterList(20162017, 20), 20162017, 20162017); } // holds the skater transformation result sets
	@ModelAttribute("goalies") public List<ConvertedGoalie> goalies() { return transformGoalies(goalieService.goalieList(20162017, 20), 20162017, 20162017); } // holds the goalie transformation result sets
	@ModelAttribute("searchableplayers") // ensures player bio data is only accessed once and can then be searched by playerid
	public Map<Integer, Player> searchableplayers(@ModelAttribute("playerbios") List<Player> playerbios) {  // retrieves all player bios from db
		Map<Integer, Player> players = new HashMap<Integer, Player>(); // creates new hashmap with playerid as key and player bio as value
		for(Player p : playerbios) players.put(p.getPlayerid(), p); // adds each player bio to map with key added
		return players; // returns new player bio hashmap
	} // searchableplayers()
	
	@RequestMapping({"/welcome"}) // handles initial loading of the application
	public String welcome(@ModelAttribute("seasons") List<Season> seasons, @ModelAttribute("baseS") int baseS, @ModelAttribute("targetS") int targetS, @ModelAttribute("min") int min) {
		return "welcome";
	} // welcome()
	
	@RequestMapping({"/intro"}) // handles initial loading of the application
	public String intro(@ModelAttribute("seasons") List<Season> seasons, @ModelAttribute("baseS") int baseS, @ModelAttribute("targetS") int targetS, @ModelAttribute("min") int min) {
		return "intro";
	} // intro()
	
	@RequestMapping({"/timemachine"}) // handles initial loading of the application
	public String timemachine(@ModelAttribute("seasons") List<Season> seasons, @ModelAttribute("baseS") int baseS, @ModelAttribute("targetS") int targetS, @ModelAttribute("min") int min) {
		return "timemachine";
	} // timemachine()
	
	@RequestMapping(value = "/skaters") // handles view for skater transformation menu with default values
	public String skaters(Model model) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("targetS", "20162017");
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		model.addAttribute("min", 20);
		return "skaters";
	} // skaters()
	
	@RequestMapping(value = "/goalies") // handles view for goalie transformation menu with default values
	public String goalies(Model model) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("targetS", "20162017");
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		model.addAttribute("min", 20);
		return "goalies";
	} // goalies()
	
	@RequestMapping(value = "/comparator") // handles view for player comparator menu with default values
	public String comparator(Model model, @ModelAttribute("playerbios") List<Player> playerbios, @ModelAttribute("searchableplayers") Map<Integer, Player> searchableplayers) {
		model.addAttribute("baseS", "20162017");
		model.addAttribute("p1", searchableplayers.get(8478550));
		model.addAttribute("p2", searchableplayers.get(8476453));
		model.addAttribute("sort", "pts");
		model.addAttribute("pageNum", 1);
		return "comparator";
	}// comparator()
	
	@RequestMapping(value = "/skatertable", method = RequestMethod.GET) // handles view for the presentation of skater transformation data
	public String skatertable(@RequestParam Map<String, String> params, Model model, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("seasons") List<Season> seasons) {
		int targetSeason = Integer.parseInt(params.get("targetSeason")); // retrieves target season for transformation from get request
		int min = Integer.parseInt(params.get("min")); // retrieves minimum games played for filtering the transformation from get request
		int baseSeason; // will hold user-specified base season
		if (params.get("baseSeason").equals("0")) { // retrieves base season for transformation from get request
			baseSeason = 0; // value used when the user wishes to transform skater data from all available seasons
			players = new ArrayList<ConvertedSkater>(); // prepares for new result set
			for (Season s : seasons) // gets skater data for each season
				players.addAll(transformStats(skaterService.skaterList(s.getSeasonid(), min), s.getSeasonid(), targetSeason)); // transforms skater data
		} // if (season==0)
		else { // handles individual season transformation requests
			baseSeason = Integer.parseInt(params.get("baseSeason")); // retrieves requested base season from get
			players = transformStats(skaterService.skaterList(baseSeason, min), baseSeason, targetSeason); // transforms base season skater data
			if (params.containsKey("include")) { // determines whether the user has requested to include skater data from target season for comparison
				players.addAll(transformStats(skaterService.skaterList(targetSeason, min), targetSeason, targetSeason)); // transforms target season skater data
			} // if (include)
		} // else
		sortSkaters(players, params.get("sort")); // retrieves user-specified sort column and sorts skater data
		model.addAttribute("players", players); // adds newly created skater transformation data to model
		model.addAttribute("skaters", players.subList(0, 25)); // limits data to first 25 results - used for pagination
		model.addAttribute("baseS", baseSeason); // sets user-specified base season to model
		model.addAttribute("targetS", targetSeason); // sets user-specified target season to model
		model.addAttribute("min", min); // sets user-specified minimum games played for filtering to model
		return "skatertable"; // directs to skater table view
	}// skatertable()
	
	@RequestMapping(value = "/goalietable", method = RequestMethod.GET) // handles view for the presentation of goalie transformation data
	public String goalietable(@RequestParam Map<String, String> params, Model model, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("seasons") List<Season> seasons) {
		int targetSeason = Integer.parseInt(params.get("targetSeason")); // retrieves target season for transformation from get request
		int min = Integer.parseInt(params.get("min")); // retrieves minimum games played for filtering the transformation from get request
		int baseSeason; // will hold user-specified base season
		if (params.get("baseSeason").equals("0")) { // retrieves base season for transformation from get request
			baseSeason = 0; // values used when the user wishes to transform goalie data from all available seasons
			goalies = new ArrayList<ConvertedGoalie>(); // prepares for new result set
			for (Season s : seasons) // gets skater data for each season
				goalies.addAll(transformGoalies(goalieService.goalieList(s.getSeasonid(), min), s.getSeasonid(), targetSeason)); // transforms goalie data
		} // if (season==0)
		else { // handles individual season transformation requests
			baseSeason= Integer.parseInt(params.get("baseSeason")); // retrieves requested base season from get
			goalies = transformGoalies(goalieService.goalieList(baseSeason, min), baseSeason, targetSeason); // transforms base season goalie data
			if (params.containsKey("include")) { // determines whether the user has requested to include goalie data from target season for comparison
				goalies.addAll(transformGoalies(goalieService.goalieList(targetSeason, min), targetSeason, targetSeason)); // transforms target season goalie data
			} // if (include)
		} // else
		sortGoalies(goalies, params.get("sort")); // retrieves user-specified sort column and sorts goalie data
		model.addAttribute("goalies", goalies); // adds newly created goalie transformation data to model
		model.addAttribute("current", goalies.subList(0, 25)); // limits data to first 25 results - used for pagination
		model.addAttribute("baseS", baseSeason); // sets user-specified base season to model
		model.addAttribute("targetS", targetSeason); // sets user-specified target season to model
		model.addAttribute("min", min); // sets user-specified minimum games played for filtering to model
		return "goalietable"; // directs to goalie table view
	} // goalietable()
	
	@RequestMapping(value = "/comptable", method = RequestMethod.GET) // handles view for the presentation of player comparison data
	public String comptable(HttpServletRequest request, @RequestParam Map<String, String> params, Model model, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("searchableplayers") Map<Integer, Player> searchableplayers) {
		int baseSeason = Integer.parseInt(params.get("baseSeason")); // retrieves user-specified base season to be used for player comparison
		Player p1 = searchableplayers.get(Integer.parseInt(params.get("player1"))); // retrieves user-specified player to be used for comparison
		Player p2 = searchableplayers.get(Integer.parseInt(params.get("player2"))); // retrieves user-specified player to be used for comparison
		if ((p1.getPosition().equals("G") && !p2.getPosition().equals("G")) || (p2.getPosition().equals("G") && !p1.getPosition().equals("G"))) { // handles incomparable data
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		} // if (incomparable data)
		if (!p1.getPosition().equals("G")) { // handles skater comparisons
			List<Skaterseasons> p1Seasons = skaterService.playerSeasons(p1.getPlayerid()); // gets data for all applicable seasons for first skater
			List<Skaterseasons> p2Seasons = skaterService.playerSeasons(p2.getPlayerid()); // gets data for all applicable seasons for second skater
			if (params.containsKey("include")) { // determines whether the user has specified to display individual season data
				players = transformStats(p1Seasons, baseSeason); // transforms skater one seasons to base year
				players.addAll(transformStats(p2Seasons, baseSeason)); // transforms skater two seasons to base year
			} // if (include)
			else { // handles skater data transformed and presented by complete career
				players = transformStats(p1Seasons, p2Seasons, baseSeason); // transforms both skater's data
			} // else
			sortSkaters(players, params.get("sort")); // sorts skater data based on user-specified column
			model.addAttribute("baseS", "All"); // used in table title
			model.addAttribute("targetS", String.valueOf(baseSeason)); // sets user-specified base season to model (uses targetS variable bc comparison data is calculated differently)
			model.addAttribute("p1", p1); // adds user-specified first comparable skater to model
			model.addAttribute("p2", p2); // adds user-specified second comparable skater to model
			model.addAttribute("players", players); // adds newly created skater transformation data to model 
			if (players.size() > 25) model.addAttribute("skaters", players.subList(0, 25)); // limits result set to first 25 skaters - pagination
			else model.addAttribute("skaters", players); // adds all skaters if less than 25 results
			return "skatertable"; // directs to skater table view
		} // if (skater)
		else { // handles goalie comparisons
			List<Goalieseasons> p1Seasons = goalieService.goalieSeasons(p1.getPlayerid()); // gets data for all applicable seasons for first goalie
			List<Goalieseasons> p2Seasons = goalieService.goalieSeasons(p2.getPlayerid()); // gets data for all applicable seasons for second goalie
			if (params.containsKey("include")) { // determines whether the user has specified to display individual season data
				goalies = transformGoalies(p1Seasons, baseSeason); // transforms goalie one seasons to base year
				goalies.addAll(transformGoalies(p2Seasons, baseSeason)); // transforms goalie two seasons to base year
			} // if (include)
			else { // handles goalie data transformed and presented by complete career
				goalies = transformGoalies(p1Seasons, p2Seasons, baseSeason); // transforms both goalie's data
			} // else
			sortGoalies(goalies, params.get("sort")); // sorts goalie data based on user-specified column
			model.addAttribute("baseS", "All"); // used in table title
			model.addAttribute("targetS", String.valueOf(baseSeason)); // sets user-specified base season to model (uses targetS variable bc comparison data is calculated differently)
			model.addAttribute("p1", p1); // adds user-specified first comparable goalie to model
			model.addAttribute("p2", p2); // adds user-specified second comparable goalie to model
			model.addAttribute("goalies", goalies); // adds newly created goalie transformation data to model 
			if (goalies.size() > 25) model.addAttribute("current", goalies.subList(0, 25)); // limits result set to first 25 goalies - pagination
			else model.addAttribute("current", goalies); // adds all goalies if less than 25 results
			return "goalietable"; // directs to goalie table view
		} // else
	} // comptable()
	
	@RequestMapping(value = "/sortskater", method = RequestMethod.GET) // handles the user-requested sorting of skater data
	public String sortskater(Model model, @RequestParam Map<String, String> params, @ModelAttribute("players") List<ConvertedSkater> players, @ModelAttribute("sort") String sort, @ModelAttribute("pageNum") int pageNum) {
		sort = params.get("sort"); // retrieves user-specified sort column from get
		pageNum = Integer.parseInt(params.get("pageNum")); // retrieves current table page number - current location in data set
		sortSkaters(players, sort); // sorts skater data based on specified column
		if (players.size() > 25*pageNum) model.addAttribute("skaters", players.subList(25*pageNum-25, 25*pageNum)); // limits data set to 25 results - pagination
		else model.addAttribute("skaters", players.subList(25*pageNum-25, players.size())); // adds all skaters if less than 25 results
		model.addAttribute("sort", sort); // sets user-specified sort column to model
		model.addAttribute("pageNum", pageNum); // sets current data location (in table) to model
		return "skatertable"; // directs to skater table view
	} // sortskater()
	
	@RequestMapping(value = "/sortgoalie", method = RequestMethod.GET) // handles the user-requested sorting of goalie data
	public String sortgoalie(Model model, @RequestParam Map<String, String> params, @ModelAttribute("goalies") List<ConvertedGoalie> goalies, @ModelAttribute("sort") String sort, @ModelAttribute("pageNum") int pageNum) {
		sort = params.get("sort"); // retrieves user-specified sort column from get
		pageNum = Integer.parseInt(params.get("pageNum")); // retrieves current table page number - current location in data set
		sortGoalies(goalies, params.get("sort")); // sorts goalie data based on specified column
		if (goalies.size() > 25*pageNum) model.addAttribute("current", goalies.subList(25*pageNum-25, 25*pageNum)); // limits data set to 25 results - pagination
		else model.addAttribute("current", goalies.subList(25*pageNum-25, goalies.size())); // adds all goalies if less than 25 results
		model.addAttribute("sort", sort); // sets user-specified sort column to model
		model.addAttribute("pageNum", pageNum); // sets current data location (in table) to model
		return "goalietable"; // directs to goalie table view
	} // sortgoalie()
	
	// sorts skater data set based on specified column
	public void sortSkaters(List<ConvertedSkater> skaters, String column) {
		skaters.sort(ConvertedSkater.getSort(column)); // sorts skaters based on specified column
		for(int i = 0; i < skaters.size(); i++) skaters.get(i).setRank(i+1); // adds skater rank based on new sort
	} // sortSkaters()
	
	// sorts goalie data set based on specified column
	public void sortGoalies(List<ConvertedGoalie> goalies, String column) {
		goalies.sort(ConvertedGoalie.getSort(column)); // sorts goalies based on specified column
		for(int i = 0; i < goalies.size(); i++) goalies.get(i).setRank(i+1); // adds goalie rank based on new sort
	} // sortGoalies()
	
	// transforms individual season skater data to specified era and returns result
	public List<ConvertedSkater> transformStats(List<Skaterseasons> players, int baseS, int targetS) {
		Season baseSeason = seasonService.getSeason(baseS).get(0); // retrieves data for base season
		Season targetSeason = seasonService.getSeason(targetS).get(0); // retrieves data for target season
		float evAdj = targetSeason.getEvgGp() / baseSeason.getEvgGp(); // calculates even strength goals adjustment
		float ppAdj = targetSeason.getPpgGp() / baseSeason.getPpgGp(); // calculates power play goals adjustment
		float shAdj = targetSeason.getShgGp() / baseSeason.getShgGp(); // calculates short handed goals adjustment
		float sAdj = targetSeason.getSGp() / baseSeason.getSGp(); // calculates shots adjustment
		float pimAdj = targetSeason.getPimGp() / baseSeason.getPimGp(); // calculates penalty minutes adjustment
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>(); // will hold transformed skater data
		for(Skaterseasons p : players) { // loops through and transforms each skater season applicable
			int evg = (int)((p.getGoals()-p.getPpg()-p.getShg())*evAdj); // calculates skater's transformed even strength goals
			int eva = (int)((p.getAssists()-p.getPpa()-p.getSha())*evAdj); // calculates skater's transformed even strength assists
			int ppg = (int)(p.getPpg()*ppAdj); // calculates skater's transformed power play goals
			int ppa = (int)(p.getPpa()*ppAdj); // calculates skater's transformed power play assists
			int shg = (int)(p.getShg()*shAdj); // calculates skater's transformed short handed goals
			int sha = (int)(p.getSha()*shAdj); // calculates skater's transformed short handed assists
			int pim = (int)(p.getPim()*pimAdj); // calculates skater's transformed penalty minutes
			int s = (int)(p.getShots()*sAdj); // calculates skater's transformed shots
			int g = evg + ppg + shg; // sums skater's transformed total goals
			int a = eva + ppa + sha; // sums skater's transformed total assists
			int pts = evg + ppg + shg + eva + ppa + sha; // sums skater's total points
			int evp = evg + eva; // sums skater's transformed total even strength points
			int ppp = ppg + ppa; // sums skater's transformed total power play points
			int shp = shg + sha; // sums skater's transformed total short handed points
			float gpg = g / p.getGp(); // calculates skater's transformed goals per game
			float apg = a / p.getGp(); // calculates skater's transformed assists per game
			float pperg = pts / p.getGp(); // calculates skater's transformed points per game
			skaters.add(new ConvertedSkater(p.getPlayername(), p.getSeasonid(), p.getGp(), evg, eva, ppg, ppa, shg, sha, pim, s, g, a, pts, evp, ppp, shp, gpg, apg, pperg)); // adds transformed skater season to result set
		} // for (skater season)
		return skaters; // returns transformed skater data
	} // transformStats(individual season)
	
	// transforms individual season goalie data to specified era and returns result
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> players, int baseS, int targetS) {
		Season baseSeason = seasonService.getSeason(baseS).get(0); // retrieves data for base season
		Season targetSeason = seasonService.getSeason(targetS).get(0); // retrieves data for target season
		float sAdj = (1.0f*targetSeason.getSGp()) / (1.0f*baseSeason.getSGp()); // calculates shots adjustment
		float svAdj = ((targetSeason.getS()*1.0f - 1.0f*targetSeason.getG()) / (1.0f*targetSeason.getS())) / ((baseSeason.getS()*1.0f - 1.0f*baseSeason.getG()) / (1.0f*baseSeason.getS())); // calculates save percentage adjustment
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>(); // will hold transformed goalie data
		for(Goalieseasons p : players) { // loops through and transforms each goalie season applicable
			if (p.getSa() > 0 && p.getSv() > 0) { // removes seasons where the goalie did not make a save
				int w = (int)(p.getW() - p.getSow()); // calculates goalie's transformed wins
				int l = (int)(p.getL() - p.getSol()); // calculates goalie's transformed losses
				int sa = (int)(p.getSa() * sAdj); // calculates goalie's transformed shots against
				float svpct = (1.0f*p.getSv())/(1.0f*p.getSa()); // calculates goalie's transformed save percentage
				int sv = (int)(1.0f*sa * svpct); // calculates goalie's transformed saves
				sv = (int)(1.0f*sv * svAdj);
				goalies.add(new ConvertedGoalie(p.getPlayername(), p.getSeasonid(), p.getGp(), p.getToi(), w, l, sa, sv/*, sa60, gaa, svpct*/)); // adds transformed goalie season to result set
			} // if (no data)
		} // for (goalie season)
		return goalies; // returns transformed goalie data
	} // transformGoalies(individual season)
	
	// transforms each season of career skater data to specified era and returns result
	public List<ConvertedSkater> transformStats(List<Skaterseasons> players, int baseS) {
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>(); // will hold transformed skater data
		for (Skaterseasons p : players) { // loops through each season of skater data
			List<Skaterseasons> temp = new ArrayList<Skaterseasons>(); // temporarily holds individual season skater data
			temp.add(p); // adds current season of data
			skaters.addAll(transformStats(temp, (p.getSeasonid()), baseS)); // adds current transformed season to skater's transformed career stats
		} // for (season)
		return skaters; // returns transformed skater career
	}// transformStats(career seasons)
	
	// transforms each season of career goalie data to specified era and returns result
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> players, int baseS) {
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>(); // will hold transformed goalie data
		for (Goalieseasons p : players) { // loops through each season of goalie data
			List<Goalieseasons> temp = new ArrayList<Goalieseasons>(); // temporarily holds individual season goalie data
			temp.add(p); // adds current season of data
			goalies.addAll(transformGoalies(temp, (p.getSeasonid()), baseS)); // adds current transformed season to goalie's transformed career stats
		} // for (season)
		return goalies; // returns transformed goalie career
	}// transformGoalies(career seasons)
	
	// helper function compiles sum of career skater data for comparison 
	public List<ConvertedSkater> transformStats(List<Skaterseasons> player1, List<Skaterseasons> player2, int baseS) {
		List<ConvertedSkater> skaters = new ArrayList<ConvertedSkater>(); // will hold transformed skater data
		skaters.add(convertSkater(player1, baseS)); // adds career data for skater one
		skaters.add(convertSkater(player2, baseS)); // adds career data for skater two
		return skaters; // returns compiled and transformed skater career data
	} // transformStats(career sum)
	
	// transforms, sums and returns a skater's career stats
	public ConvertedSkater convertSkater(List<Skaterseasons> skater, int baseS) {
		ConvertedSkater s = new ConvertedSkater(); // will hold transformed skater stats
		s.setName(skater.get(0).getPlayername()); // sets the skater name
		int minY = skater.get(0).getSeasonid(); // used to set skater's career span
		int maxY = skater.get(0).getSeasonid(); // used to set skater's career span
		for (Skaterseasons p : skater) { // loops through all a skater's active seasons
			List<Skaterseasons> temp = new ArrayList<Skaterseasons>(); // will hold individual season data
			temp.add(p); // adds current season
			s.addAll(transformStats(temp, (p.getSeasonid()), baseS)); // adds current transformed season to transformed career total
			if (p.getSeasonid() < minY) minY = p.getSeasonid(); // sets oldest year skater was active
			if (p.getSeasonid() > maxY) maxY = p.getSeasonid(); // sets most recent year skater was active
		} // for (seasons)
		s.setCareerSpan(minY, maxY); // sets the span of seasons the skater was active
		return s; // return transformed and compiled skater career data
	} // convertSkater(career)
	
	// helper function compiles sum of career goalie data for comparison 
	public List<ConvertedGoalie> transformGoalies(List<Goalieseasons> player1, List<Goalieseasons> player2, int baseS) {
		List<ConvertedGoalie> goalies = new ArrayList<ConvertedGoalie>(); // will hold transformed goalie data
		goalies.add(convertGoalie(player1, baseS)); // adds career data for goalie one
		goalies.add(convertGoalie(player2, baseS)); // adds career data for goalie two
		return goalies; // returns compiled and transformed goalie career data
	} // transformGoalies(career sum)
	
	// transforms, sums and returns a goalie's career stats
	public ConvertedGoalie convertGoalie(List<Goalieseasons> goalie, int baseS) {
		ConvertedGoalie g = new ConvertedGoalie(); // will hold transformed career stats
		g.setName(goalie.get(0).getPlayername()); // sets the goalie name
		int minY = goalie.get(0).getSeasonid(); // used to set goalie's career span
		int maxY = goalie.get(0).getSeasonid(); // used to set goalie's career span
		for (Goalieseasons p : goalie) { // loops through all a goalie's active seasons
			List<Goalieseasons> temp = new ArrayList<Goalieseasons>(); // will hold individual season data
			temp.add(p); // adds current season
			g.addAll(transformGoalies(temp, (p.getSeasonid()), baseS)); // adds current transformed season to transformed career total
			if (p.getSeasonid() < minY) minY = p.getSeasonid(); // sets oldest year goalie was active
			if (p.getSeasonid() > maxY) maxY = p.getSeasonid(); // sets most recent year goalie was active
		} // for (season)
		g.setCareerSpan(minY, maxY); // sets the span of seasons the goalie was active
		return g; // return transformed and compiled goalie career data
	} // convertGoalie(career sum)
} // class BenchwarmerController
