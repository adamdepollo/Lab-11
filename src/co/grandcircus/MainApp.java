package co.grandcircus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

	//Create scanner to be used by all methods in the class
	public static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		//Declare variables
		String cont = "yes";
		ArrayList<Movie> movieList = makeMovieList(); //Call makeMovieList() method to generate movieList
		
		//Welcome user
		System.out.println("Welcome to the Movie List Application!\n");
		
		//While loop to continue program until user decides to quit
		while (cont.equalsIgnoreCase("yes")) {
			//Print out total number of movies in the movie List
			System.out.println("There are " + movieList.size() + " movies in this list.\n");
			
			//Call method to display movie categories
			displayCategories(movieList);
			
			//Ask user if they want to continue and end loop if not
			System.out.println("Do you want to continue? (yes/no)");
			cont = scnr.nextLine();
		}

		//Say bye and close scanner
		System.out.println("Bye!");
		scnr.close();
	}

	// Method generates a list of Movie objects using the list of movies saved in
	// the MovieIO class
	public static ArrayList<Movie> makeMovieList() {
		ArrayList<Movie> movieList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			movieList.add(MovieIO.getMovie(i));
		}
		return movieList;
	}

	public static void displayMovieList(ArrayList<Movie> movieList, String category) {
		// Create List to store movies of the given category
		List<String> moviesByCategory = new LinkedList<>();

		// Start string to display movies
		String movieListPrint = "Movie List\n";
		// Declare variables for formatting
		int titleLength = 0;
		int counter = 1;

		// For loop to populate the moviesByCategory list and the longest title length
		// to use in formatting the display string
		for (Movie m : movieList) { // For each movie in the movieList ...
			if (m.getCategory().equals(category)) { // ...If the title is longer than the previously saved longest title
													// length ...
				if (m.getTitle().length() > titleLength) {
					titleLength = m.getTitle().length(); // ... then update the title length
				}
				moviesByCategory.add(m.getTitle()); // In any case, add the title to the new list of movies by category
			}
		}
		Collections.sort(moviesByCategory); // Alphabetize the new movie list

		// For loops prints a line of * chars as a divider
		for (int i = 0; i < titleLength + 25; i++) {
			movieListPrint += "*";
		}
		movieListPrint += "\n";

		// For loop adds each movie in the new list as a formatted line to the display
		// string
		for (String m : moviesByCategory) {
			movieListPrint += String.format("%-3d | %-" + titleLength + "s\n", counter, m);
			counter++;
		}

		// Print the final string
		System.out.println(movieListPrint);
	}

	// This method displays all of the possible movie categories, takes user input
	// for which category they'd like to see, and calls the method to generate the
	// list of movies under that category
	public static void displayCategories(ArrayList<Movie> movieList) {
		// Declare variables and initialize display string
		String categoryList = "Categories\n";
		int counter = 1;
		LinkedList<String> categories = new LinkedList<>();
		int categoryChoice;

		// For loop adds a line of * chars as a divider
		for (int i = 0; i < 13; i++) {
			categoryList += "*";
		}
		categoryList += "\n";

		// For loop adds each category to the display string as a new formatted line
		// with a counter
		for (Movie m : movieList) {
			if (!categoryList.contains(m.getCategory())) {
				categoryList += String.format("%-3d| %-8s\n", counter, m.getCategory());
				categories.add(m.getCategory());
				counter++;
			}
		}
		// Print the category choices
		System.out.println(categoryList);

		// Take user input for category they'd like to see and validates using the
		// Validator.getInt method.
		categoryChoice = Validator.getInt(scnr, "What category are you interested in? (enter the number)\n", 1,
				counter - 1);

		// Call method to display movies of the category selected by the user
		displayMovieList(movieList, categories.get(categoryChoice - 1));
	}

}
