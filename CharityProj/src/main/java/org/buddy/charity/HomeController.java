package org.buddy.charity;

import java.util.StringTokenizer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.FBKeywordExtractor.FBDirector;
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
public class HomeController {
	private ArrayList<Keyword> keywords;
	private ArrayList<Keyword> keywords_searchKey = new ArrayList<Keyword>(); 
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// returns 
	@RequestMapping(value = "/analyze", method = RequestMethod.GET)
	public void analyze(Locale locale, Model model, HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = model.asMap();
		
		String accessToken = (String) session.getAttribute("accessToken");

		if (accessToken != null) {
			keywords = FBDirector.FetchFBInterests(accessToken);
			map.put("keywords", keywords);
		} 
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value="searchKey", required=false) String searchKey,
			Locale locale,
			Model model,
			HttpSession session,
			HttpServletResponse response) {
		
		session.invalidate();
		Map<String, Object> map = model.asMap();
		
		// init for now
		Keyword keyTmp = new Keyword("type", searchKey, 1, 1.0); 
		keywords_searchKey.add(keyTmp); 
		
		// only one word will be used
		//for (Keyword word : keywords_searchKey) {
		String html = fetchCharity(keyTmp);
		System.out.println("html: " + html); 
		
		ArrayList<String> out = null;
		ArrayList<String> url = new ArrayList<String>(); 
		
		try {
			out = HTMLParser.ParseHTMLByString(html);
			//System.out.println("out.size(): " + out.size()); 
			
			for (int i=0; i<out.size(); i++) {
				 String oneURL = ""; 
				 int start = out.get(i).indexOf('\"'); 				 
				 int end = out.get(i).indexOf('\"', start+1); 
				 oneURL = "http://www.charitynavigator.org/" + out.get(i).substring(start+1, end); 
				 System.out.println("oneURL: " + oneURL); 
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		map.put("urlStore", url); 
		
		System.out.println("Search Key: " + searchKey); 
		return "search";
	}
	
	@RequestMapping(value = "/charity", method = RequestMethod.GET)
	public void charity(Locale locale, Model model, HttpServletResponse response) {
		// TODO pass keyword as parameter
		for (Keyword word : keywords) {
			String html = fetchCharity(word);
			
			ArrayList<String> out = new ArrayList<String>();
			
			try {
				out = HTMLParser.ParseHTMLByString(html);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			StringBuilder builder = new StringBuilder();
			for (String s : out) {
				builder.append(s);
			}
			
			PrintWriter writer;
			
			try {
				writer = response.getWriter();
				writer.print(builder.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}

	private String fetchCharity(Keyword word) {
		try {
			String parsedKeyword = URLEncoder
					.encode(word.getKeyword(), "UTF-8");
			String searchURL = "http://www.charitynavigator.org/index.cfm?keyword_list="
					+ parsedKeyword + "&Submit2=GO&bay=search.results";
			//System.out.println(searchURL); 
			return Facebook.readURL(new URL(searchURL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		return "home";
	}
}
