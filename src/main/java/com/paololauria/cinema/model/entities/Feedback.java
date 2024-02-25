package com.paololauria.cinema.model.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(generator = "feedback_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "feedback_id_generator", sequenceName = "feedback_id_seq", allocationSize = 1)
    @Column(name = "feedback_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    private int rating;
    private String comment;

    public Feedback() {
    }

    public Feedback(long id, User user, Film film, int rating, String comment) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.rating = rating;
        this.comment = comment;
    }

    public Feedback(int rating, String comment) {
        this.rating= rating;
        this.comment=comment;
    }

    public Feedback(int rating, String comment, Film film) {
        this.rating=rating;
        this.comment=comment;
        this.film=film;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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

    public String getFilmTitle() {
        return this.getFilm().getTitle();
    }

    public String getPosterImage(){
        return film.getPosterImg();
    }
}