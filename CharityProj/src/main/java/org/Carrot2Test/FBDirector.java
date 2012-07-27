package org.Carrot2Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class FBDirector {
	
	public static ArrayList<Keyword> FetchFBInterests(String accessToken) { 

		/* Initialize the facebook client. */
   		FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
   		
   		/* Execute the query from facebook*/
		String query = "SELECT uid, name, music, interests,hometown_location, current_location, education, sports, religion FROM user WHERE uid=me()";
		List<FqlUser> users = facebookClient.executeQuery(query, FqlUser.class);
		
		System.out.println("User: " + users);
		
		/* Extract the keywords from the user facebook's profile*/
		if (!users.isEmpty()){
			return users.get(0).toKeywordList();
		} else {
			return null;
		}
		
//		String query2 = "SELECT page_id, name, description, page_url FROM page WHERE page_id in (select page_id from page_fan where uid=me())";
//		List<FqlPage> pages = facebookClient.executeQuery(query2, FqlPage.class);
		
		
////		String query3 = "SELECT status_id, message FROM status WHERE uid=me()";
////		List<FqlStatus> statuses = facebookClient.executeQuery(query3, FqlStatus.class);
//
////		System.out.println("pages: " + pages);
//		System.out.println("statuses: " + statuses);
	}
}
