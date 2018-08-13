package com.knoldus;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Movie movie1 = new Movie(1, "Night in dark", "12-09-2018", "2018", 9, "Harry", "Mathew");
        Movie movie2 = new Movie(2, "Night in Sun", "12-09-2017", "2017", 9, "Brad Pitt", "Newton");
        Movie movie3 = new Movie(3, "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");
        MovieOperations movieOperations = new MovieOperations(Arrays.asList(movie1, movie2, movie3));
        Boolean flag =true;
        while(flag) {
            System.out.println("=========================Movie Viewer====================");
            System.out.println("1. Add movie \t 2. Get Movie \t 3. Delete Movie \t 4. Update Movie");
            System.out.println("5. show Limited movie \t 6. Movies with higher rating \t 7. movies per director \t 8. get  movies after given date");
            System.out.println("9. get movies in between dates \t 10. get movies in between years \t 11. get All movies \n\n");
            System.out.println("Enter choice: ");
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
                    String year = userInput.next();
                    System.out.println("rating: ");
                    Integer rating = userInput.nextInt();
                    System.out.println("actor: ");
                    String actor = userInput.next();
                    System.out.println("director: ");
                    String dir = userInput.next();
                    Movie movie =new Movie(id, name, date, year, rating, actor, dir);
                    movieOperations.addMovie(movie);
                    break;

                case 2:
                    System.out.println("ID: ");
                    int id1 = userInput.nextInt();
                    System.out.println(movieOperations.getMovie(new Long(id1)));
                    break;

                case 3:
                    System.out.println("Id to be removed: ");
                    int idToBeRemoved = userInput.nextInt();
                    System.out.println(movieOperations.removeMovie(new Long(idToBeRemoved)));
                    break;

                case 4:
                    System.out.println("ID: ");
                    int id3 = userInput.nextInt();
                    System.out.println("Name: ");
                    String updatedName = userInput.next();
                    System.out.println("release date: ");
                    String updatedDate = userInput.next();
                    System.out.println("release year: ");
                    String updatedYear = userInput.next();
                    System.out.println("rating: ");
                    Integer updatedRating = userInput.nextInt();
                    System.out.println("actor: ");
                    String updatedActor = userInput.next();
                    System.out.println("director: ");
                    String updatedDir= userInput.next();
                    movieOperations.updateMovie(id3, updatedName, updatedDate, updatedYear, updatedRating, updatedActor, updatedDir);
                    break;

                case 5:
                    System.out.println("Enter offset: ");
                    int offset =userInput.nextInt();
                    System.out.println("Enter limit: ");
                    int limit = userInput.nextInt();
                    System.out.println(movieOperations.limitMovies(offset, limit).join());
                    break;

                case 6:
                    System.out.println("Enter director: ");
                    String dir2 = userInput.next();
                    System.out.println(movieOperations.getMovieWithHigherRating(dir2).join());
                    break;

                case 7:
                    System.out.println(movieOperations.moviesPerDirector().join());
                    break;

                case 8:
                    System.out.println("Enter date: ");
                    String givenDate = userInput.next();
                    System.out.println(movieOperations.getMoviesAfterGivenDate(givenDate).join());
                    break;

                case 9:
                    System.out.println("Enter start date: ");
                    String startDate = userInput.next();
                    System.out.println("Enter end date: ");
                    String endDate = userInput.next();
                    System.out.println(movieOperations.getMoviesInBetweenDates(startDate, endDate).join());
                    break;

                case 10:
                    System.out.println("Enter start year: ");
                    String startYear = userInput.next();
                    System.out.println("Enter end year: ");
                    String endYear = userInput.next();
                    System.out.println(movieOperations.getMoviesInBetweenYears(startYear, endYear).join());
                    break;

                case 11:
                    System.out.println(movieOperations.getMovieList().join());
                    break;

                case -1:
                    flag = false;
                    break;
            }
        }
    }
}
