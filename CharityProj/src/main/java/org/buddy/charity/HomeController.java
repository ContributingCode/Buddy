package org.buddy.charity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/analyze", method = RequestMethod.GET)
	public String analyze(
			@RequestParam(value = "is_ajax", required = false) String isAjax,
			Locale locale, Model model, HttpServletResponse response,
			HttpSession session) {
		String accessToken = (String) session.getAttribute("accessToken");
		Map<String, Object> map = model.asMap();

		if (accessToken != null) {
			ArrayList<Keyword> keywords = FBDirector
					.FetchFBInterests(accessToken);
			session.setAttribute("fbKeywords", keywords);
			map.put("fbKeywords", keywords);

			if (isAjax == null) {
				return "analyze";
			} else {
				analyze_ajax(response, keywords);
			}
		}
		return null; // ajax call
	}

	private void analyze_ajax(HttpServletResponse response,
			ArrayList<Keyword> keywords) {
		try {
			PrintWriter writer = response.getWriter();
			/**
			 * <div class="myTile"> <div class="myTileContent"> <span
			 * class="label label-important">Education</span><br />
			 * <br />
			 * <span class="label label-success">Support</span> <span
			 * class="badge badge-info">18%</span> <br />
			 * <span class="label label-success">Confident</span> <span
			 * class="badge badge-info">38%</span> </div> </div>
			 */
			StringBuilder builder = new StringBuilder();

			for (Keyword word : keywords) {
				builder.append("<div class=\"myTile\">");
				builder.append("\n");
				builder.append("<div class=\"myTileContent\">");
				builder.append("\n");
				builder.append("");
				builder.append(word.getKeyword());
				builder.append("<br/> <br/>");
				builder.append("\n");

				// builder.append("<span class=\"label label-success\">Score:");
				// builder.append("</span> <span class=\"badge badge-info\">");
				// builder.append(word.getScore());
				// builder.append("</span>");

				builder.append("\n");
				builder.append("</div>\n");
				builder.append("</div>\n");
			}

			writer.print(builder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(
			@RequestParam(value = "searchKey", required = false) String searchKey,
			Locale locale, Model model, HttpSession session,
			HttpServletResponse response) {
		Map<String, Object> map = model.asMap();

		// init for now
		Keyword keyTmp = new Keyword("type", searchKey, 1, 1.0);

		// only one word will be used
		// for (Keyword word : keywords_searchKey) {
		String html = fetchCharity(keyTmp);
		System.out.println("html: " + html);

		ArrayList<Display> display = this.parseHTMLToDisplay(html);
		map.put("displayList", display);

		System.out.println("Search Key: " + searchKey);
		return "search";
	}

	@RequestMapping(value = "/charity", method = RequestMethod.GET)
	public String charity(
			@RequestParam(value = "is_ajax", required = false) String isAjax,
			Locale locale, Model model, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> map = model.asMap();
		Hashtable<Keyword, ArrayList<Display>> result = new Hashtable<Keyword, ArrayList<Display>>();
		@SuppressWarnings("unchecked")
		ArrayList<Keyword> keywords = (ArrayList<Keyword>) session
				.getAttribute("fbKeywords");
		for (Keyword word : keywords) {
			String html = fetchCharity(word);
			ArrayList<Display> displayList = parseHTMLToDisplay(html);
			result.put(word, displayList);
		}

		String html = charity_ajax(result);

		if (isAjax == null) {
			map.put("myResult", html);
			return "charity";
		} else {

			try {
				PrintWriter writer = response.getWriter();
				writer.print(html);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private String charity_ajax(Hashtable<Keyword, ArrayList<Display>> result) {
		StringBuilder builder = new StringBuilder();

		Set<Keyword> keywords = result.keySet();

		for (Keyword word : keywords) {
			if (result.get(word).size() == 0) {
				continue;
			}

			builder.append("<div style=\"clear:both\">\n");
			builder.append("<div class=\"myKeyTile\">");
			builder.append("\n");
			builder.append("<div class=\"myTileContent\">");
			builder.append("\n");
			builder.append("");
			builder.append(word.getKeyword());
			builder.append("<br/> <br/>");
			builder.append("\n");

			 builder.append("<span class=\"label label-success\">Score:");
			 builder.append("</span> <span class=\"badge badge-info\">");
			 builder.append(word.getScore());
			 builder.append("</span>");

			builder.append("\n");
			builder.append("</div>\n");
			builder.append("</div>\n");

			builder.append("<div class=\"myRow\">\n");
			builder.append("<a class=\"prev browse left\"></a>\n");
			builder.append("<div class=\"scrollable\" id=\"scrollable\">");
			builder.append("<div class=\"items\">");

			/*
			 * <div class="myRow"> <a class="prev browse left"></a> <div
			 * class="scrollable" id="scrollable"> <div class="items"> <div>
			 * <div class="myTile">xyz</div> <div class="myTile"></div> <div
			 * class="myTile"></div> <div class="myTile"></div> </div> <div>
			 * <div class="myTile">xyz</div> <div class="myTile"></div> <div
			 * class="myTile"></div> <div class="myTile"></div> </div> </div>
			 * </div> <a class="next browse right"></a> </div>
			 */
			int i = 0;
			builder.append("<div>\n");
			for (Display display : result.get(word)) {
				i++;
				if (i % 4 == 0) {
					builder.append("</div><div>\n");
				}
				builder.append("<div class=\"myTile\">\n");

				builder.append("<a href=\"" + display.getUrl() + "\">");
				builder.append(display.getName() + "</a><br /> <br />");

				builder.append("<span class=\"label label-info\">Ratting:");
				builder.append("</span> <span class=\"badge badge-important\">");
				builder.append(display.getScore());
				builder.append("</span>\n");

				builder.append("</div>\n");
			}

			builder.append("</div>\n");

			builder.append("</div></div>\n");
			builder.append(" <a class=\"next browse right\"></a></div></div>\n");
		}

		return builder.toString();

	}

	private ArrayList<Display> parseHTMLToDisplay(String html) {
		ArrayList<String> out = null;
		ArrayList<Display> display = new ArrayList<Display>();

		try {
			out = HTMLParser.ParseHTMLByString(html);
			// System.out.println("out.size(): " + out.size());

			for (int i = 0; i < out.size(); i++) {
				System.out.println("one parseHTML: " + out.get(i));

				String oneURL = "";
				int startURL = out.get(i).indexOf('\"');
				int endURL = out.get(i).indexOf('\"', startURL + 1);
				oneURL = "http://www.charitynavigator.org/"
						+ out.get(i).substring(startURL + 1, endURL);
				System.out.println("oneURL: " + oneURL);

				String name = "";
				int startName = out.get(i).indexOf(
						'>',
						out.get(i).indexOf("orgid=",
								out.get(i).indexOf("orgid=") + 6));
				int endName = out.get(i).indexOf('<', startName + 1);
				name = out.get(i).substring(startName + 1, endName);
				System.out.println("name: " + name);

				String score = "";
				int startScore = out.get(i).indexOf('\"',
						out.get(i).indexOf("title"));
				int endScore = out.get(i).indexOf('\"', startScore + 1);
				score = out.get(i).substring(startScore + 1, endScore);
				System.out.println("score: " + score);

				String location = "";
				int startLocation = out.get(i).indexOf('>',
						out.get(i).indexOf("location"));
				int endLocation = out.get(i).indexOf('<', startLocation + 1);
				location = out.get(i).substring(startLocation + 1, endLocation);
				System.out.println("location: " + location);

				String description = "";
				int startDesc = out.get(i).indexOf('>',
						out.get(i).indexOf("tagline"));
				int endDesc = out.get(i).indexOf('<', startDesc + 1);
				description = out.get(i).substring(startDesc + 1, endDesc);
				System.out.println("description: " + description);

				String category = "";
				int startCategory = out.get(i).indexOf('>',
						out.get(i).lastIndexOf("category"));
				int endCategory = out.get(i).indexOf('<', startCategory + 1);
				category = out.get(i).substring(startCategory + 1, endCategory);
				System.out.println("category: " + category);

				Display d = new Display(name, score, location, description,
						category, oneURL);
				display.add(d);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return display;
	}

	private String fetchCharity(Keyword word) {
		try {
			String parsedKeyword = URLEncoder
					.encode(word.getKeyword(), "UTF-8");
			String searchURL = "http://www.charitynavigator.org/index.cfm?keyword_list="
					+ parsedKeyword + "&Submit2=GO&bay=search.results";
			// System.out.println(searchURL);
			return Util.readURL(new URL(searchURL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		String[] words = { "Food", "Children", "YMCA", "Red Cross", "Doctor",
				"Cancer", "Veteran", "Parkinson", "Animal", "Diabetes",
				"Health", "Education", "Volunteer", "Police", "Firefighter",
				"Vietnam", "Leukemia" };

		List<String> wordList = Arrays.asList(words);

		// select 3 random number between 0 and words.size
		Collections.shuffle(wordList);
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		for (int i = 0; i < 3; i++) {
			Keyword kw = new Keyword("Random", wordList.get(i), 100, 100);
			keywords.add(kw);
		}

		Hashtable<Keyword, ArrayList<Display>> result = new Hashtable<Keyword, ArrayList<Display>>();
		for (Keyword word : keywords) {
			String html = fetchCharity(word);
			ArrayList<Display> displayList = parseHTMLToDisplay(html);
			result.put(word, displayList);
		}

		String html = charity_ajax(result);

		map.put("myResult", html);

		return "home";
	}
}
