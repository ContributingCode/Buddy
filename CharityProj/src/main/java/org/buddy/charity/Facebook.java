package org.buddy.charity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.util.StringUtils;

public class Facebook {
	private static Facebook instance = new Facebook();
	
	// get these from your FB Dev App
	private static final String api_key = "MYAPIKEY"; // what is this?
	private String secret = "611487a409b0a180b76d35762d93b923";
	private String client_id = "320773084683669";

	// set this to your servlet URL for the authentication servlet/filter
	// FIXME get relative URL & also add to facebook app configuration dashboard
	private String redirect_uri = "http://localhost:8080/CharityProj/login";

	// / set this to the list of extended permissions you want
	private static final String[] perms = new String[] { "user_about_me",
			"user_activities", "user_education_history", "user_hometown",
			"user_interests", "user_likes", "user_location",
			"user_religion_politics" };

	private Facebook() {
		CloudEnvironment env = new CloudEnvironment();
		if (env.isCloudFoundry()==true) {
			// production
			secret = "611487a409b0a180b76d35762d93b923";
			client_id = "320773084683669";
		} else {
			// dev
			secret = "7a411dd68ff9c6ab9ad8de6a6ee6b4f3";
			client_id = "382681771787130";
		}
	}
	
	public static Facebook getInstance() {
		if (instance==null) {
			instance = new Facebook();
		}
		return instance;
	}
	
	public String getRedirectUri() {
		return redirect_uri;
	}

	public void setRedirectUri(String redirectUri) {
		redirect_uri = redirectUri;
	}

	public static String getAPIKey() {
		return api_key;
	}

	public String getSecret() {
		return secret;
	}

	public String getLoginRedirectURL() {
		return "https://www.facebook.com/dialog/oauth?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&scope=" + StringUtils.arrayToCommaDelimitedString(perms);
	}

	public String getAuthURL(String authCode) {
		return "https://graph.facebook.com/oauth/access_token?client_id="
				+ client_id + "&redirect_uri=" + redirect_uri
				+ "&client_secret=" + secret + "&code=" + authCode;
	}
	
}
