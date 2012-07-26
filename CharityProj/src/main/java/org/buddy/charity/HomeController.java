package org.buddy.charity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.Carrot2Test.FBDirector;
import org.Carrot2Test.Keyword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		String accessToken = (String) session.getAttribute("accessToken");

		if (accessToken == null) {
			return "redirect:/login";
		}

		ArrayList<Keyword> keywords = FBDirector.FetchFBInterests(accessToken);

		for (Keyword word : keywords) {
			try {
				String parsedKeyword = URLEncoder.encode(word.getKeyword(), "UTF-8");
				String searchURL = "http://www.charitynavigator.org/index.cfm?keyword_list="
						+ parsedKeyword + "&Submit2=GO&bay=search.results";
				// System.out.println(Facebook.readURL(new URL(searchURL)));
				// ? How do I print them to the webpage? through jsp?
				String html = Facebook.readURL(new URL(searchURL));
				map.put("result", html);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "home";
	}
}
