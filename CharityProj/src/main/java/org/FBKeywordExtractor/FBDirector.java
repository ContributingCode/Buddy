package org.FBKeywordExtractor;

import java.awt.RenderingHints.Key;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

import org.carrot2.core.Cluster;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class FBDirector {
	
	public static ArrayList<Keyword> FetchFBInterests(String accessToken) { 

		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		
		/* Initialize the facebook client. */
   		FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
   		
   		/**
   		 * Fetch the personal information from facebook to be used as keywords directly
   		 */
   		/* Execute the query from facebook*/
		String query = "SELECT uid, name, music, interests,hometown_location, current_location, education, sports, religion FROM user WHERE uid=me()";
		List<FqlUser> users = facebookClient.executeQuery(query, FqlUser.class);
		
		//System.out.println("User: " + users);
		
		/* Extract the keywords from the user facebook's profile*/
		// keywords.addAll(users.get(0).toKeywordList());
		
		
		/**
   		 * Infer personal interests from the pages liked in facebook
   		 */
		String query2 = "SELECT page_id, name, description, page_url FROM page WHERE page_id in (select page_id from page_fan where uid=me())";
		List<FqlPage> pages = facebookClient.executeQuery(query2, FqlPage.class);

		//System.out.println("pages: " + pages);

		/* Extract the keywords from the liked pages */
		Carrot2Director carrot2 = new Carrot2Director();
		keywords.addAll(carrot2.DoCluster(pages));
		
		System.out.println(keywords);
		/* return the keywords*/
		return keywords;
		
	}
}
