package com.knoldus;

public class Movie {
    private Long id;
    private String name;
    private String releaseDate;
    private String releaseYear;
    private Integer rating; // Out of 10
    private String actor;
    private String director;

    Movie(int id, String name, String releaseDate, String releaseYear, Integer rating, String actor, String director){
        this.id = new Long(id);
        this.name = name;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.actor = actor;
        this.director = director;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getReleaseYear() {
        return this.releaseYear;
    }

    public Integer getRating() {
        return this.rating;
    }

    public String getActor() {
        return this.actor;
    }

    public String getDirector() {
        return this.director;
    }

    @Override
    public String toString() {
        return "Movie( " + id + ", " + name + ", " + releaseDate + ", " + rating + ", " +
                actor + ", " + director + ")";
    }
}
