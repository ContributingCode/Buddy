package org.Carrot2Test;
import com.restfb.Facebook;

public class FqlStatus {
	  @Facebook
	  int status_id;

	  @Facebook
	  String message;
	  
	  
	  @Override
	  public String toString() {
	    return String.format("status_id:%s, message:%s \n", status_id, message);
	  }
}
