package org.Carrot2Test;

public class Keyword{
	
	String type;
	
	String keyword;
	
	public Keyword(String t, String k) {
		
		type = t;
		keyword = k;		
	}
	
	@Override
	public String toString() {
		return "type:" + type + " keyword:" + keyword;
	}
}

