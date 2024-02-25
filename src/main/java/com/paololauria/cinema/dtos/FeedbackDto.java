package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.Feedback;
import com.paololauria.cinema.model.entities.Film;
import com.paololauria.cinema.model.entities.User;

public class FeedbackDto {
    private String filmTitle;

    private String userName;
    private int rating;
    private String comment;

    private Long filmId;
    private String posterImage;

    private long userId;

    public FeedbackDto() {
    }

    public FeedbackDto(Feedback feedback) {
        this.filmTitle = feedback.getFilm().getTitle();
        this.rating = feedback.getRating();
        this.comment = feedback.getComment();
        this.filmId= feedback.getFilm().getId();
        this.userId=feedback.getUser().getId();
        this.userName=feedback.getUser().getFirstname();
        this.posterImage = feedback.getPosterImage();

    }

    public FeedbackDto(Feedback feedback, OmdbMovieInfo movieInfo) {
        this.filmTitle = feedback.getFilm().getTitle();
        this.rating = feedback.getRating();
        this.comment = feedback.getComment();
        this.filmId= feedback.getFilm().getId();
        this.userId=feedback.getUser().getId();
        this.userName=feedback.getUser().getFirstname();
        this.posterImage = movieInfo.getPoster();
    }

    public Feedback fromDto(Film film, User user){
        Feedback fd = new Feedback(this.rating,this.comment);
                fd.setFilm(film);
                fd.setUser(user);
        return  fd;
    }



    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
