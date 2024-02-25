package com.paololauria.cinema.dtos;

import com.paololauria.cinema.model.entities.Film;

public class FilmDto {
    private long id;
    private String title;
    private int duration;
    private int releaseYear;
    private String posterImage;
    private String director;
    private String description;
    private double averageRating;

    public FilmDto() {
    }

    public FilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.director = film.getDirector();
        if (film.getReleaseYear() != null) {
            this.releaseYear = film.getReleaseYear();
        }
        if (film.getDuration() != null){
        this.duration = film.getDuration();
        }
        this.posterImage = film.getPosterImg();
        this.averageRating = film.calculateAverageRating();
        this.description= film.getDescription();
    }
    public Film fromDto (){
        Film film = new Film(this.getId(),this.getTitle(),this.getDirector(),this.getReleaseYear(),
                                this.getPosterImage(),this.getDuration(),this.getDescription());
        return film;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
