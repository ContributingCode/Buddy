package org.buddy.charity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Oranization {

	public static String findPicUrl(String search_keyword) throws MalformedURLException, IOException {
		//TODO: find the google image search url to inseart the keyword and make a working url
		String googleImageSearchUrl = "http://www.google.com/image";
		String html = Facebook.readURL(new URL(googleImageSearchUrl));
		//TODO: parse the html to get the first image, and return its link as a String
		return null;
	}
}
