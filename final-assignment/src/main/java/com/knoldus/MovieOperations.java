package com.knoldus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MovieOperations {
    private CompletableFuture<List<Movie>> movieList;

    MovieOperations() {
        movieList = CompletableFuture.supplyAsync(() -> new ArrayList<Movie>());
    }

    MovieOperations(List<Movie> listOfMovies) {
        movieList = CompletableFuture.supplyAsync(() -> new ArrayList<Movie>(listOfMovies));
    }

    public CompletableFuture<List<Movie>> addMovie(Movie movie){

        CompletableFuture<Movie> isPresent = getMovie(movie.getId());

        this.movieList = movieList.thenCompose(list -> {
            isPresent.thenApply(movieInList -> {
                if(movieInList == null){
                    list.add(movie);
                    return list;
                }
                else {
                    throw new RuntimeException("Movie with same id exists.");
                }
            });
            return movieList;
        });

        return movieList;
    }

    public CompletableFuture<Movie> getMovie(Long movieId){
        return movieList.thenApply((list) -> {
           Movie requiredMovie = list.stream()
                   .filter(movie -> movieId.equals(movie.getId()))
                   .findAny()
                   .orElse(null);
           if(requiredMovie == null)
               throw new MovieNotFoundException("Movie Not found");
           else
               return requiredMovie;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });

    }

    public CompletableFuture<List<Movie>> getMovieList(){
        return movieList;
    }

    public CompletableFuture<List<Movie>> removeMovie(Long movieId){

        CompletableFuture<Movie> requiredMovie = getMovie(movieId);

        this.movieList = movieList.thenCompose(list -> {
            requiredMovie.thenApply(movie -> {
                list.remove(list.indexOf(movie));
                return list;
            });
            return movieList;
        });

        return this.movieList;
    }

    public CompletableFuture<List<Movie>> updateMovie(int id, String name, String releaseDate, String releaseYear, Integer rating, String actor, String director){

        Long movieId = new Long(id);
        CompletableFuture<Movie> isPresent = getMovie(movieId);
        Movie updatedMovie = new Movie(id, name, releaseDate, releaseYear, rating, actor, director);
        this.movieList = removeMovie(movieId);
        this.movieList = addMovie(updatedMovie);
        return this.movieList;
    }


    public CompletableFuture<List<Movie>> limitMovies(int offset, int limit){

        return movieList.thenApply((list) -> {
            int actualLimit;
            if(offset > list.size() - 1)
                throw new RuntimeException("Offset is greater than the size of movie list.");
            else if(offset + limit > list.size() )
                actualLimit = list.size();
            else
                actualLimit = offset + limit;

            return IntStream.range(offset, actualLimit)
                    .boxed()
                    .map(index -> list.get(index))
                    .collect(Collectors.toList());
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> getMovieWithHigherRating(String directorName){
        return movieList.thenApply((list) -> {
            List<Movie> moviesWithHigherRating = list.stream()
                    .filter(movie -> movie.getRating() > 8 && directorName.equals(movie.getDirector()))
                    .collect(Collectors.toList());
            if(moviesWithHigherRating.size() == 0)
                throw new MovieNotFoundException("Required Movies not found");
            else
                return moviesWithHigherRating;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<Map<String, Long>> moviesPerDirector(){
       return  movieList.thenApply(list -> {
               return list.stream()
                        .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
        });
    }

    private LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public CompletableFuture<List<Movie>> getMoviesAfterGivenDate(String date){
        LocalDate formattedDate = parseDate(date);

        return movieList.thenApply((list) -> {
            List<Movie> moviesReleasedAfterDate = list.stream()
                    .filter(movie -> parseDate(movie.getReleaseDate()).isAfter(formattedDate))
                    .collect(Collectors.toList());
            if(moviesReleasedAfterDate.size() == 0)
                throw new MovieNotFoundException("Required Movies not found");
            else
                return moviesReleasedAfterDate;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> getMoviesInBetweenYears(String startYear, String endYear ){
        Integer startYearInt = Integer.valueOf(startYear);
        Integer endYearInt = Integer.valueOf(endYear);

        return movieList.thenApply((list) -> {
            List<Movie> moviesInBetweenYears = list.stream()
                    .filter(movie -> Integer.valueOf(movie.getReleaseYear()) > startYearInt &&
                            Integer.valueOf(movie.getReleaseYear()) < endYearInt)
                    .collect(Collectors.toList());
            if(moviesInBetweenYears.size() == 0)
                throw new MovieNotFoundException("Required Movies not found");
            else
                return moviesInBetweenYears;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    public CompletableFuture<List<Movie>> getMoviesInBetweenDates(String startDate, String endDate ){
        LocalDate formattedStartDate = parseDate(startDate);
        LocalDate formattedEndDate = parseDate(endDate);

        return movieList.thenApply((list) -> {
            List<Movie> moviesInBetweenDate = list.stream()
                    .filter(movie -> formattedStartDate.isBefore(parseDate(movie.getReleaseDate())) &&
                            formattedEndDate.isAfter(parseDate(movie.getReleaseDate())))
                    .collect(Collectors.toList());
            if(moviesInBetweenDate.size() == 0)
                throw new MovieNotFoundException("Required Movies not found");
            else
                return moviesInBetweenDate;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }


}
