package org.buddy.charity;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.FBKeywordExtractor.Keyword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SearchController {
	private ArrayList<Keyword> keywords;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value="searchKey", required=false) String searchKey,
			Locale locale,
			Model model,
			HttpSession session) {
		
		session.invalidate();
		
		System.out.println("Search Key: " + searchKey); 
		return "search";
	}
}
