package com.paololauria.cinema.dtos;


import com.paololauria.cinema.model.entities.FilmProjection;
import com.paololauria.cinema.model.entities.Reservation;

import java.util.List;

public class ProjectionReservationDto {
    private long idProjection;
    private String filmTitle;
    private long idHall;
    private String projectionDate;
    private List<Integer> reservedSeats;

    public ProjectionReservationDto() {

    }

    public ProjectionReservationDto(List<Reservation> reservations, FilmProjection fp) {
        this.idProjection = fp.getId();
        this.filmTitle = fp.getFilmTitle();
        this.idHall = fp.getHallId();
        this.projectionDate = fp.getProjectionDate().toString();
        this.reservedSeats = reservations.stream().map(Reservation::getReservedSeat).toList();
    }

    public long getIdProjection() {
        return idProjection;
    }

    public void setIdProjection(long idProjection) {
        this.idProjection = idProjection;
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

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
}
