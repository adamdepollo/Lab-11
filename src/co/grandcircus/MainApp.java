package co.grandcircus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {

		String cont = "yes";
		ArrayList<Movie> movieList = makeMovieList();
		System.out.println("Welcome to the Movie List Application!\n");
		while (cont.equalsIgnoreCase("yes")) {
			System.out.println("There are " + movieList.size() + " movies in this list.\n");
			displayCategories(movieList);
			System.out.println("Do you want to continue? (yes/no)");
			cont = scnr.nextLine();
		}

		System.out.println("Bye!");
		scnr.close();
	}

	public static ArrayList<Movie> makeMovieList() {
		ArrayList<Movie> movieList = new ArrayList<>();
		for (int i = 1; i<=100; i++ ) {
			movieList.add(MovieIO.getMovie(i));
		}
		return movieList;
	}

	public static void displayMovieList(ArrayList<Movie> movieList, String category) {
		List<String> moviesByCategory = new LinkedList<>();
		String movieListPrint = "Movie List\n";
		int titleLength = 0;
		int counter = 1;
		for (Movie m : movieList) {
			if (m.getCategory().equals(category)) {
				if (m.getTitle().length() > titleLength) {
					titleLength = m.getTitle().length();
				}
				moviesByCategory.add(m.getTitle());
			}
		}
		Collections.sort(moviesByCategory);
		for (int i = 0; i < titleLength + 25; i++) {
			movieListPrint += "*";
		}
		movieListPrint += "\n";
		for (String m : moviesByCategory) {
			movieListPrint += String.format("%-3d | %-" + titleLength + "s\n", counter, m);
			counter++;
		}
		System.out.println(movieListPrint);
	}

	public static void displayCategories(ArrayList<Movie> movieList) {
		String categoryList = "Categories\n";
		int categoryLength = 0;
		int counter = 1;
		LinkedList<String> categories = new LinkedList<>();
		int categoryChoice;
		for (Movie m : movieList) {
			if (m.getCategory().length() > categoryLength) {
				categoryLength = m.getCategory().length();
			}
		}
		for (int i = 0; i < categoryLength + 5; i++) {
			categoryList += "*";
		}
		categoryList += "\n";
		for (Movie m : movieList) {
			if (!categoryList.contains(m.getCategory())) {
				categoryList += String.format("%-3d| %-" + categoryLength + "s\n", counter, m.getCategory());
				categories.add(m.getCategory());
				counter++;
			}
		}
		System.out.println(categoryList);
		categoryChoice = Validator.getInt(scnr, "What category are you interested in? (enter the number)\n", 1,
				counter - 1);
		displayMovieList(movieList, categories.get(categoryChoice - 1));
	}

}
