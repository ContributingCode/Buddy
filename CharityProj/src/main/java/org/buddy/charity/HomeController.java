package org.buddy.charity;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private ArrayList<Keyword> keywords;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/charity", method = RequestMethod.GET)
	public void charity(Locale locale, Model model, HttpServletResponse response) {
		// TODO pass keyword as parameter
		for (Keyword word : keywords) {
			String html = fetchCharity(word);
			ArrayList<String> out = null;
			
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
					.encode(word.toString(), "UTF-8");
			String searchURL = "http://www.charitynavigator.org/index.cfm?keyword_list="
					+ parsedKeyword + "&Submit2=GO&bay=search.results";
			return Facebook.readURL(new URL(searchURL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		String accessToken = (String) session.getAttribute("accessToken");

		if (accessToken != null) {
			keywords = FBDirector.FetchFBInterests(accessToken);
			map.put("keywords", keywords);
		} 

		return "home";
	}
}
