package org.buddy.charity;

public class Display {
	public String name; 
	public String score; 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String location; 
	public String description; 
	public String category; 
	public String url; 
	
	Display(String n, String s, String l, String d, String c, String u) {
		name = n; 
		score = s; 
		location = l; 
		description = d; 
		category = c; 
		url = u; 
	}
}
