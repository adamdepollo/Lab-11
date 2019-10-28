package co.grandcircus;

public class Movie {
	private String title;
	private String category;
	
	public Movie(String title, String category) {
		setTitle(title);
		setCategory(category);
	}
	public Movie() {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	@Override
	public String toString() {
		return "Title: " + title + ", Category: " + category;
	}
	
	
}
