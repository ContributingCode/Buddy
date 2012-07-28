package org.FBKeywordExtractor;

public class Keyword {

	String type;

	String keyword;

	double score; // The cluster score

	int docs; // The support of the cluster (no of pages in the cluster)

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getDocs() {
		return docs;
	}

	public void setDocs(int docs) {
		this.docs = docs;
	}

	public Keyword(String t, String k) {

		type = t;
		keyword = k;
	}

	public Keyword(String t, String k, int d, double s) {

		type = t;
		keyword = k;

		/* These two are only used for the liked pages */
		score = s;
		docs = d;
	}

	@Override
	public String toString() {
		String s = "type:" + type + " keyword:" + keyword;
		if (score > 0 && docs > 0)
			s += " score:" + score + " support:" + docs;

		return s;
	}
}
