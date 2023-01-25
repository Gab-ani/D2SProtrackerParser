package d2s.protracker.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import d2s.protracker.ProTrackerMatchSelector;

@SpringBootApplication  
@RestController  
public class ProTrackerParserController {
	
	@Autowired
	ProTrackerMatchSelector selector;
	
	@GetMapping("/d2sprotracker")
	public String defaultAnswer() {
		return "hi, I can parse the www.dota2protracker.com and return a list of IDs of recently played pro pubs";
	}
	
	@GetMapping("/d2sprotracker/parse")
	public ArrayList<Long> returnRecentMatchesIDs() {
		return selector.getRecentMatches();
	}
	
}
