package org.buddy.charity;

import java.io.File;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

	public static ArrayList<String> ParseHTMLByString(String html)
			throws Exception {
		ArrayList<String> result;
		try {
			Document doc = Jsoup.parse(html);
			result = ParseHTML(doc);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e.getMessage());
			throw (e);
		}

		return result;
	}

	public static ArrayList<String> ParseHTMLByFile(String html_file)
			throws Exception {

		ArrayList<String> result;

		try {

			File input = new File(html_file);
			Document doc = Jsoup.parse(input, "UTF-8",
					"http://www.charitynavigator.org");
			result = ParseHTML(doc);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e.getMessage());
			throw (e);
		}
		return result;
	}

	public static ArrayList<String> ParseHTML(Document doc) throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Elements tables = doc.getElementsByTag("table");
			for (Element table : tables) {
				if (table.html().indexOf("index.cfm?bay=search.summary") > 0) {
					table.getElementsByTag("th").remove();
					Elements tds = table.getElementsByTag("td");
					for (Element td : tds) {
						Elements ps = td.getElementsByTag("p");
						for (Element p : ps) {
							if (p.html().indexOf("Add to My Charities ") > 0)
								p.remove();
						}
						result.add(td.html());
					}
					// System.out.println(table.html());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e.getMessage());
			throw (e);
		}

		return result;

	}

	public static void main(String[] args) {
		try {
			ArrayList<String> charities = ParseHTMLByFile("/home/moussa/Downloads/htmltest/index.cfm.html");
			for (String charity : charities) {
				System.out.println(charity);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
