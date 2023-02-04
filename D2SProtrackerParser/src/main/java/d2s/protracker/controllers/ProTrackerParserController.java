package d2s.protracker.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import d2s.protracker.ProTrackerMatchSelector;

@SpringBootApplication  
@RestController  
public class ProTrackerParserController {
	
	@Autowired
	ProTrackerMatchSelector selector;
	
	@GetMapping("/")
	public String defaultAnswer() {
		return "hi, I can parse the www.dota2protracker.com and return a list of IDs of recently played pro pubs";
	}
	
	@GetMapping("/parse")
	public ResponseEntity<ArrayList<Long>> returnRecentMatchesIDs() {
		var matches = selector.getRecentMatches();
		return ResponseEntity.ok().body(matches);
	}
	
	@GetMapping("/parse/{hero}")
	public ResponseEntity<ArrayList<Long>> returnRecentMatchesIDs(@PathVariable(value = "hero") String hero) {
		System.out.println("got hero parsing request " + hero);
		var matches = selector.getRecentMatches(hero);
//		matches.forEach(match -> System.out.println(match));
		return ResponseEntity.ok().body(matches);
	}
	
}
