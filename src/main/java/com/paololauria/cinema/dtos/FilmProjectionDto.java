package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Hall;

public class FilmProjectionDto {
    private long id;
    private long filmId;
    private String filmTitle;
    private String date;
    private String time;
    private long hallId;
    private String hallName;
    private String posterImage;
    private String duration;
    private Double ticketPrice;
    private double averageRating;
    private String imdbRating;
    public FilmProjectionDto() {

    }

    public FilmProjectionDto(FilmProjection filmProjection) {
        this.id = filmProjection.getId();
        this.filmId = filmProjection.getFilm().getId();
        this.filmTitle = filmProjection.getFilmTitle();
        this.date = filmProjection.getProjectionDate().toString();
        this.time = filmProjection.getProjectionTimes().toString();
        this.hallId =  filmProjection.getHallId();
        this.hallName = filmProjection.getHallName();
        this.posterImage = filmProjection.getPosterImage();
        this.duration = getDuration();
        this.ticketPrice = filmProjection.getTicketPrice();
        this.averageRating = filmProjection.getFilm().calculateAverageRating();

    }

    public FilmProjection fromDto(){

        Film film = new Film();
        film.setId(this.filmId);
        film.setTitle(this.filmTitle);
        film.setPosterImg(this.posterImage);
        film.setDuration(Integer.valueOf(this.duration));
        Hall hall = new Hall();
        hall.setId(this.hallId);
        hall.setName(this.hallName);
        return new FilmProjection(this.id, film, hall,this.ticketPrice,this.date,this.time);
    }
    public FilmProjection fromDto(Film film){

        Hall hall = new Hall();
        hall.setId(this.hallId);
        hall.setName(this.hallName);
        return new FilmProjection(this.id, film, hall,this.ticketPrice,this.date,this.time);
    }

    public void setOmdbMovieInfo(OmdbMovieInfo omdbMovieInfo) {
        if (omdbMovieInfo != null) {
            this.posterImage = omdbMovieInfo.getPoster();
            this.duration = omdbMovieInfo.getRuntime();
            this.imdbRating = omdbMovieInfo.getImdbRating();
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

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
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

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }


}
