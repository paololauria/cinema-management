package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmDetailsDto {
    private Long id;
    private String title;
    private String duration;
    private String posterImage;
    private String director;
    private String releaseYear;
    private String description;
    private List<ActorDto> actorDto;
    private String actors;
    private String awards;
    private String imdbVotes;
    private String imdbRating;
    private String boxOffice;
    private double averageRating;


    public FilmDetailsDto() {
    }

    public FilmDetailsDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.director = film.getDirector();
        this.posterImage = film.getPosterImg();
        this.description = film.getDescription();
        if (film.getDuration() != null) {
            this.duration = film.getDuration().toString();
        }
        if (film.getReleaseYear() != null){
            this.releaseYear = film.getReleaseYear().toString();
        }
        if (film.getPerformances() == null) {
            this.actorDto = new ArrayList<>();
        } else {
            this.actorDto = film.getPerformances().stream().map(ActorDto::new).toList();
        }
        this.averageRating = film.calculateAverageRating();
    }

    public Film fromDto(){
        return new Film(this.title,
                this.director,
                this.posterImage, this.duration, this.description);
    }

    public void setOmdbMovieInfo(OmdbMovieInfo omdbMovieInfo) {
        if (omdbMovieInfo != null) {
            this.description = omdbMovieInfo.getPlot();
            this.director = omdbMovieInfo.getDirector();
            this.posterImage = omdbMovieInfo.getPoster();
            this.duration = omdbMovieInfo.getRuntime();
            this.releaseYear = omdbMovieInfo.getReleased();
            this.awards = omdbMovieInfo.getAwards();
            this.imdbRating = omdbMovieInfo.getImdbRating();
            this.boxOffice = omdbMovieInfo.getBoxOffice();
            this.actors = omdbMovieInfo.getActors();
            this.imdbVotes = omdbMovieInfo.getImdbVotes();
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActorDto> getActorDto() {
        return actorDto;
    }

    public void setActorDto(List<ActorDto> actorDto) {
        this.actorDto = actorDto;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
