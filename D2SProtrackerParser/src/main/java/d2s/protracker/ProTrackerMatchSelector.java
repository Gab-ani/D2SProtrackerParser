package d2s.protracker;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class ProTrackerMatchSelector {
	
	public ArrayList<Long> getRecentMatches(String hero) {
		ArrayList<Long> ids = new ArrayList<>();
		
		try (WebClient client = new WebClient()) {
			HtmlPage page = client.getPage("https://www.dota2protracker.com/hero/" + hero); //TODO try-catch on some strange syntax error
			Document doc = Jsoup.parse(page.asXml());
			
			Elements td = doc.select("a[href]");
			for (Element row : td) {
				if(row.attr("href").contains("stratz") && !row.attr("href").contains("live")) {
					String matchReference = row.attr("href").replaceAll("https://stratz.com", "");
					matchReference = matchReference.replaceAll("/match/", "");
					if(matchReference.contains("/hero/") || matchReference.contains("/player/")) {
						continue;
					}
					if(matchReference.length() > 1) {
//						System.out.println(matchReference);
						ids.add(Long.parseLong(matchReference));
					}
				}
			}
			
		} catch (FailingHttpStatusCodeException | IOException | ScriptException e) {
			e.printStackTrace();
		}
		
		return ids;
	}
	
	public ArrayList<Long> getRecentMatches() {
		
		ArrayList<Long> ids = new ArrayList<>();
		
		try (WebClient client = new WebClient()) {
			HtmlPage page = client.getPage("https://www.dota2protracker.com");
			Document doc = Jsoup.parse(page.asXml());
			
			Elements td = doc.select("a[href]");
			for (Element row : td) {
				if(row.attr("href").contains("stratz") && !row.attr("href").contains("live")) {
					String matchReference = row.attr("href").replaceAll("https://stratz.com", "");
					matchReference = matchReference.replaceAll("/match/", "");
					if(matchReference.length() > 1)
						ids.add(Long.parseLong(matchReference));
				}
			}
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		
//		for(Long id : ids)
//			System.out.println(id);
		
		return ids;
	}

}
