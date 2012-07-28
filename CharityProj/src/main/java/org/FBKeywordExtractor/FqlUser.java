package org.FBKeywordExtractor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.restfb.Facebook;


class FqlLocation{
	@Facebook
	String city;
	
	@Facebook
	String country;
	
	@Facebook
	String zipcode;

}
class FqlSchool {
	@Facebook
	String name;
	
	@Facebook
	int id; 
	
	@Facebook
	String type;
}

class FqlEducation{
	@Facebook
	FqlSchool school;
	
	@Facebook
	String year;
	
	@Facebook
	String type;
	
	@Override
	public String toString () {
		return String.format("%s ", school.name);
	}
}

class FqlSport{
	
	@Facebook
	String id;
	
	@Facebook
	String name;
	
	@Override
	public String toString () {
		return String.format("%s ", name);
	}
}

public class FqlUser {
	  @Facebook
	  String uid;
	  
	  @Facebook
	  String name;
	  
	  @Facebook
	  String religion;
	  
	  @Facebook 
	  String music;

	  @Facebook 
	  String interests;
	  
	  @Facebook
	  String activities;
	  
	  @Facebook
	  String political;
  
	  @Facebook
	  List<FqlEducation> education;
	  
	  @Facebook
	  List<FqlSport> sports;

	  @Facebook
	  FqlLocation current_location;
	  
	  @Facebook 
	  FqlLocation hometown_location;
	  
	  @Override
	  public String toString() {
	    return String.format("%s (%s) likes %s and his religion is:%s \n Hometown:%s, CurrentLocation:%s \n education: %s \n sports: %s\n", 
	    			name, uid, interests, religion,
	    			hometown_location.city + "(" + hometown_location.country +")",
	    			current_location.city + "(" + current_location.country +")",
	    			education, sports);
	  }
	  public ArrayList<Keyword> toKeywordList(){
			
			 ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			 
			 /* religion */
			 if (religion != null && !religion.isEmpty()) {
				 keywords.add(new Keyword(religion, religion));
			 }
			 
			 /* Current Location */
			 if (current_location != null) {
				 String s = current_location.country + ", " + current_location.city;
				 keywords.add(new Keyword("Current Location", s));
			 }
			 
			 /* Hometown Location */
			 if (current_location != null) {
				 String s = hometown_location.country + ", " + hometown_location.city;
				 keywords.add(new Keyword("Hometown Location", s));
			 }
			 
			 /* Music */
			 if (music != null && !music.isEmpty()) {
				 keywords.add(new Keyword("Music", "Music"));
			 }
			 
			 /* Sports */
			 if (sports != null) {
				 if (sports.size() > 1) {
					 keywords.add(new Keyword("Sports", "Sport"));
				 } else if (sports.size() > 0) {
					 keywords.add(new Keyword("Sports", sports.get(0).name));
				 }
			 }
			 
			 /* political */
			 if (political != null && !political.isEmpty()) {
				 keywords.add(new Keyword("Politics", political));
			 }
			 
			 /* Activities */
			 if (activities != null && !activities.isEmpty()) {
				 keywords.add(new Keyword("Activities", activities));
			 }
			 
			 /* Interests*/
			 if (interests != null && !interests.isEmpty()) {
				 keywords.add(new Keyword("Interests", interests));
			 }
			 
			 /* Education*/
			 if (education != null && education.size() > 0) {
				for (int i = 0; i < education.size(); i++) {
					keywords.add(new Keyword("Education",education.get(i).school.name));
				}
			 }
			 
			 return keywords;
		}
}
