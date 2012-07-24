package org.buddy.charity;

import org.springframework.util.StringUtils;

public class Facebook {
	// get these from your FB Dev App
	private static final String api_key = "MYAPIKEY"; // what is this?
	private static final String secret = "611487a409b0a180b76d35762d93b923";
	private static final String client_id = "320773084683669";

	// set this to your servlet URL for the authentication servlet/filter
	// FIXME get relative URL & also add to facebook app configuration dashboard
	private static String redirect_uri = "http://localhost:8080/CharityProj/login";

	// / set this to the list of extended permissions you want
	private static final String[] perms = new String[] { "user_about_me",
			"user_activities", "user_education_history", "user_hometown",
			"user_interests", "user_likes", "user_location",
			"user_religion_politics" };

	public static String getRedirectUri() {
		return redirect_uri;
	}

	public static void setRedirectUri(String redirectUri) {
		redirect_uri = redirectUri;
	}

	public static String getAPIKey() {
		return api_key;
	}

	public static String getSecret() {
		return secret;
	}

	public static String getLoginRedirectURL() {
		return "https://www.facebook.com/dialog/oauth?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&scope=" + StringUtils.arrayToCommaDelimitedString(perms);
	}

	public static String getAuthURL(String authCode) {
		return "https://graph.facebook.com/oauth/access_token?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&client_secret=" + secret + "&code=" + authCode;
	}
}
