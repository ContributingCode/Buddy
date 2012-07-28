package org.FBKeywordExtractor;


/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2012, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */


import java.util.ArrayList;
import java.util.List;
import java.lang.System;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.IDocumentSource;
import org.carrot2.core.ProcessingResult;
import org.omg.CORBA.UserException;

//import com.restfb.Connection;
//import com.restfb.DefaultFacebookClient;
//import com.restfb.DefaultJsonMapper;
//import com.restfb.Facebook;
//import com.restfb.FacebookClient;
//import com.restfb.JsonMapper;
//import com.restfb.Parameter;
//import com.restfb.json.JsonArray;
//import com.restfb.json.JsonObject;
//import com.restfb.types.Page;
//import com.restfb.types.Post;
//import com.restfb.types.Url;
//import com.restfb.types.User;

//import carrot2-test.ConsoleFormatter;

/**
 * This example shows how to cluster a set of documents available as an {@link ArrayList}.
 * This setting is particularly useful for quick experiments with custom data for which
 * there is no corresponding {@link IDocumentSource} implementation. For production use,
 * it's better to implement a {@link IDocumentSource} for the custom document source, so
 * that e.g., the {@link Controller} can cache its results, if needed.
 * 
 * @see ClusteringDataFromDocumentSources
 * @see UsingCachingController
 */


public class Carrot2Test
{
	 /**
	   * RestFB Graph API client.
	   */
    

    public static void main(String [] args)
    {
     	
       		String accessToken = "AAACEdEose0cBAFRjoT9h8ww6wBKqkymROtdjhQO6uDKHaVELG1MLiUZBZBr98GYJPZC9ZCNA6ZA7OEp97sWsvlHpTrUXeuY42s20BhLtfvUVPA0T4tsiH";
    	    //String accessToken = "AAACEdEose0cBAMjEDFsIm9mfHG1U3xakcZBdh8tmqmKlO45cPTchVw6J9yuXnoeTPB1mWp7hpKQZClbDrnZCRGkMaCwGc22V3TmqKQnZBm1EgbTIENDL"; //Duy
       		
       		/*Connect to Facebook and extract the user's interests keywords*/
       		ArrayList<Keyword> keywords = FBDirector.FetchFBInterests(accessToken);
       		
       		System.out.println(keywords);

    		return;
    }
}