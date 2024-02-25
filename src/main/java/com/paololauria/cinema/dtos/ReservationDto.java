package com.paololauria.cinema.dtos;

import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Reservation;

import java.util.List;

public class ReservationDto {
    private long id;
    private long userId;
    private String filmTitle;
    private long idHall;
    private String hallName;
    private String projectionDate;
    private String projectionTime;
    private long projectionId;
    private int seatNumber;
    private boolean discount;
    private String posterImage;

    public ReservationDto() {
    }

    public ReservationDto(Reservation reservation, OmdbMovieInfo omdbMovieInfo) {
        this.id = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.projectionId = reservation.getFilmProjection().getId();
        this.seatNumber = reservation.getReservedSeat();
        this.filmTitle = reservation.getFilmProjection().getFilmTitle();
        this.projectionDate = reservation.getDate().toString();
        this.projectionTime = reservation.getProjectionTime().toString();
        this.idHall = reservation.getFilmProjection().getHallId();
        this.discount=reservation.isOnDiscount();
        this.hallName = reservation.getFilmProjection().getHallName();
        this.posterImage = omdbMovieInfo.getPoster();

    }

    public ReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.projectionId = reservation.getFilmProjection().getId();
        this.seatNumber = reservation.getReservedSeat();
        this.filmTitle = reservation.getFilmProjection().getFilmTitle();
        this.projectionDate = reservation.getDate().toString();
        this.projectionTime = reservation.getProjectionTime().toString();
        this.idHall = reservation.getFilmProjection().getHallId();
        this.discount=reservation.isOnDiscount();
        this.hallName = reservation.getFilmProjection().getHallName();
    }


    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public long getIdHall() {
        return idHall;
    }

    public void setIdHall(long idHall) {
        this.idHall = idHall;
    }

    public String getProjectionDate() {
        return projectionDate;
    }

    public void setProjectionDate(String projectionDate) {
        this.projectionDate = projectionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(long projectionId) {
        this.projectionId = projectionId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getProjectionTime() {
        return projectionTime;
    }

    public void setProjectionTime(String projectionTime) {
        this.projectionTime = projectionTime;
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
}
