package com.knoldus;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Movie movie1 = new Movie(1, "Night in dark", "12-09-2018", "2018", 9, "Harry", "Mathew");
        Movie movie2 = new Movie(2, "Night in Sun", "12-09-2017", "2017", 9, "Brad Pitt", "Newton");
        Movie movie3 = new Movie(3, "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");
        MovieOperations movieOperations = new MovieOperations(Arrays.asList(movie1, movie2, movie3));

        while(true) {
            System.out.println("====================Movie Viewer====================");
            System.out.println("1. Add movie \t 2. Get Movie \t 3. Delete Movie \t 4. Update Movie");
            System.out.println("5. show Limited movie \t 6. Movies with higher rating \t 7. movies per director \t 8. get  movies after given date");
            System.out.println("9. get movies in between dates \t 10. get movies in between years");
            Scanner userInput = new Scanner(System.in);
            int choice = userInput.nextInt();

            switch(choice){
                case 1 :
                    System.out.println("ID: ");
                    int id = userInput.nextInt();
                    System.out.println("Name: ");
                    String name = userInput.next();
                    System.out.println("release date: ");
                    String date = userInput.next();
                    System.out.println("release year: ");
                    System.out.println("rating: ");
                    Integer rating = userInput.nextInt();
                    String year =userInput.next();
                    System.out.println("actor: ");
                    String actor = userInput.next();
                    System.out.println("director: ");
                    String dir = userInput.next();
                    Movie movie =new Movie(id, name, date, year, rating, actor, dir);
                    movieOperations.addMovie(movie);

                case 2:
                    System.out.println("ID: ");
                    int id = userInput.nextInt();
                    movieOperations.getMovie(new Long(id));

                case 3: 


            }



        }
    }
}
