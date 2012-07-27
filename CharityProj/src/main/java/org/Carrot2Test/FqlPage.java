package org.Carrot2Test;

import com.restfb.Facebook;

public class FqlPage {
	  @Facebook
	  int page_id;

	  @Facebook
	  String name;
	  
	  @Facebook
	  String description;
	  
	  @Facebook
	  String page_url;
	  
	  @Override
	  public String toString() {
	    return String.format("name:%s, desc:%surl:%s  \n", name, description, page_url);
	  }

}
