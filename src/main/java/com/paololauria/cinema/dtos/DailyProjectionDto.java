package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.FilmProjection;

public class DailyProjectionDto {
    private long id;
    private String filmTitle;
    private String date;
    private String time;
    private int hallId;
    private String hallName;
    private String posterImage;
    private String duration;
    private Double averageRating;

    public DailyProjectionDto() {

    }

    public DailyProjectionDto(FilmProjection filmProjection) {
        this.id = filmProjection.getId();
        this.filmTitle = filmProjection.getFilmTitle();
        this.date = filmProjection.getProjectionDate().toString();
        this.time = filmProjection.getProjectionTimes().toString();
        this.hallId = (int) filmProjection.getHallId();
        this.hallName = filmProjection.getHallName();
        this.posterImage = filmProjection.getPosterImage();
            this.duration = String.valueOf(filmProjection.getFilmDuration());
        this.averageRating = filmProjection.getFilm().calculateAverageRating();
    }

    public void setOmdbMovieInfo(OmdbMovieInfo omdbMovieInfo) {
        if (omdbMovieInfo != null) {

            this.posterImage = omdbMovieInfo.getPoster();
            this.duration = omdbMovieInfo.getRuntime();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
