package org.FBKeywordExtractor;


import java.util.Comparator;

import org.carrot2.core.Cluster;
 
public class ClusterComparator implements Comparator<Cluster>{
 
    @Override
    public int compare(Cluster c1, Cluster c2) {
 
        double rank1 = c1.getScore();
        double rank2 = c2.getScore();
 
        /*Note that the sort is descending!*/
        if (rank1 < rank2){
            return +1;
        }else if (rank1 > rank2){
            return -1;
        }else{
            return 0;
        }
    }
}