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
public class SearchController {
	private ArrayList<Keyword> keywords;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpSession session) {
		session.invalidate();
		return "search";
	}
}
