package org.FBKeywordExtractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;


public class Carrot2Director {
	
	final static int MIN_TOTAL_PAGES = 20; // The minimum total pages to start clustering
	final static double SUPPORT_RATIO = 0.05; // The minimum support ratio
	final static int CONFIDENCE_RATIO = 25; //The minimum confiddence (here score) 
	
	public static ArrayList<Keyword> DoCluster (List<FqlPage> pages) {
		
	ArrayList<Keyword> result = new ArrayList<Keyword>();
	int noPages = pages.size();
	
	if (noPages < MIN_TOTAL_PAGES)
		return result;
	
      /* Prepare Carrot2 documents */
    final ArrayList<Document> documents = new ArrayList<Document>();
    for (FqlPage p : pages)
    {
    	if (p.description != null && !p.description.isEmpty())
    		documents.add(new Document(p.name, p.description, p.page_url));
    }
    		

    /* A controller to manage the processing pipeline. */
    final Controller controller = ControllerFactory.createSimple();

    /*
     * Perform clustering by topic using the Lingo algorithm. Lingo can 
     * take advantage of the original query, so we provide it along with the documents.
     */
    final ProcessingResult byTopicClusters = controller.process(documents, "data mining",
        LingoClusteringAlgorithm.class);
    final List<Cluster> clustersByTopic = byTopicClusters.getClusters();
    
    ArrayList<Cluster> clusters = new ArrayList<Cluster>();
    for (int i = 0; i < clustersByTopic.size(); i++) {
    	/*Only Considering the clusters with a min support and confidence */
    	if (clustersByTopic.get(i).getDocuments().size() > SUPPORT_RATIO *noPages &&
    			clustersByTopic.get(i).getScore() > CONFIDENCE_RATIO)
    		clusters.add(clustersByTopic.get(i));
	}

    ClusterComparator comparator = new ClusterComparator();
    Collections.sort(clusters, comparator);
    
    for (Cluster cluster : clusters) {
		result.add(new Keyword("Liked Pages", cluster.getLabel(), cluster.getDocuments().size(), cluster.getScore()));
	}
   //ConsoleFormatter.displayClusters(clusters);
    
    return result;

	}
}
