/*
package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MainApplication {

    public static void main(String[] args) {
        Movie movie1 = new Movie(1, "Night in dark", "12-09-2018", "2018", 9, "Harry", "Mathew");
        Movie movie2 = new Movie(2, "Night in Sun", "12-09-2017", "2017", 9, "Brad Pitt", "Newton");
        Movie movie3 = new Movie(3, "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");
        Movie movie4 = new Movie(3, "Night in Bat", "12-09-2016", "2016", 5, "Justin", "Mathew");

        MovieOperations movieOperations = new MovieOperations(Arrays.asList(movie1,movie3));

        CompletableFuture<List<Movie>> addMovie = movieOperations.addMovie(movie4);
        System.out.println("List after adding a movie: " + addMovie.join());

        CompletableFuture<Movie> some = movieOperations.getMovie(new Long(2));
        System.out.println("get" + some.join());

        */
/*CompletableFuture<List<Movie>> fa = movieOperations.removeMovie(new Long(3));
        System.out.println("After removing" + fa.join());*//*


        CompletableFuture<Map<String , Long>> movieList = movieOperations.moviesPerDirector();
        System.out.println(movieList.join());

        CompletableFuture<List<Movie>> mov = movieOperations.updateMovie(9, "nest", "12-09-2018", "2018", 4, "david", "dravid");
        System.out.println(mov.join());
    }
}
*/
