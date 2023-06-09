package com.company;

public class Movie {

    private int movie_id;
    private String movieName;
    private String genre;
    private int cost;

    public Movie() {
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Movie(int movie_id, String movieName, String genre, int cost) {
        this.movie_id = movie_id;
        this.movieName = movieName;
        this.genre = genre;
        this.cost = cost;
    }
}
